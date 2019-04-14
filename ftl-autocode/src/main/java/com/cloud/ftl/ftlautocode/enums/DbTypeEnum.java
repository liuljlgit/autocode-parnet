package com.cloud.ftl.ftlautocode.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum DbTypeEnum {

	TINYINT("TINYINT", "Byte","java.lang.Byte", "TINYINT"),
	SMALLINT("SMALLINT", "Short","java.lang.Short", "SMALLINT"),
	INT("INT", "Integer","java.lang.Integer", "INTEGER"),
	INTEGER("INTEGER", "Integer","java.lang.Integer", "INTEGER"),
	BIGINT("BIGINT", "Long","java.lang.Long", "BIGINT"),
	DOUBLE("DOUBLE", "Double","java.lang.Double", "DOUBLE"),
	FLOAT("FLOAT", "Double","java.lang.Double", "DOUBLE"),
	DECIMAL("DECIMAL", "BigDecimal","java.math.BigDecimal", "DECIMAL"),
	NUMERIC("NUMERIC", "BigDecimal","java.math.BigDecimal", "DECIMAL"),
	CHAR("CHAR", "String","java.lang.String", "CHAR"),
	VARCHAR("VARCHAR", "String","java.lang.String", "VARCHAR"),
	DATE("DATE", "Date","java.util.Date", "DATE"),
	TIMESTAMP("TIMESTAMP", "Date","java.util.Date", "TIMESTAMP"),
	DATETIME("DATETIME", "Date","java.util.Date", "TIMESTAMP"),
	TEXT("TEXT", "String","java.lang.String", "VARCHAR"),
	LONGTEXT("LONGTEXT", "String","java.lang.String", "VARCHAR")
	;

	DbTypeEnum(String dbTypeName, String javaTypeName, String javaTypePath, String mybatisTypeName) {
		this.dbTypeName = dbTypeName;
		this.javaTypeName = javaTypeName;
		this.javaTypePath = javaTypePath;
		this.mybatisTypeName = mybatisTypeName;
	}
	
	private String dbTypeName;
	
	private String javaTypeName;

	private String javaTypePath;

	private String mybatisTypeName;

	private static Map<String, DbTypeEnum> map = new HashMap<>();
	static {
		if (map == null) {
			map = new HashMap<>();
		}
		Arrays.stream(DbTypeEnum.values()).forEach(e -> map.put(e.getDbTypeName(), e));
	}
	public static DbTypeEnum getDbTypeEnum(String dbTypeName){
		return map.get(dbTypeName);
	}

	public String getDbTypeName() {
		return dbTypeName;
	}

	public void setDbTypeName(String dbTypeName) {
		this.dbTypeName = dbTypeName;
	}

	public String getJavaTypeName() {
		return javaTypeName;
	}

	public void setJavaTypeName(String javaTypeName) {
		this.javaTypeName = javaTypeName;
	}

	public String getJavaTypePath() {
		return javaTypePath;
	}

	public void setJavaTypePath(String javaTypePath) {
		this.javaTypePath = javaTypePath;
	}

	public String getMybatisTypeName() {
		return mybatisTypeName;
	}

	public void setMybatisTypeName(String mybatisTypeName) {
		this.mybatisTypeName = mybatisTypeName;
	}
}
