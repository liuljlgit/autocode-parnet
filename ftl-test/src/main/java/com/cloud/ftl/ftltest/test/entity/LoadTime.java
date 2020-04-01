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
@ApiModel("LoadTime")
public class LoadTime extends BaseQuery {

	@ApiModelProperty("实际负荷导入数据记录ID")
    @PrimaryKey
    private Long ltId;

	@ApiModelProperty("电企业ID：来源于cloud_sys.entity.entity_id")
    private Integer entityId;

	@ApiModelProperty("导入开始时间")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date startTime;

	@ApiModelProperty("导入结束时间")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date endTime;

	@ApiModelProperty("状态 (0) 未处理 （1）已处理")
    private Byte status;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date createTime;

	@ApiModelProperty("更新时间")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date statusTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String LT_ID = "lt_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ENTITY_ID = "entity_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String START_TIME = "start_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String END_TIME = "end_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS = "status";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS_TIME = "status_time";


    public void andLtId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(LT_ID,opt);
        } else if(values.length == 1){
            addConditGroup(LT_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(LT_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ LT_ID + "’ 的SQL入参个数不正确 ");
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

    public void andStartTime(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(START_TIME,opt);
        } else if(values.length == 1){
            addConditGroup(START_TIME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(START_TIME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ START_TIME + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andEndTime(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(END_TIME,opt);
        } else if(values.length == 1){
            addConditGroup(END_TIME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(END_TIME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ END_TIME + "’ 的SQL入参个数不正确 ");
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