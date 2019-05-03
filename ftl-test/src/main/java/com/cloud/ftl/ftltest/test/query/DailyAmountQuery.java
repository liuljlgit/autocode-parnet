package com.cloud.ftl.ftltest.test.query;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;

public class DailyAmountQuery extends BaseQuery {

    /**
     * field comment:日前报量管理ID
     */
	private Long daId;

    /**
     * field comment:电企业ID：来源于cloud_sys.entity.entity_id
     */
	private Integer entityId;

    /**
     * field comment:用电时间
     */
	private Date dateTime;

    /**
     * field comment:结算盈利
     */
	private BigDecimal settProfit;

    /**
     * field comment:支出金额
     */
	private BigDecimal expendProfit;

    /**
     * field comment:收入金额
     */
	private BigDecimal incomeProfit;

    /**
     * field comment:盈利小时数
     */
	private Byte profitHours;

    /**
     * field comment:亏损小时数
     */
	private Byte deficitHours;

    /**
     * field comment:当天最大负偏差率(%)
     */
	private BigDecimal minusDeviation;

    /**
     * field comment:当天最大正偏差率(%)
     */
	private BigDecimal positiveDeviation;

    /**
     * field comment:状态 (0) 弃用 （1）正常
     */
	private Byte status;

    /**
     * field comment:
     */
	private Byte ce;

    /**
     * field comment:
     */
	private Byte sa;

    /**
     * field comment:创建时间
     */
	private Date createTime;

    /**
     * field comment:更新时间
     */
	private Date statusTime;


    public static final transient String TABLE_DA_ID = "da_id";

    public static final transient String TABLE_ENTITY_ID = "entity_id";

    public static final transient String TABLE_DATE_TIME = "date_time";

    public static final transient String TABLE_SETT_PROFIT = "sett_profit";

    public static final transient String TABLE_EXPEND_PROFIT = "expend_profit";

    public static final transient String TABLE_INCOME_PROFIT = "income_profit";

    public static final transient String TABLE_PROFIT_HOURS = "profit_hours";

    public static final transient String TABLE_DEFICIT_HOURS = "deficit_hours";

    public static final transient String TABLE_MINUS_DEVIATION = "minus_deviation";

    public static final transient String TABLE_POSITIVE_DEVIATION = "positive_deviation";

    public static final transient String TABLE_STATUS = "status";

    public static final transient String TABLE_CE = "ce";

    public static final transient String TABLE_SA = "sa";

    public static final transient String TABLE_CREATE_TIME = "create_time";

    public static final transient String TABLE_STATUS_TIME = "status_time";


    public void setDaId(Long daId) {
        addCriteria(DailyAmountQuery.TABLE_DA_ID,daId);
    }

