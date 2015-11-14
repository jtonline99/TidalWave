/**
 * 
 */
package com.jtinz.cw.editors.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jtinz.cw.types.CWActivity;

/**
 * @author jt
 *
 */
public class CWActivityGraphBuilder2 
{
	LinkedList<CWActivity> _activityList = new LinkedList<CWActivity>();
	Map<Integer, ActivityRelationship> _activityRelationships = new HashMap<Integer, ActivityRelationship>();
	
	public void generateGraph(CWActivity root)
	{
		_activityList.add(root);
		traverseTree(root, _activityList, null);
		
		//System.out.println(_activityList);
		for(ActivityRelationship rel : _activityRelationships.values())
		{
			System.out.println(rel);
		}
	}
	
	private void traverseTree(CWActivity element, LinkedList<CWActivity> activityList, CWActivity parentNextElement)
	{
		if(element != null)
		{
			for(int i = 0; i < activityList.size(); i++)
			{
				CWActivity activity = activityList.get(i);
				CWActivity nextActivity = i+1 < activityList.size() ? activityList.get(i+1) : parentNextElement;
				
				String activityType = activity.getType();
				
				/*if(activityType.equals("caseActivity")) // case
				{
					
				}*/
				if(activityType.equals("switchActivity")) // switch
				{
					
				}
				else if(activityType.equals("seqActivity")) // sequence
				{
					activityList.add(activity);
					traverseTree(activity, activityList, nextActivity);
					this.addActivityRelationship(activity, nextActivity);
				}
				else
				{
					activityList.add(activity);
					traverseTree(activity, activityList, nextActivity);
					this.addActivityRelationship(activity, nextActivity);
				}
			}
		}
	}
	
	public LinkedList<CWActivity> getActivities()
	{
		return _activityList;
	}
	
	public Map<Integer, ActivityRelationship> getRelationships()
	{
		return _activityRelationships;
	}
	
	private void addActivityRelationship(CWActivity source, CWActivity target)
	{
		ActivityRelationship rel = _activityRelationships.get(source.hashCode());
		
		if(rel == null)
		{
			rel = new ActivityRelationship(source, target);
			_activityRelationships.put(source.hashCode(), rel);
		}
		else
		{
			rel.addTarget(target);
		}
	}
	
	private class ActivityRelationship
	{
		private CWActivity _source;
		List<CWActivity> _target;
		
		public ActivityRelationship(CWActivity source, CWActivity target)
		{
			this._source = source;
			this._target = new ArrayList();
			
			if(target != null)
			{
				this._target.add(target);
			}
		}
		
		public void addTarget(CWActivity target)
		{
			this._target.add(target);
		}

		/**
		 * @return the source
		 */
		public CWActivity getSource() {
			return _source;
		}

		/**
		 * @return the target
		 */
		public List<CWActivity> getTarget() {
			return _target;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ActivityRelationship [_source=" + _source + ", _target=" + _target + "]";
		}
	}
}
