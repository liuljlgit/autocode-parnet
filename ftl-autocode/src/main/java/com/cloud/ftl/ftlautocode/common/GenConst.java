package com.cloud.ftl.ftlautocode.common;


import com.cloud.ftl.ftlautocode.entity.TableColEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class GenConst {

    //java项目接口的基本路径
    public static final String BASE_JAVA_PATH = "/src/main/java/";
    public static final String BASE_RESOURCE_PATH = "/src/main/resources/";

    //模板文件目录
    public static final String TEMPLATE_PATH = "ftl-autocode/src/main/resources/template/";

    //模板文件路径
    public static final String FEIGN_FTL_PATH = "/java/feign.ftl";
    public static final String CTRL_WITH_FEIGN_FTL_PATH = "/java/controller_withfeign.ftl";
    public static final String CTRL_NO_FFEIGN_FTL_PATH = "/java/controller_nofeign.ftl";
    public static final String INFT_SERVICE_FTL_PATH = "/java/inftservice.ftl";
    public static final String IMPL_SERVICE_FTL_PATH = "/java/implservice.ftl";
    public static final String INFT_CACHE_FTL_PATH = "/java/inftcache.ftl";
    public static final String IMPL_CACHE_FTL_PATH = "/java/implcache.ftl";
    public static final String DAO_FTL_PATH = "/java/dao.ftl";
    public static final String ENTITY_PATH = "/java/entity.ftl";
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
    public static final List<TableColEntity> tableColEntitys = Lists.newArrayList();

    //表的注释
    public static String tableComment = "";

    //公共替换的map
    public static final Map<String,Object> commonReplaceMap = Maps.newHashMap();

}
