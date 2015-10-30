/**
 * 
 */
package com.jtinz.cw;


import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.jtinz.cw.types.CWBaseType;
import com.jtinz.cw.types.CWDocument;

/**
 * @author jt
 *
 */
public class CWMetaInput implements IFileEditorInput
{
	// config variables
	private String typesClasspath = "com.jtinz.cw.types";
	private Map<String, String> typesConfig;
	
	private IFileEditorInput editorInput;
	private Document metaDocument;
	private Element metaRootElement;
	private CWBaseType model;
	
	public CWMetaInput(IFileEditorInput input)
	{
		typesConfig = new HashMap<String, String>();
		typesConfig.put("document", "CWDocument");
		typesConfig.put("script", "CWScript");
		typesConfig.put("dataType", "CWDataType");
		typesConfig.put("process", "CWProcess");
		typesConfig.put("findSql", "CWFinder");
		typesConfig.put("findScript", "CWFinder");
		typesConfig.put("findDoc", "CWFinder");
		typesConfig.put("nameSpace", "CWNamespace");
		typesConfig.put("processSignal", "CWSignal");
		typesConfig.put("userInterface", "CWUserInterface");
		
		try
		{
			this.editorInput = input;
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			metaDocument = builder.parse(input.getFile().getContents());
			metaRootElement = metaDocument.getDocumentElement();
			
			String metaType = getMetaType();
			String modelName = typesConfig.get(metaType);
			
			Constructor typeConstructor = Class.forName(typesClasspath + "." + modelName).getConstructor(Element.class);
			model = (CWBaseType) typeConstructor.newInstance(metaRootElement);
			//model = new CWDocument(metaRootElement); // placeholder
			
			//CWDocument testDoc = new CWDocument(metaRootElement);
			
			//System.out.println(testDoc);
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
	public String getMetaType()
	{
		return metaRootElement.getTagName();
	}
	
	public String getMetaName()
	{
		return metaRootElement.getAttribute("name");
	}
	
	public NodeList getChildNodes()
	{
		return metaRootElement.getChildNodes();
	}
	
	public Element getRootElement()
	{
		return metaRootElement;
	}
	
	public CWBaseType getModel()
	{
		return model;
	}
	

	/*
	 * pass through
	 */

	@Override
	public <T> T getAdapter(Class<T> arg0) {
		return editorInput.getAdapter(arg0);
	}

	@Override
	public boolean exists() {
		return editorInput.exists();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return editorInput.getImageDescriptor();
	}

	@Override
	public String getName() {
		return editorInput.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		return editorInput.getPersistable();
	}

	@Override
	public String getToolTipText() {
		return editorInput.getToolTipText();
	}

	@Override
	public IStorage getStorage() throws CoreException {
		return editorInput.getStorage();
	}

	@Override
	public IFile getFile() {
		return editorInput.getFile();
	}
}
