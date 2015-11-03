package com.jtinz.cw.eclipse;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.editors.text.TextEditor;

public class XMLEditor extends TextEditor {

	private ColorManager colorManager;

	public XMLEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	
	public void init(IEditorSite site, IEditorInput input)
	{
		try
		{
			super.init(site, input);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		System.out.println(site.toString());
		System.out.println(input.toString());
		setPartName("test");
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
