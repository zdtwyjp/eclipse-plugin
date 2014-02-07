package org.framework.cg.internal.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.util.CoreUtility;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.framework.cg.internal.ui.utils.Constants;
import org.framework.cg.internal.ui.utils.FreemarkerUtil;
import org.framework.cg.internal.ui.utils.StringUtil;
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
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
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

	private void doFinish(String containerName, String fileName,
			IProgressMonitor monitor) throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		// if (!resource.exists() || !(resource instanceof IContainer)) {
		// throwCoreException("Container \"" + containerName +
		// "\" does not exist.");
		// }
		// IContainer container = (IContainer) resource;

		IPath containerNamePath = getOutputLocation(containerName);

		if (!root.exists(containerNamePath)) {
			IFolder folder = root.getFolder(containerNamePath);
			CoreUtility.createDerivedFolder(folder, true, true,
					new SubProgressMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}

//		IFolder folder = root.getFolder(containerNamePath);
//		String jspContent = createOutputStr(fileName);

		/**
		 * Create JSP.
		 */
		/** Create main.jsp*/
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(Constants.PAGE_JSP_MAIN));
		try {
			InputStream stream = openContentStream(Constants.TEMPLATE_PAGE_MAIN);
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/** Create detail.jsp*/
		final IFile detailFile = container.getFile(new Path(Constants.PAGE_JSP_DETAIL));
		try {
			InputStream stream = openContentStream(Constants.TEMPLATE_PAGE_DETAIL);
			if (detailFile.exists()) {
				detailFile.setContents(stream, true, true, monitor);
			} else {
				detailFile.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		
//		File tx = new File(path);
//		try {
//			FileOutputStream output = new FileOutputStream(tx);
//			output.write(jspContent.getBytes());
//			output.flush();
//			output.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// final IFile file = container.getFile(new Path(fileName));
		// try {
		// InputStream stream = openContentStream();
		// if (file.exists()) {
		// file.setContents(stream, true, true, monitor);
		// } else {
		// file.create(stream, true, monitor);
		// }
		// stream.close();
		// } catch (IOException e) {
		// }
		// monitor.worked(1);
		// monitor.setTaskName("Opening file for editing...");
		// getShell().getDisplay().asyncExec(new Runnable() {
		// public void run() {
		// IWorkbenchPage page =
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		// try {
		// IDE.openEditor(page, file, true);
		// } catch (PartInitException e) {
		// }
		// }
		// });
		// monitor.worked(1);
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR,
				"eclipse-plugin-code-generator", IStatus.OK, message, null);
		throw new CoreException(status);
	}
	
	private InputStream openContentStream(String ftl) {
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		ICompilationUnit compilationUnit = page.getCompilationUnit();
		String className = compilationUnit.getElementName();
		className = StringUtil.getClassName(className);
		try {
			IType[] allTypes = compilationUnit.getAllTypes();
			for(IType iType : allTypes) {
				IField[] tempFields = iType.getFields();
				for(IField field : tempFields) {
					String fieldName = field.getElementName();
					String typeSignature = field.getTypeSignature();
					if (fieldName.endsWith("Id")) {
						continue;
					}
					log.debug("typeSignature > " + typeSignature);
					jo.put("fieldName", fieldName);
					jo.put("fieldType", StringUtil.convertFieldType(typeSignature));
					ja.add(jo);
				}
			}
		}catch(JavaModelException e1) {
			e1.printStackTrace();
		}
		Map map = new HashMap();
		map.put("appname", "${appname}");
		map.put("gkGrid", "${gkGrid}");
		String jsonFields = ja.toString();
		System.out.println(jsonFields);
		map.put("fields", jsonFields);
		String prePath = StringUtil.createPrepath(className);
		map.put("prePath", prePath);
		String lowerCaseClassName = StringUtil.classNameToLowerCase(className);
		System.out.println(lowerCaseClassName);
		map.put("lowerCaseClassName", lowerCaseClassName);
		
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

//	private String createOutputStr(String fileName) {
//		List<JavaModel> fieldsList = new ArrayList<JavaModel>();
//		Class selectedClass = page.getSelectedClass();
//		String className = selectedClass.getSimpleName();
//		System.out.println(selectedClass.getSimpleName());
//		Field[] fields = selectedClass.getDeclaredFields();
//		JavaModel jm = null;
//		for (Field field : fields) {
//			String fieldName = field.getName();
//			Class fieldType = field.getType();
//			System.out.println(fieldName);
//			System.out.println(fieldType);
//			if (fieldName.endsWith("Id")) {
//				continue;
//			}
//			jm = new JavaModel();
//			jm.setFieldName(fieldName);
//			jm.setFieldType(fieldType.toString());
//			fieldsList.add(jm);
//		}
//
//		String pre = className.substring(0, 1);
//		className = pre.toLowerCase() + className.substring(1);
//		return TagTemplateUtil.generateJspPage(className, fieldsList);
//
//	}
//	
//	private String createProperties(String fileName) {
//		List<JavaModel> fieldsList = new ArrayList<JavaModel>();
//		Class selectedClass = page.getSelectedClass();
//		String className = selectedClass.getSimpleName();
//		Field[] fields = selectedClass.getDeclaredFields();
//		JavaModel jm = null;
//		for (Field field : fields) {
//			String fieldName = field.getName();
//			Class fieldType = field.getType();
//			if (fieldName.endsWith("Id")) {
//				continue;
//			}
//			jm = new JavaModel();
//			jm.setFieldName(fieldName);
//			jm.setFieldType(fieldType.toString());
//			fieldsList.add(jm);
//		}
//		String pre = className.substring(0, 1);
//		className = pre.toLowerCase() + className.substring(1);
//		return TagTemplateUtil.generateJspPage(className, fieldsList);
//
//	}

}
