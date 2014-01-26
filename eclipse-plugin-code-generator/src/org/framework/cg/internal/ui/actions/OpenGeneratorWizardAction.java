package org.framework.cg.internal.ui.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.ui.packageview.PackageFragmentRootContainer;
import org.eclipse.jdt.ui.actions.AbstractOpenWizardAction;
import org.eclipse.jface.viewers.IStructuredSelection;
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
		
//		IStructuredSelection selection = super.getSelection();
//		if (selection instanceof IStructuredSelection) {
//			Object element = ((IStructuredSelection) selection)
//					.getFirstElement();
//			System.out.println("ddddddd");
//			IProject project = null;
//			if (element instanceof IResource) {
//				project = ((IResource) element).getProject();
//			} else if (element instanceof PackageFragmentRootContainer) {
//				IJavaProject jProject = ((PackageFragmentRootContainer) element)
//						.getJavaProject();
//				project = jProject.getProject();
//			} else if (element instanceof IJavaElement) {
//				IJavaProject jProject = ((IJavaElement) element)
//						.getJavaProject();
//				project = jProject.getProject();
//			}
//
//			System.out.println(project.getName());
//		}
//		System.out.println(selection);
	}

	public void setConfiguredWizardPage(GeneratorWizardPage page) {
		fPage = page;
	}

}
