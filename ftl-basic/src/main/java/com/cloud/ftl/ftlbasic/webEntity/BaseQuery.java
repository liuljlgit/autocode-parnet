package com.cloud.ftl.ftlbasic.webEntity;

import com.cloud.ftl.ftlbasic.constant.SqlConst;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.query.ConditGroup;
import com.cloud.ftl.ftlbasic.query.OrderBy;
import com.cloud.ftl.ftlbasic.utils.MapUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class BaseQuery extends BasePage implements Serializable {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private List<ConditGroup> conditGroups;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private List<OrderBy> orderByList;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Map<String,List<Integer>> conditGroupsMap;

    public ConditGroup newConditGroup(Opt opt, BaseQuery superThis,String... groupNames){
        ConditGroup conditGroup = new ConditGroup(opt.getCode(),superThis);
        if(CollectionUtils.isEmpty(conditGroups)){
            conditGroups = Lists.newArrayList();
        }
        conditGroups.add(conditGroup);
        if(groupNames.length != 0 && !StringUtils.isEmpty(groupNames[0])){
            addConditGroupHashCode(groupNames[0],conditGroup);
        } else {
            addConditGroupHashCode(SqlConst.DEFAULT_FIELD,conditGroup);
        }
        return conditGroup;
    }

    /**
     * AND ConditGroup
     * @throws
     */
    public ConditGroup andConditGroup(String... groupName) {
        return  newConditGroup(Opt.AND,this,groupName);
    }

    /**
     * OR ConditGroup
     * @throws
     */
    public ConditGroup orConditGroup(String... groupName) {
        return newConditGroup(Opt.OR,this,groupName);
    }

    /**
     * IS NULL或者IS NOT NULL操作
     * @throws
     */
    protected void addConditGroup(String field, Opt opt) {
        if(!Opt.IS_NULL.equals(opt) && !Opt.IS_NOT_NULL.equals(opt)){
            throw new RuntimeException("操作域‘" + field + "’的操作类型不合法,此处只能为‘IS NULL’或者‘IS NOT NULL’");
        }
        ConditGroup conditGroup = newConditGroup(Opt.AND, this,field);
        conditGroup.addCondition(SqlConst.AND_SPACE + field + opt.getCode());
        conditGroup.addSqlCondition(Opt.AND.getCode(),field,opt.getCode());
    }

    /**
     * BETWEEN操作
     * @throws
     */
    protected void addConditGroup(String field, Opt opt, Object firstParam, Object secondParam) {
        if(!Opt.BETWEEN.equals(opt) && !Opt.NOT_BETWEEN.equals(opt)){
            throw new RuntimeException("操作域‘" + field + "’的操作类型不合法,此处只能为‘BETWEEN’或者‘NOT BETWEEN’");
        }
        ConditGroup conditGroup = newConditGroup(Opt.AND, this,field);
        conditGroup.addCondition(SqlConst.AND_SPACE + field + opt.getCode(),firstParam,secondParam);
        conditGroup.addSqlCondition(Opt.AND.getCode(),field,opt.getCode(),firstParam,secondParam);
    }

    /**
     * 单值或者list操作
     * @throws
     */
    protected void addConditGroup(String field, Opt opt, Object value) {
        ConditGroup conditGroup = newConditGroup(Opt.AND, this,field);
        if(Opt.AND.equals(opt) || Opt.OR.equals(opt) || Opt.ASC.equals(opt)
                || Opt.DESC.equals(opt) || Opt.BETWEEN.equals(opt) || Opt.NOT_BETWEEN.equals(opt)
                || Opt.IS_NULL.equals(opt) || Opt.IS_NOT_NULL.equals(opt)){
            throw new RuntimeException("操作域‘" + field + "’的操作类型不合法,此处不可以为：" + opt.getCode());
        }
        if(value instanceof Collection && !Opt.IN.equals(opt) && !Opt.NOT_IN.equals(opt)){
            throw new RuntimeException("操作域‘" + field + "’的操作类型不合法,此处只能为‘IN’或者‘NOT_IN’");
        }
        if(Opt.LIKE.equals(opt) || Opt.NOT_LIKE.equals(opt)){
            value = SqlConst.PERCENT + value + SqlConst.PERCENT;
        }
        conditGroup.addCondition(SqlConst.AND_SPACE + field + opt.getCode(),value);
        conditGroup.addSqlCondition(Opt.AND.getCode(),field,opt.getCode(),value);
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
     * 存储conditGroup的HashCode地址
     *
     * @param groupName
     * @param conditGroup
     */
    private void addConditGroupHashCode(String groupName,ConditGroup conditGroup){
        if(CollectionUtils.isEmpty(conditGroupsMap)){
            conditGroupsMap = Maps.newHashMap();
        }
        MapUtil.getHandleSetVal(conditGroupsMap,groupName,ArrayList::new,(list)->{
            list.add(conditGroup.hashCode());
            return list;
        });
    }

    /**
     * 清除条件组
     *
     * @param groupNames
     */
    public void clearConditGroup(String... groupNames){
        Set<Integer> hashCodeSet = new HashSet<>();
        for (String groupName : groupNames) {
            List<Integer> hashcodeList = conditGroupsMap.getOrDefault(groupName, Lists.newArrayList());
            if(!CollectionUtils.isEmpty(hashcodeList)){
                hashCodeSet.addAll(hashcodeList);
                conditGroupsMap.remove(groupName);
            }
        }
        conditGroups = conditGroups.stream()
                .filter(e -> !hashCodeSet.contains(e.hashCode()))
                .collect(Collectors.toList());
    }
}
