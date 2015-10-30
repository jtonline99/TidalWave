/**
 * 
 */
package com.jtinz.cw.types;

import java.util.HashMap;
import java.util.Map;

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
	protected static String XML_ENUM_VALUE_LIST_TAG = "valueList";
	protected static String XML_ENUM_VALUE_TAG = "value";
	protected static String XML_CODE_ATTR = "code";
	protected static String XML_PARAMETER_LIST_TAG = "parameterList";
	protected static String XML_PARAMETER_TAG = "parameter";
	protected static String XML_PARAMETER_TYPE_TAG = "type";
	protected static String XML_PARAMETER_MANDATORY_TAG = "mandatory";
	protected static String XML_ACTIVITY_LIST_TAG = "childList";
	protected static String XML_PROCESS_ACTIVITY_TAG = "activity";
	protected static String XML_ACTIVITY_TAG = "child";
	protected static String XML_X_COORD_TAG = "x";
	protected static String XML_Y_COORD_TAG = "y";
	protected static String XML_PARTICIPANT_TAG = "participant";
	protected static String XML_ELEMENT_TAG = "element";
	protected static String XML_CATEGORY_TAG = "category";
	protected static String XML_SYSTEM_TAG = "system";
	
	/*protected String name;
	protected String type;

	protected String guid;
	protected String label;
	protected String metaVersion;
	protected String parent;*/
	
	private Map<String, Object> dataMap;
	
	protected CWBaseType(Element xmlDocument)
	{
		dataMap = new HashMap<String, Object>();
		
		//name = xmlDocument.getAttribute(XML_NAME_ATTR);
		//type = xmlDocument.getAttribute(XML_TYPE_ATTR);
		set(XML_NAME_ATTR, xmlDocument.getAttribute(XML_NAME_ATTR));
		set(XML_TYPE_ATTR, xmlDocument.getAttribute(XML_TYPE_ATTR));
		set(XML_GUID_TAG, ""); // default
		set(XML_LABEL_TAG, ""); // default
		set(XML_META_VERSION_TAG, ""); // default
		set(XML_EXTENDS_TAG, ""); // default
		
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
				
				/*if(nodeType.equals(XML_GUID_TAG))
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
				}*/
				if(nodeType.equals(XML_GUID_TAG) ||
					nodeType.equals(XML_LABEL_TAG) ||
					nodeType.equals(XML_META_VERSION_TAG) ||
					nodeType.equals(XML_EXTENDS_TAG))
				{
					set(nodeType, node.getTextContent());
				}
				
				matchElement((Element)node, nodeType);
			}
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		//return name;
		return (String)get(XML_NAME_ATTR);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		//return type;
		return (String)get(XML_TYPE_ATTR);
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		//return guid;
		return (String)get(XML_GUID_TAG);
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		//return label;
		return (String)get(XML_LABEL_TAG);
	}

	/**
	 * @return the metaVersion
	 */
	public String getMetaVersion() {
		//return metaVersion;
		return (String)get(XML_META_VERSION_TAG);
	}

	/**
	 * @return the parent
	 */
	public String getParent() {
		//return parent;
		return (String)get(XML_EXTENDS_TAG);
	}
	
	public void set(String name, Object value)
	{
		dataMap.put(name, value);
	}
	
	public Object get(String name)
	{
		return dataMap.get(name);
	}
	
	protected abstract void matchElement(Element element, String nodeType);
}
