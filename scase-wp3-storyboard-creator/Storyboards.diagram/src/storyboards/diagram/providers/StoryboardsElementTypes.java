package storyboards.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import storyboards.StoryboardsPackage;
import storyboards.diagram.edit.parts.ActionEditPart;
import storyboards.diagram.edit.parts.ActionNodeNextNodeEditPart;
import storyboards.diagram.edit.parts.ActionPropertiesEditPart;
import storyboards.diagram.edit.parts.ConditionEditPart;
import storyboards.diagram.edit.parts.ConditionPathEditPart;
import storyboards.diagram.edit.parts.EndNodeEditPart;
import storyboards.diagram.edit.parts.PropertyEditPart;
import storyboards.diagram.edit.parts.StartNodeEditPart;
import storyboards.diagram.edit.parts.StartNodeFirstNodeEditPart;
import storyboards.diagram.edit.parts.StoryboardDiagramEditPart;
import storyboards.diagram.edit.parts.StoryboardEditPart;
import storyboards.diagram.part.StoryboardsDiagramEditorPlugin;

/**
 * @generated
 */
public class StoryboardsElementTypes {

	/**
	 * @generated
	 */
	private StoryboardsElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			StoryboardsDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType StoryboardDiagram_1000 = getElementType("Storyboards.diagram.StoryboardDiagram_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Action_2006 = getElementType("Storyboards.diagram.Action_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Storyboard_2012 = getElementType("Storyboards.diagram.Storyboard_2012"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType StartNode_2007 = getElementType("Storyboards.diagram.StartNode_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Property_2008 = getElementType("Storyboards.diagram.Property_2008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EndNode_2009 = getElementType("Storyboards.diagram.EndNode_2009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Condition_2010 = getElementType("Storyboards.diagram.Condition_2010"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ConditionPath_4001 = getElementType("Storyboards.diagram.ConditionPath_4001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ActionProperties_4002 = getElementType("Storyboards.diagram.ActionProperties_4002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType StartNodeFirstNode_4004 = getElementType("Storyboards.diagram.StartNodeFirstNode_4004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ActionNodeNextNode_4006 = getElementType("Storyboards.diagram.ActionNodeNextNode_4006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(StoryboardDiagram_1000, StoryboardsPackage.eINSTANCE.getStoryboardDiagram());

			elements.put(Action_2006, StoryboardsPackage.eINSTANCE.getAction());

			elements.put(Storyboard_2012, StoryboardsPackage.eINSTANCE.getStoryboard());

			elements.put(StartNode_2007, StoryboardsPackage.eINSTANCE.getStartNode());

			elements.put(Property_2008, StoryboardsPackage.eINSTANCE.getProperty());

			elements.put(EndNode_2009, StoryboardsPackage.eINSTANCE.getEndNode());

			elements.put(Condition_2010, StoryboardsPackage.eINSTANCE.getCondition());

			elements.put(ConditionPath_4001, StoryboardsPackage.eINSTANCE.getConditionPath());

			elements.put(ActionProperties_4002, StoryboardsPackage.eINSTANCE.getAction_Properties());

			elements.put(StartNodeFirstNode_4004, StoryboardsPackage.eINSTANCE.getStartNode_FirstNode());

			elements.put(ActionNodeNextNode_4006, StoryboardsPackage.eINSTANCE.getActionNode_NextNode());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(StoryboardDiagram_1000);
			KNOWN_ELEMENT_TYPES.add(Action_2006);
			KNOWN_ELEMENT_TYPES.add(Storyboard_2012);
			KNOWN_ELEMENT_TYPES.add(StartNode_2007);
			KNOWN_ELEMENT_TYPES.add(Property_2008);
			KNOWN_ELEMENT_TYPES.add(EndNode_2009);
			KNOWN_ELEMENT_TYPES.add(Condition_2010);
			KNOWN_ELEMENT_TYPES.add(ConditionPath_4001);
			KNOWN_ELEMENT_TYPES.add(ActionProperties_4002);
			KNOWN_ELEMENT_TYPES.add(StartNodeFirstNode_4004);
			KNOWN_ELEMENT_TYPES.add(ActionNodeNextNode_4006);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case StoryboardDiagramEditPart.VISUAL_ID:
			return StoryboardDiagram_1000;
		case ActionEditPart.VISUAL_ID:
			return Action_2006;
		case StoryboardEditPart.VISUAL_ID:
			return Storyboard_2012;
		case StartNodeEditPart.VISUAL_ID:
			return StartNode_2007;
		case PropertyEditPart.VISUAL_ID:
			return Property_2008;
		case EndNodeEditPart.VISUAL_ID:
			return EndNode_2009;
		case ConditionEditPart.VISUAL_ID:
			return Condition_2010;
		case ConditionPathEditPart.VISUAL_ID:
			return ConditionPath_4001;
		case ActionPropertiesEditPart.VISUAL_ID:
			return ActionProperties_4002;
		case StartNodeFirstNodeEditPart.VISUAL_ID:
			return StartNodeFirstNode_4004;
		case ActionNodeNextNodeEditPart.VISUAL_ID:
			return ActionNodeNextNode_4006;
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(elementTypeImages) {

		/**
		 * @generated
		 */
		@Override
		public boolean isKnownElementType(IElementType elementType) {
			return storyboards.diagram.providers.StoryboardsElementTypes.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return storyboards.diagram.providers.StoryboardsElementTypes.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return storyboards.diagram.providers.StoryboardsElementTypes.getElement(elementTypeAdapter);
		}
	};

}
