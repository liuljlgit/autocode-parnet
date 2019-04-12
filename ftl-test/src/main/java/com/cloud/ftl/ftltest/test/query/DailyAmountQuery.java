package com.cloud.ftl.ftltest.test.query;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;

import java.math.BigDecimal;
import java.util.Date;

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


    public Long getDaId() {
        return daId;
    }

    public void setDaId(Long daId) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DA_ID,daId);
        this.daId = daId;
    }

    public void setDaId(Opt opt,Long daId) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DA_ID,daId,opt);
        this.daId = daId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_ENTITY_ID,entityId);
        this.entityId = entityId;
    }

    public void setEntityId(Opt opt,Integer entityId) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_ENTITY_ID,entityId,opt);
        this.entityId = entityId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DATE_TIME,dateTime);
        this.dateTime = dateTime;
    }

    public void setDateTime(Opt opt,Date dateTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DATE_TIME,dateTime,opt);
        this.dateTime = dateTime;
    }

    public BigDecimal getSettProfit() {
        return settProfit;
    }

    public void setSettProfit(BigDecimal settProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SETT_PROFIT,settProfit);
        this.settProfit = settProfit;
    }

    public void setSettProfit(Opt opt,BigDecimal settProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SETT_PROFIT,settProfit,opt);
        this.settProfit = settProfit;
    }

    public BigDecimal getExpendProfit() {
        return expendProfit;
    }

    public void setExpendProfit(BigDecimal expendProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_EXPEND_PROFIT,expendProfit);
        this.expendProfit = expendProfit;
    }

    public void setExpendProfit(Opt opt,BigDecimal expendProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_EXPEND_PROFIT,expendProfit,opt);
        this.expendProfit = expendProfit;
    }

    public BigDecimal getIncomeProfit() {
        return incomeProfit;
    }

    public void setIncomeProfit(BigDecimal incomeProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_INCOME_PROFIT,incomeProfit);
        this.incomeProfit = incomeProfit;
    }

    public void setIncomeProfit(Opt opt,BigDecimal incomeProfit) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_INCOME_PROFIT,incomeProfit,opt);
        this.incomeProfit = incomeProfit;
    }

    public Byte getProfitHours() {
        return profitHours;
    }

    public void setProfitHours(Byte profitHours) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_PROFIT_HOURS,profitHours);
        this.profitHours = profitHours;
    }

    public void setProfitHours(Opt opt,Byte profitHours) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_PROFIT_HOURS,profitHours,opt);
        this.profitHours = profitHours;
    }

    public Byte getDeficitHours() {
        return deficitHours;
    }

    public void setDeficitHours(Byte deficitHours) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DEFICIT_HOURS,deficitHours);
        this.deficitHours = deficitHours;
    }

    public void setDeficitHours(Opt opt,Byte deficitHours) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_DEFICIT_HOURS,deficitHours,opt);
        this.deficitHours = deficitHours;
    }

    public BigDecimal getMinusDeviation() {
        return minusDeviation;
    }

    public void setMinusDeviation(BigDecimal minusDeviation) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_MINUS_DEVIATION,minusDeviation);
        this.minusDeviation = minusDeviation;
    }

    public void setMinusDeviation(Opt opt,BigDecimal minusDeviation) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_MINUS_DEVIATION,minusDeviation,opt);
        this.minusDeviation = minusDeviation;
    }

    public BigDecimal getPositiveDeviation() {
        return positiveDeviation;
    }

    public void setPositiveDeviation(BigDecimal positiveDeviation) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_POSITIVE_DEVIATION,positiveDeviation);
        this.positiveDeviation = positiveDeviation;
    }

    public void setPositiveDeviation(Opt opt,BigDecimal positiveDeviation) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_POSITIVE_DEVIATION,positiveDeviation,opt);
        this.positiveDeviation = positiveDeviation;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS,status);
        this.status = status;
    }

    public void setStatus(Opt opt,Byte status) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS,status,opt);
        this.status = status;
    }

    public Byte getCe() {
        return ce;
    }

    public void setCe(Byte ce) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CE,ce);
        this.ce = ce;
    }

    public void setCe(Opt opt,Byte ce) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CE,ce,opt);
        this.ce = ce;
    }

    public Byte getSa() {
        return sa;
    }

    public void setSa(Byte sa) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SA,sa);
        this.sa = sa;
    }

    public void setSa(Opt opt,Byte sa) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_SA,sa,opt);
        this.sa = sa;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CREATE_TIME,createTime);
        this.createTime = createTime;
    }

    public void setCreateTime(Opt opt,Date createTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_CREATE_TIME,createTime,opt);
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS_TIME,statusTime);
        this.statusTime = statusTime;
    }

    public void setStatusTime(Opt opt,Date statusTime) throws Exception {
        addCriteria(DailyAmountQuery.TABLE_STATUS_TIME,statusTime,opt);
        this.statusTime = statusTime;
    }

}