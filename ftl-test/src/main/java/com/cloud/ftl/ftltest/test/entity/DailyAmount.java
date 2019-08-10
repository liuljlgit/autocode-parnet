package com.cloud.ftl.ftltest.test.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;
import lombok.Data;
import com.cloud.ftl.ftltest.test.constant.DailyAmountTable;

@Data
public class DailyAmount extends BaseQuery {

    public static Map<String,String> map = DailyAmountTable.map;

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


    public void andDaId(Opt opt,Long daId) throws Exception {
        addCriteria(DailyAmountTable.DA_ID,opt,daId);
    }

    public void andDaId(Opt opt,List<Long> daIdList) throws Exception {
        addCriteria(DailyAmountTable.DA_ID,opt,daIdList);
    }

    public void andDaId(Opt opt,Long daId1,Long daId2) throws Exception {
        addCriteria(DailyAmountTable.DA_ID,opt,daId1,daId2);
    }

    public void andEntityId(Opt opt,Integer entityId) throws Exception {
        addCriteria(DailyAmountTable.ENTITY_ID,opt,entityId);
    }

    public void andEntityId(Opt opt,List<Integer> entityIdList) throws Exception {
        addCriteria(DailyAmountTable.ENTITY_ID,opt,entityIdList);
    }

    public void andEntityId(Opt opt,Integer entityId1,Integer entityId2) throws Exception {
        addCriteria(DailyAmountTable.ENTITY_ID,opt,entityId1,entityId2);
    }

    public void andDateTime(Opt opt,Date dateTime) throws Exception {
        addCriteria(DailyAmountTable.DATE_TIME,opt,dateTime);
    }

    public void andDateTime(Opt opt,List<Date> dateTimeList) throws Exception {
        addCriteria(DailyAmountTable.DATE_TIME,opt,dateTimeList);
    }

    public void andDateTime(Opt opt,Date dateTime1,Date dateTime2) throws Exception {
        addCriteria(DailyAmountTable.DATE_TIME,opt,dateTime1,dateTime2);
    }

    public void andSettProfit(Opt opt,BigDecimal settProfit) throws Exception {
        addCriteria(DailyAmountTable.SETT_PROFIT,opt,settProfit);
    }

    public void andSettProfit(Opt opt,List<BigDecimal> settProfitList) throws Exception {
        addCriteria(DailyAmountTable.SETT_PROFIT,opt,settProfitList);
    }

    public void andSettProfit(Opt opt,BigDecimal settProfit1,BigDecimal settProfit2) throws Exception {
        addCriteria(DailyAmountTable.SETT_PROFIT,opt,settProfit1,settProfit2);
    }

    public void andExpendProfit(Opt opt,BigDecimal expendProfit) throws Exception {
        addCriteria(DailyAmountTable.EXPEND_PROFIT,opt,expendProfit);
    }

    public void andExpendProfit(Opt opt,List<BigDecimal> expendProfitList) throws Exception {
        addCriteria(DailyAmountTable.EXPEND_PROFIT,opt,expendProfitList);
    }

    public void andExpendProfit(Opt opt,BigDecimal expendProfit1,BigDecimal expendProfit2) throws Exception {
        addCriteria(DailyAmountTable.EXPEND_PROFIT,opt,expendProfit1,expendProfit2);
    }

    public void andIncomeProfit(Opt opt,BigDecimal incomeProfit) throws Exception {
        addCriteria(DailyAmountTable.INCOME_PROFIT,opt,incomeProfit);
    }

    public void andIncomeProfit(Opt opt,List<BigDecimal> incomeProfitList) throws Exception {
        addCriteria(DailyAmountTable.INCOME_PROFIT,opt,incomeProfitList);
    }

    public void andIncomeProfit(Opt opt,BigDecimal incomeProfit1,BigDecimal incomeProfit2) throws Exception {
        addCriteria(DailyAmountTable.INCOME_PROFIT,opt,incomeProfit1,incomeProfit2);
    }

    public void andProfitHours(Opt opt,Byte profitHours) throws Exception {
        addCriteria(DailyAmountTable.PROFIT_HOURS,opt,profitHours);
    }

    public void andProfitHours(Opt opt,List<Byte> profitHoursList) throws Exception {
        addCriteria(DailyAmountTable.PROFIT_HOURS,opt,profitHoursList);
    }

    public void andProfitHours(Opt opt,Byte profitHours1,Byte profitHours2) throws Exception {
        addCriteria(DailyAmountTable.PROFIT_HOURS,opt,profitHours1,profitHours2);
    }

