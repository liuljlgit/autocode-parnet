package com.cloud.ftl.ftltest.test.query;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;

import java.util.Date;

public class LoadTimeQuery extends BaseQuery {

    /**
     * field comment:实际负荷导入数据记录ID
     */
	private Long ltId;

    /**
     * field comment:电企业ID：来源于cloud_sys.entity.entity_id
     */
	private Integer entityId;

    /**
     * field comment:导入开始时间
     */
	private Date startTime;

    /**
     * field comment:导入结束时间
     */
	private Date endTime;

    /**
     * field comment:状态 (0) 未处理 （1）已处理
     */
	private Byte status;

    /**
     * field comment:创建时间
     */
	private Date createTime;

    /**
     * field comment:更新时间
     */
	private Date statusTime;


    public static final transient String PROP_LT_ID = "ltId";
    public static final transient String TABLE_LT_ID = "lt_id";

    public static final transient String PROP_ENTITY_ID = "entityId";
    public static final transient String TABLE_ENTITY_ID = "entity_id";

    public static final transient String PROP_START_TIME = "startTime";
    public static final transient String TABLE_START_TIME = "start_time";

    public static final transient String PROP_END_TIME = "endTime";
    public static final transient String TABLE_END_TIME = "end_time";

    public static final transient String PROP_STATUS = "status";
    public static final transient String TABLE_STATUS = "status";

    public static final transient String PROP_CREATE_TIME = "createTime";
    public static final transient String TABLE_CREATE_TIME = "create_time";

    public static final transient String PROP_STATUS_TIME = "statusTime";
    public static final transient String TABLE_STATUS_TIME = "status_time";


    public Long getLtId() {
        return ltId;
    }

    public void setLtId(Long ltId) throws Exception {
        addCriteria("lt_id",ltId);
        this.ltId = ltId;
    }

    public void setLtId(Opt opt,Long ltId) throws Exception {
        addCriteria("lt_id",ltId,opt);
        this.ltId = ltId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) throws Exception {
        addCriteria("entity_id",entityId);
        this.entityId = entityId;
    }

    public void setEntityId(Opt opt,Integer entityId) throws Exception {
        addCriteria("entity_id",entityId,opt);
        this.entityId = entityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) throws Exception {
        addCriteria("start_time",startTime);
        this.startTime = startTime;
    }

    public void setStartTime(Opt opt,Date startTime) throws Exception {
        addCriteria("start_time",startTime,opt);
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) throws Exception {
        addCriteria("end_time",endTime);
        this.endTime = endTime;
    }

    public void setEndTime(Opt opt,Date endTime) throws Exception {
        addCriteria("end_time",endTime,opt);
        this.endTime = endTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) throws Exception {
        addCriteria("status",status);
        this.status = status;
    }

    public void setStatus(Opt opt,Byte status) throws Exception {
        addCriteria("status",status,opt);
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) throws Exception {
        addCriteria("create_time",createTime);
        this.createTime = createTime;
    }

    public void setCreateTime(Opt opt,Date createTime) throws Exception {
        addCriteria("create_time",createTime,opt);
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) throws Exception {
        addCriteria("status_time",statusTime);
        this.statusTime = statusTime;
    }

    public void setStatusTime(Opt opt,Date statusTime) throws Exception {
        addCriteria("status_time",statusTime,opt);
        this.statusTime = statusTime;
    }

}