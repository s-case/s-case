package storyboards.diagram.part;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.FileSystemElement;
import org.eclipse.ui.internal.wizards.datatransfer.DataTransferMessages;
import org.eclipse.ui.internal.wizards.datatransfer.FileSystemExportOperation;
import org.eclipse.ui.internal.wizards.datatransfer.IDataTransferHelpContextIds;
import org.eclipse.ui.internal.wizards.datatransfer.WizardFileSystemResourceExportPage1;

/**
 * @generated NOT
 */
public class StoryboardsExportWizardPage extends WizardFileSystemResourceExportPage1 {

	private ExtensibleURIConverterImpl urihandler;

	protected Resource diagram;

	public StoryboardsExportWizardPage(IWorkbench workbench, IStructuredSelection selection, String fileImportMask) {
		super(selection);
		urihandler = new ExtensibleURIConverterImpl();
	}

	@Override
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		composite.setFont(parent.getFont());

		createResourcesGroup(composite);
		createDestinationGroup(composite);

		restoreResourceSpecificationWidgetValues(); // ie.- local
		restoreWidgetValues(); // ie.- subclass hook

		updateWidgetEnablements();
		setPageComplete(determinePageCompletion());
		setErrorMessage(null); // should not initially have error message

		setControl(composite);
		giveFocusToDestination();
		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(getControl(), IDataTransferHelpContextIds.FILE_SYSTEM_EXPORT_WIZARD_PAGE);
	}

	@Override
	public boolean finish() {
		List resourcesToExport = getWhiteCheckedResources();
		if (!ensureTargetIsValid(new File(getDestinationValue()))) {
			return false;
		}

		for (Object file : resourcesToExport) {
			String filename = ((org.eclipse.core.internal.resources.File) file).getName();
			int i = filename.lastIndexOf('.');
			if (i <= 0 || !filename.substring(i + 1).equals("sbd")) {
				setErrorMessage("All files imported must have the sbd extension!");
				return false;
			}
		}

		saveDirtyEditors();
		saveWidgetValues();

		FileSystemExportOperation op = new FileSystemExportOperation(null, resourcesToExport, getDestinationValue(),
				this);

		op.setCreateLeadupStructure(false);
		op.setOverwriteFiles(true);

		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			displayErrorDialog(e.getTargetException());
			return false;
		}

		IStatus status = op.getStatus();
		if (!status.isOK()) {
			ErrorDialog.openError(getContainer().getShell(), DataTransferMessages.DataTransfer_exportProblems, null, // no
																														// special
																														// message
					status);
			return false;
		}

		return true;
	}
}
