package com.cloud.ftl.ftlautocode.common;


import com.cloud.ftl.ftlautocode.entity.TableColEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenConst {

    //java项目接口的基本路径
    public static final String BASE_JAVA_PATH = "/src/main/java/";
    public static final String BASE_RESOURCE_PATH = "/src/main/resources/";

    //模板文件目录
    public static final String TEMPLATE_PATH = "ftl-autocode/src/main/resources/template/";

    //模板文件路径
    public static final String CTRL_FTL_PATH = "/java/controller.ftl";
    public static final String INFT_SERVICE_FTL_PATH = "/java/inftservice.ftl";
    public static final String INFT_REDIS_FTL_PATH = "/java/inftredis.ftl";
    public static final String IMPL_SERVICE_FTL_PATH = "/java/implservice.ftl";
    public static final String IMPL_REDIS_FTL_PATH = "/java/implredis.ftl";
    public static final String DAO_FTL_PATH = "/java/dao.ftl";
    public static final String ENTITY_PATH = "/java/entity.ftl";
    public static final String REQ_PATH = "/java/req.ftl";
    public static final String RESP_PATH = "/java/resp.ftl";
    public static final String XML_FTL_PATH = "/xml/mybatis.ftl";

    //java的"/"和"."
    public static final String SPRIT = "/";
    public static final String SPOT = ".";

    //java文件个xml文件后缀
    public static final String JAVA_FILE_SUFFIX = ".java";
    public static final String XML_FILE_SUFFIX = ".xml";

    //自定义代码开始和结束
    public static final String JAVA_CUSTOM_BEGIN_CODE = "//------------------------ custom code begin ------------------------//";
    public static final String JAVA_CUSTOM_END_CODE = "//======================== custom code end ========================//";
    public static final String XML_CUSTOM_BEGIN_CODE = "<!--~~~~~~~~~~~~~~~~~~~~~~ custom code begin ~~~~~~~~~~~~~~~~~~~~~~-->";
    public static final String XML_CUSTOM_END_CODE = "<!--~~~~~~~~~~~~~~~~~~~~~~ custom code end ~~~~~~~~~~~~~~~~~~~~~~-->";

    //数据库中的列值转换成list
    public static final List<TableColEntity> tableColEntitys = new ArrayList<>();

    //公共替换的map
    public static final Map<String,Object> commonReplaceMap = new HashMap<>();
}
