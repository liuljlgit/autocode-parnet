package com.cloud.ftl.ftltest.test.entity;

import com.cloud.ftl.ftlbasic.aspect.PrimaryKey;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("DailyAmount")
public class DailyAmount extends BaseQuery {

	@ApiModelProperty("日前报量管理ID")
    @PrimaryKey
    private Long daId;

	@ApiModelProperty("电企业ID：来源于cloud_sys.entity.entity_id")
    private Integer entityId;

	@ApiModelProperty("用电时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dateTime;

	@ApiModelProperty("结算盈利")
    private BigDecimal settProfit;

	@ApiModelProperty("支出金额")
    private BigDecimal expendProfit;

	@ApiModelProperty("收入金额")
    private BigDecimal incomeProfit;

	@ApiModelProperty("盈利小时数")
    private Byte profitHours;

	@ApiModelProperty("亏损小时数")
    private Byte deficitHours;

	@ApiModelProperty("当天最大负偏差率(%)")
    private BigDecimal minusDeviation;

	@ApiModelProperty("当天最大正偏差率(%)")
    private BigDecimal positiveDeviation;

	@ApiModelProperty("状态 (0) 弃用 （1）正常")
    private Byte status;

	@ApiModelProperty("")
    private Byte ce;

	@ApiModelProperty("")
    private Byte sa;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

	@ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date statusTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String DA_ID = "da_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ENTITY_ID = "entity_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String DATE_TIME = "date_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String SETT_PROFIT = "sett_profit";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String EXPEND_PROFIT = "expend_profit";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String INCOME_PROFIT = "income_profit";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String PROFIT_HOURS = "profit_hours";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String DEFICIT_HOURS = "deficit_hours";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String MINUS_DEVIATION = "minus_deviation";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String POSITIVE_DEVIATION = "positive_deviation";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS = "status";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CE = "ce";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String SA = "sa";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS_TIME = "status_time";


    public void andDaId(Opt opt) {
        addConditGroup(DA_ID,opt);
    }

    public void andDaId(Opt opt,Long daId) {
        addConditGroup(DA_ID,opt,daId);
    }

    public void andDaId(Opt opt,List<Long> list) {
        addConditGroup(DA_ID,opt,list);
    }

    public void andDaId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(DA_ID,opt,firstParam,secondParam);
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

    public void andDateTime(Opt opt) {
        addConditGroup(DATE_TIME,opt);
    }

    public void andDateTime(Opt opt,Date dateTime) {
        addConditGroup(DATE_TIME,opt,dateTime);
    }

    public void andDateTime(Opt opt,List<Date> list) {
        addConditGroup(DATE_TIME,opt,list);
    }

    public void andDateTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(DATE_TIME,opt,firstParam,secondParam);
    }

    public void andSettProfit(Opt opt) {
        addConditGroup(SETT_PROFIT,opt);
    }

    public void andSettProfit(Opt opt,BigDecimal settProfit) {
        addConditGroup(SETT_PROFIT,opt,settProfit);
    }

    public void andSettProfit(Opt opt,List<BigDecimal> list) {
        addConditGroup(SETT_PROFIT,opt,list);
    }

    public void andSettProfit(Opt opt,BigDecimal firstParam,BigDecimal secondParam) {
        addConditGroup(SETT_PROFIT,opt,firstParam,secondParam);
    }

    public void andExpendProfit(Opt opt) {
        addConditGroup(EXPEND_PROFIT,opt);
    }

    public void andExpendProfit(Opt opt,BigDecimal expendProfit) {
        addConditGroup(EXPEND_PROFIT,opt,expendProfit);
    }

    public void andExpendProfit(Opt opt,List<BigDecimal> list) {
        addConditGroup(EXPEND_PROFIT,opt,list);
    }

    public void andExpendProfit(Opt opt,BigDecimal firstParam,BigDecimal secondParam) {
        addConditGroup(EXPEND_PROFIT,opt,firstParam,secondParam);
    }

    public void andIncomeProfit(Opt opt) {
        addConditGroup(INCOME_PROFIT,opt);
    }

    public void andIncomeProfit(Opt opt,BigDecimal incomeProfit) {
        addConditGroup(INCOME_PROFIT,opt,incomeProfit);
    }

    public void andIncomeProfit(Opt opt,List<BigDecimal> list) {
        addConditGroup(INCOME_PROFIT,opt,list);
    }

    public void andIncomeProfit(Opt opt,BigDecimal firstParam,BigDecimal secondParam) {
        addConditGroup(INCOME_PROFIT,opt,firstParam,secondParam);
    }

    public void andProfitHours(Opt opt) {
        addConditGroup(PROFIT_HOURS,opt);
    }

    public void andProfitHours(Opt opt,Byte profitHours) {
        addConditGroup(PROFIT_HOURS,opt,profitHours);
    }

    public void andProfitHours(Opt opt,List<Byte> list) {
        addConditGroup(PROFIT_HOURS,opt,list);
    }

    public void andProfitHours(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(PROFIT_HOURS,opt,firstParam,secondParam);
    }

    public void andDeficitHours(Opt opt) {
        addConditGroup(DEFICIT_HOURS,opt);
    }

    public void andDeficitHours(Opt opt,Byte deficitHours) {
        addConditGroup(DEFICIT_HOURS,opt,deficitHours);
    }

    public void andDeficitHours(Opt opt,List<Byte> list) {
        addConditGroup(DEFICIT_HOURS,opt,list);
    }

    public void andDeficitHours(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(DEFICIT_HOURS,opt,firstParam,secondParam);
    }

    public void andMinusDeviation(Opt opt) {
        addConditGroup(MINUS_DEVIATION,opt);
    }

    public void andMinusDeviation(Opt opt,BigDecimal minusDeviation) {
        addConditGroup(MINUS_DEVIATION,opt,minusDeviation);
    }

    public void andMinusDeviation(Opt opt,List<BigDecimal> list) {
        addConditGroup(MINUS_DEVIATION,opt,list);
    }

    public void andMinusDeviation(Opt opt,BigDecimal firstParam,BigDecimal secondParam) {
        addConditGroup(MINUS_DEVIATION,opt,firstParam,secondParam);
    }

    public void andPositiveDeviation(Opt opt) {
        addConditGroup(POSITIVE_DEVIATION,opt);
    }

    public void andPositiveDeviation(Opt opt,BigDecimal positiveDeviation) {
        addConditGroup(POSITIVE_DEVIATION,opt,positiveDeviation);
    }

    public void andPositiveDeviation(Opt opt,List<BigDecimal> list) {
        addConditGroup(POSITIVE_DEVIATION,opt,list);
    }

    public void andPositiveDeviation(Opt opt,BigDecimal firstParam,BigDecimal secondParam) {
        addConditGroup(POSITIVE_DEVIATION,opt,firstParam,secondParam);
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

    public void andCe(Opt opt) {
        addConditGroup(CE,opt);
    }

    public void andCe(Opt opt,Byte ce) {
        addConditGroup(CE,opt,ce);
    }

    public void andCe(Opt opt,List<Byte> list) {
        addConditGroup(CE,opt,list);
    }

    public void andCe(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(CE,opt,firstParam,secondParam);
    }

    public void andSa(Opt opt) {
        addConditGroup(SA,opt);
    }

    public void andSa(Opt opt,Byte sa) {
        addConditGroup(SA,opt,sa);
    }

    public void andSa(Opt opt,List<Byte> list) {
        addConditGroup(SA,opt,list);
    }

    public void andSa(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(SA,opt,firstParam,secondParam);
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