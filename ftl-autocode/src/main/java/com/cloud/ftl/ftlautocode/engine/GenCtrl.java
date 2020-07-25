package com.cloud.ftl.ftlautocode.engine;

import com.cloud.ftl.ftlautocode.common.GenConst;
import com.cloud.ftl.ftlautocode.req.GenReq;
import com.cloud.ftl.ftlautocode.util.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/*  请求参数如下所示:
{
    "mySqlUrl":"jdbc:mysql://192.168.1.114:3306/test",
    "mySqlName":"root",
    "mySqlPass":"root",
    "mySqlDriver":"com.mysql.jdbc.Driver",
    "projectBasePath":"E:\\myProject\\autocode-parnet\\ftl-test",
    "ctrlPath":"com.cloud.ftl.ftltest.test.controller",
    "inftServicePath":"com.cloud.ftl.ftltest.test.service",
    "implServicePath":"com.cloud.ftl.ftltest.test.service.impl",
    "inftCachePath":"com.cloud.ftl.ftltest.test.cache",
    "implCachePath":"com.cloud.ftl.ftltest.test.cache.impl",
    "daoPath":"com.cloud.ftl.ftltest.test.dao",
    "entityPath":"com.cloud.ftl.ftltest.test.entity",
    "xmlPath":"mybatis.mapper.test",
    "update":false,
    "isCreateFeign":false,
    "tableNames":["daily_amount","load_time"]
}

{
    "mySqlUrl":"jdbc:mysql://192.168.1.114:3306/test",
    "mySqlName":"root",
    "mySqlPass":"root",
    "mySqlDriver":"com.mysql.jdbc.Driver",
    "projectBasePath":"E:\\myProject\\autocode-parnet\\ftl-test",
    "feignBasePath":"E:\\myProject\\autocode-parnet\\ftl-test-client",
    "defaultClientKey":"code.test.server",
    "defaultClientValue":"code-test-server",
    "ctrlPath":"com.cloud.ftl.ftltest.test.controller",
    "inftServicePath":"com.cloud.ftl.ftltest.test.service",
    "implServicePath":"com.cloud.ftl.ftltest.test.service.impl",
    "inftCachePath":"com.cloud.ftl.ftltest.test.cache",
    "implCachePath":"com.cloud.ftl.ftltest.test.cache.impl",
    "daoPath":"com.cloud.ftl.ftltest.test.dao",
    "feignPath":"com.cloud.ftl.testclient.feign",
    "entityPath":"com.cloud.ftl.testclient.entity",
    "xmlPath":"mybatis.mapper",
    "update":false,
    "isCreateFeign":true,
    "tableNames":["daily_amount","load_time"]
}
*/

@RestController
@RequestMapping("/gen")
public class GenCtrl {

    @Autowired
    GenService genService;

    @RequestMapping("/table2java")
    public String genMysqlTable2JavaFile(@RequestBody GenReq genReq) {
        if (Objects.nonNull(genReq.getProjectBasePath())) {
            FreemarkerUtil.projectBasePath = genReq.getProjectBasePath();
        }
        for (String tableName : genReq.getTableNames()) {
            if (genReq.getUpdate()) {
                update(genReq, tableName);
            } else {
                create(genReq, tableName);
            }
        }
        return "生成文件成功!";
    }

    /**
     * 更新表结构生成代码
     *
     * @param genReq
     * @param tableName
     */
    private void update(GenReq genReq, String tableName) {
        //0.读取数据库信息，存储到GenConst.tableColEntitys
        genService.initMySql2JavaInfo(genReq, tableName);
        //0.生成公共替换的Map<String,Object>,存储到GenConst.commonReplaceMap
        genService.initCommonReplaceMap(genReq, tableName);
        if(!genReq.getCreateFeign()){
            //1.生成entity文件
            genService.genEntityFile(genReq);
        } else {
            //1.生成entity文件(生成到feign中)
            FreemarkerUtil.projectBasePath = genReq.getFeignBasePath();
            genService.genEntityFile(genReq);
        }
        FreemarkerUtil.projectBasePath = genReq.getProjectBasePath();
        //2.生成dao文件(生成到server中)
        genService.genDaoFile(genReq);
        //3.生成xml文件(生成到server中)
        genService.genXmlFile(genReq);
    }

    /**
     * 新表生成代码
     *
     * @param genReq
     * @param tableName
     */
    private void create(GenReq genReq, String tableName) {
            //0.读取数据库信息，存储到GenConst.tableColEntitys
            genService.initMySql2JavaInfo(genReq, tableName);
            //0.生成公共替换的Map<String,Object>,存储到GenConst.commonReplaceMap
            genService.initCommonReplaceMap(genReq, tableName);
            if(!genReq.getCreateFeign()){
                //1.生成entity文件
                genService.genEntityFile(genReq);
                //2.生成控制层文件
                genService.genCtrlFile(genReq, GenConst.CTRL_NO_FFEIGN_FTL_PATH);
            } else {
                //1.生成feign文件(生成到feign中)
                FreemarkerUtil.projectBasePath = genReq.getFeignBasePath();
                genService.genEntityFile(genReq);
                genService.getFeignFile(genReq);

                //2.生成ctrl文件(生成到server中)
                FreemarkerUtil.projectBasePath = genReq.getProjectBasePath();
                genService.genCtrlFile(genReq, GenConst.CTRL_WITH_FEIGN_FTL_PATH);
            }
            //3.生成inft service文件
            genService.genInftServiceFile(genReq);
            //4.生成impl service文件
            genService.genImplServiceFile(genReq);
            //5.生成dao文件
            genService.genDaoFile(genReq);
            //6.生成xml文件
            genService.genXmlFile(genReq);
            //7.生成inft cache文件
            genService.genInftCacheFile(genReq);
            //8.生成impl cache文件
            genService.genImplCacheFile(genReq);
        }
}
