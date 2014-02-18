package org.framework.cg.internal.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.util.CoreUtility;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.framework.cg.internal.ui.utils.Constants;
import org.framework.cg.internal.ui.utils.FreemarkerUtil;
import org.framework.cg.internal.ui.utils.ServiceUtil;
import org.framework.cg.internal.ui.utils.StringUtil;
import org.framework.cg.internal.ui.vo.FieldModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorCreationWizard extends Wizard implements INewWizard {

	private GeneratorWizardPage page;
	private IStructuredSelection selection;
	private FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
	private Logger log = LoggerFactory.getLogger(GeneratorCreationWizard.class);

	public GeneratorCreationWizard(GeneratorWizardPage page) {
		setDialogSettings(JavaPlugin.getDefault().getDialogSettings());
		setWindowTitle(Constants.GeneratorCreationWizard_title);
		this.page = page;
	}

	public GeneratorCreationWizard() {
		this(null);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}

	public IStructuredSelection getSelection() {
		return selection;
	}

	@Override
	public void addPages() {
		super.addPages();
		if (page == null) {
			page = new GeneratorWizardPage(selection);
			page.setWizard(this);
		}
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		final String javaPath = page.getJavaPath();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, javaPath, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error",
					realException.getMessage());
			return false;
		}
		return true;
	}
	
	private void createJSPFile(IWorkspaceRoot root, IProgressMonitor monitor, 
			String containerName) throws CoreException{
		IResource resource = root.findMember(new Path(containerName));
		IPath containerNamePath = getOutputLocation(containerName);
		/** JSP file path*/
		if (!root.exists(containerNamePath)) {
			IFolder folder = root.getFolder(containerNamePath);
			CoreUtility.createDerivedFolder(folder, true, true,
					new SubProgressMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}
		root = ResourcesPlugin.getWorkspace().getRoot();
		resource = root.findMember(new Path(containerName));
		/** Create main.jsp*/
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(Constants.PAGE_JSP_MAIN));
		InputStream stream = openContentStream(Constants.TEMPLATE_PAGE_MAIN);
		if (file.exists()) {
//			file.setContents(stream, true, true, monitor);
			throw new RuntimeException("main.jsp is exists!");
		} else {
			file.create(stream, true, monitor);
		}
		try {
			stream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		/** Create detail.jsp*/
		final IFile detailFile = container.getFile(new Path(Constants.PAGE_JSP_DETAIL));
		stream = openContentStream(Constants.TEMPLATE_PAGE_DETAIL);
		if (detailFile.exists()) {
//			detailFile.setContents(stream, true, true, monitor);
			throw new RuntimeException("detail.jsp is exists!");
		} else {
			detailFile.create(stream, true, monitor);
		}
		try {
			stream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createJavaFile(IWorkspaceRoot root, IProgressMonitor monitor, 
			String javaPath, String className, 
			String javaPageName, String templateName) throws CoreException{
		
		/** JAVA file path*/
		IResource javaPathResource = root.findMember(new Path(javaPath));
		IPath newJavaPath = getOutputLocation(javaPath);
		if (!root.exists(newJavaPath)){
			IFolder folder = root.getFolder(newJavaPath);
			CoreUtility.createDerivedFolder(folder, true, true,
					new SubProgressMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}
		root = ResourcesPlugin.getWorkspace().getRoot();
		javaPathResource = root.findMember(new Path(javaPath));
		
		IContainer actContainer = (IContainer) javaPathResource;
		final IFile actFile = actContainer.getFile(new Path(className + javaPageName));
		InputStream stream = openContentStream(templateName);
		if (actFile.exists()) {
//			actFile.setContents(stream, true, true, monitor);
			throw new RuntimeException("java file is exists!");
		} else {
			actFile.create(stream, true, monitor);
		}
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String checkJSPFilePath(String filePath, String lowerCaseClassName){
		if(StringUtil.isEmpty(filePath) || StringUtil.isEmpty(lowerCaseClassName)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String newPath = filePath;
		String pre = lowerCaseClassName.substring(0, 1);
		if(filePath.endsWith(pre) || filePath.endsWith(pre+"/")){
			if(!filePath.endsWith("/")){
				newPath += "/";
			}
			newPath += lowerCaseClassName;
		}else if(!filePath.endsWith(lowerCaseClassName) && !filePath.endsWith(lowerCaseClassName+"/")){
			if(!filePath.endsWith("/")){
				newPath += "/";
			}
			newPath += pre + "/" + lowerCaseClassName;
		}
		return newPath;
	}
	
	private String checkJavaFilePath(String filePath, String lowerCaseClassName){
		if(StringUtil.isEmpty(filePath) || StringUtil.isEmpty(lowerCaseClassName)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String newPath = filePath;
		String pre = lowerCaseClassName.substring(0, 1);
		if(!filePath.endsWith(pre) && !filePath.endsWith(pre+"/")){
			if(!filePath.endsWith("/")){
				newPath += "/";
			}
			newPath += pre;
		}
		return newPath;
	}

	private void doFinish(String containerName, String fileName, String javaPath,
			IProgressMonitor monitor) throws CoreException {
		if (containerName.length() == 0) {
			throwCoreException("File path must be specified");
			return;
		}
		if (javaPath.length() == 0) {
			throwCoreException("File path must be specified");
			return;
		}
		
		monitor.beginTask("Creating " + fileName, 2);
		ICompilationUnit compilationUnit = page.getCompilationUnit();
		String className = compilationUnit.getElementName();
		className = ServiceUtil.getClassName(className);
		String lowerCaseClassName = ServiceUtil.classNameToLowerCase(className);
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		
		/** Create JSP File*/
//		String jspContainerName = containerName + "/" + lowerCaseClassName;
		String jspContainerName = checkJSPFilePath(containerName, lowerCaseClassName);
		createJSPFile(root, monitor, jspContainerName);
		
		/** Create Act File*/
		javaPath = checkJavaFilePath(javaPath, lowerCaseClassName);
		String actJavaPath = javaPath + "/" + Constants.PACKAGE_ACT;
		createJavaFile(root, monitor, actJavaPath, className, Constants.PAGE_JAVA_ACT, Constants.TEMPLATE_PAGE_ACT);
		
		/** Create Mng File*/
		String mngJavaPath = javaPath + "/" + Constants.PACKAGE_MNG;
		createJavaFile(root, monitor, mngJavaPath, className, Constants.PAGE_JAVA_MNG, Constants.TEMPLATE_PAGE_MNG);
		
		/** Create MngImpl File*/
		String mngImplJavaPath = javaPath + "/" + Constants.PACKAGE_MNG + "/" + Constants.PACKAGE_IMPL;
		createJavaFile(root, monitor, mngImplJavaPath, className, Constants.PAGE_JAVA_MNGIMPL, Constants.TEMPLATE_PAGE_MNGIMPL);
		
		/** Create Dao File*/
		String daoJavaPath = javaPath + "/" + Constants.PACKAGE_DAO;
		createJavaFile(root, monitor, daoJavaPath, className, Constants.PAGE_JAVA_DAO, Constants.TEMPLATE_PAGE_DAO);
		
		/** Create DaoImpl File*/
		String daoImplJavaPath = javaPath + "/" + Constants.PACKAGE_DAO + "/" + Constants.PACKAGE_IMPL;
		createJavaFile(root, monitor, daoImplJavaPath, className, Constants.PAGE_JAVA_DAOIMPL, Constants.TEMPLATE_PAGE_DAOIMPL);
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR,
				"eclipse-plugin-code-generator", IStatus.OK, message, null);
		throw new CoreException(status);
	}
	
	private InputStream openContentStream(String ftl) {
		TableViewer tableViewer = page.getTableViewer();
		@SuppressWarnings("unchecked")
		List<FieldModel> list = (List<FieldModel>)tableViewer.getInput();
		JSONArray ja = new JSONArray();
		ICompilationUnit compilationUnit = page.getCompilationUnit();
		String className = compilationUnit.getElementName();
		className = ServiceUtil.getClassName(className);
		for(FieldModel fm : list) {
			JSONObject jo = fm.toJSONObject();
			ja.add(jo);
		}
		Map map = new HashMap();
		map.put("appname", "${appname}");
		map.put("gkGrid", "${gkGrid}");
		String jsonFields = ja.toString();
		log.info("jsonFields > " + jsonFields);
		map.put("fields", jsonFields);
		String prePath = ServiceUtil.createPrepath(className);
		map.put("prePath", prePath);
		String lowerCaseClassName = ServiceUtil.classNameToLowerCase(className);
		map.put("lowerCaseClassName", lowerCaseClassName);
		map.put("className", className);
		String packageStr = ServiceUtil.getPackageNameFromClassName(className);
		map.put("package", packageStr);
		map.put("mappingPath", prePath);
		String ajaxGridSql = ServiceUtil.createAjaxGridSql(list, className);
		map.put("ajaxGridSql", ajaxGridSql);
		String queryParam = ServiceUtil.createQueryParam(list);
		map.put("queryParam", queryParam);
		log.info("Map > " + map.toString());
		try {
			freemarkerUtil.process(ftl, map);
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		try {
			InputStream is = new FileInputStream(new File(FreemarkerUtil.getTempFilePath()));
			return is;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream("".getBytes());
	}

	public IPath getOutputLocation(String fold) {
		return new Path(fold).makeAbsolute();
	}

}
