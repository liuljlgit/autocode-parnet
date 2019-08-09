package com.cloud.ftl.ftlautocode.req;

import java.util.List;

/**
 * 入参
 */
public class GenReq {

    /**
     * mysql路径地址
     * "jdbc:mysql://localhost:3306/test"
     */
    private String mySqlUrl;

    /**
     * mysql数据库用户名
     * "root"
     */
    private String mySqlName;

    /**
     * mysql数据库密码
     * "root"
     */
    private String mySqlPass;

    /**
     * mysql驱动
     * "com.mysql.jdbc.Driver"
     */
    private String mySqlDriver;

    /**
     * 设定文件生成的路径
     * "E:\\myProject\\ftl-autocode"
     */
    private String projectBasePath;

    /**
     * 生成控制层文件的包路径
     * "com.cloud.gen.ftlautocode.test.controller"
     */
    private String ctrlPath;

    /**
     * 生成service接口文件的包路径
     * "com.cloud.gen.ftlautocode.test.service.inft"
     */
    private String inftServicePath;

    /**
     * 生成service实现文件的包路径
     * "com.cloud.gen.ftlautocode.test.service.impl"
     */
    private String implServicePath;

    /**
     * 生成redis接口文件的包路径
     * "com.cloud.gen.ftlautocode.test.cache.inft"
     */
    private String inftRedisPath;

    /**
     * 生成redis实现文件的包路径
     * "com.cloud.gen.ftlautocode.test.cache.impl"
     */
    private String implRedisPath;

    /**
     * 生成dao文件的包路径
     * "com.cloud.gen.ftlautocode.test.dao"
     */
    private String daoPath;

    /**
     * 生成实体文件的包路径
     * "com.cloud.gen.ftlautocode.test.entity"
     */
    private String entityPath;

    /**
     * 生成查询实体文件的包路径
     * "com.cloud.gen.ftlautocode.test.queryentity"
     */
    private String queryEntityPath;

    /**
     * 生成请求实体文件的包路径
     * "com.cloud.gen.ftlautocode.test.webentity.req"
     */
    private String reqPath;

    /**
     * 生成返回实体文件的包路径
     * "com.cloud.gen.ftlautocode.test.webentity.resp"
     */
    private String respPath;

    /**
     * 生成xml文件的目录路径
     * "mybatis.mapper.test";
     */
    private String tableConstPath;

    /**
     * 生成xml文件的目录路径
     * "mybatis.mapper.test";
     */
    private String xmlPath;

    /**
     * 是否更新文件
     * 1.如果是，那么是customcode里面的代码将不会被替换
     * 2.如果不是，将会生成新的文件
     */
    private Boolean update = Boolean.FALSE;

    /**
     * 表名称数组
     * ["aTable","bTable"]
     */
    private List<String> tableNames;

    public String getMySqlUrl() {
        return mySqlUrl;
    }

    public void setMySqlUrl(String mySqlUrl) {
        this.mySqlUrl = mySqlUrl;
    }

    public String getMySqlName() {
        return mySqlName;
    }

    public void setMySqlName(String mySqlName) {
        this.mySqlName = mySqlName;
    }

    public String getMySqlPass() {
        return mySqlPass;
    }

    public void setMySqlPass(String mySqlPass) {
        this.mySqlPass = mySqlPass;
    }

    public String getMySqlDriver() {
        return mySqlDriver;
    }

    public void setMySqlDriver(String mySqlDriver) {
        this.mySqlDriver = mySqlDriver;
    }

    public String getProjectBasePath() {
        return projectBasePath;
    }

    public void setProjectBasePath(String projectBasePath) {
        this.projectBasePath = projectBasePath;
    }

    public String getCtrlPath() {
        return ctrlPath;
    }

    public void setCtrlPath(String ctrlPath) {
        this.ctrlPath = ctrlPath;
    }

    public String getInftServicePath() {
        return inftServicePath;
    }

    public void setInftServicePath(String inftServicePath) {
        this.inftServicePath = inftServicePath;
    }

    public String getImplServicePath() {
        return implServicePath;
    }

    public void setImplServicePath(String implServicePath) {
        this.implServicePath = implServicePath;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    public String getInftRedisPath() {
        return inftRedisPath;
    }

    public void setInftRedisPath(String inftRedisPath) {
        this.inftRedisPath = inftRedisPath;
    }

    public String getImplRedisPath() {
        return implRedisPath;
    }

    public void setImplRedisPath(String implRedisPath) {
        this.implRedisPath = implRedisPath;
    }

    public String getReqPath() {
        return reqPath;
    }

    public void setReqPath(String reqPath) {
        this.reqPath = reqPath;
    }

    public String getRespPath() {
        return respPath;
    }

    public void setRespPath(String respPath) {
        this.respPath = respPath;
    }

    public String getQueryEntityPath() {
        return queryEntityPath;
    }

    public void setQueryEntityPath(String queryEntityPath) {
        this.queryEntityPath = queryEntityPath;
    }

    public String getTableConstPath() {
        return tableConstPath;
    }

    public void setTableConstPath(String tableConstPath) {
        this.tableConstPath = tableConstPath;
    }
}
