package com.cloud.ftl.ftltest.test.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.constant.PatternConst;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;

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
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
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
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date createTime;

	@ApiModelProperty("更新时间")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
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


    public void andDaId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(DA_ID,opt);
        } else if(values.length == 1){
            addConditGroup(DA_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(DA_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ DA_ID + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andEntityId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(ENTITY_ID,opt);
        } else if(values.length == 1){
            addConditGroup(ENTITY_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(ENTITY_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ ENTITY_ID + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andDateTime(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(DATE_TIME,opt);
        } else if(values.length == 1){
            addConditGroup(DATE_TIME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(DATE_TIME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ DATE_TIME + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andSettProfit(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(SETT_PROFIT,opt);
        } else if(values.length == 1){
            addConditGroup(SETT_PROFIT,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(SETT_PROFIT,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ SETT_PROFIT + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andExpendProfit(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(EXPEND_PROFIT,opt);
        } else if(values.length == 1){
            addConditGroup(EXPEND_PROFIT,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(EXPEND_PROFIT,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ EXPEND_PROFIT + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andIncomeProfit(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(INCOME_PROFIT,opt);
        } else if(values.length == 1){
            addConditGroup(INCOME_PROFIT,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(INCOME_PROFIT,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ INCOME_PROFIT + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andProfitHours(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(PROFIT_HOURS,opt);
        } else if(values.length == 1){
            addConditGroup(PROFIT_HOURS,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(PROFIT_HOURS,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ PROFIT_HOURS + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andDeficitHours(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(DEFICIT_HOURS,opt);
        } else if(values.length == 1){
            addConditGroup(DEFICIT_HOURS,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(DEFICIT_HOURS,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ DEFICIT_HOURS + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andMinusDeviation(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(MINUS_DEVIATION,opt);
        } else if(values.length == 1){
            addConditGroup(MINUS_DEVIATION,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(MINUS_DEVIATION,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ MINUS_DEVIATION + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andPositiveDeviation(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(POSITIVE_DEVIATION,opt);
        } else if(values.length == 1){
            addConditGroup(POSITIVE_DEVIATION,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(POSITIVE_DEVIATION,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ POSITIVE_DEVIATION + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andStatus(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(STATUS,opt);
        } else if(values.length == 1){
            addConditGroup(STATUS,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(STATUS,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ STATUS + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andCe(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(CE,opt);
        } else if(values.length == 1){
            addConditGroup(CE,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(CE,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ CE + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andSa(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(SA,opt);
        } else if(values.length == 1){
            addConditGroup(SA,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(SA,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ SA + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andCreateTime(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(CREATE_TIME,opt);
        } else if(values.length == 1){
            addConditGroup(CREATE_TIME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(CREATE_TIME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ CREATE_TIME + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andStatusTime(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(STATUS_TIME,opt);
        } else if(values.length == 1){
            addConditGroup(STATUS_TIME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(STATUS_TIME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ STATUS_TIME + "’ 的SQL入参个数不正确 ");
        }
    }

}