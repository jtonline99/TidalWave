/**
 * 
 */
package com.jtinz.cw.types;

import org.w3c.dom.Element;

/**
 * @author jt
 *
 */
public class CWDType extends CWBaseType 
{

	protected CWDType(Element xmlDocument) {
		super(xmlDocument);
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) {
		
	}

}
