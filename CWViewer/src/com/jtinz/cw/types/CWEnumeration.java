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
public class CWEnumeration extends CWBaseType {
	// contains: enumerationValue:List

	protected CWEnumeration(Element xmlDocument) {
		super(xmlDocument);
		
		set(XML_ENUM_VALUE_LIST_TAG, new ArrayList<CWEnumerationValue>());
		
		parseElement(xmlDocument);
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) {
		
		if(nodeType.equals(XML_ENUM_VALUE_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_ENUM_VALUE_TAG))
		{
			getValueList().add(new CWEnumerationValue(element));
		}
	}

	/**
	 * @return the valueList
	 */
	public List<CWEnumerationValue> getValueList() {
		return (List<CWEnumerationValue>)get(XML_ENUM_VALUE_LIST_TAG);
	}

}