    public void andDeficitHours(Opt opt,Byte deficitHours) throws Exception {
        addCriteria(DailyAmountTable.DEFICIT_HOURS,opt,deficitHours);
    }

    public void andDeficitHours(Opt opt,List<Byte> deficitHoursList) throws Exception {
        addCriteria(DailyAmountTable.DEFICIT_HOURS,opt,deficitHoursList);
    }

    public void andDeficitHours(Opt opt,Byte deficitHours1,Byte deficitHours2) throws Exception {
        addCriteria(DailyAmountTable.DEFICIT_HOURS,opt,deficitHours1,deficitHours2);
    }

    public void andMinusDeviation(Opt opt,BigDecimal minusDeviation) throws Exception {
        addCriteria(DailyAmountTable.MINUS_DEVIATION,opt,minusDeviation);
    }

    public void andMinusDeviation(Opt opt,List<BigDecimal> minusDeviationList) throws Exception {
        addCriteria(DailyAmountTable.MINUS_DEVIATION,opt,minusDeviationList);
    }

    public void andMinusDeviation(Opt opt,BigDecimal minusDeviation1,BigDecimal minusDeviation2) throws Exception {
        addCriteria(DailyAmountTable.MINUS_DEVIATION,opt,minusDeviation1,minusDeviation2);
    }

    public void andPositiveDeviation(Opt opt,BigDecimal positiveDeviation) throws Exception {
        addCriteria(DailyAmountTable.POSITIVE_DEVIATION,opt,positiveDeviation);
    }

    public void andPositiveDeviation(Opt opt,List<BigDecimal> positiveDeviationList) throws Exception {
        addCriteria(DailyAmountTable.POSITIVE_DEVIATION,opt,positiveDeviationList);
    }

    public void andPositiveDeviation(Opt opt,BigDecimal positiveDeviation1,BigDecimal positiveDeviation2) throws Exception {
        addCriteria(DailyAmountTable.POSITIVE_DEVIATION,opt,positiveDeviation1,positiveDeviation2);
    }

    public void andStatus(Opt opt,Byte status) throws Exception {
        addCriteria(DailyAmountTable.STATUS,opt,status);
    }

    public void andStatus(Opt opt,List<Byte> statusList) throws Exception {
        addCriteria(DailyAmountTable.STATUS,opt,statusList);
    }

    public void andStatus(Opt opt,Byte status1,Byte status2) throws Exception {
        addCriteria(DailyAmountTable.STATUS,opt,status1,status2);
    }

    public void andCe(Opt opt,Byte ce) throws Exception {
        addCriteria(DailyAmountTable.CE,opt,ce);
    }

    public void andCe(Opt opt,List<Byte> ceList) throws Exception {
        addCriteria(DailyAmountTable.CE,opt,ceList);
    }

    public void andCe(Opt opt,Byte ce1,Byte ce2) throws Exception {
        addCriteria(DailyAmountTable.CE,opt,ce1,ce2);
    }

    public void andSa(Opt opt,Byte sa) throws Exception {
        addCriteria(DailyAmountTable.SA,opt,sa);
    }

    public void andSa(Opt opt,List<Byte> saList) throws Exception {
        addCriteria(DailyAmountTable.SA,opt,saList);
    }

    public void andSa(Opt opt,Byte sa1,Byte sa2) throws Exception {
        addCriteria(DailyAmountTable.SA,opt,sa1,sa2);
    }

    public void andCreateTime(Opt opt,Date createTime) throws Exception {
        addCriteria(DailyAmountTable.CREATE_TIME,opt,createTime);
    }

    public void andCreateTime(Opt opt,List<Date> createTimeList) throws Exception {
        addCriteria(DailyAmountTable.CREATE_TIME,opt,createTimeList);
    }

    public void andCreateTime(Opt opt,Date createTime1,Date createTime2) throws Exception {
        addCriteria(DailyAmountTable.CREATE_TIME,opt,createTime1,createTime2);
    }

    public void andStatusTime(Opt opt,Date statusTime) throws Exception {
        addCriteria(DailyAmountTable.STATUS_TIME,opt,statusTime);
    }

    public void andStatusTime(Opt opt,List<Date> statusTimeList) throws Exception {
        addCriteria(DailyAmountTable.STATUS_TIME,opt,statusTimeList);
    }

    public void andStatusTime(Opt opt,Date statusTime1,Date statusTime2) throws Exception {
        addCriteria(DailyAmountTable.STATUS_TIME,opt,statusTime1,statusTime2);
    }

}