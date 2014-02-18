package org.framework.cg.internal.ui.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.ui.actions.AbstractOpenWizardAction;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.PlatformUI;
import org.framework.cg.internal.ui.utils.Constants;
import org.framework.cg.internal.ui.wizards.GeneratorCreationWizard;
import org.framework.cg.internal.ui.wizards.GeneratorWizardPage;

public class OpenGeneratorWizardAction extends AbstractOpenWizardAction {

	private GeneratorWizardPage fPage;

	@Override
	protected INewWizard createWizard() throws CoreException {
		return new GeneratorCreationWizard(fPage);
	}

	/**
	 * Creates an instance of the <code>OpenGeneratorWizardAction</code>.
	 */
	public OpenGeneratorWizardAction() {
		setText(Constants.OpenGeneratorWizardAction_text);
		setDescription(Constants.OpenGeneratorWizardAction_description);
		setToolTipText(Constants.OpenGeneratorWizardAction_tooltip);
//		setImageDescriptor(JavaPluginImages.DESC_WIZBAN_NEWCLASS);
		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(this, Constants.OPEN_CLASS_WIZARD_ACTION);

		fPage = null;
	}

	public void setConfiguredWizardPage(GeneratorWizardPage page) {
		fPage = page;
	}

}
