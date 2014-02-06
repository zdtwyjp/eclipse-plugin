package org.framework.cg.internal.ui.utils;

import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;

public class FreemarkerUtil {
	private static Configuration cfg = null;

	// 初始化工作
	public void init() throws Exception {
		cfg = new Configuration();
		// 设置模板文件位置
		cfg.setDirectoryForTemplateLoading(new File("src/templates"));
	}

	public static void process(String ftl, SimpleHash m, OutputStream stream) throws Exception {
		// 使用Configuration实例加载指定模板
		Template template = cfg.getTemplate(ftl);
		// 合并处理（模板 + 数据模型）
		template.process(m, new OutputStreamWriter(System.out));
	}

}
