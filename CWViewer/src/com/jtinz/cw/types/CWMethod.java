package com.jtinz.cw.types;

import org.w3c.dom.Element;

public class CWMethod extends CWBaseType 
{
	// base variable set
	/*private String valueType;
	private boolean autosave;
	private boolean validate;
	
	private String script;*/
	
	protected CWMethod(Element xmlDocument) {
		super(xmlDocument);
		
		parseElement(xmlDocument);
	}

	@Override
	protected void matchElement(Element element, String nodeType) {
		if(nodeType.equals(XML_VALUE_TYPE_TAG) ||
				nodeType.equals(XML_VALIDATE_TAG))
		{
			//valueType = element.getTextContent();
			set(nodeType, element.getTextContent());
		}
		else if(nodeType.equals(XML_AUTOSAVE_TAG))
		{
			//autosave = element.getTextContent().equals("1") ? true : false;;
			set("XML_AUTOSAVE_TAG", element.getTextContent().equals("1") ? "true" : "false");
		}
		else if(nodeType.equals(XML_VALIDATE_TAG))
		{
			//validate = element.getTextContent().equals("1") ? true : false;;
			set("XML_VALIDATE_TAG", element.getTextContent().equals("1") ? "true" : "false");
		}
		/*else if(nodeType.equals(XML_SCRIPT_TAG))
		{
			script = element.getTextContent();
		}*/
	}
	
	public String getValueType()
	{
		return (String)get(XML_VALUE_TYPE_TAG);
	}
	
	public boolean isAutosave()
	{
		return ((String)get(XML_AUTOSAVE_TAG)).equals("true") ? true : false;
	}
	
	public boolean isValidate()
	{
		return ((String)get(XML_VALIDATE_TAG)).equals("true") ? true : false;
	}
	
	public String getScript()
	{
		return (String)get(XML_SCRIPT_TAG);
	}
}
