package storyboards.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import storyboards.diagram.edit.parts.ActionEditPart;
import storyboards.diagram.edit.parts.ConditionEditPart;
import storyboards.diagram.edit.parts.EndNodeEditPart;
import storyboards.diagram.edit.parts.StartNodeEditPart;
import storyboards.diagram.edit.parts.StoryboardEditPart;
import storyboards.diagram.providers.StoryboardsElementTypes;
import storyboards.diagram.providers.StoryboardsModelingAssistantProvider;

/**
 * @generated
 */
public class StoryboardsModelingAssistantProviderOfStartNodeEditPart extends StoryboardsModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((StartNodeEditPart) sourceEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSource(StartNodeEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(StoryboardsElementTypes.StartNodeFirstNode_4004);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((StartNodeEditPart) sourceEditPart, targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSourceAndTarget(StartNodeEditPart source, IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof ActionEditPart) {
			types.add(StoryboardsElementTypes.StartNodeFirstNode_4004);
		}
		if (targetEditPart instanceof StoryboardEditPart) {
			types.add(StoryboardsElementTypes.StartNodeFirstNode_4004);
		}
		if (targetEditPart instanceof StartNodeEditPart) {
			types.add(StoryboardsElementTypes.StartNodeFirstNode_4004);
		}
		if (targetEditPart instanceof EndNodeEditPart) {
			types.add(StoryboardsElementTypes.StartNodeFirstNode_4004);
		}
		if (targetEditPart instanceof ConditionEditPart) {
			types.add(StoryboardsElementTypes.StartNodeFirstNode_4004);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((StartNodeEditPart) sourceEditPart, relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForTarget(StartNodeEditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == StoryboardsElementTypes.StartNodeFirstNode_4004) {
			types.add(StoryboardsElementTypes.Action_2006);
			types.add(StoryboardsElementTypes.Storyboard_2012);
			types.add(StoryboardsElementTypes.StartNode_2007);
			types.add(StoryboardsElementTypes.EndNode_2009);
			types.add(StoryboardsElementTypes.Condition_2010);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((StartNodeEditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(StartNodeEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(StoryboardsElementTypes.ConditionPath_4001);
		types.add(StoryboardsElementTypes.StartNodeFirstNode_4004);
		types.add(StoryboardsElementTypes.ActionNodeNextNode_4006);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((StartNodeEditPart) targetEditPart, relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(StartNodeEditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == StoryboardsElementTypes.ConditionPath_4001) {
			types.add(StoryboardsElementTypes.Condition_2010);
		} else if (relationshipType == StoryboardsElementTypes.StartNodeFirstNode_4004) {
			types.add(StoryboardsElementTypes.StartNode_2007);
		} else if (relationshipType == StoryboardsElementTypes.ActionNodeNextNode_4006) {
			types.add(StoryboardsElementTypes.Action_2006);
			types.add(StoryboardsElementTypes.Storyboard_2012);
		}
		return types;
	}

}
