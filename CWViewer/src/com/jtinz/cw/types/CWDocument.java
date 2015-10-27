/**
 * 
 */
package com.jtinz.cw.types;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

/**
 * @author jt
 *
 */
public class CWDocument extends CWBaseType {
	// base document fields
	//private boolean generatedKey;
	
	// lists
	//private List<CWVariable> variableList = new ArrayList<CWVariable>();
	//private List<CWMethod> methodList = new ArrayList<CWMethod>();
	
	// db information
	//private String dbSchema;
	
	// dom builder
	public CWDocument(Element xmlDocument)
	{
		super(xmlDocument);
		
		set(XML_VARIABLE_LIST_TAG, new ArrayList<CWVariable>());
		set(XML_METHOD_LIST_TAG, new ArrayList<CWMethod>());
		
		parseElement(xmlDocument);
	}

	/**
	 * @return the generatedKey
	 */
	public boolean isGeneratedKey() {
		return ((String)get(XML_GENERATED_KEY_TAG)).equals("true") ? true : false;
	}

	/**
	 * @return the dbSchema
	 */
	public String getDbSchema() {
		return (String)get(XML_DB_SCHEMA_TAG);
	}

	/**
	 * @return the variableList
	 */
	public List<CWVariable> getVariableList() {
		return (List<CWVariable>)get(XML_VARIABLE_LIST_TAG);
	}

	/**
	 * @return the variableList
	 */
	public List<CWMethod> getMethodList() {
		return (List<CWMethod>)get(XML_METHOD_LIST_TAG);
	}

	@Override
	protected void matchElement(Element element, String nodeType) 
	{
		if(nodeType.equals(XML_GENERATED_KEY_TAG))
		{
			//generatedKey = element.getTextContent().equals("true") ? true : false;
			set(XML_GENERATED_KEY_TAG, element.getTextContent().equals("true") ? "true" : "false");
		}
		else if(nodeType.equals(XML_DB_SCHEMA_TAG))
		{
			//dbSchema = element.getTextContent();
			set(XML_DB_SCHEMA_TAG, element.getTextContent());
		}
		else if(nodeType.equals(XML_VARIABLE_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_VARIABLE_TAG))
		{
			getVariableList().add(new CWVariable(element));
		}
		else if(nodeType.equals(XML_METHOD_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_METHOD_TAG))
		{
			getMethodList().add(new CWMethod(element));
		}
		
	}
}
