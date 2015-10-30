/**
 * 
 */
package com.jtinz.cw.editors;

import java.lang.reflect.Method;

import org.eclipse.core.runtime.IProgressMonitor;
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
import com.jtinz.cw.types.CWBaseType;
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
		setPartName("Methods");
		
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
			CWBaseType document = (CWBaseType)input.getModel();//new CWDocument(input.getRootElement());
			
			/*GridLayout layout = new GridLayout();
		    layout.numColumns = 1;
		    parent.setLayout(layout);*/
		    
		    parent.setLayout(new FillLayout(SWT.VERTICAL));
			
			SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
			
			// setup table
			final TableViewer tableViewer = new TableViewer(sashForm, SWT.MULTI | SWT.H_SCROLL
				      | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
			//tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {});
			// setup name column
			TableViewerColumn colName = new TableViewerColumn(tableViewer, SWT.NONE);
			colName.getColumn().setWidth(200);
			colName.getColumn().setText("Name");
			colName.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    //return ((CWMethod)element).getName();
					  try
					  {
						  Method getNameMethod = element.getClass().getMethod("getName");
						  
						  return (String) getNameMethod.invoke(element);
					  }
					  catch(Exception ex)
					  {}
					  
					  return "";
				  }
				});
			
			// setup value column
			TableViewerColumn colValue = new TableViewerColumn(tableViewer, SWT.NONE);
			colValue.getColumn().setWidth(200);
			colValue.getColumn().setText("Type");
			colValue.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    //return ((CWMethod)element).getValueType();
					  try
					  {
						  Method getNameMethod = element.getClass().getMethod("getValueType");
						  
						  return (String) getNameMethod.invoke(element);
					  }
					  catch(Exception ex)
					  {}
					  
					  return "";
				  }
				});
			
			final Table table = tableViewer.getTable();
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			
			tableViewer.setContentProvider(ArrayContentProvider.getInstance());
			//tableViewer.setInput(document.getMethodList());
			try
			  {
				  Method getNameMethod = document.getClass().getMethod("getMethodList");
				  
				  tableViewer.setInput(getNameMethod.invoke(document));
			  }
			  catch(Exception ex)
			  {}
			
			/*GridData gridData = new GridData();
		    gridData.verticalAlignment = GridData.FILL;
		    gridData.horizontalSpan = 2;
		    gridData.grabExcessHorizontalSpace = true;
		    gridData.grabExcessVerticalSpace = true;
		    gridData.horizontalAlignment = GridData.FILL;
		    tableViewer.getControl().setLayoutData(gridData);*/
			
		    // setup script text area
			final TextViewer textViewer = new TextViewer(sashForm, SWT.MULTI | SWT.V_SCROLL);
			textViewer.setDocument(new Document(""));
			
			/*gridData = new GridData();
		    gridData.verticalAlignment = GridData.FILL;
		    gridData.horizontalSpan = 2;
		    gridData.grabExcessHorizontalSpace = true;
		    gridData.grabExcessVerticalSpace = true;
		    gridData.horizontalAlignment = GridData.FILL;
		    textViewer.getControl().setLayoutData(gridData);*/
			
			sashForm.setWeights(new int[]{1, 3});
		    
		    // add listener to populate textViewer field
		    tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		    	  @Override
		    	  public void selectionChanged(SelectionChangedEvent event) {
		    	    IStructuredSelection selection = (IStructuredSelection)tableViewer.getSelection();
		    	    CWMethod firstElement = (CWMethod)selection.getFirstElement();
		    	    
		    	    textViewer.getDocument().set(firstElement.getScript());
		    	  }
		    	}); 

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
