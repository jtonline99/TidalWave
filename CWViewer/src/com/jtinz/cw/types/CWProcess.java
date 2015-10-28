/**
 * 
 */
package com.jtinz.cw.types;

import org.w3c.dom.Element;

/**
 * @author jt
 *
 */
public class CWProcess extends CWBaseType {

	public CWProcess(Element xmlDocument) {
		super(xmlDocument);
		
		set(XML_TYPE_TAG, "process"); // default in the type
		
		parseElement(xmlDocument);
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) {
		// TODO Auto-generated method stub

	}

}
