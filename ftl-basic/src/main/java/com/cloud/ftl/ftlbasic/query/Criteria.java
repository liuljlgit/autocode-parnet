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

/**
 * 这个类相当于是一个"()"
 * @author lijun
 */
@Data
public class Criteria {

    /**
     * field comment:判断是否有值合法
     */
    public boolean isValid() {
        return criterions.size() > 0;
    }

    /**
     * field comment:每一个Criterion就是一个条件
     */
    private List<Criterion> criterions = Lists.newArrayList();

    /**
     * field comment:()前的操作,AND 或者 OR.
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

    public Criteria() {}

    public Criteria(String opt) {
        this.opt = opt;
    }

    /**
     * 单值或者list操作
     * @param condition
     * @param value
     */
    public void addCriterion(String condition, Object value) {
        if (StringUtils.isEmpty(condition)) {
            throw new RuntimeException("condition表达式不能为空");
        }
        if (Objects.isNull(value)) {
            throw new RuntimeException("操作值不能为空");
        }
        Criterion criterion = new Criterion();
        criterion.setCondition(condition);
        if(value instanceof Collection){
            criterion.setList(value);
            criterion.setListValue(true);
        }else{
            criterion.setValue1(value);
            criterion.setOneValue(true);
        }
        criterions.add(criterion);
    }

    /**
     * IsNull和IsNotNull操作
     *
     * @param condition
     */
    public void addCriterion(String condition) {
        if (StringUtils.isEmpty(condition)) {
            throw new RuntimeException("condition表达式不能为空");
        }
        Criterion criterion = new Criterion();
        criterion.setCondition(condition);
        criterion.setNoValue(true);
        criterions.add(criterion);
    }

    /**
     * Between和NotBetween操作
     *
     * @param condition
     * @param value1
     * @param value2
     */
    public void addCriterion(String condition, Object value1, Object value2) {
        if (StringUtils.isEmpty(condition)) {
            throw new RuntimeException("condition表达式不能为空");
        }
        if (Objects.isNull(value1) || Objects.isNull(value2)) {
            throw new RuntimeException("‘between’或者‘not between’操作值不能为空");
        }
        Criterion criterion = new Criterion();
        criterion.setCondition(condition);
        criterion.setValue1(value1);
        criterion.setValue2(value2);
        criterion.setSecondValue(true);
        criterions.add(criterion);
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
    public Criteria and(String field,Opt opt,Object... values) {
        addQuartets(Opt.AND,field,opt,values);
        if(values.length == 0 || values.length > 2){
            throw new RuntimeException("参数个数不正确");
        }
        if(values.length == 1){
            if(!opt.getOneValueOptSet().contains(opt)){
                throw new RuntimeException("操作类型不合法");
            }
            if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
                values[0] = SqlConst.PERCENT + values[0] + SqlConst.PERCENT;
            }
            if(Opt.IS.equals(opt) || Opt.IS_NOT.equals(opt)){
                addCriterion(SqlConst.AND_SPACE + field + opt.getCode() + SqlConst.NULL);
            } else {
                addCriterion(SqlConst.AND_SPACE + field + opt.getCode() , values[0]);
            }
        }else{
            if(!opt.getTwoValueOptSet().contains(opt)){
                throw new RuntimeException("操作类型不合法");
            }
            addCriterion(SqlConst.AND_SPACE + field + opt.getCode() , values[0] , values[1]);
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
    public Criteria or(String field,Opt opt,Object... values) {
        if(values.length == 0 || values.length > 2){
            throw new RuntimeException("参数个数不正确");
        }
        addQuartets(Opt.OR,field,opt,values);
        if(values.length == 1){
            if(!opt.getOneValueOptSet().contains(opt)){
                throw new RuntimeException("操作类型不合法");
            }
            if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
                values[0] = SqlConst.PERCENT + values[0] + SqlConst.PERCENT;
            }
            if(Opt.IS.equals(opt) || Opt.IS_NOT.equals(opt)){
                addCriterion(SqlConst.OR_SPACE + field + opt.getCode() + SqlConst.NULL);
            } else {
                addCriterion(SqlConst.OR_SPACE + field + opt.getCode() , values[0]);
            }
        }else{
            if(!opt.getTwoValueOptSet().contains(opt)){
                throw new RuntimeException("操作类型不合法");
            }
            addCriterion(SqlConst.OR_SPACE + field + opt.getCode() , values[0] , values[1]);
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
        quartets.add(new Quartet<>(conOpt,field,dbOpt, JSON.toJSONString(values)));
    }

}
