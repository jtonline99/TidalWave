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
public class CWActivity extends CWBaseType {

	private CWActivity parent = null;
	
	public CWActivity(Element xmlDocument)
	{
		this(xmlDocument, null);
	}
	
	public CWActivity(Element xmlDocument, CWActivity parent) {
		super(xmlDocument);
		
		this.parent = parent;
		
		set(XML_ACTIVITY_LIST_TAG, new ArrayList<CWActivity>());
		set(XML_METHOD_LIST_TAG, new ArrayList<CWMethod>());
		set(XML_X_COORD_TAG, "0");
		set(XML_Y_COORD_TAG, "0");
		set(XML_PARTICIPANT_TAG, "");
		set(XML_ELEMENT_TAG, "");

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
		else if(nodeType.equals(XML_ACTIVITY_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_ACTIVITY_TAG))
		{
			getActivityList().add(new CWActivity(element, this));
		}
		else if(nodeType.equals(XML_X_COORD_TAG) ||
				nodeType.equals(XML_Y_COORD_TAG) ||
				nodeType.equals(XML_PARTICIPANT_TAG) ||
				nodeType.equals(XML_ELEMENT_TAG))
		{
			set(nodeType, element.getTextContent());
		}
	}
	

	/**
	 * @return the methodList
	 */
	public List<CWMethod> getMethodList() {
		return (List<CWMethod>)get(XML_METHOD_LIST_TAG);
	}

	/**
	 * @return the activityList
	 */
	public List<CWActivity> getActivityList() {
		return (List<CWActivity>)get(XML_ACTIVITY_LIST_TAG);
	}

	/**
	 * @return the x coord
	 */
	public int getX() {
		String x = (String)get(XML_X_COORD_TAG);
		
		return x != null ? Integer.parseInt(x) : 0;
	}

	/**
	 * @return the y coord
	 */
	public int getY() {
		String y = (String)get(XML_X_COORD_TAG);
		
		return y != null ? Integer.parseInt(y) : 0;
	}

	/**
	 * @return the participant
	 */
	public String getParticipant() {
		return (String)get(XML_PARTICIPANT_TAG);
	}

	/**
	 * @return the element
	 */
	public String getElement() {
		return (String)get(XML_ELEMENT_TAG);
	}

	/**
	 * @return the parent
	 */
	public CWActivity getParentActivity() {
		return this.parent;
	}

}
