package com.cloud.ftl.ftltest.test.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BasePage;
import java.io.Serializable;

public class LoadTime extends BasePage implements Serializable {

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

    public void setLtId(Long ltId) {
        this.ltId = ltId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

}