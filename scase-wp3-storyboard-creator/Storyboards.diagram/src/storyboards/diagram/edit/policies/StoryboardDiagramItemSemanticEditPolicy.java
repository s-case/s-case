package storyboards.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import storyboards.diagram.edit.commands.ActionCreateCommand;
import storyboards.diagram.edit.commands.ConditionCreateCommand;
import storyboards.diagram.edit.commands.EndNodeCreateCommand;
import storyboards.diagram.edit.commands.PropertyCreateCommand;
import storyboards.diagram.edit.commands.StartNodeCreateCommand;
import storyboards.diagram.edit.commands.StoryboardCreateCommand;
import storyboards.diagram.providers.StoryboardsElementTypes;

/**
 * @generated
 */
public class StoryboardDiagramItemSemanticEditPolicy extends StoryboardsBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StoryboardDiagramItemSemanticEditPolicy() {
		super(StoryboardsElementTypes.StoryboardDiagram_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (StoryboardsElementTypes.Action_2006 == req.getElementType()) {
			return getGEFWrapper(new ActionCreateCommand(req));
		}
		if (StoryboardsElementTypes.Storyboard_2012 == req.getElementType()) {
			return getGEFWrapper(new StoryboardCreateCommand(req));
		}
		if (StoryboardsElementTypes.StartNode_2007 == req.getElementType()) {
			return getGEFWrapper(new StartNodeCreateCommand(req));
		}
		if (StoryboardsElementTypes.Property_2008 == req.getElementType()) {
			return getGEFWrapper(new PropertyCreateCommand(req));
		}
		if (StoryboardsElementTypes.EndNode_2009 == req.getElementType()) {
			return getGEFWrapper(new EndNodeCreateCommand(req));
		}
		if (StoryboardsElementTypes.Condition_2010 == req.getElementType()) {
			return getGEFWrapper(new ConditionCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}

	}

}
