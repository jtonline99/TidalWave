/**
 * 
 */
package com.jtinz.cw.types;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author jt
 *
 */
public abstract class CWBaseType {
	protected static String XML_NAME_ATTR = "name";
	protected static String XML_NAME_TAG = "name";
	protected static String XML_GUID_TAG = "guid";
	protected static String XML_LABEL_TAG = "label";
	protected static String XML_META_VERSION_TAG = "metaVersion";
	protected static String XML_GENERATED_KEY_TAG = "generatedKey";
	protected static String XML_EXTENDS_TAG = "extends";
	protected static String XML_VARIABLE_LIST_TAG = "variableList";
	protected static String XML_METHOD_LIST_TAG = "methodList";
	protected static String XML_DB_SCHEMA_TAG = "dbSchema";
	protected static String XML_TYPE_ATTR = "type";
	protected static String XML_TYPE_TAG = "type";
	protected static String XML_VALUE_TYPE_TAG = "valueType";
	protected static String XML_TABLE_TAG = "table";
	protected static String XML_COLUMN_TAG = "column";
	protected static String XML_PRIMARY_KEY_TAG = "key";
	protected static String XML_VARIABLE_TAG = "variable";
	protected static String XML_METHOD_TAG = "method";
	protected static String XML_AUTOSAVE_TAG = "autosave";
	protected static String XML_VALIDATE_TAG = "validate";
	protected static String XML_SCRIPT_TAG = "script";
	
	protected String name;
	protected String type;

	protected String guid;
	protected String label;
	protected String metaVersion;
	protected String parent;
	
	protected CWBaseType(Element xmlDocument)
	{
		name = xmlDocument.getAttribute(XML_NAME_ATTR);
		type = xmlDocument.getAttribute(XML_TYPE_ATTR);
		
		//parseElement(xmlDocument);
	}
	
	protected void parseElement(Element xmlDocument)
	{
		NodeList children = xmlDocument.getChildNodes();
		
		for(int i = 0; i < children.getLength(); i++)
		{
			Node node = children.item(i);
			
			if(node instanceof Element)
			{
				String nodeType = ((Element)node).getTagName();
				
				if(nodeType.equals(XML_GUID_TAG))
				{
					guid = node.getTextContent();
				}
				else if(nodeType.equals(XML_LABEL_TAG))
				{
					label = node.getTextContent();
				}
				else if(nodeType.equals(XML_META_VERSION_TAG))
				{
					metaVersion = node.getTextContent();
				}
				else if(nodeType.equals(XML_EXTENDS_TAG))
				{
					parent = node.getTextContent();
				}
				
				matchElement((Element)node, nodeType);
			}
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the metaVersion
	 */
	public String getMetaVersion() {
		return metaVersion;
	}

	/**
	 * @return the parent
	 */
	public String getParent() {
		return parent;
	}
	
	protected abstract void matchElement(Element element, String nodeType);
}
