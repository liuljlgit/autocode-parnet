package com.cloud.ftl.ftltest.test.entity;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class DailyAmount extends BaseEntity{

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


    public static final transient String PROP_DA_ID = "daId";

    public static final transient String PROP_ENTITY_ID = "entityId";

    public static final transient String PROP_DATE_TIME = "dateTime";

    public static final transient String PROP_SETT_PROFIT = "settProfit";

    public static final transient String PROP_EXPEND_PROFIT = "expendProfit";

    public static final transient String PROP_INCOME_PROFIT = "incomeProfit";

    public static final transient String PROP_PROFIT_HOURS = "profitHours";

    public static final transient String PROP_DEFICIT_HOURS = "deficitHours";

    public static final transient String PROP_MINUS_DEVIATION = "minusDeviation";

    public static final transient String PROP_POSITIVE_DEVIATION = "positiveDeviation";

    public static final transient String PROP_STATUS = "status";

    public static final transient String PROP_CE = "ce";

    public static final transient String PROP_SA = "sa";

    public static final transient String PROP_CREATE_TIME = "createTime";

    public static final transient String PROP_STATUS_TIME = "statusTime";


    public Long getDaId() {
        return daId;
    }

    public void setDaId(Long daId) throws Exception {
        addCriteria("da_id",daId);
        this.daId = daId;
    }

    public void setDaId(Opt opt, Long daId) throws Exception {
        addCriteria("da_id",daId,opt);
        this.daId = daId;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) throws Exception {
        addCriteria("date_time",dateTime);
        this.dateTime = dateTime;
    }

    public void setDateTime(Opt opt,Date dateTime) throws Exception {
        addCriteria("date_time",dateTime,opt);
        this.dateTime = dateTime;
    }

    public BigDecimal getSettProfit() {
        return settProfit;
    }

    public void setSettProfit(BigDecimal settProfit) throws Exception {
        addCriteria("sett_profit",settProfit);
        this.settProfit = settProfit;
    }

    public void setSettProfit(Opt opt,BigDecimal settProfit) throws Exception {
        addCriteria("sett_profit",settProfit,opt);
        this.settProfit = settProfit;
    }

    public BigDecimal getExpendProfit() {
        return expendProfit;
    }

    public void setExpendProfit(BigDecimal expendProfit) throws Exception {
        addCriteria("expend_profit",expendProfit);
        this.expendProfit = expendProfit;
    }

    public void setExpendProfit(Opt opt,BigDecimal expendProfit) throws Exception {
        addCriteria("expend_profit",expendProfit,opt);
        this.expendProfit = expendProfit;
    }

    public BigDecimal getIncomeProfit() {
        return incomeProfit;
    }

    public void setIncomeProfit(BigDecimal incomeProfit) throws Exception {
        addCriteria("income_profit",incomeProfit);
        this.incomeProfit = incomeProfit;
    }

    public void setIncomeProfit(Opt opt,BigDecimal incomeProfit) throws Exception {
        addCriteria("income_profit",incomeProfit,opt);
        this.incomeProfit = incomeProfit;
    }

    public Byte getProfitHours() {
        return profitHours;
    }

    public void setProfitHours(Byte profitHours) throws Exception {
        addCriteria("profit_hours",profitHours);
        this.profitHours = profitHours;
    }

    public void setProfitHours(Opt opt,Byte profitHours) throws Exception {
        addCriteria("profit_hours",profitHours,opt);
        this.profitHours = profitHours;
    }

    public Byte getDeficitHours() {
        return deficitHours;
    }

    public void setDeficitHours(Byte deficitHours) throws Exception {
        addCriteria("deficit_hours",deficitHours);
        this.deficitHours = deficitHours;
    }

    public void setDeficitHours(Opt opt,Byte deficitHours) throws Exception {
        addCriteria("deficit_hours",deficitHours,opt);
        this.deficitHours = deficitHours;
    }

    public BigDecimal getMinusDeviation() {
        return minusDeviation;
    }

    public void setMinusDeviation(BigDecimal minusDeviation) throws Exception {
        addCriteria("minus_deviation",minusDeviation);
        this.minusDeviation = minusDeviation;
    }

    public void setMinusDeviation(Opt opt,BigDecimal minusDeviation) throws Exception {
        addCriteria("minus_deviation",minusDeviation,opt);
        this.minusDeviation = minusDeviation;
    }

    public BigDecimal getPositiveDeviation() {
        return positiveDeviation;
    }

    public void setPositiveDeviation(BigDecimal positiveDeviation) throws Exception {
        addCriteria("positive_deviation",positiveDeviation);
        this.positiveDeviation = positiveDeviation;
    }

    public void setPositiveDeviation(Opt opt,BigDecimal positiveDeviation) throws Exception {
        addCriteria("positive_deviation",positiveDeviation,opt);
        this.positiveDeviation = positiveDeviation;
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

    public Byte getCe() {
        return ce;
    }

    public void setCe(Byte ce) throws Exception {
        addCriteria("ce",ce);
        this.ce = ce;
    }

    public void setCe(Opt opt,Byte ce) throws Exception {
        addCriteria("ce",ce,opt);
        this.ce = ce;
    }

    public Byte getSa() {
        return sa;
    }

    public void setSa(Byte sa) throws Exception {
        addCriteria("sa",sa);
        this.sa = sa;
    }

    public void setSa(Opt opt,Byte sa) throws Exception {
        addCriteria("sa",sa,opt);
        this.sa = sa;
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