package com.cloud.ftl.ftltest;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtlTestApplicationTests {

	@Test
	public void contextLoads() {
	}

	/**
	 * 生成AsciiDocs格式文档,并汇总成一个文件
	 * @throws Exception
	 */
	@Test
	public void generateAsciiDocsToFile() throws Exception {
		//    输出Ascii到单文件
		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
				.withMarkupLanguage(MarkupLanguage.ASCIIDOC)
				.withOutputLanguage(Language.ZH)
				.withPathsGroupedBy(GroupBy.TAGS)
				.withGeneratedExamples()
				.withoutInlineSchema()
				.build();

		Swagger2MarkupConverter.from(new URL("http://localhost:9081/v2/api-docs"))
				.withConfig(config)
				.build()
				.toFile(Paths.get("./docs_adoc/autocode"));
	}

}
