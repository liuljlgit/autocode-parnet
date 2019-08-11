package com.cloud.ftl.ftlbasic.query;

import com.alibaba.fastjson.JSON;
import com.cloud.ftl.ftlbasic.enums.Opt;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 这个类相当于是一个"()"
 */
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
    private List<Criterion> criterions;

    /**
     * field comment:()前的操作。and或者or
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
    List<Quartet<Opt,String, Opt, String>> quartets = new ArrayList<>();

    public Criteria() {}

    public Criteria(String opt) {
        this.opt = opt;
    }

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public void setCriterions(List<Criterion> criterions) {
        this.criterions = criterions;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public List<Quartet<Opt, String, Opt, String>> getQuartets() {
        return quartets;
    }

    public void setQuartets(List<Quartet<Opt, String, Opt, String>> quartets) {
        this.quartets = quartets;
    }

    /**
     * IsNull、IsNotNull
     * @param condition
     */
    public void addCriterion(String condition) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }
        if(CollectionUtils.isEmpty(criterions)){
            criterions = new ArrayList<>();
        }
        Criterion criterion = new Criterion();
        criterion.setCondition(condition);
        criterion.setNoValue(true);
        criterions.add(criterion);
    }

    /**
     * EqualTo、NotEqualTo、GreaterThan、GreaterThanOrEqualTo、LessThan、LessThanOrEqualTo、Like、NotLike、In、NotIn
     * @param condition
     * @param value
     */
    public void addCriterion(String condition, Object value) {
        if (value == null) {
            throw new RuntimeException("value cannot be null");
        }
        if(CollectionUtils.isEmpty(criterions)){
            criterions = new ArrayList<>();
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
     * Between、NotBetween
     * @param condition
     * @param value1
     * @param value2
     */
    public void addCriterion(String condition, Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("value1 and value2 cannot be null");
        }
        if(CollectionUtils.isEmpty(criterions)){
            criterions = new ArrayList<>();
        }
        Criterion criterion = new Criterion();
        criterion.setCondition(condition);
        criterion.setValue1(value1);
        criterion.setValue2(value2);
        criterion.setSecondValue(true);
        criterions.add(criterion);
    }


    /**
     * and操作
     * @param opt
     * @param field
     * @param values
     * @return
     * @throws Exception
     */
    public Criteria and(String field,Opt opt,Object... values) throws Exception {
        addQuartets(Opt.AND,field,opt,values);
        if(values.length>2){
            throw new Exception("values length can not bigger than 2");
        }
        if(values.length == 0){
            if(!Opt.IS_NULL.equals(opt) && !Opt.IS_NOT_NULL.equals(opt)){
                throw new Exception("when values lenth equal to 0,opt is Opt.IS_NULL or opt is Opt.IS_NOT_NULL");
            }
            addCriterion("and "+field+" "+opt.getCode());
        }else if(values.length == 1){
            if(!Opt.EQUAL.equals(opt) && !Opt.NOT_EQUAL.equals(opt) && !Opt.LIKE.equals(opt) && !Opt.NOT_LIKE.equals(opt)
                    && !Opt.GREATER.equals(opt) && !Opt.GREATER_EQUAL.equals(opt) && !Opt.LESS.equals(opt) && !Opt.LESS_EQUAL.equals(opt)
                    && !Opt.IN.equals(opt) && !Opt.NOT_IN.equals(opt) && !Opt.IS.equals(opt) && !Opt.IS_NOT.equals(opt)){
                throw new Exception("opt enumes type not support!");
            }
            if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
                values[0] = "%"+values[0]+"%";
            }
            if(Opt.IS.equals(opt) || Opt.IS_NOT.equals(opt)){
                values[0] = null;
            }
            addCriterion("and "+field+" "+opt.getCode()+" ",values[0]);
        }else{
            //检查opt是否是between或者是not between
            if(!Opt.BETWEEN.equals(opt) && !Opt.NOT_BETWEEN.equals(opt)){
                throw new Exception("when values lenth equal to 2,opt is Opt.BETWEEN or opt is Opt.NOT_BETWEEN");
            }
            addCriterion("and "+field+" "+opt.getCode()+" ",values[0],values[1]);
        }
        return this;
    }

    /**
     * or操作
     * @param opt
     * @param field
     * @param values
     * @return
     * @throws Exception
     */
    public Criteria or(String field,Opt opt,Object... values) throws Exception {
        addQuartets(Opt.OR,field,opt,values);
        if(values.length>2){
            throw new Exception("values length can not bigger than 2");
        }
        if(values.length == 0){
            if(!Opt.IS_NULL.equals(opt) && !Opt.IS_NOT_NULL.equals(opt)){
                throw new Exception("when values lenth equal to 0,opt is Opt.IS_NULL or opt is Opt.IS_NOT_NULL");
            }
            addCriterion("or "+field+" "+opt.getCode());
        }else if(values.length == 1){
            if(!Opt.EQUAL.equals(opt) && !Opt.NOT_EQUAL.equals(opt) && !Opt.LIKE.equals(opt) && !Opt.NOT_LIKE.equals(opt)
                    && !Opt.GREATER.equals(opt) && !Opt.GREATER_EQUAL.equals(opt) && !Opt.LESS.equals(opt) && !Opt.LESS_EQUAL.equals(opt)
                    && !Opt.IN.equals(opt) && !Opt.NOT_IN.equals(opt) && !Opt.IS.equals(opt) && !Opt.IS_NOT.equals(opt)){
                throw new Exception("opt enumes type not support!");
            }
            if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
                values[0] = "%"+values[0]+"%";
            }
            if(Opt.IS.equals(opt) || Opt.IS_NOT.equals(opt)){
                values[0] = null;
            }
            addCriterion("or "+field+" "+opt.getCode()+" ",values[0]);
        }else{
            //检查opt是否是between或者是not between
            if(!Opt.BETWEEN.equals(opt) && !Opt.NOT_BETWEEN.equals(opt)){
                throw new Exception("when values lenth equal to 2,opt is Opt.BETWEEN or opt is Opt.NOT_BETWEEN");
            }
            addCriterion("or "+field+" "+opt.getCode()+" ",values[0],values[1]);
        }
        return this;
    }


    /**
     * 主要是用来生成Quartet值
     * @param conOpt
     * @param field
     * @param dbOpt
     * @param values
     * 例子: and da_id = 1000
     */
    public void addQuartets(Opt conOpt,String field,Opt dbOpt,Object... values){
        if(values.length == 0){
            quartets.add(new Quartet<>(conOpt,field,dbOpt,"null"));
        }else{
            quartets.add(new Quartet<>(conOpt,field,dbOpt, JSON.toJSONString(values)));
        }
    }

}
