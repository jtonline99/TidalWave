package com.jtinz.cw;


import java.io.InputStream;
import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.*;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.jtinz.cw.types.CWBaseType;

import org.eclipse.ui.ide.IDE;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class CWEditor extends MultiPageEditorPart implements IResourceChangeListener{

	// config variables
	private String editorClasspath = "com.jtinz.cw.editors";
	private Map<String, String[]> designConfig;
	
	/** The text editor used in page 0. */
	private TextEditor editor;

	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private StyledText text;
	/**
	 * Creates a multi-page editor example.
	 */
	public CWEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		
		designConfig = new HashMap<String, String[]>();
		designConfig.put("document", new String[]{ "CWVariableEditor", "CWMethodEditor"});
		designConfig.put("script", new String[]{"CWScriptEditor"});
		designConfig.put("dataType", new String[]{});
		designConfig.put("process", new String[]{"CWProcessEditor"});
		designConfig.put("findSql", new String[]{"CWMethodEditor"});
		designConfig.put("findScript", new String[]{"CWMethodEditor"});
		designConfig.put("findDoc", new String[]{"CWMethodEditor"});
		designConfig.put("namespace", new String[]{});
	}
	/**
	 * Creates the source viewer page of the multi-page editor,
	 * which contains a text editor.
	 */
	void createSourcePage() {
		try {
			editor = new TextEditor();
			int index = addPage(editor, getEditorInput());
			setPageText(index, "Source");
			
		} catch (PartInitException e) {
			ErrorDialog.openError(
				getSite().getShell(),
				"Error creating nested text editor",
				null,
				e.getStatus());
		}
	}
	/**
	 * Creates the design viewer page of the multi-page editor,
	 * which contains whatever applicable control.
	 */
	void createDesignPage() {
		try {
			/*CWPropertiesEditor cwedit3 = new CWPropertiesEditor();
			//editor = new TextEditor();
			int index = addPage(cwedit3, getEditorInput());
			setPageText(index, cwedit3.getPartName());
			
			CWVariableEditor cwedit1 = new CWVariableEditor();
			//editor = new TextEditor();
			index = addPage(cwedit1, getEditorInput());
			setPageText(index, cwedit1.getPartName());
			
			CWMethodEditor cwedit2 = new CWMethodEditor();
			index = addPage(cwedit2, getEditorInput());
			setPageText(index, cwedit2.getPartName());*/
			
			
			// test stuff
			/*InputStream data = ((IFileEditorInput)getEditorInput()).getFile().getContents();*/
			CWMetaInput meta = (CWMetaInput)getEditorInput();
			
			//setPartName(meta.getMetaName());// + " [" + meta.getMetaType() + "]");
			//setPartName(meta.getModel().getName());
			CWBaseType model = meta.getModel();
			String pageName = meta.getMetaName();
			String pageLabel = model != null ? model.getLabel() : null;
			String partName = null;
			
			if(pageLabel != null && !pageLabel.equals(""))
			{
				partName = pageLabel;
			}
			else if(pageName != null && !pageName.equals(""))
			{
				partName = pageName;
			}
			else
			{
				partName = meta.getFile().getName();
			}
			
			setPartName(partName);
			
			String metaType = meta.getMetaType();
			String[] views = designConfig.get(metaType);
			EditorPart designEditor;
			int index;
			
			if(views != null)
			{
			
				designEditor = (EditorPart) Class.forName(editorClasspath + ".CWPropertiesEditor").newInstance();
				index = addPage(designEditor, getEditorInput());
				setPageText(index, designEditor.getPartName());
			
			
				for(String view : views)
				{
					designEditor = (EditorPart) Class.forName(editorClasspath + "." + view).newInstance();
					index = addPage(designEditor, getEditorInput());
					setPageText(index, designEditor.getPartName());
				}
			
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
	/**
	 * Creates page 1 of the multi-page editor,
	 * which allows you to change the font used in page 2.
	 */
	/*void createPage1() {

		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;

		Button fontButton = new Button(composite, SWT.NONE);
		GridData gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 2;
		fontButton.setLayoutData(gd);
		fontButton.setText("Change Font...");
		
		fontButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setFont();
			}
		});

		int index = addPage(composite);
		setPageText(index, "Properties");
	}*/
	/**
	 * Creates page 2 of the multi-page editor,
	 * which shows the sorted text.
	 */
	/*void createPage2() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		text.setEditable(false);

		int index = addPage(composite);
		setPageText(index, "Preview");
	}*/
	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createDesignPage();
		createSourcePage();
		//createPage1();
		//createPage2();
	}
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		//getEditor(0).doSave(monitor);
	}
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	public void doSaveAs() {
		/*IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());*/
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	/*public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}*/
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
		throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		
		super.init(site, new CWMetaInput((IFileEditorInput)editorInput));
		
		//((IFileEditorInput)editorInput).getFile().getContents()
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	/*protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			sortWords();
		}
	}*/
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i<pages.length; i++){
						if(((FileEditorInput)editor.getEditorInput()).getFile().getProject().equals(event.getResource())){
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart,true);
						}
					}
				}            
			});
		}
	}
	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	/*void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}*/
	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
	/*void sortWords() {

		String editorText =
			editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();

		StringTokenizer tokenizer =
			new StringTokenizer(editorText, " \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		ArrayList editorWords = new ArrayList();
		while (tokenizer.hasMoreTokens()) {
			editorWords.add(tokenizer.nextToken());
		}

		Collections.sort(editorWords, Collator.getInstance());
		StringWriter displayText = new StringWriter();
		for (int i = 0; i < editorWords.size(); i++) {
			displayText.write(((String) editorWords.get(i)));
			displayText.write(System.getProperty("line.separator"));
		}
		text.setText(displayText.toString());
	}*/
}
