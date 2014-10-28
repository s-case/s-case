package storyboards.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import storyboards.Action;
import storyboards.ActionNode;
import storyboards.Condition;
import storyboards.ConditionPath;
import storyboards.EndNode;
import storyboards.Node;
import storyboards.Property;
import storyboards.StartNode;
import storyboards.Storyboard;
import storyboards.StoryboardDiagram;
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
import storyboards.diagram.providers.StoryboardsElementTypes;

/**
 * @generated
 */
public class StoryboardsDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<StoryboardsNodeDescriptor> getSemanticChildren(View view) {
		switch (StoryboardsVisualIDRegistry.getVisualID(view)) {
		case StoryboardDiagramEditPart.VISUAL_ID:
			return getStoryboardDiagram_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsNodeDescriptor> getStoryboardDiagram_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		StoryboardDiagram modelElement = (StoryboardDiagram) view.getElement();
		LinkedList<StoryboardsNodeDescriptor> result = new LinkedList<StoryboardsNodeDescriptor>();
		for (Iterator<?> it = modelElement.getStoryboardactions().iterator(); it.hasNext();) {
			Action childElement = (Action) it.next();
			int visualID = StoryboardsVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ActionEditPart.VISUAL_ID) {
				result.add(new StoryboardsNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getStoryboardstoryboards().iterator(); it.hasNext();) {
			Storyboard childElement = (Storyboard) it.next();
			int visualID = StoryboardsVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == StoryboardEditPart.VISUAL_ID) {
				result.add(new StoryboardsNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		{
			StartNode childElement = modelElement.getStoryboardstartnode();
			int visualID = StoryboardsVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == StartNodeEditPart.VISUAL_ID) {
				result.add(new StoryboardsNodeDescriptor(childElement, visualID));
			}
		}
		for (Iterator<?> it = modelElement.getStoryboardproperties().iterator(); it.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = StoryboardsVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == PropertyEditPart.VISUAL_ID) {
				result.add(new StoryboardsNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		{
			EndNode childElement = modelElement.getStoryboardendnode();
			int visualID = StoryboardsVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == EndNodeEditPart.VISUAL_ID) {
				result.add(new StoryboardsNodeDescriptor(childElement, visualID));
			}
		}
		for (Iterator<?> it = modelElement.getStoryboardconditions().iterator(); it.hasNext();) {
			Condition childElement = (Condition) it.next();
			int visualID = StoryboardsVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ConditionEditPart.VISUAL_ID) {
				result.add(new StoryboardsNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getContainedLinks(View view) {
		switch (StoryboardsVisualIDRegistry.getVisualID(view)) {
		case StoryboardDiagramEditPart.VISUAL_ID:
			return getStoryboardDiagram_1000ContainedLinks(view);
		case ActionEditPart.VISUAL_ID:
			return getAction_2006ContainedLinks(view);
		case StoryboardEditPart.VISUAL_ID:
			return getStoryboard_2012ContainedLinks(view);
		case StartNodeEditPart.VISUAL_ID:
			return getStartNode_2007ContainedLinks(view);
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2008ContainedLinks(view);
		case EndNodeEditPart.VISUAL_ID:
			return getEndNode_2009ContainedLinks(view);
		case ConditionEditPart.VISUAL_ID:
			return getCondition_2010ContainedLinks(view);
		case ConditionPathEditPart.VISUAL_ID:
			return getConditionPath_4001ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getIncomingLinks(View view) {
		switch (StoryboardsVisualIDRegistry.getVisualID(view)) {
		case ActionEditPart.VISUAL_ID:
			return getAction_2006IncomingLinks(view);
		case StoryboardEditPart.VISUAL_ID:
			return getStoryboard_2012IncomingLinks(view);
		case StartNodeEditPart.VISUAL_ID:
			return getStartNode_2007IncomingLinks(view);
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2008IncomingLinks(view);
		case EndNodeEditPart.VISUAL_ID:
			return getEndNode_2009IncomingLinks(view);
		case ConditionEditPart.VISUAL_ID:
			return getCondition_2010IncomingLinks(view);
		case ConditionPathEditPart.VISUAL_ID:
			return getConditionPath_4001IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getOutgoingLinks(View view) {
		switch (StoryboardsVisualIDRegistry.getVisualID(view)) {
		case ActionEditPart.VISUAL_ID:
			return getAction_2006OutgoingLinks(view);
		case StoryboardEditPart.VISUAL_ID:
			return getStoryboard_2012OutgoingLinks(view);
		case StartNodeEditPart.VISUAL_ID:
			return getStartNode_2007OutgoingLinks(view);
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2008OutgoingLinks(view);
		case EndNodeEditPart.VISUAL_ID:
			return getEndNode_2009OutgoingLinks(view);
		case ConditionEditPart.VISUAL_ID:
			return getCondition_2010OutgoingLinks(view);
		case ConditionPathEditPart.VISUAL_ID:
			return getConditionPath_4001OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getStoryboardDiagram_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getAction_2006ContainedLinks(View view) {
		Action modelElement = (Action) view.getElement();
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Action_Properties_4002(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getStoryboard_2012ContainedLinks(View view) {
		Storyboard modelElement = (Storyboard) view.getElement();
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getStartNode_2007ContainedLinks(View view) {
		StartNode modelElement = (StartNode) view.getElement();
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_StartNode_FirstNode_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getProperty_2008ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getEndNode_2009ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getCondition_2010ContainedLinks(View view) {
		Condition modelElement = (Condition) view.getElement();
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_ConditionPath_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getConditionPath_4001ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getAction_2006IncomingLinks(View view) {
		Action modelElement = (Action) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_ConditionPath_4001(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_StartNode_FirstNode_4004(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getStoryboard_2012IncomingLinks(View view) {
		Storyboard modelElement = (Storyboard) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_ConditionPath_4001(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_StartNode_FirstNode_4004(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getStartNode_2007IncomingLinks(View view) {
		StartNode modelElement = (StartNode) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_ConditionPath_4001(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_StartNode_FirstNode_4004(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getProperty_2008IncomingLinks(View view) {
		Property modelElement = (Property) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Action_Properties_4002(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getEndNode_2009IncomingLinks(View view) {
		EndNode modelElement = (EndNode) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_ConditionPath_4001(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_StartNode_FirstNode_4004(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getCondition_2010IncomingLinks(View view) {
		Condition modelElement = (Condition) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view
				.eResource().getResourceSet().getResources());
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_ConditionPath_4001(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_StartNode_FirstNode_4004(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getConditionPath_4001IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getAction_2006OutgoingLinks(View view) {
		Action modelElement = (Action) view.getElement();
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Action_Properties_4002(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getStoryboard_2012OutgoingLinks(View view) {
		Storyboard modelElement = (Storyboard) view.getElement();
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_ActionNode_NextNode_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getStartNode_2007OutgoingLinks(View view) {
		StartNode modelElement = (StartNode) view.getElement();
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_StartNode_FirstNode_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getProperty_2008OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getEndNode_2009OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getCondition_2010OutgoingLinks(View view) {
		Condition modelElement = (Condition) view.getElement();
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_ConditionPath_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StoryboardsLinkDescriptor> getConditionPath_4001OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<StoryboardsLinkDescriptor> getContainedTypeModelFacetLinks_ConditionPath_4001(
			Condition container) {
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		for (Iterator<?> links = container.getConditionPaths().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof ConditionPath) {
				continue;
			}
			ConditionPath link = (ConditionPath) linkObject;
			if (ConditionPathEditPart.VISUAL_ID != StoryboardsVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNextConditionNode();
			result.add(new StoryboardsLinkDescriptor(container, dst, link, StoryboardsElementTypes.ConditionPath_4001,
					ConditionPathEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StoryboardsLinkDescriptor> getIncomingTypeModelFacetLinks_ConditionPath_4001(Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != StoryboardsPackage.eINSTANCE.getConditionPath_NextConditionNode()
					|| false == setting.getEObject() instanceof ConditionPath) {
				continue;
			}
			ConditionPath link = (ConditionPath) setting.getEObject();
			if (ConditionPathEditPart.VISUAL_ID != StoryboardsVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			if (false == link.eContainer() instanceof Condition) {
				continue;
			}
			Condition container = (Condition) link.eContainer();
			result.add(new StoryboardsLinkDescriptor(container, target, link,
					StoryboardsElementTypes.ConditionPath_4001, ConditionPathEditPart.VISUAL_ID));

		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StoryboardsLinkDescriptor> getIncomingFeatureModelFacetLinks_Action_Properties_4002(
			Property target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == StoryboardsPackage.eINSTANCE.getAction_Properties()) {
				result.add(new StoryboardsLinkDescriptor(setting.getEObject(), target,
						StoryboardsElementTypes.ActionProperties_4002, ActionPropertiesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StoryboardsLinkDescriptor> getIncomingFeatureModelFacetLinks_StartNode_FirstNode_4004(
			Node target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == StoryboardsPackage.eINSTANCE.getStartNode_FirstNode()) {
				result.add(new StoryboardsLinkDescriptor(setting.getEObject(), target,
						StoryboardsElementTypes.StartNodeFirstNode_4004, StartNodeFirstNodeEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StoryboardsLinkDescriptor> getIncomingFeatureModelFacetLinks_ActionNode_NextNode_4006(
			Node target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == StoryboardsPackage.eINSTANCE.getActionNode_NextNode()) {
				result.add(new StoryboardsLinkDescriptor(setting.getEObject(), target,
						StoryboardsElementTypes.ActionNodeNextNode_4006, ActionNodeNextNodeEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StoryboardsLinkDescriptor> getOutgoingFeatureModelFacetLinks_Action_Properties_4002(
			Action source) {
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		for (Iterator<?> destinations = source.getProperties().iterator(); destinations.hasNext();) {
			Property destination = (Property) destinations.next();
			result.add(new StoryboardsLinkDescriptor(source, destination,
					StoryboardsElementTypes.ActionProperties_4002, ActionPropertiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StoryboardsLinkDescriptor> getOutgoingFeatureModelFacetLinks_StartNode_FirstNode_4004(
			StartNode source) {
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		Node destination = source.getFirstNode();
		if (destination == null) {
			return result;
		}
		result.add(new StoryboardsLinkDescriptor(source, destination, StoryboardsElementTypes.StartNodeFirstNode_4004,
				StartNodeFirstNodeEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StoryboardsLinkDescriptor> getOutgoingFeatureModelFacetLinks_ActionNode_NextNode_4006(
			ActionNode source) {
		LinkedList<StoryboardsLinkDescriptor> result = new LinkedList<StoryboardsLinkDescriptor>();
		Node destination = source.getNextNode();
		if (destination == null) {
			return result;
		}
		result.add(new StoryboardsLinkDescriptor(source, destination, StoryboardsElementTypes.ActionNodeNextNode_4006,
				ActionNodeNextNodeEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<StoryboardsNodeDescriptor> getSemanticChildren(View view) {
			return StoryboardsDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<StoryboardsLinkDescriptor> getContainedLinks(View view) {
			return StoryboardsDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<StoryboardsLinkDescriptor> getIncomingLinks(View view) {
			return StoryboardsDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<StoryboardsLinkDescriptor> getOutgoingLinks(View view) {
			return StoryboardsDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
