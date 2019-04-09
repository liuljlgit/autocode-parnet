<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackagePath}.I${className}Dao">
    <resultMap id="BaseResultMap" type="${entityPackagePath}.${className}">
        <id column="${IdColEntity.field}" jdbcType="${IdColEntity.fieldDbType}" property="${IdColEntity.fieldJavaName}" />
        <#list tableColEntitys as col>
        <#if (col.key != "PRI")>
        <result column="${col.field}" jdbcType="${col.fieldDbType}" property="${col.fieldJavaName}" />
        </#if>
        </#list>
    </resultMap>

</mapper>