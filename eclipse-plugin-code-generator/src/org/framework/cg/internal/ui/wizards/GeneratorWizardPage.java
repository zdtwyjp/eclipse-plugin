package org.framework.cg.internal.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.framework.cg.internal.ui.utils.Constants;
import org.framework.cg.internal.ui.utils.ServiceUtil;
import org.framework.cg.internal.ui.utils.StringUtil;
import org.framework.cg.internal.ui.vo.ContentProvider;
import org.framework.cg.internal.ui.vo.MyCellModifier;
import org.framework.cg.internal.ui.vo.Sorter;
import org.framework.cg.internal.ui.vo.TableLabelProvider;

public class GeneratorWizardPage extends NewTypeWizardPage {
	private final static String PAGE_NAME= "GeneratorWizardPage";
	private Text containerText;

	private Text fileText;
	
	private Text javaPathText;

	private ISelection selection;
	
	private ICompilationUnit compilationUnit;
	
	private TableViewer tableViewer;

	public GeneratorWizardPage(ISelection selection) {
		super(true, PAGE_NAME);
		setTitle(Constants.GeneratorWizardPage_title);
		setDescription(Constants.GeneratorWizardPage_description);
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		initCompilationUnit();
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
		
		/** TableView Configuration*/
		tableViewer = new TableViewer(container, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		
		final Table table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		/** Autoable width*/
		table.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent arg0) {
                TableColumn[] columns = table.getColumns();
                 int clientWidth = table.getBounds().width;
                for(int i=0;i<columns.length;i++){
                    columns[i].setWidth((clientWidth)/columns.length);
                }
            }
        });
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		gd.heightHint = 250;
		gd.minimumHeight = 100;
		table.setLayoutData(gd);

		final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn.setWidth(39);
		newColumnTableColumn.setText(Constants.COLUMN_NAME_NUMBER);
		newColumnTableColumn.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.NO_ASC : Sorter.NO_DESC);
				asc = !asc;
			}
		});

		final TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn_1.setWidth(85);
		newColumnTableColumn_1.setText(Constants.COLUMN_NAME_FIELD_NAME);
		newColumnTableColumn_1.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.FIELD_NAME_ASC : Sorter.FIELD_NAME_DESC);
				asc = !asc;
			}
		});
		
		final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn_2.setWidth(41);
		newColumnTableColumn_2.setText(Constants.COLUMN_NAME_LABEL);
		newColumnTableColumn_2.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.LABLE_ASC : Sorter.LABLE_DESC);
				asc = !asc;
			}
		});
		
		final TableColumn newColumnTableColumn_3 = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn_3.setWidth(43);
		newColumnTableColumn_3.setText(Constants.COLUMN_NAME_FIELD_TYPE);
		newColumnTableColumn_3.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.FIELD_TYPE_ASC : Sorter.FIELD_TYPE_DESC);
				asc = !asc;
			}
		});
		
		final TableColumn newColumnTableColumn_4 = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn_4.setWidth(126);
		newColumnTableColumn_4.setText(Constants.COLUMN_NAME_TAG_TYPE);
		newColumnTableColumn_4.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.TAG_TYPE_ASC : Sorter.TAG_TYPE_DESC);
				asc = !asc;
			}
		});
		
		final TableColumn newColumnTableColumn_5 = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn_5.setWidth(126);
		newColumnTableColumn_5.setText(Constants.COLUMN_NAME_NULLABLE);
		newColumnTableColumn_5.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.NULLABLE_ASC : Sorter.NULLABLE_DESC);
				asc = !asc;
			}
		});
		
		final TableColumn newColumnTableColumn_6 = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn_6.setWidth(126);
		newColumnTableColumn_6.setText(Constants.COLUMN_NAME_QUERYABLE);
		newColumnTableColumn_6.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.QUERYABLE_ASC : Sorter.QUERYABLE_DESC);
				asc = !asc;
			}
		});
		
		final TableColumn newColumnTableColumn_7 = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn_7.setWidth(126);
		newColumnTableColumn_7.setText(Constants.COLUMN_NAME_COLUMNDISPLAYABLE);
		newColumnTableColumn_7.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.COLUMNDISPLAYABLE_ASC : Sorter.COLUMNDISPLAYABLE_DESC);
				asc = !asc;
			}
		});
		
		final TableColumn newColumnTableColumn_8 = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn_8.setWidth(126);
		newColumnTableColumn_8.setText(Constants.COLUMN_NAME_UNIQUE);
		newColumnTableColumn_8.addSelectionListener(new SelectionAdapter(){
			boolean asc = true;
			public void widgetSelected(SelectionEvent e){
				tableViewer.setSorter(asc ? Sorter.UNIQUE_ASC : Sorter.UNIQUE_DESC);
				asc = !asc;
			}
		});
		
		 
		tableViewer.setContentProvider(new ContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setInput(ServiceUtil.createFieldModelFromClass(this.compilationUnit));
		
		tableViewer.setColumnProperties(new String[]{
				Constants.COLUMN_NAME_NUMBER, 
				Constants.COLUMN_NAME_FIELD_NAME, 
				Constants.COLUMN_NAME_LABEL, 
				Constants.COLUMN_NAME_FIELD_TYPE, 
				Constants.COLUMN_NAME_TAG_TYPE,
				Constants.COLUMN_NAME_NULLABLE,
				Constants.COLUMN_NAME_QUERYABLE,
				Constants.COLUMN_NAME_COLUMNDISPLAYABLE,
				Constants.COLUMN_NAME_UNIQUE
		});
		CellEditor[] cellEditor = new CellEditor[9];
		cellEditor[0] = null;
		cellEditor[1] = null;
		cellEditor[2] = new TextCellEditor(tableViewer.getTable());
		cellEditor[3] = null;
		cellEditor[4] = new ComboBoxCellEditor(tableViewer.getTable(), MyCellModifier.TAGS, SWT.READ_ONLY);
		cellEditor[5] = new CheckboxCellEditor(tableViewer.getTable());
		cellEditor[6] = new CheckboxCellEditor(tableViewer.getTable());
		cellEditor[7] = new CheckboxCellEditor(tableViewer.getTable());
		cellEditor[8] = new CheckboxCellEditor(tableViewer.getTable());
		tableViewer.setCellEditors(cellEditor);
		ICellModifier modifier = new MyCellModifier(tableViewer);
		tableViewer.setCellModifier(modifier);
//		Text text = (Text)cellEditor[3].getControl();
//		text.addVerifyListener(new VerifyListener(){
//			public void verifyText(VerifyEvent e){
//				String inStr = e.text;
//				if (inStr.length() > 0){
//					try{
//						Integer.parseInt(inStr);
//						e.doit = true;
//					}catch(Exception ep){
//						e.doit = false;
//					}
//				}
//			}
//		});
		
//		 MessageDialog.openInformation(null,"Eclipse-plugin-code-generator","Run was executed.1333444");
		
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
		
		/** JAVA Configuration*/ 
		Group javaGrp = new Group(container, SWT.NONE);
		javaGrp.setText(Constants.GeneratorWizardPage_java_config);
		layout = new GridLayout();
		javaGrp.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		javaGrp.setLayoutData(gd);
		
		label = new Label(javaGrp, SWT.NULL);
		label.setText(Constants.GeneratorWizardPage_java_config_filepath);

		javaPathText = new Text(javaGrp, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		javaPathText.setLayoutData(gd);
		javaPathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		button = new Button(javaGrp, SWT.PUSH);
		button.setText(Constants.WizardPage_Common_browse);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleJavaBrowse();
			}
		});
		
		initialize();
		dialogChanged();
		setControl(container);

	}

	public ICompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public void setCompilationUnit(ICompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}
	
	public TableViewer getTableViewer(){
		return this.tableViewer;
	}
	
	private void initCompilationUnit(){
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof ICompilationUnit) {
				ICompilationUnit compilationUnit = (ICompilationUnit) obj;
				this.compilationUnit = compilationUnit;
			}
		}
	}

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
			} else if (obj instanceof ICompilationUnit) {
				ICompilationUnit compilationUnit = (ICompilationUnit) obj;
				fileText.setText(compilationUnit.getPath().toString());
				this.compilationUnit = compilationUnit;
				// init jsp and java path
				String jspDefaultPath = "/" + getProjectName() + Constants.DEFAULT_PATH_JSP; 
				String javaDefaultPath = "/" + getProjectName() + Constants.DEFAULT_PATH_JAVA;
				containerText.setText(jspDefaultPath);
				javaPathText.setText(javaDefaultPath);
			}
		}
	}

	private void handleBrowse() {
		ContainerSelectionDialog dialog = null;
		String projectName = getProjectName();
		if(projectName != null){
			dialog = new ContainerSelectionDialog(
					getShell(), ResourcesPlugin.getWorkspace().getRoot().getProject(projectName), false,
					"Select new file container");
		}else{
			dialog = new ContainerSelectionDialog(
					getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
					"Select new file container");
		}
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
	}

	private void handleJavaBrowse() {
		ContainerSelectionDialog dialog = null;
		String projectName = getProjectName();
		if(projectName != null){
			dialog = new ContainerSelectionDialog(
					getShell(), ResourcesPlugin.getWorkspace().getRoot().getProject(projectName), false,
					"Select new file container");
		}else{
			dialog = new ContainerSelectionDialog(
					getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
					"Select new file container");
		}
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				javaPathText.setText(((Path) result[0]).toString());
			}
		}
	}
	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getContainerName()));
		String containerName = getContainerName();
		if (containerName.length() == 0) {
			updateInfoStatus("File path must be specified");
			return;
		}
		
		String javaPath = getJavaPath();
		if (javaPath.length() == 0) {
			updateInfoStatus("File path must be specified");
			return;
		}
		
//		if (container == null
//				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
//			updateInfoStatus("File container must exist");
//			return;
//		}
		
//		if (!container.isAccessible()) {
//			updateInfoStatus("Project must be writable");
//			return;
//		}
		
		updateInfoStatus(null);
	}
	
	private String getProjectName(){
		String path = compilationUnit.getPath().toString();
		if(StringUtil.isEmpty(path)){
			return null;
		}
		path = path.substring(1);
		String[] paths = path.split("/");
		if(paths.length > 0){
			return paths[0];
		}
		return null;
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
	
	public String getJavaPath(){
		return javaPathText.getText();
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
