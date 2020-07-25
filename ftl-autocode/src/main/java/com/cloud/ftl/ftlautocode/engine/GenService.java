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
            Connection con = DriverManager.getConnection(genReq.getMySqlUrl(), genReq.getMySqlName(), genReq.getMySqlPass());
            ResultSet rs = con.createStatement().executeQuery("show full columns from " + tableName);
            //获取表注释
            String tableSchema = genReq.getMySqlUrl().substring(genReq.getMySqlUrl().lastIndexOf("/") + 1);
            ResultSet commentRs = con.createStatement()
                    .executeQuery("select TABLE_COMMENT from INFORMATION_SCHEMA.Tables where table_schema = '" + tableSchema + "' and TABLE_NAME = '" + tableName + "'");
            while (rs.next()) {
                String field = rs.getString("Field").toLowerCase();
                String type = rs.getString("Type");
                if (type.contains("(")) {
                    type = type.substring(0, type.indexOf("("));
                }
                type = type.toUpperCase();
                String key = rs.getString("key");
                String comment = rs.getString("Comment");
                String fieldJavaType = DbTypeEnum.getDbTypeEnum(type).getJavaTypeName();
                String fieldDbType = DbTypeEnum.getDbTypeEnum(type).getDbTypeName();
                String fieldMybatisType = DbTypeEnum.getDbTypeEnum(type).getMybatisTypeName();
                String fieldJavaName = HumpUtil.convertToJava(field);
                TableColEntity tableColEntity = new TableColEntity(field, type, key, comment, fieldJavaType, fieldJavaName, fieldDbType, fieldMybatisType);
                GenConst.tableColEntitys.add(tableColEntity);
            }
            //获取表的注释
            commentRs.next();
            GenConst.tableComment = commentRs.getString("TABLE_COMMENT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成公共替换的map
     *
     * @param genReq
     */
    public void initCommonReplaceMap(GenReq genReq, String tableName) {
        GenConst.commonReplaceMap.put("className", HumpUtil.convertToJavaClass(tableName));
        GenConst.commonReplaceMap.put("objectName", HumpUtil.convertToJava(tableName));
        GenConst.commonReplaceMap.put("tableName", tableName);
        GenConst.commonReplaceMap.put("ctrlPackagePath", genReq.getCtrlPath());
        GenConst.commonReplaceMap.put("inftServicePackagePath", genReq.getInftServicePath());
        GenConst.commonReplaceMap.put("implServicePackagePath", genReq.getImplServicePath());
        GenConst.commonReplaceMap.put("inftCachePackagePath", genReq.getInftCachePath());
        GenConst.commonReplaceMap.put("implCachePackagePath", genReq.getImplCachePath());
        GenConst.commonReplaceMap.put("daoPackagePath", genReq.getDaoPath());
        GenConst.commonReplaceMap.put("entityPackagePath", genReq.getEntityPath());
        GenConst.commonReplaceMap.put("feignPackagePath", genReq.getFeignPath());
        GenConst.commonReplaceMap.put("tableComment", GenConst.tableComment);
        GenConst.commonReplaceMap.put("tableColEntitys", GenConst.tableColEntitys);
        GenConst.commonReplaceMap.put("IdColEntity", GenConst.tableColEntitys.stream().filter(e -> e.getKey().equals("PRI")).collect(Collectors.toList()).get(0));
        GenConst.commonReplaceMap.put("defaultClientKey", genReq.getDefaultClientKey());
        GenConst.commonReplaceMap.put("defaultClientValue", genReq.getDefaultClientValue());
    }

    /**
     * 生成控制层文件
     *
     * @param genReq
     */
    public void genCtrlFile(GenReq genReq,String ctrlFtlPath) {
        FreemarkerUtil.outputFile(genReq.getCtrlPath(),
                ctrlFtlPath,
                GenConst.commonReplaceMap.get("className").toString().concat("Ctrl"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成service接口文件
     *
     * @param genReq
     */
    public void genInftServiceFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getInftServicePath(),
                GenConst.INFT_SERVICE_FTL_PATH,
                "I" + GenConst.commonReplaceMap.get("className").toString().concat("Service"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成service实现文件
     *
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
     * 生成cache接口文件
     *
     * @param genReq
     */
    public void genInftCacheFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getInftCachePath(),
                GenConst.INFT_CACHE_FTL_PATH,
                "I" + GenConst.commonReplaceMap.get("className").toString().concat("Cache"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成cache实现文件
     *
     * @param genReq
     */
    public void genImplCacheFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getImplCachePath(),
                GenConst.IMPL_CACHE_FTL_PATH,
                GenConst.commonReplaceMap.get("className").toString().concat("CacheImpl"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成实体文件
     *
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
     *
     * @param genReq
     */
    public void genDaoFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getDaoPath(),
                GenConst.DAO_FTL_PATH,
                "I" + GenConst.commonReplaceMap.get("className").toString().concat("Dao"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成xml文件
     *
     * @param genReq
     */
    public void genXmlFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getXmlPath(),
                GenConst.XML_FTL_PATH,
                GenConst.commonReplaceMap.get("className").toString() + "Mapper",
                false,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }

    /**
     * 生成feign文件
     *
     * @param genReq
     */
    public void getFeignFile(GenReq genReq) {
        FreemarkerUtil.outputFile(genReq.getFeignPath(),
                GenConst.FEIGN_FTL_PATH,
                GenConst.commonReplaceMap.get("className").toString().concat("Feign"),
                true,
                genReq.getUpdate(),
                GenConst.commonReplaceMap);
    }
}
