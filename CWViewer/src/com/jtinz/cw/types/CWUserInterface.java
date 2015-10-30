/**
 * 
 */
package com.jtinz.cw.types;

import org.w3c.dom.Element;

/**
 * @author jt
 *
 */
public class CWUserInterface extends CWBaseType 
{

	public CWUserInterface(Element xmlDocument) {
		super(xmlDocument);
		set(XML_TYPE_TAG, "userInterface"); // default in the type
		
		parseElement(xmlDocument);
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) {
		
	}

}
