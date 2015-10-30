/**
 * 
 */
package com.jtinz.cw.types;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.w3c.dom.Element;

/**
 * @author jt
 *
 */
public class CWProcess extends CWBaseType {

	public CWProcess(Element xmlDocument) {
		super(xmlDocument);
		
		set(XML_TYPE_TAG, "process"); // default in the type
		set(XML_METHOD_LIST_TAG, new ArrayList<CWMethod>());
		//set(XML_ACTIVITY_LIST_TAG, new ArrayList<CWMethod>());
		
		parseElement(xmlDocument);
		
		//System.out.println(get(XML_PROCESS_ACTIVITY_TAG));
	}

	/* (non-Javadoc)
	 * @see com.jtinz.cw.types.CWBaseType#matchElement(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	protected void matchElement(Element element, String nodeType) 
	{
		if(nodeType.equals(XML_METHOD_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_METHOD_TAG))
		{
			getMethodList().add(new CWMethod(element));
		}
		else if(nodeType.equals(XML_PROCESS_ACTIVITY_TAG))
		{
				//set(nodeType, element.getTextContent());
			set(nodeType, new CWActivity(element));
		}
		/*else if(nodeType.equals(XML_ACTIVITY_LIST_TAG))
		{
			parseElement(element); // parse children
		}
		else if(nodeType.equals(XML_CHILD_ACTIVITY_TAG))
		{
			getMethodList().add(new CWMethod(element));
		}*/
	}

	/**
	 * @return the methodList
	 */
	public List<CWMethod> getMethodList() {
		return (List<CWMethod>)get(XML_METHOD_LIST_TAG);
	}

	/**
	 * @return the activity
	 */
	public CWActivity getActivity() {
		return (CWActivity)get(XML_PROCESS_ACTIVITY_TAG);
	}

}
