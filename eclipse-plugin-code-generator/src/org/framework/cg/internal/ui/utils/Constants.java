package org.framework.cg.internal.ui.utils;

import org.eclipse.jdt.ui.JavaUI;

public class Constants {
	/**
	 * WizardMessage
	 */
	public static final String WizardPage_Common_browse = "Browse...";
	public static final String OpenGeneratorWizardAction_text = "Generator";
	public static final String OpenGeneratorWizardAction_description = "Opens the new configuration wizards";
	public static final String OpenGeneratorWizardAction_tooltip = "Opens the new configuration wizard";
	public static final String GeneratorCreationWizard_title = "Code Generator";
	public static final String GeneratorWizardPage_title = "Code Configuration";
	public static final String GeneratorWizardPage_description = "Create file(JSP Action Mng MngImpl Dao and DaoImpl).";
	public static final String GeneratorWizardPage_jsp_config = "JSP Configuration";
	public static final String GeneratorWizardPage_jsp_config_filename = "&File name:";
	public static final String GeneratorWizardPage_jsp_config_filepath = "&File path:";
	public static final String GeneratorWizardPage_java_config = "JAVA Configuration";
	public static final String GeneratorWizardPage_java_config_filename = "&File name:";
	public static final String GeneratorWizardPage_java_config_filepath = "&File path:";
	
	/**
	 * JavaHelpContext
	 */
	public static final String PREFIX = JavaUI.ID_PLUGIN + '.';
	public static final String OPEN_CLASS_WIZARD_ACTION = PREFIX + "open_class_wizard_action";
	
	
	/**
	 * Page name
	 */
	public static final String PAGE_JSP_DETAIL = "detail.jsp";
	public static final String PAGE_JSP_MAIN = "main.jsp";
	public static final String PAGE_JAVA_ACT = "Act.java";
	public static final String PAGE_JAVA_DAO = "Dao.java";
	public static final String PAGE_JAVA_DAOIMPL = "DaoImpl.java";
	public static final String PAGE_JAVA_MNG = "Mng.java";
	public static final String PAGE_JAVA_MNGIMPL = "MngImpl.java";
	
	/**
	 * Tempalte name
	 */
	public static final String TEMPLATE_PAGE_DETAIL = "detail_jsp.ftl";
	public static final String TEMPLATE_PAGE_MAIN = "main_jsp.ftl";
	public static final String TEMPLATE_PAGE_ACT = "act.ftl";
	public static final String TEMPLATE_PAGE_DAO = "dao.ftl";
	public static final String TEMPLATE_PAGE_DAOIMPL = "daoImpl.ftl";
	public static final String TEMPLATE_PAGE_MNG = "mng.ftl";
	public static final String TEMPLATE_PAGE_MNGIMPL = "mngImpl.ftl";
	
	/**
	 * PrePath
	 */
	public static final String PRE_PATH = "/admin";
	
	/**
	 * tmp file path and name
	 */
	public static final String TMP_FILE_PATH = "D:/tmp/";
	public static final String TMP_FILE_NAME = "freemarker.tmp";
	
	/**
	 * Package name
	 */
	public static final String PACKAGE_ACT = "act";
	public static final String PACKAGE_MNG = "mng";
	public static final String PACKAGE_IMPL = "impl";
	public static final String PACKAGE_DAO = "dao";
	
	/**
	 * Column name
	 */
	public static final String COLUMN_NAME_NUMBER = "No";
	public static final String COLUMN_NAME_FIELD_NAME = "FieldName";
	public static final String COLUMN_NAME_FIELD_TYPE = "FieldType";
	public static final String COLUMN_NAME_NULLABLE = "Nullable";
	public static final String COLUMN_NAME_LABEL = "Label";
	public static final String COLUMN_NAME_TAG_TYPE = "TagType";
	public static final String COLUMN_NAME_QUERYABLE = "Queryable";
	public static final String COLUMN_NAME_COLUMNDISPLAYABLE = "ColumnDisplayable";
	public static final String COLUMN_NAME_UNIQUE = "Unique";
	
	/**
	 * Tag name
	 */
	public static final String TAG_NAME_INPUT = "input";
	public static final String TAG_NAME_SELECT = "select";
	public static final String TAG_NAME_DATEPICKER = "datePicker";
	public static final String TAG_NAME_TEXTAREA = "textarea";
	public static final String TAG_NAME_MULTISELECT = "multiSelect";
	public static final String TAG_NAME_MULTISELECT_11 = "multiSelect1.1";
	
	/**
	 * default path
	 */
	public static final String DEFAULT_PATH_JAVA = "/src/main/java/com/tibet/app";
	public static final String DEFAULT_PATH_JSP = "/src/main/webapp/WEB-INF/views/admin";

}
