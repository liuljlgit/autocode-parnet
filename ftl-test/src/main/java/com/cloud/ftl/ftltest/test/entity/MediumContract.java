package com.cloud.ftl.ftltest.test.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BasePage;
import java.io.Serializable;

public class MediumContract extends BasePage implements Serializable {

    /**
     * field comment:中长期合约id
     */
	private Long mcId;

    /**
     * field comment:企业id
     */
	private Integer entityId;

    /**
     * field comment:合同编号
     */
	private String contractNo;

    /**
     * field comment:合同名称
     */
	private String contractName;

    /**
     * field comment:合约类型：1.购电合约 2.售电合约
     */
	private Byte contractType;

    /**
     * field comment:交易方
     */
	private String contractPart;

    /**
     * field comment:交易方类型。1：发电企业，2：售电公司
     */
	private Byte dealType;

    /**
     * field comment:合同开始时间。YYYY-MM-dd HH:mm:ss
     */
	private Date contractStartTime;

    /**
     * field comment:合同结束时间。YYYY-MM-dd HH:mm:ss
     */
	private Date contractEndTime;

    /**
     * field comment:交易电量
     */
	private BigDecimal dealQuan;

    /**
     * field comment:曲线类型：1.自定义曲线 2.典型曲线-峰平谷分解曲线 3.典型曲线-全天平均曲线 4.典型曲线-高峰曲线
     */
	private Byte curveType;

    /**
     * field comment:审核状态：1.待审核 2.待备案 3.已备案
     */
	private Byte checkStatus;

    /**
     * field comment:交易品种：1.双边协商交易  2.挂牌交易  3.典型合约集中交易（年度）  4..典型合约集中交易（月度）  5.典型合约集中交易（周度）
     */
	private Byte tradeBreed;

    /**
     * field comment:签约状态。0：未签约，1：已签约
     */
	private Byte signType;

    /**
     * field comment:状态 (0) 弃用 （1）正常
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

    /**
     * field comment:交易价格
     */
	private BigDecimal contractPrice;


    public static final transient String PROP_MC_ID = "mcId";

    public static final transient String PROP_ENTITY_ID = "entityId";

    public static final transient String PROP_CONTRACT_NO = "contractNo";

    public static final transient String PROP_CONTRACT_NAME = "contractName";

    public static final transient String PROP_CONTRACT_TYPE = "contractType";

    public static final transient String PROP_CONTRACT_PART = "contractPart";

    public static final transient String PROP_DEAL_TYPE = "dealType";

    public static final transient String PROP_CONTRACT_START_TIME = "contractStartTime";

    public static final transient String PROP_CONTRACT_END_TIME = "contractEndTime";

    public static final transient String PROP_DEAL_QUAN = "dealQuan";

    public static final transient String PROP_CURVE_TYPE = "curveType";

    public static final transient String PROP_CHECK_STATUS = "checkStatus";

    public static final transient String PROP_TRADE_BREED = "tradeBreed";

    public static final transient String PROP_SIGN_TYPE = "signType";

    public static final transient String PROP_STATUS = "status";

    public static final transient String PROP_CREATE_TIME = "createTime";

    public static final transient String PROP_STATUS_TIME = "statusTime";

    public static final transient String PROP_CONTRACT_PRICE = "contractPrice";


    public Long getMcId() {
        return mcId;
    }

    public void setMcId(Long mcId) {
        this.mcId = mcId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Byte getContractType() {
        return contractType;
    }

    public void setContractType(Byte contractType) {
        this.contractType = contractType;
    }

    public String getContractPart() {
        return contractPart;
    }

    public void setContractPart(String contractPart) {
        this.contractPart = contractPart;
    }

    public Byte getDealType() {
        return dealType;
    }

    public void setDealType(Byte dealType) {
        this.dealType = dealType;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public BigDecimal getDealQuan() {
        return dealQuan;
    }

    public void setDealQuan(BigDecimal dealQuan) {
        this.dealQuan = dealQuan;
    }

    public Byte getCurveType() {
        return curveType;
    }

    public void setCurveType(Byte curveType) {
        this.curveType = curveType;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Byte getTradeBreed() {
        return tradeBreed;
    }

    public void setTradeBreed(Byte tradeBreed) {
        this.tradeBreed = tradeBreed;
    }

    public Byte getSignType() {
        return signType;
    }

    public void setSignType(Byte signType) {
        this.signType = signType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

}