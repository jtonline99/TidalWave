/**
 * 
 */
package com.jtinz.cw.editors.providers;

import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.EntityConnectionData;
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
		if (entity instanceof CWActivity)
		{
			CWActivity activity = (CWActivity)entity;
			CWActivity nextActivity = null;
			//return activity.getActivityList().toArray();
			List<CWActivity> activityList = activity.getActivityList();
			
			if(activityList.size() > 0) // return first child
			{
				nextActivity = activityList.get(0);
				
				if(nextActivity.getType().equals("caseActivity") == false)
				{
					System.out.println(activity.getName() + "->" + nextActivity.getName()); // test
					return new CWActivity[] { nextActivity };
				}
				else if(nextActivity.getType().equals("caseActivity") == true) // test addition
				{
					List<CWActivity> caseActivityList = nextActivity.getActivityList();
					
					if(caseActivityList.size() > 0)
					{
						CWActivity caseNextActivity = caseActivityList.get(0);
					
						System.out.println(activity.getName() + "->" + caseNextActivity.getName()); // test
						return new CWActivity[] { caseNextActivity };
					}
					
				}
			}
			//else // no children, search parent
			//{
				nextActivity = findNextActivity(activity);
				System.out.println(activity.getName() + "->" + (nextActivity != null ? nextActivity.getName() : "null activity")); // test
				return nextActivity != null ? new CWActivity[] { nextActivity } : null;
			//}
		}
		

		System.out.println("null activity"); // test
		return null;
	}
	
	private CWActivity findNextActivity(CWActivity activity)
	{
		CWActivity parentActivity = activity.getParentActivity();
		
		if(parentActivity != null)
		{
			if(parentActivity.getType().equals("caseActivity") == true)
			{
				return findNextActivity(parentActivity);
			}
			else
			{
				List<CWActivity> activityList = parentActivity.getActivityList();
				
				for(int i = 0; i < activityList.size(); i++)
				{
					if(activityList.get(i).equals(activity))
					{
						if(i+1 >= activityList.size())
						{
							return findNextActivity(parentActivity);
						}
						else
						{
							return activityList.get(i+1);
						}
					}
				}
			}
		}
		
		return null;
	}

}
