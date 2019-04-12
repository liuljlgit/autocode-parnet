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

    <sql id="Base_Object_List">
    <#list tableColEntitys as col>
<#if col_index == 0>        ${r'#{'}${col.fieldJavaName}}<#else>${r'#{'}${col.fieldJavaName}}</#if><#if col_index < (tableColEntitys?size - 1)>,</#if><#rt>
    </#list>

    </sql>

    <sql id="Base_Item_List">
    <#list tableColEntitys as col>
<#if col_index == 0>        ${r'#{item.'}${col.fieldJavaName}}<#else>${r'#{item.'}${col.fieldJavaName}}</#if><#if col_index < (tableColEntitys?size - 1)>,</#if><#rt>
    </#list>

    </sql>

    <sql id="where_sql">
        <if test="criterias != null">
            <foreach collection="criterias" item="criteria" separator=" ">
                <if test="criteria.valid">
                    ${r'${'}criteria.opt}
                    <choose>
                        <when test="criteria.criterions.size() > 1">
                            <trim prefix="(" prefixOverrides="and|or" suffix=")">
                                <foreach collection="criteria.criterions" item="criterion">
                                    <choose>
                                        <when test="criterion.noValue">
                                            ${r'${'}criterion.condition}
                                        </when>
                                        <when test="criterion.oneValue">
                                            ${r'${'}criterion.condition} ${r'#{'}criterion.value1}
                                        </when>
                                        <when test="criterion.secondValue">
                                            ${r'${'}criterion.condition} ${r'#{'}criterion.value1} and ${r'#{'}criterion.value2}
                                        </when>
                                        <when test="criterion.listValue">
                                            ${r'${'}criterion.condition}
                                            <foreach collection="criterion.list" item="listItem" open="(" close=")" separator=",">
                                                ${r'#{'}listItem}
                                            </foreach>
                                        </when>
                                    </choose>
                                </foreach>
                            </trim>
                        </when>
                        <otherwise>
                            <trim prefixOverrides="and|or">
                                <foreach collection="criteria.criterions" item="criterion">
                                    <choose>
                                        <when test="criterion.noValue">
                                            ${r'${'}criterion.condition}
                                        </when>
                                        <when test="criterion.oneValue">
                                            ${r'${'}criterion.condition} ${r'#{'}criterion.value1}
                                        </when>
                                        <when test="criterion.secondValue">
                                            ${r'${'}criterion.condition} ${r'#{'}criterion.value1} and ${r'#{'}criterion.value2}
                                        </when>
                                        <when test="criterion.listValue">
                                            ${r'${'}criterion.condition}
                                            <foreach collection="criterion.list" item="listItem" open="(" close=")" separator=",">
                                                ${r'#{'}listItem}
                                            </foreach>
                                        </when>
                                    </choose>
                                </foreach>
                            </trim>
                        </otherwise>
                    </choose>
                </if>
            </foreach>
        </if>
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

    <!--查询列表-->
    <select id="find${className}List" resultMap="BaseResultMap">
        select <include refid="Base_Column_List" /> from ${tableName}
        <where>
            <include refid="where_sql" />
        </where>
        <if test="orderByClause!=null and orderByClause!=''">
            order by ${r'${'}orderByClause}
        </if>
        <if test="page != null and pageSize != null and page > 0" >
            limit ${r'#{'}index},${r'#{'}pageSize}
        </if>
    </select>

    <!--查询列表总数-->
    <select id="getTotal${className}" resultType="java.lang.Long">
        select count(*) from ${tableName}
        <where>
            <include refid="where_sql" />
        </where>
    </select>

    <!--插入对象-->
    <insert id="add${className}">
        insert into ${tableName}(<include refid="Base_Column_List" />)
        values(<include refid="Base_Object_List" />)
    </insert>

    <!--批量插入对象-->
    <insert id="batchAdd${className}">
        insert into ${tableName}(<include refid="Base_Column_List" />)
        values
        <foreach collection="list" index="index" item="item" separator=",">
            (<include refid="Base_Item_List" />)
        </foreach>
    </insert>

    <!--更新对象-->
    <update id="update${className}">
        update ${tableName}
        <set>
            <#list tableColEntitys as col>
            <if test="${col.fieldJavaName} != null">
                ${col.field} = ${r'#{'}${col.fieldJavaName}}<#if col_index < (tableColEntitys?size - 1)>,</#if>
            </if>
            </#list>
        </set>
        <where>
            AND ${IdColEntity.field} = ${r'#{'}${IdColEntity.fieldJavaName}}
        </where>
    </update>

    <!--批量更新对象-->
    <update id="batchUpdate${className}">
        <foreach collection="list" separator=";" item="item">
            update ${tableName}
            <set>
                <#list tableColEntitys as col>
                <if test="item.${col.fieldJavaName} != null">
                    ${col.field} = ${r'#{item.'}${col.fieldJavaName}}<#if col_index < (tableColEntitys?size - 1)>,</#if>
                </if>
                </#list>
            </set>
            <where>
                AND ${IdColEntity.field} = ${r'#{item.'}${IdColEntity.fieldJavaName}}
            </where>
        </foreach>
    </update>

    <!--更新对象(全更新)-->
    <update id="fullUpdate${className}">
        update ${tableName}
        <set>
            <#list tableColEntitys as col>
            ${col.field} = ${r'#{'}${col.fieldJavaName}}<#if col_index < (tableColEntitys?size - 1)>,</#if>
            </#list>
        </set>
        <where>
            AND ${IdColEntity.field} = ${r'#{'}${IdColEntity.fieldJavaName}}
        </where>
    </update>

    <!--批量更新对象(全更新)-->
    <update id="batchFullUpdate${className}">
        <foreach collection="list" separator=";" item="item">
            update ${tableName}
            <set>
                <#list tableColEntitys as col>
                ${col.field} = ${r'#{item.'}${col.fieldJavaName}}<#if col_index < (tableColEntitys?size - 1)>,</#if>
                </#list>
            </set>
            <where>
                AND ${IdColEntity.field} = ${r'#{item.'}${IdColEntity.fieldJavaName}}
            </where>
        </foreach>
    </update>

    <!--根据主键删除对象-->
    <delete id="delete${className}">
        delete from ${tableName}
        <where>
            AND ${IdColEntity.field} = ${r'#{'}${IdColEntity.fieldJavaName}}
        </where>
    </delete>

    <!-- 批量删除对象 -->
    <delete id="batchDelete${className}">
        delete from ${tableName}
        <where>
            ${IdColEntity.field} in
            <foreach collection="list" open="(" close=")" separator="," item="item">
                ${r'#{'}item}
            </foreach>
        </where>
    </delete>

    <!--根据ID列表获取记录列表-->
    <select id="find${className}ByIdList" resultMap="BaseResultMap">
        select <include refid="Base_Column_List" /> from ${tableName}
        <where>
            ${IdColEntity.field} in
            <foreach collection="list" open="(" close=")" separator="," item="item">
                ${r'#{'}item}
            </foreach>
        </where>
    </select>

    <!--~~~~~~~~~~~~~~~~~~~~~~ custom code begin ~~~~~~~~~~~~~~~~~~~~~~-->
    ${customCode!""}
    <!--~~~~~~~~~~~~~~~~~~~~~~ custom code end ~~~~~~~~~~~~~~~~~~~~~~-->

</mapper>