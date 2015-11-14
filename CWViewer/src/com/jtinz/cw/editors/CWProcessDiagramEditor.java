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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.zest.core.viewers.GraphViewer;

import com.jtinz.cw.CWMetaInput;
import com.jtinz.cw.editors.helpers.CWActivityGraphBuilder;
import com.jtinz.cw.editors.helpers.CWActivityGraphBuilder2;
import com.jtinz.cw.editors.providers.CWActivityGraphContentProvider;
import com.jtinz.cw.editors.providers.CWActivityGraphLayoutAlgorithm;
import com.jtinz.cw.editors.providers.CWActivityLabelProvider;
import com.jtinz.cw.editors.providers.CWActivityTableLabelProvider;
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
public class CWProcessDiagramEditor extends EditorPart
{
	GraphViewer graphViewer;

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
		setPartName("Design");
		
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

			CWActivityGraphBuilder builder = new CWActivityGraphBuilder();
			builder.generateGraph(document.getActivity());
			
			graphViewer = new GraphViewer(parent, SWT.BORDER);
			
			graphViewer.setContentProvider(new CWActivityGraphContentProvider());
			graphViewer.setLabelProvider(new CWActivityTableLabelProvider());
			graphViewer.setLayoutAlgorithm(new CWActivityGraphLayoutAlgorithm(), true);
			graphViewer.setInput(builder);
			//graphViewer.applyLayout();
			
			//CWActivityGraphBuilder2 testBuilder = new CWActivityGraphBuilder2();
			//testBuilder.generateGraph(document.getActivity());
			
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
		graphViewer.applyLayout();
		
	}
	
}