    public void setDaId(Opt opt,Long daId) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DA_ID,opt,daId);
    }

    public void setDaId(Opt opt,List<Long> daIdList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DA_ID,opt,daIdList);
    }

    public void setDaId(Opt opt,Long daId1,Long daId2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DA_ID,opt,daId1,daId2);
    }

    public void setEntityId(Integer entityId) {
        addCriteria(DailyAmountQuery.TABLE_ENTITY_ID,entityId);
    }

    public void setEntityId(Opt opt,Integer entityId) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_ENTITY_ID,opt,entityId);
    }

    public void setEntityId(Opt opt,List<Integer> entityIdList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_ENTITY_ID,opt,entityIdList);
    }

    public void setEntityId(Opt opt,Integer entityId1,Integer entityId2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_ENTITY_ID,opt,entityId1,entityId2);
    }

    public void setDateTime(Date dateTime) {
        addCriteria(DailyAmountQuery.TABLE_DATE_TIME,dateTime);
    }

    public void setDateTime(Opt opt,Date dateTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DATE_TIME,opt,dateTime);
    }

    public void setDateTime(Opt opt,List<Date> dateTimeList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DATE_TIME,opt,dateTimeList);
    }

    public void setDateTime(Opt opt,Date dateTime1,Date dateTime2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DATE_TIME,opt,dateTime1,dateTime2);
    }

    public void setSettProfit(BigDecimal settProfit) {
        addCriteria(DailyAmountQuery.TABLE_SETT_PROFIT,settProfit);
    }

    public void setSettProfit(Opt opt,BigDecimal settProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SETT_PROFIT,opt,settProfit);
    }

    public void setSettProfit(Opt opt,List<BigDecimal> settProfitList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SETT_PROFIT,opt,settProfitList);
    }

    public void setSettProfit(Opt opt,BigDecimal settProfit1,BigDecimal settProfit2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SETT_PROFIT,opt,settProfit1,settProfit2);
    }

    public void setExpendProfit(BigDecimal expendProfit) {
        addCriteria(DailyAmountQuery.TABLE_EXPEND_PROFIT,expendProfit);
    }

    public void setExpendProfit(Opt opt,BigDecimal expendProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_EXPEND_PROFIT,opt,expendProfit);
    }

    public void setExpendProfit(Opt opt,List<BigDecimal> expendProfitList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_EXPEND_PROFIT,opt,expendProfitList);
    }

    public void setExpendProfit(Opt opt,BigDecimal expendProfit1,BigDecimal expendProfit2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_EXPEND_PROFIT,opt,expendProfit1,expendProfit2);
    }

    public void setIncomeProfit(BigDecimal incomeProfit) {
        addCriteria(DailyAmountQuery.TABLE_INCOME_PROFIT,incomeProfit);
    }

    public void setIncomeProfit(Opt opt,BigDecimal incomeProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_INCOME_PROFIT,opt,incomeProfit);
    }

    public void setIncomeProfit(Opt opt,List<BigDecimal> incomeProfitList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_INCOME_PROFIT,opt,incomeProfitList);
    }

    public void setIncomeProfit(Opt opt,BigDecimal incomeProfit1,BigDecimal incomeProfit2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_INCOME_PROFIT,opt,incomeProfit1,incomeProfit2);
    }

    public void setProfitHours(Byte profitHours) {
        addCriteria(DailyAmountQuery.TABLE_PROFIT_HOURS,profitHours);
    }

    public void setProfitHours(Opt opt,Byte profitHours) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_PROFIT_HOURS,opt,profitHours);
    }

    public void setProfitHours(Opt opt,List<Byte> profitHoursList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_PROFIT_HOURS,opt,profitHoursList);
    }

    public void setProfitHours(Opt opt,Byte profitHours1,Byte profitHours2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_PROFIT_HOURS,opt,profitHours1,profitHours2);
    }

    public void setDeficitHours(Byte deficitHours) {
        addCriteria(DailyAmountQuery.TABLE_DEFICIT_HOURS,deficitHours);
    }

    public void setDeficitHours(Opt opt,Byte deficitHours) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DEFICIT_HOURS,opt,deficitHours);
    }

    public void setDeficitHours(Opt opt,List<Byte> deficitHoursList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DEFICIT_HOURS,opt,deficitHoursList);
    }

    public void setDeficitHours(Opt opt,Byte deficitHours1,Byte deficitHours2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DEFICIT_HOURS,opt,deficitHours1,deficitHours2);
    }

    public void setMinusDeviation(BigDecimal minusDeviation) {
        addCriteria(DailyAmountQuery.TABLE_MINUS_DEVIATION,minusDeviation);
    }

    public void setMinusDeviation(Opt opt,BigDecimal minusDeviation) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_MINUS_DEVIATION,opt,minusDeviation);
    }

    public void setMinusDeviation(Opt opt,List<BigDecimal> minusDeviationList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_MINUS_DEVIATION,opt,minusDeviationList);
    }

    public void setMinusDeviation(Opt opt,BigDecimal minusDeviation1,BigDecimal minusDeviation2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_MINUS_DEVIATION,opt,minusDeviation1,minusDeviation2);
    }

    public void setPositiveDeviation(BigDecimal positiveDeviation) {
        addCriteria(DailyAmountQuery.TABLE_POSITIVE_DEVIATION,positiveDeviation);
    }

    public void setPositiveDeviation(Opt opt,BigDecimal positiveDeviation) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_POSITIVE_DEVIATION,opt,positiveDeviation);
    }

    public void setPositiveDeviation(Opt opt,List<BigDecimal> positiveDeviationList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_POSITIVE_DEVIATION,opt,positiveDeviationList);
    }

    public void setPositiveDeviation(Opt opt,BigDecimal positiveDeviation1,BigDecimal positiveDeviation2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_POSITIVE_DEVIATION,opt,positiveDeviation1,positiveDeviation2);
    }

    public void setStatus(Byte status) {
        addCriteria(DailyAmountQuery.TABLE_STATUS,status);
    }

    public void setStatus(Opt opt,Byte status) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS,opt,status);
    }

    public void setStatus(Opt opt,List<Byte> statusList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS,opt,statusList);
    }

    public void setStatus(Opt opt,Byte status1,Byte status2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS,opt,status1,status2);
    }

    public void setCe(Byte ce) {
        addCriteria(DailyAmountQuery.TABLE_CE,ce);
    }

    public void setCe(Opt opt,Byte ce) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CE,opt,ce);
    }

    public void setCe(Opt opt,List<Byte> ceList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CE,opt,ceList);
    }

    public void setCe(Opt opt,Byte ce1,Byte ce2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CE,opt,ce1,ce2);
    }

    public void setSa(Byte sa) {
        addCriteria(DailyAmountQuery.TABLE_SA,sa);
    }

    public void setSa(Opt opt,Byte sa) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SA,opt,sa);
    }

    public void setSa(Opt opt,List<Byte> saList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SA,opt,saList);
    }

    public void setSa(Opt opt,Byte sa1,Byte sa2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SA,opt,sa1,sa2);
    }

    public void setCreateTime(Date createTime) {
        addCriteria(DailyAmountQuery.TABLE_CREATE_TIME,createTime);
    }

    public void setCreateTime(Opt opt,Date createTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CREATE_TIME,opt,createTime);
    }

    public void setCreateTime(Opt opt,List<Date> createTimeList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CREATE_TIME,opt,createTimeList);
    }

    public void setCreateTime(Opt opt,Date createTime1,Date createTime2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CREATE_TIME,opt,createTime1,createTime2);
    }

    public void setStatusTime(Date statusTime) {
        addCriteria(DailyAmountQuery.TABLE_STATUS_TIME,statusTime);
    }

    public void setStatusTime(Opt opt,Date statusTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS_TIME,opt,statusTime);
    }

    public void setStatusTime(Opt opt,List<Date> statusTimeList) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS_TIME,opt,statusTimeList);
    }

    public void setStatusTime(Opt opt,Date statusTime1,Date statusTime2) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS_TIME,opt,statusTime1,statusTime2);
    }

}