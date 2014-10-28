package storyboards.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import storyboards.diagram.part.StoryboardsVisualIDRegistry;

/**
 * @generated
 */
public class StoryboardsEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (StoryboardsVisualIDRegistry.getVisualID(view)) {

			case StoryboardDiagramEditPart.VISUAL_ID:
				return new StoryboardDiagramEditPart(view);

			case ActionEditPart.VISUAL_ID:
				return new ActionEditPart(view);

			case ActionNameEditPart.VISUAL_ID:
				return new ActionNameEditPart(view);

			case StoryboardEditPart.VISUAL_ID:
				return new StoryboardEditPart(view);

			case StoryboardNameEditPart.VISUAL_ID:
				return new StoryboardNameEditPart(view);

			case StartNodeEditPart.VISUAL_ID:
				return new StartNodeEditPart(view);

			case StartNodePreconditionEditPart.VISUAL_ID:
				return new StartNodePreconditionEditPart(view);

			case PropertyEditPart.VISUAL_ID:
				return new PropertyEditPart(view);

			case PropertyNameEditPart.VISUAL_ID:
				return new PropertyNameEditPart(view);

			case EndNodeEditPart.VISUAL_ID:
				return new EndNodeEditPart(view);

			case ConditionEditPart.VISUAL_ID:
				return new ConditionEditPart(view);

			case ConditionNameEditPart.VISUAL_ID:
				return new ConditionNameEditPart(view);

			case ConditionPathEditPart.VISUAL_ID:
				return new ConditionPathEditPart(view);

			case ConditionPathNameEditPart.VISUAL_ID:
				return new ConditionPathNameEditPart(view);

			case ActionPropertiesEditPart.VISUAL_ID:
				return new ActionPropertiesEditPart(view);

			case StartNodeFirstNodeEditPart.VISUAL_ID:
				return new StartNodeFirstNodeEditPart(view);

			case ActionNodeNextNodeEditPart.VISUAL_ID:
				return new ActionNodeNextNodeEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE.getTextCellEditorLocator(source);
	}

}
