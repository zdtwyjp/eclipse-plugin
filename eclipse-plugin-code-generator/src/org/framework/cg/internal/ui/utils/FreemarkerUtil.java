
package org.framework.cg.internal.ui.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.framework.cg.internal.Activator;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtil {
	private static Configuration cfg = null;
	
	public FreemarkerUtil(){
		try {
			init();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 初始化工作
	public void init() throws IOException {
		if(cfg == null){
			cfg = new Configuration();
			// 获取模版相对路径
			URL url = Activator.getDefault().getBundle().getResource("templates");
			URL fileUrl = FileLocator.toFileURL(url);
			File file = new File(fileUrl.getFile());
			// 设置模板文件位置
			cfg.setDirectoryForTemplateLoading(file);
//			cfg.setDirectoryForTemplateLoading(new File("E:/Repository/Eclipse/eclipse-plugin/eclipse-plugin-code-generator/src/templates"));
		}
	}

	public static void process(String ftl, Map<Object, Object> map)
			throws Exception {
		// 使用Configuration实例加载指定模板
		Template template = cfg.getTemplate(ftl, "UTF-8");
		createFile();
		// 合并处理（模板 + 数据模型）
		Writer writer = new OutputStreamWriter(new FileOutputStream(getTempFilePath()), "UTF-8");
		template.process(map, writer);
	}
	
	public static String getTempFilePath(){
		return Constants.TMP_FILE_PATH + Constants.TMP_FILE_NAME;
	}
	
	protected static void createFile(){
		File tmpFile = new File(Constants.TMP_FILE_PATH);
		if(!tmpFile.exists()){
			tmpFile.mkdirs();
			tmpFile = new File(getTempFilePath());
			if(!tmpFile.exists()){
				try {
					tmpFile.createNewFile();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
