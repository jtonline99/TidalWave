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
public class CWFinder extends CWBaseType {

	public CWFinder(Element xmlDocument) {
		super(xmlDocument);
		
		set(XML_TYPE_TAG, "finder"); // default in the type
		set(XML_METHOD_LIST_TAG, new ArrayList<CWMethod>());
		
		parseElement(xmlDocument);
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) {
		if(nodeType.equals(XML_METHOD_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_METHOD_TAG))
		{
			getMethodList().add(new CWMethod(element));
		}
	}

	/**
	 * @return the variableList
	 */
	public List<CWMethod> getMethodList() {
		return (List<CWMethod>)get(XML_METHOD_LIST_TAG);
	}

}
