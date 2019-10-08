package com.cloud.ftl.ftltest;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.util.PoiMergeCellUtil;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtlTestApplicationTests {

	Map<String, Object> value = new HashMap<String, Object>();

	@Test
	public void one() throws Exception {
		TemplateExportParams params = new TemplateExportParams(
				"E:\\myProject\\autocode-parnet\\ftl-test\\src\\main\\resources\\temp\\for_Col.xlsx", true);
		params.setColForEach(true);
		Workbook book = ExcelExportUtil.exportExcel(params, value);
		//PoiMergeCellUtil.mergeCells(book.getSheetAt(0), 1, 0,1);
		FileOutputStream fos = new FileOutputStream("D:/ExcelExportTemplateColFeTest_one.xlsx");
		book.write(fos);
		fos.close();

	}

	@Before
	public void testBefore() {
		List<Map<String, Object>> colList = new ArrayList<Map<String, Object>>();
		//先处理表头
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "小明挑战");
		map.put("zq", "正确");
		map.put("cw", "错误");
		map.put("tj", "统计");
		map.put("zqmk", "t.zq_xm");
		map.put("cwmk", "t.cw_xm");
		map.put("tjmk", "t.tj_xm");
		colList.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "小红挑战");
		map.put("zq", "正确");
		map.put("cw", "错误");
		map.put("tj", "统计");
		map.put("zqmk", "n:t.zq_xh");
		map.put("cwmk", "n:t.cw_xh");
		map.put("tjmk", "n:t.tj_xh");
		colList.add(map);

		value.put("colList", colList);

		List<Map<String, Object>> valList = new ArrayList<Map<String, Object>>();
		map = new HashMap<String, Object>();
		map.put("one", "运动");
		map.put("two", "跑步");
		map.put("zq_xm", 1);
		map.put("cw_xm", 2);
		map.put("tj_xm", 3);
		map.put("zq_xh", 4);
		map.put("cw_xh", 2);
		map.put("tj_xh", 6);
		valList.add(map);
		map = new HashMap<String, Object>();
		map.put("one", "运动");
		map.put("two", "跳高");
		map.put("zq_xm", 1);
		map.put("cw_xm", 2);
		map.put("tj_xm", 3);
		map.put("zq_xh", 4);
		map.put("cw_xh", 2);
		map.put("tj_xh", 6);
		valList.add(map);
		map = new HashMap<String, Object>();
		map.put("one", "文化");
		map.put("two", "数学");
		map.put("zq_xm", 1);
		map.put("cw_xm", 2);
		map.put("tj_xm", 3);
		map.put("zq_xh", 4);
		map.put("cw_xh", 2);
		map.put("tj_xh", 6);
		valList.add(map);

		value.put("valList", valList);
	}

//	@Test
//	public void contextLoads() {
//	}

	/**
	 * 生成AsciiDocs格式文档,并汇总成一个文件
	 * @throws Exception
	 */
//	@Test
//	public void generateAsciiDocsToFile() throws Exception {
//		//    输出Ascii到单文件
//		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
//				.withMarkupLanguage(MarkupLanguage.ASCIIDOC)
//				.withOutputLanguage(Language.ZH)
//				.withPathsGroupedBy(GroupBy.TAGS)
//				.withGeneratedExamples()
//				.withoutInlineSchema()
//				.build();
//
//		Swagger2MarkupConverter.from(new URL("http://localhost:9081/v2/api-docs"))
//				.withConfig(config)
//				.build()
//				.toFile(Paths.get("./docs_adoc/autocode"));
//	}

//	Map<String, Object> value = new HashMap<String, Object>();
//
//	@Test
//	public void one() throws Exception {
//		TemplateExportParams params = new TemplateExportParams(
//				"E:\\myProject\\autocode-parnet\\ftl-test\\src\\main\\resources\\temp\\for_Col.xlsx", true);
//		params.setColForEach(true);
//		Workbook book = ExcelExportUtil.exportExcel(params, value);
//		PoiMergeCellUtil.mergeCells(book.getSheetAt(0), 1, 0,1);
//		FileOutputStream fos = new FileOutputStream("D:/ExcelExportTemplateColFeTest_one.xlsx");
//		book.write(fos);
//		fos.close();
//	}
//
//	@Before
//	public void testBefore() {
//		List<Map<String, Object>> colList = new ArrayList<Map<String, Object>>();
//		//先处理表头
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "小明挑战");
//		map.put("zq", "正确");
//		map.put("cw", "错误");
//		map.put("tj", "统计");
//		map.put("zqmk", "t.zq_xm");
//		map.put("cwmk", "t.cw_xm");
//		map.put("tjmk", "t.tj_xm");
//		colList.add(map);
//
//		map = new HashMap<String, Object>();
//		map.put("name", "小红挑战");
//		map.put("zq", "正确");
//		map.put("cw", "错误");
//		map.put("tj", "统计");
//		map.put("zqmk", "n:t.zq_xh");
//		map.put("cwmk", "n:t.cw_xh");
//		map.put("tjmk", "n:t.tj_xh");
//		colList.add(map);
//
//		map = new HashMap<String, Object>();
//		map.put("name", "小三挑战");
//		map.put("zq", "正确");
//		map.put("cw", "错误");
//		map.put("tj", "统计");
//		map.put("zqmk", "n:t.zq_xh");
//		map.put("cwmk", "n:t.cw_xh");
//		map.put("tjmk", "n:t.tj_xh");
//		colList.add(map);
//
//		value.put("colList", colList);
//
//		List<Map<String, Object>> valList = new ArrayList<Map<String, Object>>();
//		map = new HashMap<String, Object>();
//		map.put("one", "运动");
//		map.put("two", "跑步");
//		map.put("zq_xm", 1);
//		map.put("cw_xm", 2);
//		map.put("tj_xm", 3);
//		map.put("zq_xh", 4);
//		map.put("cw_xh", 2);
//		map.put("tj_xh", 6);
//		valList.add(map);
//		map = new HashMap<String, Object>();
//		map.put("one", "运动");
//		map.put("two", "跑步");
//		map.put("zq_xm", 1);
//		map.put("cw_xm", 2);
//		map.put("tj_xm", 3);
//		map.put("zq_xh", 4);
//		map.put("cw_xh", 2);
//		map.put("tj_xh", 6);
//		valList.add(map);
//		map = new HashMap<String, Object>();
//		map.put("one", "运动");
//		map.put("two", "跳高");
//		map.put("zq_xm", 1);
//		map.put("cw_xm", 2);
//		map.put("tj_xm", 3);
//		map.put("zq_xh", 4);
//		map.put("cw_xh", 2);
//		map.put("tj_xh", 6);
//		valList.add(map);
//		map = new HashMap<String, Object>();
//		map.put("one", "运动");
//		map.put("two", "跳高");
//		map.put("zq_xm", 1);
//		map.put("cw_xm", 2);
//		map.put("tj_xm", 3);
//		map.put("zq_xh", 4);
//		map.put("cw_xh", 2);
//		map.put("tj_xh", 6);
//		valList.add(map);
//		value.put("valList", valList);
//	}
}
