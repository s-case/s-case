package storyboards.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import storyboards.StoryboardDiagram;
import storyboards.StoryboardsPackage;
import storyboards.diagram.edit.parts.ActionEditPart;
import storyboards.diagram.edit.parts.ActionNameEditPart;
import storyboards.diagram.edit.parts.ConditionEditPart;
import storyboards.diagram.edit.parts.ConditionNameEditPart;
import storyboards.diagram.edit.parts.ConditionPathEditPart;
import storyboards.diagram.edit.parts.ConditionPathNameEditPart;
import storyboards.diagram.edit.parts.EndNodeEditPart;
import storyboards.diagram.edit.parts.PropertyEditPart;
import storyboards.diagram.edit.parts.PropertyNameEditPart;
import storyboards.diagram.edit.parts.StartNodeEditPart;
import storyboards.diagram.edit.parts.StartNodePreconditionEditPart;
import storyboards.diagram.edit.parts.StoryboardDiagramEditPart;
import storyboards.diagram.edit.parts.StoryboardEditPart;
import storyboards.diagram.edit.parts.StoryboardNameEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class StoryboardsVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "Storyboards.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (StoryboardDiagramEditPart.MODEL_ID.equals(view.getType())) {
				return StoryboardDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return storyboards.diagram.part.StoryboardsVisualIDRegistry.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				StoryboardsDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (StoryboardsPackage.eINSTANCE.getStoryboardDiagram().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((StoryboardDiagram) domainElement)) {
			return StoryboardDiagramEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = storyboards.diagram.part.StoryboardsVisualIDRegistry.getModelID(containerView);
		if (!StoryboardDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (StoryboardDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = storyboards.diagram.part.StoryboardsVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = StoryboardDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case StoryboardDiagramEditPart.VISUAL_ID:
			if (StoryboardsPackage.eINSTANCE.getAction().isSuperTypeOf(domainElement.eClass())) {
				return ActionEditPart.VISUAL_ID;
			}
			if (StoryboardsPackage.eINSTANCE.getStoryboard().isSuperTypeOf(domainElement.eClass())) {
				return StoryboardEditPart.VISUAL_ID;
			}
			if (StoryboardsPackage.eINSTANCE.getStartNode().isSuperTypeOf(domainElement.eClass())) {
				return StartNodeEditPart.VISUAL_ID;
			}
			if (StoryboardsPackage.eINSTANCE.getProperty().isSuperTypeOf(domainElement.eClass())) {
				return PropertyEditPart.VISUAL_ID;
			}
			if (StoryboardsPackage.eINSTANCE.getEndNode().isSuperTypeOf(domainElement.eClass())) {
				return EndNodeEditPart.VISUAL_ID;
			}
			if (StoryboardsPackage.eINSTANCE.getCondition().isSuperTypeOf(domainElement.eClass())) {
				return ConditionEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = storyboards.diagram.part.StoryboardsVisualIDRegistry.getModelID(containerView);
		if (!StoryboardDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (StoryboardDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = storyboards.diagram.part.StoryboardsVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = StoryboardDiagramEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case StoryboardDiagramEditPart.VISUAL_ID:
			if (ActionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StoryboardEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartNodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PropertyEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndNodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ConditionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ActionEditPart.VISUAL_ID:
			if (ActionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StoryboardEditPart.VISUAL_ID:
			if (StoryboardNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartNodeEditPart.VISUAL_ID:
			if (StartNodePreconditionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PropertyEditPart.VISUAL_ID:
			if (PropertyNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ConditionEditPart.VISUAL_ID:
			if (ConditionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ConditionPathEditPart.VISUAL_ID:
			if (ConditionPathNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (StoryboardsPackage.eINSTANCE.getConditionPath().isSuperTypeOf(domainElement.eClass())) {
			return ConditionPathEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(StoryboardDiagram element) {
		return true;
	}

	/**
	 * @generated
	 */
	public static boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	 * @generated
	 */
	public static boolean isCompartmentVisualID(int visualID) {
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case StoryboardDiagramEditPart.VISUAL_ID:
			return false;
		case ActionEditPart.VISUAL_ID:
		case StartNodeEditPart.VISUAL_ID:
		case PropertyEditPart.VISUAL_ID:
		case EndNodeEditPart.VISUAL_ID:
		case ConditionEditPart.VISUAL_ID:
		case StoryboardEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		 * @generated
		 */
		@Override
		public int getVisualID(View view) {
			return storyboards.diagram.part.StoryboardsVisualIDRegistry.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return storyboards.diagram.part.StoryboardsVisualIDRegistry.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return storyboards.diagram.part.StoryboardsVisualIDRegistry.getNodeVisualID(containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return storyboards.diagram.part.StoryboardsVisualIDRegistry.checkNodeVisualID(containerView, domainElement,
					candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return storyboards.diagram.part.StoryboardsVisualIDRegistry.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return storyboards.diagram.part.StoryboardsVisualIDRegistry.isSemanticLeafVisualID(visualID);
		}
	};

}
