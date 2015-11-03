/**
 * 
 */
package com.jtinz.cw.editors.providers;

import java.util.Comparator;
import java.util.List;

import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.layouts.Filter;
import org.eclipse.zest.layouts.InvalidLayoutConfiguration;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutEntity;
import org.eclipse.zest.layouts.LayoutRelationship;
import org.eclipse.zest.layouts.progress.ProgressListener;

import com.jtinz.cw.types.CWActivity;

/**
 * @author jt
 *
 */
public class CWActivityGraphLayoutAlgorithm implements LayoutAlgorithm {

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#applyLayout(org.eclipse.zest.layouts.LayoutEntity[], org.eclipse.zest.layouts.LayoutRelationship[], double, double, double, double, boolean, boolean)
	 */
	@Override
	public void applyLayout(LayoutEntity[] entitiesToLayout, LayoutRelationship[] relationshipsToConsider, double x,
			double y, double width, double height, boolean asynchronous, boolean continuous)
					throws InvalidLayoutConfiguration {
		
		for(LayoutEntity entity : entitiesToLayout)
		{
			GraphNode node = (GraphNode)entity.getGraphData();
			CWActivity data = (CWActivity)node.getData();
			double tx = data.getX(), ty = data.getY();
			entity.setLocationInLayout(tx, ty);
			entity.setSizeInLayout(80, 50);
			
			System.out.println(tx +","+ ty);
		}
		//System.out.println("test layout");

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#isRunning()
	 */
	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#setComparator(java.util.Comparator)
	 */
	@Override
	public void setComparator(Comparator comparator) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#setFilter(org.eclipse.zest.layouts.Filter)
	 */
	@Override
	public void setFilter(Filter filter) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#setEntityAspectRatio(double)
	 */
	@Override
	public void setEntityAspectRatio(double ratio) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#getEntityAspectRatio()
	 */
	@Override
	public double getEntityAspectRatio() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#addProgressListener(org.eclipse.zest.layouts.progress.ProgressListener)
	 */
	@Override
	public void addProgressListener(ProgressListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#removeProgressListener(org.eclipse.zest.layouts.progress.ProgressListener)
	 */
	@Override
	public void removeProgressListener(ProgressListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#setStyle(int)
	 */
	@Override
	public void setStyle(int style) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#getStyle()
	 */
	@Override
	public int getStyle() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#addEntity(org.eclipse.zest.layouts.LayoutEntity)
	 */
	@Override
	public void addEntity(LayoutEntity entity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#addRelationship(org.eclipse.zest.layouts.LayoutRelationship)
	 */
	@Override
	public void addRelationship(LayoutRelationship relationship) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#removeEntity(org.eclipse.zest.layouts.LayoutEntity)
	 */
	@Override
	public void removeEntity(LayoutEntity entity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#removeRelationship(org.eclipse.zest.layouts.LayoutRelationship)
	 */
	@Override
	public void removeRelationship(LayoutRelationship relationship) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.layouts.LayoutAlgorithm#removeRelationships(java.util.List)
	 */
	@Override
	public void removeRelationships(List relationships) {
		// TODO Auto-generated method stub

	}

}
