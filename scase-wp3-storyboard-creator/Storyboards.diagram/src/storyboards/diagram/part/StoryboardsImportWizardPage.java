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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
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
import org.eclipse.ui.internal.wizards.datatransfer.WizardFileSystemResourceImportPage1;

/**
 * @generated NOT
 */
public class StoryboardsImportWizardPage extends WizardFileSystemResourceImportPage1 {

	private ExtensibleURIConverterImpl urihandler;

	protected Resource diagram;

	public StoryboardsImportWizardPage(IWorkbench workbench, IStructuredSelection selection, String fileImportMask) {
		super(workbench, selection);
		urihandler = new ExtensibleURIConverterImpl();
	}

	protected IPath getFilePath(String filename) {
		IPath path = getContainerFullPath();
		if (path == null) {
			path = new Path(""); //$NON-NLS-1$
		}
		String fileName = filename;
		if (fileName != null) {
			path = path.append(fileName);
		}
		return path;
	}

	@Override
	public boolean finish() {
		if (!ensureSourceIsValid()) {
			return false;
		}

		saveWidgetValues();

		Iterator resourcesEnum = getSelectedResources().iterator();
		ArrayList<File> fileSystemObjects = new ArrayList<File>();
		while (resourcesEnum.hasNext()) {
			File fileSystemObject = (File) ((FileSystemElement) resourcesEnum.next()).getFileSystemObject();
			String filename = fileSystemObject.getName();
			int i = filename.lastIndexOf('.');
			if (i <= 0 || !filename.substring(i + 1).equals("sbd")) {
				setErrorMessage("All files imported must have the sbd extension!");
				return false;
			}
			fileSystemObjects.add(fileSystemObject);
		}
		for (final File file : fileSystemObjects) {

			IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

				protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
					URI uri = URI.createPlatformResourceURI(getFilePath(file.getName()).toString(), false);
					diagram = StoryboardsDiagramEditorUtil.createDiagram(uri, monitor);
					if (diagram != null) {

						ArrayList<String> datalines = new ArrayList<String>();

						try {
							BufferedReader brlocal = new BufferedReader(new InputStreamReader(
									urihandler.createInputStream(uri)));
							String line;
							while ((line = brlocal.readLine()) != null) {
								String trimmedline = line.trim();
								if (trimmedline.startsWith("<auth.storyboards")) {
									line = line.substring(0, line.lastIndexOf('/')) + '>';
									datalines.add(line);

									/**
									 * READ FILE
									 */

									BufferedReader br;
									br = new BufferedReader(new FileReader(file));
									String fileline;
									while ((fileline = br.readLine()) != null) {
										String trimmedfileline = fileline.trim();
										if (trimmedfileline.startsWith("<storyboard")
												|| trimmedfileline.startsWith("<condition")
												|| trimmedfileline.startsWith("</storyboard")
												|| trimmedfileline.startsWith("</auth.storyboards"))
											datalines.add(fileline);
									}
									br.close();

									/**
									 * END READ FILE
									 */
								} else
									datalines.add(line);

							}
							brlocal.close();

							BufferedWriter brlocalw = new BufferedWriter(new OutputStreamWriter(
									urihandler.createOutputStream(uri)));
							for (String dataline : datalines) {
								brlocalw.write(dataline);
							}
							brlocalw.close();

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

						try {
							StoryboardsDiagramEditorUtil.openDiagram(diagram);
						} catch (PartInitException e) {
							ErrorDialog.openError(getContainer().getShell(),
									Messages.StoryboardsCreationWizardOpenEditorError, null, e.getStatus());
						}

					}
				}
			};
			try {
				getContainer().run(false, true, op);
			} catch (InterruptedException e) {
				return false;
			} catch (InvocationTargetException e) {
				if (e.getTargetException() instanceof CoreException) {
					ErrorDialog.openError(getContainer().getShell(), Messages.StoryboardsCreationWizardCreationError,
							null, ((CoreException) e.getTargetException()).getStatus());
				} else {
					StoryboardsDiagramEditorPlugin.getInstance().logError(
							"Error creating diagram", e.getTargetException()); //$NON-NLS-1$
				}
				return false;
			}
		}
		return diagram != null;
	}

	@Override
	protected void createOptionsGroup(Composite parent) {

	}

	@Override
	protected void createSourceGroup(Composite parent) {
		createRootDirectoryGroup(parent);
		createFileSelectionGroup(parent);
		Composite buttonComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.makeColumnsEqualWidth = true;
		buttonComposite.setLayout(layout);
		buttonComposite.setFont(parent.getFont());
		GridData buttonData = new GridData(SWT.FILL, SWT.FILL, true, false);
		buttonComposite.setLayoutData(buttonData);
	}

	@Override
	protected void enableButtonGroup(boolean enable) {

	}

}
