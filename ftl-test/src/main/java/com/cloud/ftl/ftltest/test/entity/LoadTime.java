package com.cloud.ftl.ftltest.test.entity;

import com.cloud.ftl.ftlbasic.webEntity.BaseEntity;

import java.util.Date;

public class LoadTime extends BaseEntity{

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

    public static final transient String PROP_ENTITY_ID = "entityId";

    public static final transient String PROP_START_TIME = "startTime";

    public static final transient String PROP_END_TIME = "endTime";

    public static final transient String PROP_STATUS = "status";

    public static final transient String PROP_CREATE_TIME = "createTime";

    public static final transient String PROP_STATUS_TIME = "statusTime";


    public Long getLtId() {
        return ltId;
    }

    public void setLtId(Long ltId) throws Exception {
        addCriteria("lt_id",ltId);
        this.ltId = ltId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) throws Exception {
        addCriteria("entity_id",entityId);
        this.entityId = entityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) throws Exception {
        addCriteria("start_time",startTime);
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) throws Exception {
        addCriteria("end_time",endTime);
        this.endTime = endTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) throws Exception {
        addCriteria("status",status);
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) throws Exception {
        addCriteria("create_time",createTime);
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) throws Exception {
        addCriteria("status_time",statusTime);
        this.statusTime = statusTime;
    }

}