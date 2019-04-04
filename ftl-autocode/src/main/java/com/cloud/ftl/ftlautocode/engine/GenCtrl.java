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
        "projectBasePath":"E:\\myProject\\autocode-parnet\\ftl-autocode",
        "ctrlPath":"com.cloud.ftl.ftlautocode.test.controller",
        "inftServicePath":"com.cloud.ftl.ftlautocode.test.service.inft",
        "implServicePath":"com.cloud.ftl.ftlautocode.test.service.impl",
        "daoPath":"com.cloud.ftl.ftlautocode.test.dao",
        "entityPath":"com.cloud.ftl.ftlautocode.test.entity",
        "xmlPath":"mybatis.mapper.test",
        "isUpdate":true,
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
        }
        //返回
        return "生成文件成功!";
    }
}
