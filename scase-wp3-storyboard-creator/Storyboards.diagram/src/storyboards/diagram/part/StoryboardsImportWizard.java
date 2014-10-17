package storyboards.diagram.part;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IImportWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * @generated NOT
 */
public class StoryboardsImportWizard extends Wizard implements IImportWizard {

	private IWorkbench workbench;

	private IStructuredSelection selection;

	private StoryboardsImportWizardPage diagramModelFilePage;

	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		this.workbench = workbench;
		this.selection = currentSelection;
		setWindowTitle(Messages.StoryboardsImportWizardTitle);
		setDefaultPageImageDescriptor(StoryboardsDiagramEditorPlugin
				.getBundledImageDescriptor("icons/wizban/NewStoryboardsWizard.gif")); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	public void addPages() {
		diagramModelFilePage = new StoryboardsImportWizardPage(workbench, selection, "sbd"); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFilePage.setTitle(Messages.StoryboardsImportWizard_DiagramModelFilePageTitle);
		diagramModelFilePage.setDescription(Messages.StoryboardsImportWizard_DiagramModelFilePageDescription);
		addPage(diagramModelFilePage);
	}

	public boolean performFinish() {
		return diagramModelFilePage.finish();
	}

}
