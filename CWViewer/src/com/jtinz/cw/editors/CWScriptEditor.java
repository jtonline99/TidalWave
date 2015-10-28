/**
 * 
 */
package com.jtinz.cw.editors;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.jtinz.cw.CWMetaInput;
import com.jtinz.cw.types.CWDocument;
import com.jtinz.cw.types.CWMethod;
import com.jtinz.cw.types.CWParameter;
import com.jtinz.cw.types.CWScript;

/**
 * @author jt
 *
 */
public class CWScriptEditor extends EditorPart
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
		setPartName("Script");
		
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
			CWMetaInput input = (CWMetaInput)getEditorInput();
			CWScript document = new CWScript(input.getRootElement());
			List<CWParameter> documentParameters = document.getParameterList();
			String documentScript = document.getScript();
			
			/*GridLayout layout = new GridLayout();
		    layout.numColumns = 1;
		    parent.setLayout(layout);*/
			parent.setLayout(new FillLayout(SWT.VERTICAL));
			
			SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
			
			// setup table
			final TableViewer tableViewer = new TableViewer(sashForm, SWT.MULTI | SWT.H_SCROLL
				      | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
			
			// setup name column
			TableViewerColumn colName = new TableViewerColumn(tableViewer, SWT.NONE);
			colName.getColumn().setWidth(200);
			colName.getColumn().setText("Name");
			colName.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    return ((CWParameter)element).getName();
				  }
				});
			
			// setup value column
			TableViewerColumn colValue = new TableViewerColumn(tableViewer, SWT.NONE);
			colValue.getColumn().setWidth(200);
			colValue.getColumn().setText("Type");
			colValue.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    return ((CWParameter)element).getType();
				  }
				});
			
			final Table table = tableViewer.getTable();
			table.setHeaderVisible(true);
			table.setLinesVisible(true); 
				
			tableViewer.setContentProvider(ArrayContentProvider.getInstance());
			tableViewer.setInput(documentParameters);
			
			/*GridData gridData = new GridData();
		    gridData.verticalAlignment = GridData.FILL;
		    gridData.horizontalSpan = 2;
		    gridData.grabExcessHorizontalSpace = true;
		    gridData.grabExcessVerticalSpace = true;
		    gridData.horizontalAlignment = GridData.FILL;*/
		    //tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
		    // setup script text area
			final TextViewer textViewer = new TextViewer(sashForm, SWT.MULTI | SWT.V_SCROLL);
			textViewer.setDocument(new Document(documentScript));
			
			/*gridData = new GridData();
		    gridData.verticalAlignment = GridData.FILL;
		    gridData.horizontalSpan = 2;
		    gridData.grabExcessHorizontalSpace = true;
		    gridData.grabExcessVerticalSpace = true;
		    gridData.horizontalAlignment = GridData.FILL;*/
		    //textViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
		    
		    sashForm.setWeights(new int[]{1, 3});

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
