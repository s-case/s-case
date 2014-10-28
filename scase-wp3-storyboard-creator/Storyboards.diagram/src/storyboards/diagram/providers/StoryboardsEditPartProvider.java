package storyboards.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import storyboards.diagram.edit.parts.StoryboardDiagramEditPart;
import storyboards.diagram.edit.parts.StoryboardsEditPartFactory;
import storyboards.diagram.part.StoryboardsVisualIDRegistry;

/**
 * @generated
 */
public class StoryboardsEditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public StoryboardsEditPartProvider() {
		super(new StoryboardsEditPartFactory(), StoryboardsVisualIDRegistry.TYPED_INSTANCE,
				StoryboardDiagramEditPart.MODEL_ID);
	}

}
