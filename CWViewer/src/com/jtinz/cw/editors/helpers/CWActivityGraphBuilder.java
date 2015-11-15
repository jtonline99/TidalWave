/**
 * 
 */
package com.jtinz.cw.editors.helpers;

import java.util.LinkedList;

import com.jtinz.cw.types.CWActivity;

/**
 * @author jt
 *
 */
public class CWActivityGraphBuilder 
{
	LinkedList<CWActivity> _activityList = new LinkedList<CWActivity>();
	
	public void generateGraph(CWActivity root)
	{
		_activityList.add(root);
		traverseTree(root, _activityList);
		
		//System.out.println(_activityList);
	}
	
	private void traverseTree(CWActivity element, LinkedList<CWActivity> activityList)
	{
		if(element != null)
		{
			for(CWActivity activity : element.getActivityList())
			{
				traverseTree(activity, activityList);
				
				if(activity.getType().equals("caseActivity") != true &&
				   activity.getType().equals("onExceptionActivity") != true &&
				   activity.getType().equals("compensateActivity") != true 
						)
				{
					activityList.add(activity);
				}
			}
		}
	}
	
	public LinkedList<CWActivity> getActivities()
	{
		return _activityList;
	}
}
