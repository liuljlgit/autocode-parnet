package com.cloud.ftl.ftlautocode.engine;

import com.cloud.ftl.ftlautocode.req.GenReq;
import com.cloud.ftl.ftlautocode.util.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/*  请求参数如下所示:
    {
        "mySqlUrl":"jdbc:mysql://192.168.123.135:3306/test",
        "mySqlName":"root",
        "mySqlPass":"root",
        "mySqlDriver":"com.mysql.jdbc.Driver",
        "projectBasePath":"E:\\myProject\\autocode-parnet\\ftl-test",
        "ctrlPath":"com.cloud.ftl.ftltest.test.controller",
        "inftServicePath":"com.cloud.ftl.ftltest.test.service.inft",
        "implServicePath":"com.cloud.ftl.ftltest.test.service.impl",
        "inftRedisPath":"com.cloud.ftl.ftltest.test.cache.inft",
        "implRedisPath":"com.cloud.ftl.ftltest.test.cache.impl",
        "daoPath":"com.cloud.ftl.ftltest.test.dao",
        "entityPath":"com.cloud.ftl.ftltest.test.entity",
        "queryEntityPath":"com.cloud.ftl.ftltest.test.query",
        "respPath":"com.cloud.ftl.ftltest.test.webentity.resp",
        "reqPath":"com.cloud.ftl.ftltest.test.webentity.req",
        "xmlPath":"mybatis.mapper.test",
        "update":true,
        "tableNames":["daily_amount","load_time"]
    }
*/

@RestController
@RequestMapping("/gen")
public class GenCtrl {

    @Autowired
    GenService genService;

    @RequestMapping("/table2java")
    public String genMysqlTable2JavaFile(@RequestBody GenReq genReq){
        if(Objects.nonNull(genReq.getProjectBasePath())){
            FreemarkerUtil.projectBasePath = genReq.getProjectBasePath();
        }
        for (String tableName : genReq.getTableNames()) {
            if(genReq.getUpdate()){
                update(genReq, tableName);
            }else{
                create(genReq, tableName);
            }
        }
        return "生成文件成功!";
    }

    /**
     * 更新表结构生成代码
     * @param genReq
     * @param tableName
     */
    private void update(GenReq genReq, String tableName){
        //0.读取数据库信息，存储到GenConst.tableColEntitys
        genService.initMySql2JavaInfo(genReq,tableName);
        //0.生成公共替换的Map<String,Object>,存储到GenConst.commonReplaceMap
        genService.initCommonReplaceMap(genReq,tableName);
        //1.生成entity文件
        genService.genEntityFile(genReq);
        //2.生成dao文件
        genService.genDaoFile(genReq);
        //3.生成xml文件
        genService.genXmlFile(genReq);
        //4.生成queryentity文件
        genService.genQueryEntityFile(genReq);
    }

    /**
     * 新表生成代码
     * @param genReq
     * @param tableName
     */
    private void create(GenReq genReq, String tableName) {
            //0.读取数据库信息，存储到GenConst.tableColEntitys
            genService.initMySql2JavaInfo(genReq,tableName);
            //0.生成公共替换的Map<String,Object>,存储到GenConst.commonReplaceMap
            genService.initCommonReplaceMap(genReq,tableName);
            //1.生成控制层文件
            genService.genCtrlFile(genReq);
            //2.生成inft service文件
            genService.genInftServiceFile(genReq);
            //3.生成impl service文件
            genService.genImplServiceFile(genReq);
            //4.生成entity文件
            genService.genEntityFile(genReq);
            //5.生成dao文件
            genService.genDaoFile(genReq);
            //6.生成xml文件
            genService.genXmlFile(genReq);
            //7.生成inft redis文件
            genService.genInftRedisFile(genReq);
            //8.生成impl redis文件
            genService.genImplRedisFile(genReq);
            //9.生成resp文件
            genService.genReqFile(genReq);
            //10.生成req文件
            genService.genRespFile(genReq);
            //11.生成queryentity文件
            genService.genQueryEntityFile(genReq);
        }
}
