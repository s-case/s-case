package storyboards.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import storyboards.diagram.providers.StoryboardsElementTypes;

/**
 * @generated
 */
public class ConditionPathItemSemanticEditPolicy extends StoryboardsBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ConditionPathItemSemanticEditPolicy() {
		super(StoryboardsElementTypes.ConditionPath_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
