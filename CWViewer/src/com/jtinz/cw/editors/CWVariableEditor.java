/**
 * 
 */
package com.jtinz.cw.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.jtinz.cw.CWMetaInput;
import com.jtinz.cw.types.CWDocument;
import com.jtinz.cw.types.CWVariable;

/**
 * @author jt
 *
 */
public class CWVariableEditor extends EditorPart
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
		setPartName("Variables");
		
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
			CWDocument document = (CWDocument)input.getModel();
			
			String documentDbSchema = document.getDbSchema();
			boolean documentHasGeneratedKey = document.isGeneratedKey();
			
			GridLayout layout = new GridLayout();
		    layout.numColumns = 2;
		    parent.setLayout(layout);
		    
		    // setup dbSchema label
		    new Label(parent, SWT.NONE).setText("DB Schema");
		    Text text5 = new Text(parent, SWT.BORDER);
		    text5.setText(documentDbSchema != null ? documentDbSchema : "");
		    text5.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		    
		    // setup generated key label
		    new Label(parent, SWT.NONE).setText("Generated Key");
		    Button checkbox1 = new Button(parent, SWT.CHECK);
		    checkbox1.setSelection(documentHasGeneratedKey);
			
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
				    return ((CWVariable)element).getName();
				  }
				});
			
			// setup value column
			TableViewerColumn colValue = new TableViewerColumn(tableViewer, SWT.NONE);
			colValue.getColumn().setWidth(200);
			colValue.getColumn().setText("Type");
			colValue.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    return ((CWVariable)element).getValueType();
				  }
				});
			
			// setup db table column
			TableViewerColumn colTable = new TableViewerColumn(tableViewer, SWT.NONE);
			colTable.getColumn().setWidth(200);
			colTable.getColumn().setText("DB Table");
			colTable.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    return ((CWVariable)element).getTable();
				  }
				});
			
			// setup db column column
			TableViewerColumn colColumn = new TableViewerColumn(tableViewer, SWT.NONE);
			colColumn.getColumn().setWidth(200);
			colColumn.getColumn().setText("DB Column");
			colColumn.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    return ((CWVariable)element).getColumn();
				  }
				});
			
			final Table table = tableViewer.getTable();
			table.setHeaderVisible(true);
			table.setLinesVisible(true); 
			
			tableViewer.setContentProvider(ArrayContentProvider.getInstance());
			tableViewer.setInput(document.getVariableList());
			
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
