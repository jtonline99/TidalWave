/**
 * 
 */
package com.jtinz.cw.editors.providers;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.jtinz.cw.types.CWActivity;
import com.jtinz.cw.types.CWProcess;

/**
 * @author jt
 *
 */
public class CWActivityTreeContentProvider implements ITreeContentProvider 
{
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// unimplemented
		
	}

	@Override
	public Object[] getChildren(Object arg0) 
	{
		if(arg0 instanceof CWActivity)
		{
			List<CWActivity> children = ((CWActivity)arg0).getActivityList();
			
			return children != null ? children.toArray() : null;
		}
		else if(arg0 instanceof CWProcess)
		{
			return new CWActivity[] {((CWProcess)arg0).getActivity()};
		}
		
		return null;
	}

	@Override
	public Object[] getElements(Object arg0) {
		return this.getChildren(arg0);
	}

	@Override
	public Object getParent(Object arg0) {
		if(arg0 instanceof CWActivity)
			return ((CWActivity)arg0).getParentActivity();
		
		return null;
	}

	@Override
	public boolean hasChildren(Object arg0) {
		if(arg0 instanceof CWActivity)
		{
			List<CWActivity> children = ((CWActivity)arg0).getActivityList();
			
			return children != null && children.size() > 0 ? true : false;
		}
		else if(arg0 instanceof CWProcess)
		{
			if(((CWProcess)arg0).getActivity() != null)
				return true;
		}
		return false;
	}
}
