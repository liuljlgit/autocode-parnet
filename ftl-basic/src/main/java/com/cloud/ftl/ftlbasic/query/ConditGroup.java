package com.cloud.ftl.ftlbasic.query;

import com.alibaba.fastjson.JSONObject;
import com.cloud.ftl.ftlbasic.constant.SqlConst;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.SqlCondition;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.CollectionUtils;
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
     * field comment:
     * 用来记录sql语句,用于生成rediskey
     */
    private List<SqlCondition> sqlConditions = Lists.newArrayList();

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
        addSqlCondition(Opt.AND.getCode(),field,opt.getCode(),values);
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
        addSqlCondition(Opt.OR.getCode(),field,opt.getCode(),values);
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
     * 例子: and da_id = 1000
     * @param conStr
     * @param field
     * @param optStr
     * @param values
     */
    public void addSqlCondition(String conStr,String field,String optStr,Object... values){
        if(values.length > 0){
            sqlConditions.add(new SqlCondition(conStr,field,optStr, JSONObject.toJSONString(values)));
        } else {
            sqlConditions.add(new SqlCondition(conStr,field,optStr,null));
        }
    }

    public String getConditionRedisKey(){
        if(CollectionUtils.isEmpty(sqlConditions)){
            return SqlConst.BLANK;
        }
        if(sqlConditions.size() == 1){
            StringBuilder str = new StringBuilder(this.opt);
            getConditionStr(str);
            return str.toString();
        }
        StringBuilder str = new StringBuilder(this.opt + SqlConst.LEFT_BRACKETS);
        getConditionStr(str);
        str.append(SqlConst.RIGHT_BRACKETS);
        return str.toString();
    }

    private void getConditionStr(StringBuilder str) {
        Boolean isFirst = Boolean.TRUE;
        for (SqlCondition e : sqlConditions) {
            String values = e.getValues();
            String firstConStr;
            if(isFirst){
                firstConStr = SqlConst.BLANK;
                isFirst = Boolean.FALSE;
            } else {
                firstConStr = e.getConStr();
            }
            if(StringUtils.isEmpty(values)){
                str.append(firstConStr).append(e.getField()).append(e.getOptStr());
            } else {
                str.append(firstConStr).append(e.getField()).append(e.getOptStr()).append(e.getValues());
            }
        }
    }

}
