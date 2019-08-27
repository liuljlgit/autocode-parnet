package com.cloud.ftl.ftlbasic.webEntity;

import com.cloud.ftl.ftlbasic.constant.SqlConst;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.query.Criteria;
import com.cloud.ftl.ftlbasic.query.OrderBy;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class BaseQuery extends BasePage {

    private List<Criteria> criterias;

    private List<OrderBy> orderByList;

    private Map<String,Integer> criteriasMap;

    private Criteria createCriteria(Opt opt,String... field){
        Criteria criteria = new Criteria(opt.getCode());
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        criterias.add(criteria);
        if(field.length != 0 && !StringUtils.isEmpty(field[0])){
            saveCriteriaHashCode(field[0],criteria);
        } else {
            saveCriteriaHashCode(SqlConst.DEFAULT_FIELD,criteria);
        }
        return criteria;
    }

    /**
     * and (...)
     * @throws
     */
    public Criteria andCriteria(String... field) {
        return  createCriteria(Opt.AND,field);
    }

    /**
     * or (...)
     * @throws
     */
    public Criteria orCriteria(String... field) {
        return createCriteria(Opt.OR);
    }

    /**
     * EQUAL操作
     * @throws
     */
    public void addCriteria(String field,Object value) {
        Criteria criteria = createCriteria(Opt.AND, field);
        criteria.addCriterion(SqlConst.AND_SPACE + field + Opt.EQUAL.getCode(),value);
        criteria.addQuartets(Opt.AND,field,Opt.EQUAL,value);
    }

    /**
     * BETWEEN操作
     * @throws
     */
    public void addCriteria(String field,Opt opt,Object value1,Object value2) {
        if(!Opt.BETWEEN.equals(opt) && !Opt.NOT_BETWEEN.equals(opt)){
            throw new RuntimeException("操作域‘"+field+"’的操作类型不合法,此处只能为‘BETWEEN’或者‘NOT BETWEEN’");
        }
        Criteria criteria = createCriteria(Opt.AND, field);
        criteria.addCriterion(SqlConst.AND_SPACE + field + opt.getCode(),value1,value2);
        criteria.addQuartets(Opt.AND,field,opt,value1,value2);
    }

    /**
     * 单值或者list操作
     * @throws
     */
    public void addCriteria(String field,Opt opt,Object value) {
        Criteria criteria = createCriteria(Opt.AND, field);
        if(Opt.AND.equals(opt) || Opt.OR.equals(opt) || Opt.ASC.equals(opt)
                || Opt.DESC.equals(opt) || Opt.BETWEEN.equals(opt) || Opt.NOT_BETWEEN.equals(opt)){
            throw new RuntimeException("操作域‘"+field+"’的操作类型不合法，此处不能为‘AND’、‘OR’、‘ASC’、‘DESC’、‘BETWEEN’和‘NOT BETWEEN’");
        }
        if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
            value = SqlConst.PERCENT + value + SqlConst.PERCENT;
        }
        if(Opt.IS.equals(opt) || Opt.IS_NOT.equals(opt)){
            criteria.addCriterion(SqlConst.AND_SPACE + field + opt.getCode() + SqlConst.NULL);
        } else {
            criteria.addCriterion(SqlConst.AND_SPACE + field + opt.getCode(),value);
        }
        criteria.addQuartets(Opt.AND,field,opt,value);
    }


    /**
     * 增加排序操作
     * @param field
     * @param mode
     */
    public void addOrderBy(String field,Opt mode) {
        if(!Opt.ASC.equals(mode) && !Opt.DESC.equals(mode)){
            throw new RuntimeException("排序类型不正确");
        }
        if(Objects.isNull(orderByList)){
            orderByList = Lists.newArrayList();
        }
        orderByList.add(new OrderBy(field,mode.getCode()));
    }

    /**
     * 存储criteria的地址
     * @param field
     * @param criteria
     */
    private void saveCriteriaHashCode(String field,Criteria criteria){
        if(CollectionUtils.isEmpty(criteriasMap)){
            criteriasMap = new HashMap<>();
        }
        criteriasMap.put(field,criteria.hashCode());
    }

    /**
     * 清除条件组
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
