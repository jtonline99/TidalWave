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
public class CWScript extends CWBaseType {

	public CWScript(Element xmlDocument) {
		super(xmlDocument);
		
		set(XML_PARAMETER_LIST_TAG, new ArrayList<CWParameter>());
		set(XML_TYPE_TAG, "script"); // default in the type
		
		parseElement(xmlDocument);
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) {

		if(nodeType.equals(XML_PARAMETER_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_PARAMETER_TAG))
		{
			getParameterList().add(new CWParameter(element));
		}
		else if(nodeType.equals(XML_SCRIPT_TAG))
		{
			set(nodeType, element.getTextContent());
		}
	}

	/**
	 * @return the variableList
	 */
	public List<CWParameter> getParameterList() {
		return (List<CWParameter>)get(XML_PARAMETER_LIST_TAG);
	}

	/**
	 * @return the script
	 */
	public String getScript() {
		return (String)get(XML_SCRIPT_TAG);
	}

}
