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
<#if col_index == 0>        ${r'#{at.'}${col.fieldJavaName}}<#else>${r'#{at.'}${col.fieldJavaName}}</#if><#if col_index < (tableColEntitys?size - 1)>,</#if><#rt>
    </#list>

    </sql>

    <sql id="Set_Not_Null_List">
        <set>
        <#list tableColEntitys as col>
            <if test="qt.${col.fieldJavaName} != null">
                ${col.field} = ${r'#{qt.'}${col.fieldJavaName}}<#if col_index < (tableColEntitys?size - 1)>,</#if>
            </if>
        </#list>
        </set>
    </sql>

    <sql id="Set_With_Null_List">
        <set>
        <#list tableColEntitys as col>
            ${col.field} = ${r'#{qt.'}${col.fieldJavaName}}<#if col_index < (tableColEntitys?size - 1)>,</#if>
        </#list>
        </set>
    </sql>

    <sql id="where_sql">
        <#list tableColEntitys as col>
        <if test="et.${col.fieldJavaName} != null">
            AND ${col.field} = ${r'#{et.'}${col.fieldJavaName}}
        </if>
        </#list>
        <if test="et.criterias != null">
            <foreach collection="et.criterias" item="criteria" separator=" ">
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
    <select id="selectMaxId" resultType="java.lang.Long">
        select IFNULL(max(${IdColEntity.field}), 0) from `${tableName}`
    </select>

    <!--根据主键获取对象-->
    <select id="selectById" resultMap="BaseResultMap">
        select <include refid="Base_Column_List" /> from ${tableName}
        <where>
            AND ${IdColEntity.field} = ${r'#{'}${IdColEntity.fieldJavaName}}
        </where>
    </select>

    <!--查询列表-->
    <select id="selectList" resultMap="BaseResultMap">
        select <include refid="Base_Column_List" /> from ${tableName}
        <where>
            <include refid="where_sql" />
        </where>
        <if test="et.orderByClause!=null and et.orderByClause!=''">
            order by ${r'${et.'}orderByClause}
        </if>
        <if test="et.page != null and et.pageSize != null and et.page > 0" >
            limit ${r'#{et.'}index},${r'#{et.'}pageSize}
        </if>
    </select>

    <!--查询列表总数-->
    <select id="selectCount" resultType="java.lang.Long">
        select count(1) from ${tableName}
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
        <foreach collection="list" index="index" item="at" separator=",">
            (<include refid="Base_Object_List" />)
        </foreach>
    </insert>

    <!--更新对象-->
    <update id="updateNotNull">
        update ${tableName}
        <include refid="Set_Not_Null_List" />
        <where>
            AND ${IdColEntity.field} = ${r'#{qt.'}${IdColEntity.fieldJavaName}}
        </where>
    </update>

    <!--批量更新对象-->
    <update id="updateBatchNotNull">
        <foreach collection="list" separator=";" item="qt">
            update ${tableName}
            <include refid="Set_Not_Null_List" />
            <where>
                AND ${IdColEntity.field} = ${r'#{qt.'}${IdColEntity.fieldJavaName}}
            </where>
        </foreach>
    </update>

    <!--更新对象(全更新)-->
    <update id="updateWithNull">
        update ${tableName}
        <include refid="Set_With_Null_List" />
        <where>
            AND ${IdColEntity.field} = ${r'#{qt.'}${IdColEntity.fieldJavaName}}
        </where>
    </update>

    <!--批量更新对象(全更新)-->
    <update id="updateBatchWithNull">
        <foreach collection="list" separator=";" item="qt">
            update ${tableName}
            <include refid="Set_With_Null_List" />
            <where>
                AND ${IdColEntity.field} = ${r'#{qt.'}${IdColEntity.fieldJavaName}}
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
    <select id="selectBatchIds" resultMap="BaseResultMap">
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