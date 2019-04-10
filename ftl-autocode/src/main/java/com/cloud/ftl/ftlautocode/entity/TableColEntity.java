package com.cloud.ftl.ftlautocode.entity;

public class TableColEntity {

    //------------------------------- 数据库信息 ----------------------------------//
    /**
     * 列名称
     */
    private String field;

    /**
     * 列类型
     */
    private String type;

    /**
     * 列键值
     */
    private String key;

    /**
     * 列备注
     */
    private String comment;

    //------------------------ 利用数据库信息转换成需要的信息 -------------------------//

    /**
     * java类型
     */
    private String fieldJavaType;

    /**
     * java名称
     */
    private String fieldJavaName;

    /**
     * db类型
     */
    private String fieldDbType;

    /**
     * mybatis类型
     */
    private String fieldMybatisType;

    public TableColEntity(String field, String type, String key, String comment, String fieldJavaType, String fieldJavaName,String fieldDbType,String fieldMybatisType) {
        this.field = field;
        this.type = type;
        this.key = key;
        this.comment = comment;
        this.fieldJavaType = fieldJavaType;
        this.fieldJavaName = fieldJavaName;
        this.fieldDbType = fieldDbType;
        this.fieldMybatisType = fieldMybatisType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFieldJavaType() {
        return fieldJavaType;
    }

    public void setFieldJavaType(String fieldJavaType) {
        this.fieldJavaType = fieldJavaType;
    }

    public String getFieldJavaName() {
        return fieldJavaName;
    }

    public void setFieldJavaName(String fieldJavaName) {
        this.fieldJavaName = fieldJavaName;
    }

    public String getFieldDbType() {
        return fieldDbType;
    }

    public void setFieldDbType(String fieldDbType) {
        this.fieldDbType = fieldDbType;
    }

    public String getFieldMybatisType() {
        return fieldMybatisType;
    }

    public void setFieldMybatisType(String fieldMybatisType) {
        this.fieldMybatisType = fieldMybatisType;
    }
}
