/**
 * 
 */
package com.jtinz.cw.editors;

import java.lang.reflect.Method;
import java.util.List;

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
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.jtinz.cw.CWMetaInput;
import com.jtinz.cw.editors.providers.CWActivityLabelProvider;
import com.jtinz.cw.editors.providers.CWActivityTreeContentProvider;
import com.jtinz.cw.types.CWActivity;
import com.jtinz.cw.types.CWBaseType;
import com.jtinz.cw.types.CWDocument;
import com.jtinz.cw.types.CWMethod;
import com.jtinz.cw.types.CWProcess;
import com.jtinz.cw.types.CWVariable;

/**
 * @author jt
 *
 */
public class CWProcessEditor extends EditorPart
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
		setPartName("Activities");
		
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
			CWProcess document = (CWProcess)input.getModel();
			
			/*GridLayout layout = new GridLayout();
		    layout.numColumns = 2;
		    parent.setLayout(layout);*/
		    
			parent.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
			
		    TreeViewer treeViewer = new TreeViewer(sashForm, SWT.MULTI | SWT.H_SCROLL
				      | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		    treeViewer.setContentProvider(new CWActivityTreeContentProvider());
		    treeViewer.setLabelProvider(new CWActivityLabelProvider());
		    treeViewer.setInput(document);
		    treeViewer.expandAll();
		    
		    /*TreeColumn column1 = new TreeColumn(treeViewer, SWT.LEFT);
		    column1.setText("Name");
		    column1.setWidth(200);
		    TreeColumn column2 = new TreeColumn(treeViewer, SWT.CENTER);
		    column2.setText("Column 2");
		    column2.setWidth(200);*/
		    
		    //treeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		    
		    
		    final TabFolder tabFolder = new TabFolder(sashForm, SWT.NONE);
		    
		    TabItem one = new TabItem(tabFolder, SWT.NONE);
		    one.setText("Methods");
		    one.setToolTipText("Methods related to the selected process activity");
		    
		    
		    SashForm methodGroup = new SashForm(tabFolder, SWT.VERTICAL);
		    
		    one.setControl(methodGroup);
		    
		    //parent.setLayout(new FillLayout(SWT.VERTICAL));
		    
		    		// setup table
		 			final TableViewer methodTableViewer = new TableViewer(methodGroup, SWT.MULTI | SWT.H_SCROLL
		 				      | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		 			//tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {});
		 			// setup name column
		 			TableViewerColumn colName = new TableViewerColumn(methodTableViewer, SWT.NONE);
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
		 			TableViewerColumn colValue = new TableViewerColumn(methodTableViewer, SWT.NONE);
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
		 			
		 			final Table table = methodTableViewer.getTable();
		 			table.setHeaderVisible(true);
		 			table.setLinesVisible(true);
		 			
		 			
		 			methodTableViewer.setContentProvider(ArrayContentProvider.getInstance());
		 			
		 			/*try
		 			  {
		 				  Method getNameMethod = document.getClass().getMethod("getMethodList");
		 				  
		 				  tableViewer.setInput(getNameMethod.invoke(document));
		 			  }
		 			  catch(Exception ex)
		 			  {}*/
		 			
		 			// setup script text area
		 			final TextViewer methodTextViewer = new TextViewer(methodGroup, SWT.MULTI | SWT.V_SCROLL);
		 			methodTextViewer.setDocument(new Document(""));
		 			
		 			
		 			sashForm.setWeights(new int[]{1, 2});
		 			methodGroup.setWeights(new int[]{1, 2});
		 			
		 			// update method list when new activity selected
		 			treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

						@Override
						public void selectionChanged(SelectionChangedEvent event) {
							//IStructuredSelection selection = (IStructuredSelection)methodTableViewer.getSelection();
							try
							  {
							IStructuredSelection selection = (IStructuredSelection) event.getSelection();
							Object firstElement = selection.getFirstElement();//(CWBaseType)selection.getFirstElement();
				    	    //List<CWMethod> methodList = firstElement.getMethodList();
							
				    	    
								  Method getNameMethod = firstElement.getClass().getMethod("getMethodList");
								  Object methodList = getNameMethod.invoke(firstElement);
								  
								  methodTableViewer.setInput(methodList);
							  }
							  catch(Exception ex)
							  {
								  System.out.println(ex);
							  }
						}
		 				
		 			});
		 			
		 			
		 			// update script text field when different method selected
		 			methodTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
				    	  @Override
				    	  public void selectionChanged(SelectionChangedEvent event) {
				    		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
							//IStructuredSelection selection = (IStructuredSelection)methodTableViewer.getSelection();
				    	    if(selection.size() > 0)
				    	    {
					    		CWMethod firstElement = (CWMethod)selection.getFirstElement();
					    	    
					    	    methodTextViewer.getDocument().set(firstElement.getScript());
				    	    }
				    	    else
				    	    {
				    	    	// empty selection
				    	    	methodTextViewer.getDocument().set("");
				    	    }
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
