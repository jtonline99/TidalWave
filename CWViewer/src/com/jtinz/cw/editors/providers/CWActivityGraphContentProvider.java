/**
 * 
 */
package com.jtinz.cw.editors.providers;

import java.util.ArrayList;
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
			//List<CWActivity> nextActivityList = null;
			CWActivity nextActivity = null;
			String activityType = activity.getType();
			//return activity.getActivityList().toArray();
			/*List<CWActivity> activityList = activity.getActivityList();
			
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
			}*/
			
			if(activityType.equals("seqActivity"))// ||
			   //activityType.equals("onExceptionActivity"))// ||
			   //activityType.equals("scriptActivity"))// ||
			   //activityType.equals("opActivity"))
			{
				// get next entity
				/*nextActivity = findNextActivity(activity);
				
				return nextActivity != null ? new CWActivity[] { nextActivity } : null;*/

				List<CWActivity> nextActivityList = findNextActivity(activity);
				
				if(nextActivityList != null && nextActivityList.size() > 0)
				{
					CWActivity exceptionActivity = findException(activity);
					
					if(exceptionActivity != null)
					{
						nextActivityList.add(exceptionActivity);
					}
					
					return nextActivityList.toArray();
				}
				else
				{
					//return null;
					// find next segment
					nextActivity = findNextSegment(activity);
					
					return nextActivity != null ? new CWActivity[] { nextActivity } : null;
				}
			}
			else if(activityType.equals("subflowActivity") ||
			   activityType.equals("scriptActivity") ||
			   activityType.equals("opActivity") ||
			   activityType.equals("syncActivity") ||
			   activityType.equals("timeoutActivity")
			   )
			{
				// get next entity
				/*nextActivity = findNextActivity(activity);
				
				if(nextActivity == null)
				{
					// find next segment
					nextActivity = findNextSegment(activity);
				}
				
				return nextActivity != null ? new CWActivity[] { nextActivity } : null;*/
				List<CWActivity> nextActivityList = findNextActivity(activity);
				
				if(nextActivityList != null && nextActivityList.size() > 0)
				{
					return nextActivityList.toArray();
				}
				else
				{
					// find next segment
					nextActivity = findNextSegment(activity);
					
					return nextActivity != null ? new CWActivity[] { nextActivity } : null;
				}
			}
			/*else if(activityType.equals("onExceptionActivity") ||
					activityType.equals("compensateActivity")
					)// ||
			   //activityType.equals("scriptActivity"))// ||
			   //activityType.equals("opActivity"))
			{
				// get next entity
				nextActivity = findNextActivity(activity);
				
				return nextActivity != null ? new CWActivity[] { nextActivity } : null;
			}*/
			else if(activityType.equals("switchActivity"))
			{
				// traverse cases and return list
				
				// get next entity
				//nextActivity = findNextActivity(activity);
				
				//if(nextActivity == null)
				//{
					// find next segment
				//	nextActivity = findNextSegment(activity);
				//}
				
				//return nextActivity != null ? new CWActivity[] { nextActivity } : null;
				List<CWActivity> nextActivityList = traverseCaseActivities(activity);
				
				if(nextActivityList != null && nextActivityList.size() > 0)
				{
					return nextActivityList.toArray();
				}
				else
				{
					return null;
				}
			}
			else if(activityType.equals("choiceActivity"))
			{
				// return list
				List<CWActivity> nextActivityList = activity.getActivityList();
				
				if(nextActivityList!= null && nextActivityList.size() > 0)
				{
					return nextActivityList.toArray();
				}
				else
				{
					return null;
				}
			}
			else if(activityType.equals("caseActivity") ||
					activityType.equals("completeActivity") ||
					activityType.equals("onExceptionActivity") ||
					activityType.equals("compensateActivity")
					)
			{
				// ignore
			}
			
			// repeatActivity??
			
			/*if(activity.getType().equals("completeActivity") == false)
			{
			
				nextActivityList = findNextActivity(activity);
				
				if(nextActivityList.size() > 0)
				{
					return nextActivityList.toArray();
				}
				
				//else // no children, search parent
				//{
					nextActivity = findNextSegment(activity);
					System.out.println(activity.getName() + "->" + (nextActivity != null ? nextActivity.getName() : "null activity")); // test
					return nextActivity != null ? new CWActivity[] { nextActivity } : null;
				//}
			}*/
			System.out.println(activity.getName() + "-> null"); // test
		}
		

		//System.out.println("null activity"); // test
		return null;
	}
	
	private List<CWActivity> findNextActivity(CWActivity activity)
	{
		List<CWActivity> activityList = activity.getActivityList();
		CWActivity nextActivity = null;
		String activityType = activity.getType();
		String nextActivityType = null;
		List<CWActivity> resultList = new ArrayList<CWActivity>();
		//List<CWActivity> resultList = new ArrayList<CWActivity>();
		
		if(activityList.size() > 0) // return first child
		{
			nextActivity = activityList.get(0);
			nextActivityType = nextActivity.getType();
			
			/*if(nextActivity.getType().equals("caseActivity") == false)
			{
				System.out.println(activity.getName() + "->" + nextActivity.getName()); // test
				return nextActivity;
			}
			else */if(nextActivityType.equals("caseActivity") == true ||
					  nextActivityType.equals("onExceptionActivity") == true ||
					  nextActivityType.equals("compensateActivity") == true
					) // test addition
			{
				List<CWActivity> edgeActivityList = nextActivity.getActivityList();
				
				if(edgeActivityList.size() > 0)
				{
					CWActivity edgeNextActivity = edgeActivityList.get(0);
				
					System.out.println(activity.getName() + "->" + edgeNextActivity.getName()); // test
					//return edgeNextActivity;
					resultList.add(edgeNextActivity);
					
					if(nextActivityType.equals("compensateActivity") == true)
					{
						if(activityList.size() > 1)
						{
							resultList.add(activityList.get(1));
						}
						else
						{
							CWActivity nextSegment = findNextSegment(activity);
							
							// return next segment
							if(nextSegment != null)
								resultList.add(nextSegment);
						}
					}
				}
				
			}
			else
			{
				System.out.println(activity.getName() + "->" + nextActivity.getName()); // test
				//return nextActivity;
				resultList.add(nextActivity);
			}
		}
		
		return resultList;
		//return null;
	}
	
	private List<CWActivity> traverseCaseActivities(CWActivity activity)
	{
		List<CWActivity> activityList = activity.getActivityList();
		CWActivity nextActivity = null;
		List<CWActivity> resultList = new ArrayList<CWActivity>();
		
		for(int i = 0; i < activityList.size(); i++) // return first child
		{
			//if(activity.getType().equals("switchActivity") || i == 0)
			//{
				nextActivity = activityList.get(i);
				
				/*if(nextActivity.getType().equals("caseActivity") == false)
				{
					System.out.println(activity.getName() + "->" + nextActivity.getName()); // test
					//return new CWActivity[] {nextActivity};
					resultList.add(nextActivity);
				}
				else */
				if(nextActivity.getType().equals("caseActivity") == true) // test addition
				{
					List<CWActivity> caseActivityList = nextActivity.getActivityList();
					
					if(caseActivityList.size() > 0)
					{
						CWActivity caseNextActivity = caseActivityList.get(0);
					
						System.out.println(activity.getName() + "->" + caseNextActivity.getName()); // test
						// new CWActivity[] {caseNextActivity};
						resultList.add(caseNextActivity);
					}
					else
					{
						CWActivity nextSegment = findNextSegment(nextActivity);
						
						// return next segment
						if(nextSegment != null)
							resultList.add(nextSegment);
					}
					
				}
			//}
		}
		
		return resultList;
		//return null;
	}
	
	private CWActivity findNextSegment(CWActivity activity)
	{
		CWActivity parentActivity = activity.getParentActivity();
		//CWActivity nextActivity = null;
		
		if(parentActivity != null)
		{
			//if(parentActivity.getType().equals("caseActivity") == true ||
			//   parentActivity.getType().equals("switchActivity") == true)
			if(parentActivity.getType().equals("compensateActivity") == true ||
			   //parentActivity.getType().equals("choiceActivity") == true ||
			   parentActivity.getType().equals("onExceptionActivity") == true)
			{
				return null; //shouldn't traverse above this parent
			}
			else if(parentActivity.getType().equals("seqActivity") == false)
			{
				return findNextSegment(parentActivity);
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
							return findNextSegment(parentActivity);
						}
						else
						{
							CWActivity nextActivity = activityList.get(i+1);
							
							if(nextActivity.getType().equals("onExceptionActivity") == false)
							{
								return nextActivity;
							}
							
							//return activityList.get(i+1);
						}
					}
				}
			}
		}
		
		return null;
	}
	
	private CWActivity findException(CWActivity activity)
	{
		List<CWActivity> activityList = activity.getActivityList();
		
		for(int i = 0; i < activityList.size(); i++)
		{
			if(activityList.get(i).getType().equals("onExceptionActivity") == true)
			{
				List<CWActivity> nextActivityList = findNextActivity(activityList.get(i));
				
				if(nextActivityList.size() > 0)
				{
					return nextActivityList.get(0);
				}
			}
		}
		
		return null;
	}

}
