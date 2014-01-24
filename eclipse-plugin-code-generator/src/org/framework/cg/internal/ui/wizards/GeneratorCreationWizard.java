package org.framework.cg.internal.ui.wizards;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class GeneratorCreationWizard  extends Wizard implements INewWizard {

	private GeneratorWizardPage page;
	private IStructuredSelection selection;
	
	public GeneratorCreationWizard(GeneratorWizardPage page){
		setDialogSettings(JavaPlugin.getDefault().getDialogSettings());
		setWindowTitle("Test");
		this.page = page;
	}
	
	public GeneratorCreationWizard(){
		this(null);
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}

	@Override
	public boolean performFinish() {
		return false;
	}
	
	public IStructuredSelection getSelection() {
		return selection;
	}
	
	@Override
	public void addPages() {
		super.addPages();
		if(page == null){
			page = new GeneratorWizardPage(selection);
			page.setWizard(this);
//			page.init(getSelection());
		}
		addPage(page);
	}
	
	
}
