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
import com.jtinz.cw.types.CWBaseType;
import com.jtinz.cw.types.CWDocument;
import com.jtinz.cw.types.CWVariable;

/**
 * @author jt
 *
 */
public class CWPropertiesEditor extends EditorPart
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
		setPartName("Properties");
		
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
			CWBaseType document = (CWBaseType)input.getModel();
			
			String documentName = document.getName();
			String documentLabel = document.getLabel();
			String documentType = document.getType();
			String documentParent = document.getParent();
			
			GridLayout layout = new GridLayout();
		    layout.numColumns = 2;
		    parent.setLayout(layout);

		    // setup name label
		    new Label(parent, SWT.NONE).setText("Name");
		    Text text1 = new Text(parent, SWT.BORDER);
		    text1.setText(documentName != null ? documentName : "");
		    text1.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		    
		    // setup "label" label
		    new Label(parent, SWT.NONE).setText("Label");
		    Text text2 = new Text(parent, SWT.BORDER);
		    text2.setText(documentLabel != null ? documentLabel : "");
		    text2.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		    
		    // setup type label
		    new Label(parent, SWT.NONE).setText("Type");
		    Text text3 = new Text(parent, SWT.BORDER);
		    text3.setText(documentType != null ? documentType : "");
		    text3.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		    
		    // setup extends label
		    new Label(parent, SWT.NONE).setText("Extends");
		    Text text4 = new Text(parent, SWT.BORDER);
		    text4.setText(documentParent != null ? documentParent : "");
		    text4.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

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
