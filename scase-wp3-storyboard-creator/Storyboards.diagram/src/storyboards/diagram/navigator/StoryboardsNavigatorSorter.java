package storyboards.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import storyboards.diagram.part.StoryboardsVisualIDRegistry;

/**
 * @generated
 */
public class StoryboardsNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4008;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof StoryboardsNavigatorItem) {
			StoryboardsNavigatorItem item = (StoryboardsNavigatorItem) element;
			return StoryboardsVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
