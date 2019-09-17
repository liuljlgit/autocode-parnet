一、概述
====
autocode是自动代码生成工具，只需一键就能生成包括controller,service,dao,xml,redis等文件。使项目可高效开发，项目集成了复杂查询和简单查询，都使用redis进行缓存存储。使用此项目，只需一键生成代码，后续的业务逻辑我们就不再需要写sql文件，只需调用系统封装好的函数进行高效开发。

二、表设计原则
====
表的主键类型需为BIGINT类型

三、使用流程
====

a）克隆autocode-parnet仓库,并把里面的ftl-basic打成一个jar包<br>
b）在你的项目中加入jar包<br>
```
  <dependency>
    <groupId>com.cloud.ftl</groupId>
    <artifactId>ftl-basic</artifactId>
    <version>${ftl.basic.version}</version>
  </dependency>
```
c）启动ftl-autocode项目，使用postman请求接口生成代码<br>
```
 {
 	"mySqlUrl":"jdbc:mysql://localhost:3306/test",                                 
 	"mySqlName":"root",
 	"mySqlPass":"root",
 	"mySqlDriver":"com.mysql.jdbc.Driver",
 	"projectBasePath":"E:\\myProject\\autocode-parnet\\ftl-test",   //项目模块在本地地址
 	"ctrlPath":"com.cloud.ftl.ftltest.test.controller",
 	"inftServicePath":"com.cloud.ftl.ftltest.test.service.inft",
 	"implServicePath":"com.cloud.ftl.ftltest.test.service.impl",
 	"inftCachePath":"com.cloud.ftl.ftltest.test.cache.inft",
 	"implCachePath":"com.cloud.ftl.ftltest.test.cache.impl",
 	"daoPath":"com.cloud.ftl.ftltest.test.dao",
 	"entityPath":"com.cloud.ftl.ftltest.test.entity",
 	"xmlPath":"mybatis.mapper.test",    
 	"update":false,                                                //是否是更新操作，更新操作只会重新生成xml、dao和entity等文件             
 	"tableNames":["daily_amount","load_time"]
 }
```
<br>
    
四、总结
====
ftl-autocode模块为生成代码模块,启动然后请求接口即可；<br>
ftl-basic模块为基础类模块，生成的代码需要用到此包里面的类；<br>
ftl-test模块为测试模块，如果只是想测试一下，可以把代码生成在此模块下去测试；<br>
