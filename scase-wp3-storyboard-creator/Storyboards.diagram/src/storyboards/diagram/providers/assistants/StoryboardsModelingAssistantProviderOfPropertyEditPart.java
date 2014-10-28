package storyboards.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import storyboards.diagram.edit.parts.PropertyEditPart;
import storyboards.diagram.providers.StoryboardsElementTypes;
import storyboards.diagram.providers.StoryboardsModelingAssistantProvider;

/**
 * @generated
 */
public class StoryboardsModelingAssistantProviderOfPropertyEditPart extends StoryboardsModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((PropertyEditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(PropertyEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(StoryboardsElementTypes.ActionProperties_4002);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((PropertyEditPart) targetEditPart, relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(PropertyEditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == StoryboardsElementTypes.ActionProperties_4002) {
			types.add(StoryboardsElementTypes.Action_2006);
		}
		return types;
	}

}
