/**
 * 
 */
package com.jtinz.cw.types;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author jt
 *
 */
public class CWDocument extends CWBaseType {
	// base document fields
	private boolean generatedKey;
	
	// lists
	private List<CWVariable> variableList = new ArrayList<CWVariable>();
	private List<CWMethod> methodList = new ArrayList<CWMethod>();
	
	// db information
	private String dbSchema;
	
	// dom builder
	public CWDocument(Element xmlDocument)
	{
		super(xmlDocument);
		parseElement(xmlDocument);
	}

	/**
	 * @return the generatedKey
	 */
	public boolean isGeneratedKey() {
		return generatedKey;
	}

	/**
	 * @return the dbSchema
	 */
	public String getDbSchema() {
		return dbSchema;
	}

	/**
	 * @return the variableList
	 */
	public List<CWVariable> getVariableList() {
		return variableList;
	}

	@Override
	protected void matchElement(Element element, String nodeType) 
	{
		if(nodeType.equals(XML_GENERATED_KEY_TAG))
		{
			generatedKey = element.getTextContent().equals("true") ? true : false;
		}
		else if(nodeType.equals(XML_DB_SCHEMA_TAG))
		{
			dbSchema = element.getTextContent();
		}
		else if(nodeType.equals(XML_VARIABLE_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_VARIABLE_TAG))
		{
			variableList.add(new CWVariable(element));
		}
		else if(nodeType.equals(XML_METHOD_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_METHOD_TAG))
		{
			methodList.add(new CWMethod(element));
		}
		
	}
}
