package org.framework.cg.internal.ui.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class GeneratorWizardPage extends WizardPage {

	private ISelection selection;
	
	protected GeneratorWizardPage(String pageName) {
		super(pageName);
	}
	
	public GeneratorWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Multi-page Editor File");
		setDescription("This wizard creates a new file with *.txt extension that can be opened by a multi-page editor.");
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		
	}

}
