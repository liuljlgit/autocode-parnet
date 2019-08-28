package com.cloud.ftl.ftltest.test.entity;

import com.cloud.ftl.ftlbasic.aspect.PrimaryKey;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoadTime extends BaseQuery {

    /**
     * field comment:实际负荷导入数据记录ID
     */
    @PrimaryKey
    private Long ltId;

    /**
     * field comment:电企业ID：来源于cloud_sys.entity.entity_id
     */
    private Integer entityId;

    /**
     * field comment:导入开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    /**
     * field comment:导入结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    /**
     * field comment:状态 (0) 未处理 （1）已处理
     */
    private Byte status;

    /**
     * field comment:创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * field comment:更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date statusTime;

    @JsonIgnore
    public static final transient String LT_ID = "lt_id";

    @JsonIgnore
    public static final transient String ENTITY_ID = "entity_id";

    @JsonIgnore
    public static final transient String START_TIME = "start_time";

    @JsonIgnore
    public static final transient String END_TIME = "end_time";

    @JsonIgnore
    public static final transient String STATUS = "status";

    @JsonIgnore
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    public static final transient String STATUS_TIME = "status_time";


    public void andLtId(Opt opt) {
        addConditGroup(LT_ID,opt);
    }

    public void andLtId(Opt opt,Long ltId) {
        addConditGroup(LT_ID,opt,ltId);
    }

    public void andLtId(Opt opt,List<Long> list) {
        addConditGroup(LT_ID,opt,list);
    }

    public void andLtId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(LT_ID,opt,firstParam,secondParam);
    }

    public void andEntityId(Opt opt) {
        addConditGroup(ENTITY_ID,opt);
    }

    public void andEntityId(Opt opt,Integer entityId) {
        addConditGroup(ENTITY_ID,opt,entityId);
    }

    public void andEntityId(Opt opt,List<Integer> list) {
        addConditGroup(ENTITY_ID,opt,list);
    }

    public void andEntityId(Opt opt,Integer firstParam,Integer secondParam) {
        addConditGroup(ENTITY_ID,opt,firstParam,secondParam);
    }

    public void andStartTime(Opt opt) {
        addConditGroup(START_TIME,opt);
    }

    public void andStartTime(Opt opt,Date startTime) {
        addConditGroup(START_TIME,opt,startTime);
    }

    public void andStartTime(Opt opt,List<Date> list) {
        addConditGroup(START_TIME,opt,list);
    }

    public void andStartTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(START_TIME,opt,firstParam,secondParam);
    }

    public void andEndTime(Opt opt) {
        addConditGroup(END_TIME,opt);
    }

    public void andEndTime(Opt opt,Date endTime) {
        addConditGroup(END_TIME,opt,endTime);
    }

    public void andEndTime(Opt opt,List<Date> list) {
        addConditGroup(END_TIME,opt,list);
    }

    public void andEndTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(END_TIME,opt,firstParam,secondParam);
    }

    public void andStatus(Opt opt) {
        addConditGroup(STATUS,opt);
    }

    public void andStatus(Opt opt,Byte status) {
        addConditGroup(STATUS,opt,status);
    }

    public void andStatus(Opt opt,List<Byte> list) {
        addConditGroup(STATUS,opt,list);
    }

    public void andStatus(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(STATUS,opt,firstParam,secondParam);
    }

    public void andCreateTime(Opt opt) {
        addConditGroup(CREATE_TIME,opt);
    }

    public void andCreateTime(Opt opt,Date createTime) {
        addConditGroup(CREATE_TIME,opt,createTime);
    }

    public void andCreateTime(Opt opt,List<Date> list) {
        addConditGroup(CREATE_TIME,opt,list);
    }

    public void andCreateTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(CREATE_TIME,opt,firstParam,secondParam);
    }

    public void andStatusTime(Opt opt) {
        addConditGroup(STATUS_TIME,opt);
    }

    public void andStatusTime(Opt opt,Date statusTime) {
        addConditGroup(STATUS_TIME,opt,statusTime);
    }

    public void andStatusTime(Opt opt,List<Date> list) {
        addConditGroup(STATUS_TIME,opt,list);
    }

    public void andStatusTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(STATUS_TIME,opt,firstParam,secondParam);
    }

}