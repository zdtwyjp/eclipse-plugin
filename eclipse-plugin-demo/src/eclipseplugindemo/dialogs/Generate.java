package eclipseplugindemo.dialogs;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.ui.actions.OpenNewClassWizardAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import eclipseplugindemo.edting.ContentProvider;
import eclipseplugindemo.edting.MyCellModifier;
import eclipseplugindemo.edting.People;
import eclipseplugindemo.model.ListModel;
import eclipseplugindemo.model.TableContentProvider;
import eclipseplugindemo.model.TableLabelProvider;
import eclipseplugindemo.model.User;

public class Generate {

	protected Shell shell;
	private Table table;
	private Table table_2;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Generate window = new Generate();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(719, 614);
		shell.setText("SWT Application");

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openFileDialog();
			}
		});
		btnNewButton.setBounds(10, 25, 132, 27);
		btnNewButton.setText("FileDialog");

		Button btnBrowseopentype = new Button(shell, SWT.NONE);
		btnBrowseopentype.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openTypeSelectionDialog();
			}
		});
		btnBrowseopentype.setText("Browse(OpenType)");
		btnBrowseopentype.setBounds(148, 25, 132, 27);
		
		Button btnFile = new Button(shell, SWT.NONE);
		btnFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectionDialogBrowse();
			}
		});
		btnFile.setText("Browse(FileSelect)");
		btnFile.setBounds(286, 25, 125, 27);
		
		Button btnBrowsefilteredtypeselect = new Button(shell, SWT.NONE);
		btnBrowsefilteredtypeselect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filteredTypeSelection();
			}
		});
		btnBrowsefilteredtypeselect.setText("Browse(FilteredTypeSelect)");
		btnBrowsefilteredtypeselect.setBounds(10, 68, 216, 27);
		
		Button btnBrowsenewClass = new Button(shell, SWT.NONE);
		btnBrowsenewClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new OpenNewClassWizardAction().run();
			}
		});
		btnBrowsenewClass.setText("Browse(New Class)");
		btnBrowsenewClass.setBounds(232, 68, 156, 27);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 103, 580, 100);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("New Column");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("New Column");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("New Column");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("New Column");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("New Column");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[]{"New TableItem","New TableItem","New TableItem"});
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[]{"New TableItem","New TableItem","New TableItem"});
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(new String[]{"New TableItem","New TableItem","New TableItem"});
		
		
		
		
		Table table2 = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table2.setHeaderVisible(true);
		table2.setLinesVisible(true);
		table2.setBounds(10, 232, 580, 114);
		TableColumn c1 = new TableColumn(table2, SWT.NONE);
		c1.setWidth(100);
		c1.setText("ID");
		TableColumn c2 = new TableColumn(table2, SWT.NONE);
		c2.setWidth(100);
		c2.setText("Name");
		
		
		TableViewer tableViewer = new TableViewer(table2);
		tableViewer.setContentProvider(new TableContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		ListModel model = new ListModel();
		tableViewer.setInput(model);
		model.add(new User("1", "yjp"));
		model.add(new User("2", "qqp"));
		
		
		TableViewer tableViewer_1 = new TableViewer(shell, SWT.CHECK|SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		table_2 = tableViewer_1.getTable();
		table_2.setBounds(10, 378, 580, 200);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_5 = tableViewerColumn.getColumn();
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("id");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_7 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("name");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_8 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("sex");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_9 = tableViewerColumn_4.getColumn();
		tblclmnNewColumn_9.setWidth(100);
		tblclmnNewColumn_9.setText("age");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_10 = tableViewerColumn_5.getColumn();
		tblclmnNewColumn_10.setWidth(100);
		tblclmnNewColumn_10.setText("createDate");
		
//		TableItem tableItem_11 = new TableItem(table_2, SWT.NONE);
//		tableItem_11.setText(new String[]{"New TableItem","New TableItem","New TableItem","New TableItem","New TableItem"});
//		tableItem_11.setText(new String[]{"New TableItem","New TableItem","New TableItem","New TableItem","New TableItem"});
//		tableItem_11.setText(new String[]{"New TableItem","New TableItem","New TableItem","New TableItem","New TableItem"});
		tableViewer_1.setContentProvider(new ContentProvider());
		tableViewer_1.setLabelProvider(new eclipseplugindemo.edting.TableLabelProvider());
		tableViewer_1.setInput(People.getPeople());
		
		tableViewer_1.setColumnProperties(new String[]{"id","name","sex","age","createDate"});
        CellEditor[] cellEditor = new CellEditor[5];
        cellEditor[0] = null;
        cellEditor[1] = new ComboBoxCellEditor(tableViewer_1.getTable(),MyCellModifier.NAMES,SWT.READ_ONLY);
        cellEditor[2] = new CheckboxCellEditor(tableViewer_1.getTable());
        cellEditor[3] = new TextCellEditor(tableViewer_1.getTable());
        cellEditor[4] = null;
        tableViewer_1.setCellEditors(cellEditor);
        ICellModifier modifier = new MyCellModifier(tableViewer_1);
        tableViewer_1.setCellModifier(modifier);
        Text text = (Text)cellEditor[3].getControl();
		text.addVerifyListener(new VerifyListener(){
			public void verifyText(VerifyEvent e){
				String inStr = e.text;
				if (inStr.length() > 0){
					try{
						Integer.parseInt(inStr);
						e.doit = true;
					}catch(Exception ep){
						e.doit = false;
					}
				}
			}
		});
	}

	private void openFileDialog() {
		FileDialog fileDialog = new FileDialog(shell);
		fileDialog.open();
	}

	private void openTypeSelectionDialog() {
		OpenTypeSelectionDialog dialog = new OpenTypeSelectionDialog(shell,
				true, PlatformUI.getWorkbench().getProgressService(), null,
				IJavaSearchConstants.TYPE);
		dialog.setTitle(JavaUIMessages.OpenTypeAction_dialogTitle);
		dialog.setMessage(JavaUIMessages.OpenTypeAction_dialogMessage);
		int result = dialog.open();
		if (result != IDialogConstants.OK_ID)
			return;
	}
	
	private void selectionDialogBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				shell, ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
//				containerText.setText(((Path) result[0]).toString());
			}
		}
	}
	
	private void filteredTypeSelection(){
		FilteredTypesSelectionDialog dialog= new FilteredTypesSelectionDialog(shell, false,
			PlatformUI.getWorkbench().getProgressService(),null, IJavaSearchConstants.CLASS);
		dialog.setTitle(NewWizardMessages.NewTypeWizardPage_SuperClassDialog_title);
		dialog.setMessage(NewWizardMessages.NewTypeWizardPage_SuperClassDialog_message);
		dialog.open();
//		dialog.setInitialPattern(getSuperClass());
	}
}
