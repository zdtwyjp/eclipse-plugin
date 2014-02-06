package org.framework.cg.internal.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.framework.cg.internal.ui.utils.Constants;

public class GeneratorWizardPage extends NewTypeWizardPage {
	private final static String PAGE_NAME= "GeneratorWizardPage";
	private Text containerText;

	private Text fileText;

	private ISelection selection;
	
	private Class selectedClass;

	public GeneratorWizardPage(ISelection selection) {
		super(true, PAGE_NAME);
		setTitle(Constants.GeneratorWizardPage_title);
		setDescription(Constants.GeneratorWizardPage_description);
		this.selection = selection;
	}
	
	

	public Class getSelectedClass() {
		return selectedClass;
	}



	@Override
	public void createControl(Composite parent) {
		/**
		 * Outside Container
		 */
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 4;
		layout.verticalSpacing = 9;
//		container.setLayout(initGridLayout(new GridLayout(1, false), true));
//		container.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		
		/**
		 * Inner Container
		 */
		/** File name*/ 
		Label label = new Label(container, SWT.NULL);
		label.setText(Constants.GeneratorWizardPage_jsp_config_filename);

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		fileText.setLayoutData(gd);
		fileText.setEditable(false);
//		fileText.addModifyListener(new ModifyListener() {
//			public void modifyText(ModifyEvent e) {
//				dialogChanged();
//			}
//		});
		
		/** JSP Configuration*/ 
		Group jspGrp = new Group(container, SWT.NONE);
		jspGrp.setText(Constants.GeneratorWizardPage_jsp_config);
		layout = new GridLayout();
		jspGrp.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		jspGrp.setLayoutData(gd);
		
		label = new Label(jspGrp, SWT.NULL);
		label.setText(Constants.GeneratorWizardPage_jsp_config_filepath);

		containerText = new Text(jspGrp, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button button = new Button(jspGrp, SWT.PUSH);
		button.setText(Constants.WizardPage_Common_browse);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		
		initialize();
		dialogChanged();
		setControl(container);

	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer){
					container = (IContainer) obj;
				}else{
					container = ((IResource) obj).getParent();
				}
				containerText.setText(container.getFullPath().toString());
			} else if (obj instanceof IJavaElement) {
				IJavaElement javaElement = (IJavaElement) obj;
				fileText.setText(javaElement.getPath().toString());
				this.selectedClass = javaElement.getClass();
			}
		}
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getContainerName()));
//		String fileName = getFileName();

		if (getContainerName().length() == 0) {
			updateInfoStatus("File container must be specified");
			return;
		}
//		if (container == null
//				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
//			updateInfoStatus("File container must exist");
//			return;
//		}
		
		if (!container.isAccessible()) {
			updateInfoStatus("Project must be writable");
			return;
		}
		
//		if (fileName.length() == 0) {
//			updateInfoStatus("File name must be specified");
//			return;
//		}
//		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
//			updateInfoStatus("File name must be valid");
//			return;
//		}
//		int dotLoc = fileName.lastIndexOf('.');
//		if (dotLoc != -1) {
//			String ext = fileName.substring(dotLoc + 1);
//			if (ext.equalsIgnoreCase("txt") == false) {
//				updateInfoStatus("File extension must be \"txt\"");
//				return;
//			}
//		}
		updateInfoStatus(null);
	}

	private void updateInfoStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFileName() {
		return fileText.getText();
	}
	
	private GridLayout initGridLayout(GridLayout layout, boolean margins) {
		layout.horizontalSpacing= convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.verticalSpacing= convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		if (margins) {
			layout.marginWidth= convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
			layout.marginHeight= convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		} else {
			layout.marginWidth= 0;
			layout.marginHeight= 0;
		}
		return layout;
	}
}
