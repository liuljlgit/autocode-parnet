package com.cloud.ftl.ftlbasic.query;

import com.alibaba.fastjson.JSON;
import com.cloud.ftl.ftlbasic.constant.SqlConst;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.google.common.collect.Lists;
import lombok.Data;
import org.javatuples.Quartet;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
public class ConditGroup {

    /**
     * field comment:
     * 判断是否有值合法
     */
    public boolean isValid() {
        return conditions.size() > 0;
    }

    /**
     * field comment:
     * Condition代表一个查询条件
     */
    private List<Condition> conditions = Lists.newArrayList();

    /**
     * field comment:
     * AND () 或者 OR ()
     */
    private String opt = Opt.AND.getCode();

    /**
     * 这个字段主要是方便转换成redis key而存在的
     * 一个Quartet对应的是一个Criterion
     * 一个Criteria生成的redis key值是：opt + quartets
     * 元组信息：记录四个内容分别是
     * 1.链接信息 and 还是 or
     * 2.对象的属性字段（对象的表字段）
     * 3.操作类型
     * 4.操作值 使用fastJson转换成字符串
     */
    private List<Quartet<Opt,String, Opt, String>> quartets = Lists.newArrayList();

    public ConditGroup() {}

    public ConditGroup(String opt) {
        this.opt = opt;
    }

    /**
     * 单值或者list操作
     * @param conditStr
     * @param value
     */
    public void addCondition(String conditStr, Object value) {
        if (StringUtils.isEmpty(conditStr)) {
            throw new RuntimeException("condition表达式不能为空");
        }
        if (Objects.isNull(value)) {
            throw new RuntimeException("操作值不能为空");
        }
        Condition condition = new Condition();
        condition.setCondition(conditStr);
        if(value instanceof Collection){
            condition.setList(value);
            condition.setListValue(true);
        }else{
            condition.setValue1(value);
            condition.setOneValue(true);
        }
        conditions.add(condition);
    }

    /**
     * IsNull和IsNotNull操作
     *
     * @param conditStr
     */
    public void addCondition(String conditStr) {
        if (StringUtils.isEmpty(conditStr)) {
            throw new RuntimeException("condition表达式不能为空");
        }
        Condition condition = new Condition();
        condition.setCondition(conditStr);
        condition.setNoValue(true);
        conditions.add(condition);
    }

    /**
     * Between和NotBetween操作
     *
     * @param conditStr
     * @param value1
     * @param value2
     */
    public void addCondition(String conditStr, Object value1, Object value2) {
        if (StringUtils.isEmpty(conditStr)) {
            throw new RuntimeException("condition表达式不能为空");
        }
        if (Objects.isNull(value1) || Objects.isNull(value2)) {
            throw new RuntimeException("‘between’或者‘not between’操作值不能为空");
        }
        Condition condition = new Condition();
        condition.setCondition(conditStr);
        condition.setValue1(value1);
        condition.setValue2(value2);
        condition.setSecondValue(true);
        conditions.add(condition);
    }

    /**
     * AND操作
     *
     * @param opt
     * @param field
     * @param values
     * @return
     * @throws Exception
     */
    public ConditGroup andCondit(String field, Opt opt, Object... values) {
        if(values.length > 2){
            throw new RuntimeException("参数个数不正确");
        }
        addQuartets(Opt.AND,field,opt,values);
        if(values.length == 0){
            if(!Opt.IS_NULL.equals(opt) && !Opt.IS_NOT_NULL.equals(opt)){
                throw new RuntimeException("操作类型不合法,此处只能为‘is null’或者‘is not null’");
            }
            addCondition(SqlConst.AND_SPACE + field + opt.getCode());
        } else if(values.length == 1){
            if(!opt.getOneValueOptSet().contains(opt)){
                throw new RuntimeException("操作类型不合法");
            }
            if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
                values[0] = SqlConst.PERCENT + values[0] + SqlConst.PERCENT;
            }
            addCondition(SqlConst.AND_SPACE + field + opt.getCode() , values[0]);
        }else{
            if(!Opt.BETWEEN.equals(opt) && !Opt.NOT_BETWEEN.equals(opt)){
                throw new RuntimeException("操作类型不合法,此处只能为‘between’或者‘not between’");
            }
            addCondition(SqlConst.AND_SPACE + field + opt.getCode() , values[0] , values[1]);
        }
        return this;
    }

    /**
     * OR操作
     *
     * @param opt
     * @param field
     * @param values
     * @return
     * @throws Exception
     */
    public ConditGroup orCondit(String field, Opt opt, Object... values) {
        if(values.length > 2){
            throw new RuntimeException("参数个数不正确");
        }
        addQuartets(Opt.OR,field,opt,values);
        if(values.length == 0){
            if(!Opt.IS_NULL.equals(opt) && !Opt.IS_NOT_NULL.equals(opt)){
                throw new RuntimeException("操作类型不合法,此处只能为‘is null’或者‘is not null’");
            }
            addCondition(SqlConst.OR_SPACE + field + opt.getCode());
        } else if(values.length == 1){
            if(!opt.getOneValueOptSet().contains(opt)){
                throw new RuntimeException("操作类型不合法");
            }
            if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
                values[0] = SqlConst.PERCENT + values[0] + SqlConst.PERCENT;
            }
            addCondition(SqlConst.OR_SPACE + field + opt.getCode() , values[0]);
        }else{
            if(!Opt.BETWEEN.equals(opt) && !Opt.NOT_BETWEEN.equals(opt)){
                throw new RuntimeException("操作类型不合法,此处只能为‘between’或者‘not between’");
            }
            addCondition(SqlConst.OR_SPACE + field + opt.getCode() , values[0] , values[1]);
        }
        return this;
    }


    /**
     * 保存sql信息到元组中
     *
     * @param conOpt
     * @param field
     * @param dbOpt
     * @param values
     * 例子: and da_id = 1000
     */
    public void addQuartets(Opt conOpt,String field,Opt dbOpt,Object... values){
        if(values.length > 0){
            quartets.add(new Quartet<>(conOpt,field,dbOpt, JSON.toJSONString(values)));
        } else {
            quartets.add(new Quartet<>(conOpt,field,dbOpt, SqlConst.NULL));
        }
    }

}
