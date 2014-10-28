package storyboards.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import storyboards.diagram.providers.StoryboardsElementTypes;
import storyboards.diagram.providers.StoryboardsModelingAssistantProvider;

/**
 * @generated
 */
public class StoryboardsModelingAssistantProviderOfStoryboardDiagramEditPart extends
		StoryboardsModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(6);
		types.add(StoryboardsElementTypes.Action_2006);
		types.add(StoryboardsElementTypes.Storyboard_2012);
		types.add(StoryboardsElementTypes.StartNode_2007);
		types.add(StoryboardsElementTypes.Property_2008);
		types.add(StoryboardsElementTypes.EndNode_2009);
		types.add(StoryboardsElementTypes.Condition_2010);
		return types;
	}

}
