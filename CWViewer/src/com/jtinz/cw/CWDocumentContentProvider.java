/**
 * 
 */
package com.jtinz.cw;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.jtinz.cw.types.CWDocument;

/**
 * @author jt
 *
 */
public class CWDocumentContentProvider implements IStructuredContentProvider {

	private CWDocument document;
	
	public CWDocumentContentProvider(CWDocument document)
	{
		this.document = document;
	}
	
	@Override
	public void dispose() {
		// not implemented
		
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// not implemented
		
	}

	@Override
	public Object[] getElements(Object input) {
		return document.getVariableList().toArray();
	}

}
