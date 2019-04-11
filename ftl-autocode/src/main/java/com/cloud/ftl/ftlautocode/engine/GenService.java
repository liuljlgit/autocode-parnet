package com.cloud.ftl.ftlautocode.engine;

import com.cloud.ftl.ftlautocode.common.GenConst;
import com.cloud.ftl.ftlautocode.entity.TableColEntity;
import com.cloud.ftl.ftlautocode.enums.DbTypeEnum;
import com.cloud.ftl.ftlautocode.req.GenReq;
import com.cloud.ftl.ftlautocode.util.FreemarkerUtil;
import com.cloud.ftl.ftlautocode.util.HumpUtil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Service
public class GenService {

    /**
     * 读取数据库信息
     */
    public void initMySql2JavaInfo(GenReq genReq, String tableName) {
        try {
            GenConst.tableColEntitys.clear();
            Connection con = DriverManager.getConnection(genReq.getMySqlUrl(),genReq.getMySqlName(),genReq.getMySqlPass());
            ResultSet rs = con.createStatement().executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                String field = rs.getString("Field");
                String type = rs.getString("Type");
                if(type.indexOf("(") != -1){
                    type = type.substring(0,type.indexOf("("));
                }
                type = type.toUpperCase();
                String key = rs.getString("key");
                String comment = rs.getString("Comment");
                String fieldJavaType = DbTypeEnum.getDbTypeEnum(type).getJavaTypeName();
                String fieldDbType = DbTypeEnum.getDbTypeEnum(type).getDbTypeName();
                String fieldMybatisType = DbTypeEnum.getDbTypeEnum(type).getMybatisTypeName();
                String fieldJavaName = HumpUtil.convertToJava(field);
                TableColEntity tableColEntity = new TableColEntity(field,type,key,comment,fieldJavaType,fieldJavaName,fieldDbType,fieldMybatisType);
                GenConst.tableColEntitys.add(tableColEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成公共替换的map
     * @param genReq
     */
    public void initCommonReplaceMap(GenReq genReq,String tableName) {
        GenConst.commonReplaceMap.put("className", HumpUtil.toUpperCaseFirstOne(HumpUtil.convertToJava(tableName)));
        GenConst.commonReplaceMap.put("objectName", HumpUtil.toLowerCaseFirstOne(HumpUtil.convertToJava(tableName)));
        GenConst.commonReplaceMap.put("tableName", tableName);
        GenConst.commonReplaceMap.put("ctrlPackagePath",genReq.getCtrlPath());
        GenConst.commonReplaceMap.put("inftServicePackagePath",genReq.getInftServicePath());
        GenConst.commonReplaceMap.put("implServicePackagePath",genReq.getImplServicePath());
        GenConst.commonReplaceMap.put("inftRedisPackagePath",genReq.getInftRedisPath());
        GenConst.commonReplaceMap.put("implRedisPackagePath",genReq.getImplRedisPath());
        GenConst.commonReplaceMap.put("daoPackagePath",genReq.getDaoPath());
        GenConst.commonReplaceMap.put("entityPackagePath",genReq.getEntityPath());
        GenConst.commonReplaceMap.put("queryEntityPackagePath",genReq.getQueryEntityPath());
        GenConst.commonReplaceMap.put("reqPackagePath",genReq.getReqPath());
        GenConst.commonReplaceMap.put("respPackagePath",genReq.getRespPath());
        GenConst.commonReplaceMap.put("tableColEntitys",GenConst.tableColEntitys);
        GenConst.commonReplaceMap.put("IdColEntity",GenConst.tableColEntitys.stream().filter(e->e.getKey().equals("PRI")).collect(Collectors.toList()).get(0));
    }

    /**
     * 生成控制层文件
     * @param genReq
     */
    public void genCtrlFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getCtrlPath(),
                GenConst.CTRL_FTL_PATH,
                GenConst.commonReplaceMap.get("className").toString().concat("Ctrl"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成service接口文件
     * @param genReq
     */
    public void genInftServiceFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getInftServicePath(),
                GenConst.INFT_SERVICE_FTL_PATH,
                "I"+GenConst.commonReplaceMap.get("className").toString().concat("Service"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成service实现文件
     * @param genReq
     */
    public void genImplServiceFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getImplServicePath(),
                GenConst.IMPL_SERVICE_FTL_PATH,
                GenConst.commonReplaceMap.get("className").toString().concat("ServiceImpl"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成实体文件
     * @param genReq
     */
    public void genEntityFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getEntityPath(),
                GenConst.ENTITY_PATH,
                GenConst.commonReplaceMap.get("className").toString(),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成dao文件
     * @param genReq
     */
    public void genDaoFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getDaoPath(),
                GenConst.DAO_FTL_PATH,
                "I"+GenConst.commonReplaceMap.get("className").toString().concat("Dao"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成xml文件
     * @param genReq
     */
    public void genXmlFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getXmlPath(),
                GenConst.XML_FTL_PATH,
                GenConst.commonReplaceMap.get("className").toString()+"Mapper",
                false,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成redis接口文件
     * @param genReq
     */
    public void genInftRedisFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getInftRedisPath(),
                GenConst.INFT_REDIS_FTL_PATH,
                "I"+GenConst.commonReplaceMap.get("className").toString().concat("Redis"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成redis实现文件
     * @param genReq
     */
    public void genImplRedisFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getImplRedisPath(),
                GenConst.IMPL_REDIS_FTL_PATH,
                GenConst.commonReplaceMap.get("className").toString().concat("RedisImpl"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成req文件
     * @param genReq
     */
    public void genReqFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getReqPath(),
                GenConst.REQ_PATH,
                GenConst.commonReplaceMap.get("className").toString().concat("Req"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成resp文件
     * @param genReq
     */
    public void genRespFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getRespPath(),
                GenConst.RESP_PATH,
                GenConst.commonReplaceMap.get("className").toString().concat("Resp"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成queryentity文件
     * @param genReq
     */
    public void genQueryEntityFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getQueryEntityPath(),
                GenConst.QUERY_ENTITY_PATH,
                GenConst.commonReplaceMap.get("className").toString().concat("Query"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }
}
