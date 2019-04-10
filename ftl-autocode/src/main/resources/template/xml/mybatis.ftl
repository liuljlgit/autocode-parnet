<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackagePath}.I${className}Dao">
    <resultMap id="BaseResultMap" type="${entityPackagePath}.${className}">
        <id column="${IdColEntity.field}" jdbcType="${IdColEntity.fieldMybatisType}" property="${IdColEntity.fieldJavaName}" />
        <#list tableColEntitys as col>
        <#if (col.key != "PRI")>
        <result column="${col.field}" jdbcType="${col.fieldMybatisType}" property="${col.fieldJavaName}" />
        </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
    <#list tableColEntitys as col>
<#if col_index == 0>        ${col.field}<#else>${col.field}</#if><#if col_index < (tableColEntitys?size - 1)>,</#if><#rt>
    </#list>

    </sql>

    <!--获取最大的主键-->
    <select id="selectMax${className}Id" resultType="java.lang.Long">
        select IFNULL(max(${IdColEntity.field}), 0) from `${tableName}`
    </select>

    <!--根据主键获取对象-->
    <select id="load${className}ByKey" resultMap="BaseResultMap">
        select <include refid="Base_Column_List" /> from ${tableName}
        <where>
            AND ${IdColEntity.field} = ${r'#{'}${IdColEntity.fieldJavaName}}
        </where>
    </select>

</mapper>