/**
 * 
 */
package com.jtinz.cw.editors.providers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import com.jtinz.cw.editors.helpers.CWActivityGraphBuilder;
import com.jtinz.cw.types.CWActivity;
import com.jtinz.cw.types.CWProcess;

/**
 * @author jt
 *
 */
public class CWActivityGraphContentProvider implements IGraphEntityContentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IGraphEntityContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		/*if (inputElement instanceof CWActivity) {
			CWActivity node = (CWActivity) inputElement;
		      return node.getActivityList().toArray();
		    }
		else if(inputElement instanceof CWProcess)
		{
			CWProcess node = (CWProcess) inputElement;
		      return new CWActivity[]{node.getActivity()};
		}
		    throw new RuntimeException("Type not supported");*/
		
		if (inputElement instanceof CWActivityGraphBuilder)
		{
			CWActivityGraphBuilder node = (CWActivityGraphBuilder) inputElement;
			return node.getActivities().toArray();
		}
		throw new RuntimeException("Type not supported");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IGraphEntityContentProvider#getConnectedTo(java.lang.Object)
	 */
	@Override
	public Object[] getConnectedTo(Object entity) {
		//System.out.println(entity); // hmmm??
		
		return null;
	}

}
