package com.cloud.ftl.ftlbasic.webEntity;

import com.cloud.ftl.ftlbasic.constant.BasicConst;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.query.Criteria;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class BaseQuery extends BasePage {

    private List<Criteria> criterias;

    private String orderByClause;

    private Map<String,Integer> criteriasMap;

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }

    public Map<String, Integer> getCriteriasMap() {
        return criteriasMap;
    }

    public void setCriteriasMap(Map<String, Integer> criteriasMap) {
        this.criteriasMap = criteriasMap;
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
        addCriteria2Map(BasicConst.DEFAULT,criteria);
        return criteria;
    }

    /**
     * and Criteria
     * @throws Exception
     */
    public Criteria andCriteria(String field) {
        if(StringUtils.isEmpty(field)){
            return andCriteria();
        }else{
            Criteria criteria = new Criteria(Opt.AND.getCode());
            if(CollectionUtils.isEmpty(criterias)){
                criterias = new ArrayList<>();
            }
            criterias.add(criteria);
            addCriteria2Map(field,criteria);
            return criteria;
        }
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
        addCriteria2Map(BasicConst.DEFAULT,criteria);
        return criteria;
    }

    /**
     * or Criteria
     * @throws Exception
     */
    public Criteria orCriteria(String field) {
        if(StringUtils.isEmpty(field)){
            return orCriteria();
        }else{
            Criteria criteria = new Criteria(Opt.OR.getCode());
            if(CollectionUtils.isEmpty(criterias)){
                criterias = new ArrayList<>();
            }
            criterias.add(criteria);
            addCriteria2Map(field,criteria);
            return criteria;
        }
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
        criteria.addQuartets(Opt.AND,field,Opt.EQUAL,value); //增加元组信息
        criterias.add(criteria);
        addCriteria2Map(field,criteria);
    }

    /**
     * 对对象进行set操作的时候调用
     * @throws Exception
     */
    public void addCriteria(String field,Opt opt,Object value1,Object value2) throws Exception {
        if(!Opt.BETWEEN.equals(opt) && !Opt.NOT_BETWEEN.equals(opt)){
            throw new Exception("opt enums type not support!");
        }
        Criteria criteria = new Criteria(Opt.AND.getCode());
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        criteria.addCriterion("and "+field+" "+opt.getCode()+" ",value1,value2);
        criteria.addQuartets(Opt.AND,field,opt,value1,value2);   //增加元组信息
        criterias.add(criteria);
        addCriteria2Map(field,criteria);
    }

    /**
     * 对对象进行set操作的时候调用
     * @throws Exception
     */
    public void addCriteria(String field,Opt opt,Object value) throws Exception {
        Criteria criteria = new Criteria(Opt.AND.getCode());
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        if(Opt.AND.equals(opt) || Opt.OR.equals(opt) || Opt.ASC.equals(opt)
                || Opt.DESC.equals(opt) || Opt.BETWEEN.equals(opt) || Opt.NOT_BETWEEN.equals(opt)){
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
        criteria.addCriterion("and "+field+" "+opt.getCode()+" ",value);
        criteria.addQuartets(Opt.AND,field,opt,value);   //增加元组信息
        criterias.add(criteria);
        addCriteria2Map(field,criteria);
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

    /**
     * 增加criteria的hashcode到map中
     * @param field
     * @param criteria
     */
    private void addCriteria2Map(String field,Criteria criteria){
        if(CollectionUtils.isEmpty(criteriasMap)){
            criteriasMap = new HashMap<>();
        }
        criteriasMap.put(field,criteria.hashCode());
    }

    /**
     * 清除某个field下的查询条件
     * @param fields
     */
    public void cleanCriteria(String... fields){
        Set<Integer> hashCodeSet = new HashSet<>();
        for (String field : fields) {
            Integer hashcode = criteriasMap.getOrDefault(field, null);
            if(Objects.nonNull(hashcode)){
                hashCodeSet.add(hashcode);
                criteriasMap.remove(field);
            }
        }
        criterias = criterias.stream()
                .filter(e -> !hashCodeSet.contains(e.hashCode()))
                .collect(Collectors.toList());
    }
}
