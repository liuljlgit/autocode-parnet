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
@ApiModel("BaseHoliday")
public class BaseHoliday extends BaseQuery {

	@ApiModelProperty("主键，没有具体意义")
    @PrimaryKey
    private Long hId;

	@ApiModelProperty("")
    private Integer year;

	@ApiModelProperty("1-法定假日;2-法定周末调班;")
    private Byte holidayType;

	@ApiModelProperty("1-春节；2-国庆；3-三天的小长假")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date beginDate;

	@ApiModelProperty("1-春节；2-国庆；3-三天的小长假")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date endDate;

	@ApiModelProperty("")
    private String remark;

	@ApiModelProperty("状态")
    private Byte status;

	@ApiModelProperty("")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date createTime;

	@ApiModelProperty("")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date statusTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String H_ID = "h_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String YEAR = "year";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String HOLIDAY_TYPE = "holiday_type";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String BEGIN_DATE = "begin_date";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String END_DATE = "end_date";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String REMARK = "remark";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS = "status";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS_TIME = "status_time";


    public void andHId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(H_ID,opt);
        } else if(values.length == 1){
            addConditGroup(H_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(H_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ H_ID + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andYear(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(YEAR,opt);
        } else if(values.length == 1){
            addConditGroup(YEAR,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(YEAR,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ YEAR + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andHolidayType(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(HOLIDAY_TYPE,opt);
        } else if(values.length == 1){
            addConditGroup(HOLIDAY_TYPE,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(HOLIDAY_TYPE,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ HOLIDAY_TYPE + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andBeginDate(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(BEGIN_DATE,opt);
        } else if(values.length == 1){
            addConditGroup(BEGIN_DATE,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(BEGIN_DATE,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ BEGIN_DATE + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andEndDate(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(END_DATE,opt);
        } else if(values.length == 1){
            addConditGroup(END_DATE,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(END_DATE,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ END_DATE + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andRemark(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(REMARK,opt);
        } else if(values.length == 1){
            addConditGroup(REMARK,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(REMARK,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ REMARK + "’ 的SQL入参个数不正确 ");
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