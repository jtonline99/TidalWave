/**
 * 
 */
package com.jtinz.cw.types;

import org.w3c.dom.Element;

/**
 * @author jt
 *
 */
public class CWParameter extends CWBaseType {

	public CWParameter(Element xmlDocument) {
		super(xmlDocument);
		
		set(XML_PARAMETER_TYPE_TAG, ""); // blank out the type field, it seems to be incorrect in the model
		
		parseElement(xmlDocument);
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) {
		if(nodeType.equals(XML_PARAMETER_TYPE_TAG))
		{
			//dbSchema = element.getTextContent();
			set(nodeType, element.getTextContent());
		}
	}

}
