/**
 * 
 */
package com.jtinz.cw.types;

import org.w3c.dom.Element;

/**
 * @author jt
 *
 */
public class CWEnumerationValue extends CWBaseType {
	// contains: name, type, code
	
	protected CWEnumerationValue(Element xmlDocument) {
		super(xmlDocument);
		
		set(XML_CODE_ATTR, xmlDocument.getAttribute(XML_CODE_ATTR));
		
		//parseElement(xmlDocument);
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) {
		
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		//return parent;
		return (String)get(XML_CODE_ATTR);
	}

}
