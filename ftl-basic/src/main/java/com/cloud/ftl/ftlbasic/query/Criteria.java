package com.cloud.ftl.ftlbasic.query;

import com.cloud.ftl.ftlbasic.enums.Opt;
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

    public Criteria() {

    }

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

}
