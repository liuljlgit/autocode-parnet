package com.cloud.ftl.ftlbasic.webEntity;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.query.Criteria;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseQuery extends BasePage {

    private List<Criteria> criterias;

    private String orderByClause;

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * and Criteria
     * @throws Exception
     */
    public Criteria andCriteria() {
        Criteria criteria = new Criteria(Opt.AND.getCode());
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        criterias.add(criteria);
        return criteria;
    }

    /**
     * or Criteria
     * @throws Exception
     */
    public Criteria orCriteria() {
        Criteria criteria = new Criteria(Opt.OR.getCode());
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        criterias.add(criteria);
        return criteria;
    }

    /**
     * 自定义生成一个Criteria
     * @param criteria
     * @throws Exception
     */
    public void addCriteria(Criteria criteria) throws Exception {
        if(Objects.isNull(criteria)){
            throw new Exception("criteria can not be null");
        }
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        criterias.add(criteria);
    }

    /**
     * 对对象进行set操作的时候调用
     * @throws Exception
     */
    public void addCriteria(String field,Object value) {
        Criteria criteria = new Criteria(Opt.AND.getCode());
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        criteria.addCriterion("and "+field+" "+Opt.EQUAL.getCode()+" ",value);
        criterias.add(criteria);
    }

    /**
     * 对对象进行set操作的时候调用
     * @throws Exception
     */
    public void addCriteria(String field,Object value,Opt opt) throws Exception {
        Criteria criteria = new Criteria(Opt.AND.getCode());
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        if(Opt.AND.equals(opt) || Opt.OR.equals(opt) || Opt.ASC.equals(opt) || Opt.DESC.equals(opt)){
            throw new Exception("opt enums type not support!");
        }
        if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
            value = "%"+value+"%";
        }
        if(Opt.IS_NULL.equals(opt) || Opt.IS_NOT_NULL.equals(opt)){
            value = "";
        }
        if(Opt.IS.equals(opt) || Opt.IS_NOT.equals(opt)){
            value = null;
        }
        if(Opt.BETWEEN.equals(opt) || Opt.NOT_BETWEEN.equals(opt)){
            criteria.addCriterion("and "+field+" "+opt.getCode()+" ",value,value);
            criterias.add(criteria);
        }else{
            criteria.addCriterion("and "+field+" "+opt.getCode()+" ",value);
            criterias.add(criteria);
        }
    }

    /**
     * 增加排序操作
     * @param field
     * @param desc
     * @throws Exception
     */
    public void addOrderBy(String field,Boolean desc) {
        String order;
        if(desc){
            order = Opt.DESC.getCode();
        }else{
            order = Opt.ASC.getCode();
        }
        if(StringUtils.isEmpty(orderByClause)){
            orderByClause = field + " "+order;
        }else{
            orderByClause = orderByClause + "," +field + " "+order;
        }
    }
}
