package com.cloud.ftl.ftlbasic.webEntity;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.query.Criteria;
import org.springframework.util.CollectionUtils;

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
    public Criteria andCriteria() throws Exception {
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
    public Criteria orCriteria() throws Exception {
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
    public void addCriteria(String field,Object value) throws Exception {
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
        criteria.addCriterion("and "+field+" "+opt.getCode()+" ",value);
        criterias.add(criteria);
    }
}
