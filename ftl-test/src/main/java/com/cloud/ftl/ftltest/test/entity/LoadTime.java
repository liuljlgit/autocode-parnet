package com.cloud.ftl.ftltest.test.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;
import lombok.Data;
import com.cloud.ftl.ftltest.test.constant.LoadTimeTable;

@Data
public class LoadTime extends BaseQuery {

    public static Map<String,String> map = LoadTimeTable.map;

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


    public void andLtId(Opt opt,Long ltId) throws Exception {
        addCriteria(LoadTimeTable.LT_ID,opt,ltId);
    }

    public void andLtId(Opt opt,List<Long> ltIdList) throws Exception {
        addCriteria(LoadTimeTable.LT_ID,opt,ltIdList);
    }

    public void andLtId(Opt opt,Long ltId1,Long ltId2) throws Exception {
        addCriteria(LoadTimeTable.LT_ID,opt,ltId1,ltId2);
    }

    public void andEntityId(Opt opt,Integer entityId) throws Exception {
        addCriteria(LoadTimeTable.ENTITY_ID,opt,entityId);
    }

    public void andEntityId(Opt opt,List<Integer> entityIdList) throws Exception {
        addCriteria(LoadTimeTable.ENTITY_ID,opt,entityIdList);
    }

    public void andEntityId(Opt opt,Integer entityId1,Integer entityId2) throws Exception {
        addCriteria(LoadTimeTable.ENTITY_ID,opt,entityId1,entityId2);
    }

    public void andStartTime(Opt opt,Date startTime) throws Exception {
        addCriteria(LoadTimeTable.START_TIME,opt,startTime);
    }

    public void andStartTime(Opt opt,List<Date> startTimeList) throws Exception {
        addCriteria(LoadTimeTable.START_TIME,opt,startTimeList);
    }

    public void andStartTime(Opt opt,Date startTime1,Date startTime2) throws Exception {
        addCriteria(LoadTimeTable.START_TIME,opt,startTime1,startTime2);
    }

    public void andEndTime(Opt opt,Date endTime) throws Exception {
        addCriteria(LoadTimeTable.END_TIME,opt,endTime);
    }

    public void andEndTime(Opt opt,List<Date> endTimeList) throws Exception {
        addCriteria(LoadTimeTable.END_TIME,opt,endTimeList);
    }

    public void andEndTime(Opt opt,Date endTime1,Date endTime2) throws Exception {
        addCriteria(LoadTimeTable.END_TIME,opt,endTime1,endTime2);
    }

    public void andStatus(Opt opt,Byte status) throws Exception {
        addCriteria(LoadTimeTable.STATUS,opt,status);
    }

    public void andStatus(Opt opt,List<Byte> statusList) throws Exception {
        addCriteria(LoadTimeTable.STATUS,opt,statusList);
    }

    public void andStatus(Opt opt,Byte status1,Byte status2) throws Exception {
        addCriteria(LoadTimeTable.STATUS,opt,status1,status2);
    }

    public void andCreateTime(Opt opt,Date createTime) throws Exception {
        addCriteria(LoadTimeTable.CREATE_TIME,opt,createTime);
    }

    public void andCreateTime(Opt opt,List<Date> createTimeList) throws Exception {
        addCriteria(LoadTimeTable.CREATE_TIME,opt,createTimeList);
    }

    public void andCreateTime(Opt opt,Date createTime1,Date createTime2) throws Exception {
        addCriteria(LoadTimeTable.CREATE_TIME,opt,createTime1,createTime2);
    }

    public void andStatusTime(Opt opt,Date statusTime) throws Exception {
        addCriteria(LoadTimeTable.STATUS_TIME,opt,statusTime);
    }

    public void andStatusTime(Opt opt,List<Date> statusTimeList) throws Exception {
        addCriteria(LoadTimeTable.STATUS_TIME,opt,statusTimeList);
    }

    public void andStatusTime(Opt opt,Date statusTime1,Date statusTime2) throws Exception {
        addCriteria(LoadTimeTable.STATUS_TIME,opt,statusTime1,statusTime2);
    }

}