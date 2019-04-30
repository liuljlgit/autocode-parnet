package com.cloud.ftl.ftltest.test.query;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;

import java.math.BigDecimal;
import java.util.Date;

public class MediumContractQuery extends BaseQuery {

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


    public static final transient String TABLE_MC_ID = "mc_id";

    public static final transient String TABLE_ENTITY_ID = "entity_id";

    public static final transient String TABLE_CONTRACT_NO = "contract_no";

    public static final transient String TABLE_CONTRACT_NAME = "contract_name";

    public static final transient String TABLE_CONTRACT_TYPE = "contract_type";

    public static final transient String TABLE_CONTRACT_PART = "contract_part";

    public static final transient String TABLE_DEAL_TYPE = "deal_type";

    public static final transient String TABLE_CONTRACT_START_TIME = "contract_start_time";

    public static final transient String TABLE_CONTRACT_END_TIME = "contract_end_time";

    public static final transient String TABLE_DEAL_QUAN = "deal_quan";

    public static final transient String TABLE_CURVE_TYPE = "curve_type";

    public static final transient String TABLE_CHECK_STATUS = "check_status";

    public static final transient String TABLE_STATUS = "status";

    public static final transient String TABLE_CREATE_TIME = "create_time";

    public static final transient String TABLE_STATUS_TIME = "status_time";

    public static final transient String TABLE_CONTRACT_PRICE = "contract_price";


    public Long getMcId() {
        return mcId;
    }

    public void setMcId(Long mcId) throws Exception {
        addCriteria(MediumContractQuery.TABLE_MC_ID,mcId);
        this.mcId = mcId;
    }

    public void setMcId(Opt opt,Long mcId) throws Exception {
        addCriteria(MediumContractQuery.TABLE_MC_ID,mcId,opt);
        this.mcId = mcId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) throws Exception {
        addCriteria(MediumContractQuery.TABLE_ENTITY_ID,entityId);
        this.entityId = entityId;
    }

    public void setEntityId(Opt opt,Integer entityId) throws Exception {
        addCriteria(MediumContractQuery.TABLE_ENTITY_ID,entityId,opt);
        this.entityId = entityId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_NO,contractNo);
        this.contractNo = contractNo;
    }

    public void setContractNo(Opt opt,String contractNo) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_NO,contractNo,opt);
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_NAME,contractName);
        this.contractName = contractName;
    }

    public void setContractName(Opt opt,String contractName) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_NAME,contractName,opt);
        this.contractName = contractName;
    }

    public Byte getContractType() {
        return contractType;
    }

    public void setContractType(Byte contractType) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_TYPE,contractType);
        this.contractType = contractType;
    }

    public void setContractType(Opt opt,Byte contractType) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_TYPE,contractType,opt);
        this.contractType = contractType;
    }

    public String getContractPart() {
        return contractPart;
    }

    public void setContractPart(String contractPart) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_PART,contractPart);
        this.contractPart = contractPart;
    }

    public void setContractPart(Opt opt,String contractPart) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_PART,contractPart,opt);
        this.contractPart = contractPart;
    }

    public Byte getDealType() {
        return dealType;
    }

    public void setDealType(Byte dealType) throws Exception {
        addCriteria(MediumContractQuery.TABLE_DEAL_TYPE,dealType);
        this.dealType = dealType;
    }

    public void setDealType(Opt opt,Byte dealType) throws Exception {
        addCriteria(MediumContractQuery.TABLE_DEAL_TYPE,dealType,opt);
        this.dealType = dealType;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_START_TIME,contractStartTime);
        this.contractStartTime = contractStartTime;
    }

    public void setContractStartTime(Opt opt,Date contractStartTime) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_START_TIME,contractStartTime,opt);
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_END_TIME,contractEndTime);
        this.contractEndTime = contractEndTime;
    }

    public void setContractEndTime(Opt opt,Date contractEndTime) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_END_TIME,contractEndTime,opt);
        this.contractEndTime = contractEndTime;
    }

    public BigDecimal getDealQuan() {
        return dealQuan;
    }

    public void setDealQuan(BigDecimal dealQuan) throws Exception {
        addCriteria(MediumContractQuery.TABLE_DEAL_QUAN,dealQuan);
        this.dealQuan = dealQuan;
    }

    public void setDealQuan(Opt opt,BigDecimal dealQuan) throws Exception {
        addCriteria(MediumContractQuery.TABLE_DEAL_QUAN,dealQuan,opt);
        this.dealQuan = dealQuan;
    }

    public Byte getCurveType() {
        return curveType;
    }

    public void setCurveType(Byte curveType) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CURVE_TYPE,curveType);
        this.curveType = curveType;
    }

    public void setCurveType(Opt opt,Byte curveType) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CURVE_TYPE,curveType,opt);
        this.curveType = curveType;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CHECK_STATUS,checkStatus);
        this.checkStatus = checkStatus;
    }

    public void setCheckStatus(Opt opt,Byte checkStatus) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CHECK_STATUS,checkStatus,opt);
        this.checkStatus = checkStatus;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) throws Exception {
        addCriteria(MediumContractQuery.TABLE_STATUS,status);
        this.status = status;
    }

    public void setStatus(Opt opt,Byte status) throws Exception {
        addCriteria(MediumContractQuery.TABLE_STATUS,status,opt);
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CREATE_TIME,createTime);
        this.createTime = createTime;
    }

    public void setCreateTime(Opt opt,Date createTime) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CREATE_TIME,createTime,opt);
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) throws Exception {
        addCriteria(MediumContractQuery.TABLE_STATUS_TIME,statusTime);
        this.statusTime = statusTime;
    }

    public void setStatusTime(Opt opt,Date statusTime) throws Exception {
        addCriteria(MediumContractQuery.TABLE_STATUS_TIME,statusTime,opt);
        this.statusTime = statusTime;
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_PRICE,contractPrice);
        this.contractPrice = contractPrice;
    }

    public void setContractPrice(Opt opt,BigDecimal contractPrice) throws Exception {
        addCriteria(MediumContractQuery.TABLE_CONTRACT_PRICE,contractPrice,opt);
        this.contractPrice = contractPrice;
    }

}