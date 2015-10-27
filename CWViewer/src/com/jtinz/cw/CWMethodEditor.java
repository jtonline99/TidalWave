/**
 * 
 */
package com.jtinz.cw;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.jtinz.cw.types.CWDocument;
import com.jtinz.cw.types.CWMethod;

/**
 * @author jt
 *
 */
public class CWMethodEditor extends EditorPart
{

	@Override
	public void doSave(IProgressMonitor progressMonitor) 
	{
		// not implemented
	}

	@Override
	public void doSaveAs() {
		// not implemented
	}

	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException 
	{
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		
		setInput(editorInput);
		setSite(site);
		//setPartName("test");
		
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// add controls
		try
		{
			//InputStream data = ((IFileEditorInput)getEditorInput()).getFile().getContents();
			//CWMeta meta = new CWMeta(data);
			
			//setPartName(meta.getMetaName() + " [" + meta.getMetaType() + "]");
			//System.out.println(getSite().getPart().getTitle());
			//IWorkbenchPage page = getSite().getPage();
			//String i = ";";
			//System.out.println(getSite().getPage().getActiveEditor().getTitle());
			
			// setup table
			TableViewer tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				      | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
			
			// setup name column
			TableViewerColumn colName = new TableViewerColumn(tableViewer, SWT.NONE);
			colName.getColumn().setWidth(200);
			colName.getColumn().setText("Name");
			colName.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    return ((CWMethod)element).getName();
				  }
				});
			
			// setup value column
			TableViewerColumn colValue = new TableViewerColumn(tableViewer, SWT.NONE);
			colValue.getColumn().setWidth(200);
			colValue.getColumn().setText("Type");
			colValue.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    return ((CWMethod)element).getValueType();
				  }
				});
			
			final Table table = tableViewer.getTable();
			table.setHeaderVisible(true);
			table.setLinesVisible(true); 
				
			//tableViewer.setContentProvider(new CWDocumentContentProvider(new CWDocument(((CWMetaInput)getEditorInput()).getRootElement())));
			//tableViewer.setInput(getEditorInput());
			CWMetaInput input = (CWMetaInput)getEditorInput();
			CWDocument document = new CWDocument(input.getRootElement());
			
			tableViewer.setContentProvider(ArrayContentProvider.getInstance());
			tableViewer.setInput(document.getMethodList());
			
			GridData gridData = new GridData();
		    gridData.verticalAlignment = GridData.FILL;
		    gridData.horizontalSpan = 2;
		    gridData.grabExcessHorizontalSpace = true;
		    gridData.grabExcessVerticalSpace = true;
		    gridData.horizontalAlignment = GridData.FILL;
		    tableViewer.getControl().setLayoutData(gridData);

		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	@Override
	public void setFocus()
	{
		// not implemented
		
	}
	
}
