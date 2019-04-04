package com.cloud.ftl.ftlautocode.util;

import com.cloud.ftl.ftlautocode.common.GenConst;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;


public class FreemarkerUtil {

	//默认在引入包的情况下，生成的文档是相对于当前项目的。如果是需要自定义指定位置（比如：D:\aProject）,可以在执行自动生成文件前配置FreemarkerUtil.projectBasePath = "D:\\aProject";
	public static String projectBasePath;

	static {
		File directory = new File("");
		projectBasePath = directory.getAbsolutePath();
	}

	/**
	 * 生成文件
	 * @param packagePath 生成的包文件路径(com.cloud.xx.xx)
	 * @param tempPath 模板文件路径(GenConst.CTRL_FTL_PATH)
	 * @param fileName 生成的文件名称
	 * @param createJava 是否是创建java文件，如果不是则创建xml文件
	 * @param data 需要替换的map
	 */
	public static void outputFile(String packagePath,String tempPath,String fileName,Boolean createJava,Boolean isUpdate,Map<String, Object> data) {
		Writer fileWriter = null;
		Reader fileReader = null;
		try {
			//配置模板路径
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
			configuration.setDirectoryForTemplateLoading(new File(GenConst.TEMPLATE_PATH));
			Template temp = configuration.getTemplate(tempPath);
			if(isUpdate){
				//获取自定义代码进行替换
				String fileContent = FileUtil.readFile(new File(getOutPutFilePath(fileName,packagePath,createJava)));
				data.put("customCode",getCustomCode(fileContent));
			}
			fileWriter = new FileWriter(getOutPutFilePath(fileName,packagePath,createJava));
			temp.process(data, fileWriter);
			fileWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 得到生成的文件路径
	 * @param fileName
	 * @param packagePath
	 * @param createJava
	 * @return
	 */
	public static String getOutPutFilePath(String fileName,String packagePath,Boolean createJava){
		File dirFile;
		String outputPath;
		if(createJava){
			dirFile = new File(projectBasePath+ GenConst.BASE_JAVA_PATH+packagePath.replace(GenConst.SPOT, GenConst.SPRIT)+GenConst.SPRIT);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			outputPath = dirFile.toString()+GenConst.SPRIT+fileName + GenConst.JAVA_FILE_SUFFIX;
		}else{
			dirFile = new File(projectBasePath+ GenConst.BASE_RESOURCE_PATH+packagePath.replace(GenConst.SPOT, GenConst.SPRIT)+GenConst.SPRIT);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			outputPath = dirFile.toString()+GenConst.SPRIT+fileName + GenConst.XML_FILE_SUFFIX;
		}
		return outputPath;
	}

	/**
	 * 得到自定义代码
	 * @param fileContent
	 * @return
	 */
	public static String getCustomCode(String fileContent) {
		String result = "";
		if(fileContent != null) {
			String beginCode = GenConst.JAVA_CUSTOM_BEGIN_CODE;
			String endCode = GenConst.JAVA_CUSTOM_END_CODE;
			int startIndex = fileContent.indexOf(beginCode) + beginCode.length();
			int endIndex = fileContent.indexOf(endCode);
			if(startIndex != -1 && endIndex != -1) {
				result = fileContent.substring(startIndex, endIndex);
				startIndex = result.indexOf("\n") + "\n".length();
				endIndex = result.lastIndexOf("\r");
				if(startIndex != -1 && endIndex != -1) {
					result = result.substring(startIndex, endIndex);
				}
			}
		}
		return result;
	}

}
