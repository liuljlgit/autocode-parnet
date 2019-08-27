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
            <if test="ut.${col.fieldJavaName} != null">
                ${col.field} = ${r'#{ut.'}${col.fieldJavaName}}<#if col_index < (tableColEntitys?size - 1)>,</#if>
            </if>
        </#list>
        </set>
    </sql>

    <sql id="Set_With_Null_List">
        <set>
        <#list tableColEntitys as col>
            ${col.field} = ${r'#{ut.'}${col.fieldJavaName}}<#if col_index < (tableColEntitys?size - 1)>,</#if>
        </#list>
        </set>
    </sql>

    <sql id="where_sql">
        <#list tableColEntitys as col>
        <if test="st.${col.fieldJavaName} != null">
            AND ${col.field} = ${r'#{st.'}${col.fieldJavaName}}
        </if>
        </#list>
        <if test="st.criterias != null">
            <foreach collection="st.criterias" item="criteria" separator=" ">
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
        <if test="st.orderByList!=null and st.orderByList.size() > 0">
            order by
            <foreach collection="st.orderByList" item="order" separator=",">
                ${r'${'}order.field} ${r'${'}order.mode}
            </foreach>
        </if>
        <if test="st.page != null and st.pageSize != null and st.page > 0" >
            limit ${r'#{st.'}index},${r'#{st.'}pageSize}
        </if>
    </select>

    <!--查询列表总数-->
    <select id="selectCount" resultType="java.lang.Long">
        select count(1) from ${tableName}
        <where>
            <include refid="where_sql" />
        </where>
    </select>

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

    <!--插入对象-->
    <insert id="add">
        insert into ${tableName}(<include refid="Base_Column_List" />)
        values(<include refid="Base_Object_List" />)
    </insert>

    <!--批量插入对象-->
    <insert id="addBatch">
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
            AND ${IdColEntity.field} = ${r'#{ut.'}${IdColEntity.fieldJavaName}}
        </where>
    </update>

    <!--批量更新对象-->
    <update id="updateBatchNotNull">
        <foreach collection="list" separator=";" item="ut">
            update ${tableName}
            <include refid="Set_Not_Null_List" />
            <where>
                AND ${IdColEntity.field} = ${r'#{ut.'}${IdColEntity.fieldJavaName}}
            </where>
        </foreach>
    </update>

    <!--更新对象(全更新)-->
    <update id="updateWithNull">
        update ${tableName}
        <include refid="Set_With_Null_List" />
        <where>
            AND ${IdColEntity.field} = ${r'#{ut.'}${IdColEntity.fieldJavaName}}
        </where>
    </update>

    <!--批量更新对象(全更新)-->
    <update id="updateBatchWithNull">
        <foreach collection="list" separator=";" item="ut">
            update ${tableName}
            <include refid="Set_With_Null_List" />
            <where>
                AND ${IdColEntity.field} = ${r'#{ut.'}${IdColEntity.fieldJavaName}}
            </where>
        </foreach>
    </update>

    <!--更新对象,根据查询条件更新-->
    <update id="updateByMap">
        update ${tableName}
        <set>
            <foreach collection="um" index="key" item="value" separator=",">
                ${r'${'}key} = ${r'#{'}value}
            </foreach>
        </set>
        <where>
            <include refid="where_sql" />
        </where>
    </update>

    <!--根据查询条件删除记录-->
    <delete id="delete">
        delete from ${tableName}
        <where>
            <include refid="where_sql" />
        </where>
    </delete>

    <!--根据主键删除对象-->
    <delete id="deleteById">
        delete from ${tableName}
        <where>
            AND ${IdColEntity.field} = ${r'#{'}${IdColEntity.fieldJavaName}}
        </where>
    </delete>

    <!-- 批量删除对象 -->
    <delete id="deleteBatchIds">
        delete from ${tableName}
        <where>
            ${IdColEntity.field} in
            <foreach collection="list" open="(" close=")" separator="," item="item">
                ${r'#{'}item}
            </foreach>
        </where>
    </delete>


    <!--~~~~~~~~~~~~~~~~~~~~~~ custom code begin ~~~~~~~~~~~~~~~~~~~~~~-->
    ${customCode!""}
    <!--~~~~~~~~~~~~~~~~~~~~~~ custom code end ~~~~~~~~~~~~~~~~~~~~~~-->

</mapper>