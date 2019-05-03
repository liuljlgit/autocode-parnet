package com.cloud.ftl.ftltest.test.query;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;

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


    public static final transient String TABLE_LT_ID = "lt_id";

    public static final transient String TABLE_ENTITY_ID = "entity_id";

    public static final transient String TABLE_START_TIME = "start_time";

    public static final transient String TABLE_END_TIME = "end_time";

    public static final transient String TABLE_STATUS = "status";

    public static final transient String TABLE_CREATE_TIME = "create_time";

    public static final transient String TABLE_STATUS_TIME = "status_time";


    public void setLtId(Long ltId) {
        addCriteria(LoadTimeQuery.TABLE_LT_ID,ltId);
    }

    public void setLtId(Opt opt,Long ltId) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_LT_ID,opt,ltId);
    }

    public void setLtId(Opt opt,List<Long> ltIdList) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_LT_ID,opt,ltIdList);
    }

    public void setLtId(Opt opt,Long ltId1,Long ltId2) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_LT_ID,opt,ltId1,ltId2);
    }

    public void setEntityId(Integer entityId) {
        addCriteria(LoadTimeQuery.TABLE_ENTITY_ID,entityId);
    }

    public void setEntityId(Opt opt,Integer entityId) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_ENTITY_ID,opt,entityId);
    }

    public void setEntityId(Opt opt,List<Integer> entityIdList) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_ENTITY_ID,opt,entityIdList);
    }

    public void setEntityId(Opt opt,Integer entityId1,Integer entityId2) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_ENTITY_ID,opt,entityId1,entityId2);
    }

    public void setStartTime(Date startTime) {
        addCriteria(LoadTimeQuery.TABLE_START_TIME,startTime);
    }

    public void setStartTime(Opt opt,Date startTime) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_START_TIME,opt,startTime);
    }

    public void setStartTime(Opt opt,List<Date> startTimeList) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_START_TIME,opt,startTimeList);
    }

    public void setStartTime(Opt opt,Date startTime1,Date startTime2) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_START_TIME,opt,startTime1,startTime2);
    }

    public void setEndTime(Date endTime) {
        addCriteria(LoadTimeQuery.TABLE_END_TIME,endTime);
    }

    public void setEndTime(Opt opt,Date endTime) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_END_TIME,opt,endTime);
    }

    public void setEndTime(Opt opt,List<Date> endTimeList) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_END_TIME,opt,endTimeList);
    }

    public void setEndTime(Opt opt,Date endTime1,Date endTime2) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_END_TIME,opt,endTime1,endTime2);
    }

    public void setStatus(Byte status) {
        addCriteria(LoadTimeQuery.TABLE_STATUS,status);
    }

    public void setStatus(Opt opt,Byte status) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_STATUS,opt,status);
    }

    public void setStatus(Opt opt,List<Byte> statusList) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_STATUS,opt,statusList);
    }

    public void setStatus(Opt opt,Byte status1,Byte status2) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_STATUS,opt,status1,status2);
    }

    public void setCreateTime(Date createTime) {
        addCriteria(LoadTimeQuery.TABLE_CREATE_TIME,createTime);
    }

    public void setCreateTime(Opt opt,Date createTime) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_CREATE_TIME,opt,createTime);
    }

    public void setCreateTime(Opt opt,List<Date> createTimeList) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_CREATE_TIME,opt,createTimeList);
    }

    public void setCreateTime(Opt opt,Date createTime1,Date createTime2) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_CREATE_TIME,opt,createTime1,createTime2);
    }

    public void setStatusTime(Date statusTime) {
        addCriteria(LoadTimeQuery.TABLE_STATUS_TIME,statusTime);
    }

    public void setStatusTime(Opt opt,Date statusTime) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_STATUS_TIME,opt,statusTime);
    }

    public void setStatusTime(Opt opt,List<Date> statusTimeList) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_STATUS_TIME,opt,statusTimeList);
    }

    public void setStatusTime(Opt opt,Date statusTime1,Date statusTime2) throws Exception {
        addCriteria(LoadTimeQuery.TABLE_STATUS_TIME,opt,statusTime1,statusTime2);
    }

}