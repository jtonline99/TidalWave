package com.jtinz.cw.types;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CWVariable extends CWBaseType {
	// base variable set
	private String valueType;
	
	// db information
	private String table;
	private String column;
	private boolean primaryKey;
	
	public CWVariable(Element xmlDocument)
	{
		super(xmlDocument);
		
		parseElement(xmlDocument);
	}

	/**
	 * @return the value
	 */
	public String getValueType() {
		return valueType;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	@Override
	protected void matchElement(Element element, String nodeType) {
		if(nodeType.equals(XML_VALUE_TYPE_TAG))
		{
			valueType = element.getTextContent();
		}
		else if(nodeType.equals(XML_TABLE_TAG))
		{
			table = element.getTextContent();
		}
		else if(nodeType.equals(XML_COLUMN_TAG))
		{
			column = element.getTextContent();
		}
		else if(nodeType.equals(XML_PRIMARY_KEY_TAG))
		{
			primaryKey = element.getTextContent().equals("1") ? true : false;
		}
	}
}
