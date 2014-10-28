package storyboards.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import storyboards.diagram.edit.parts.ActionEditPart;
import storyboards.diagram.edit.parts.ActionNameEditPart;
import storyboards.diagram.edit.parts.ActionNodeNextNodeEditPart;
import storyboards.diagram.edit.parts.ActionPropertiesEditPart;
import storyboards.diagram.edit.parts.ConditionEditPart;
import storyboards.diagram.edit.parts.ConditionNameEditPart;
import storyboards.diagram.edit.parts.ConditionPathEditPart;
import storyboards.diagram.edit.parts.ConditionPathNameEditPart;
import storyboards.diagram.edit.parts.EndNodeEditPart;
import storyboards.diagram.edit.parts.PropertyEditPart;
import storyboards.diagram.edit.parts.PropertyNameEditPart;
import storyboards.diagram.edit.parts.StartNodeEditPart;
import storyboards.diagram.edit.parts.StartNodeFirstNodeEditPart;
import storyboards.diagram.edit.parts.StartNodePreconditionEditPart;
import storyboards.diagram.edit.parts.StoryboardDiagramEditPart;
import storyboards.diagram.edit.parts.StoryboardEditPart;
import storyboards.diagram.edit.parts.StoryboardNameEditPart;
import storyboards.diagram.part.StoryboardsDiagramEditorPlugin;
import storyboards.diagram.part.StoryboardsVisualIDRegistry;
import storyboards.diagram.providers.StoryboardsElementTypes;
import storyboards.diagram.providers.StoryboardsParserProvider;

/**
 * @generated
 */
public class StoryboardsNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider,
		ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		StoryboardsDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		StoryboardsDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof StoryboardsNavigatorItem && !isOwnView(((StoryboardsNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof StoryboardsNavigatorGroup) {
			StoryboardsNavigatorGroup group = (StoryboardsNavigatorGroup) element;
			return StoryboardsDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof StoryboardsNavigatorItem) {
			StoryboardsNavigatorItem navigatorItem = (StoryboardsNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (StoryboardsVisualIDRegistry.getVisualID(view)) {
		case StoryboardDiagramEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http:///auth/storyboards.ecore?StoryboardDiagram", StoryboardsElementTypes.StoryboardDiagram_1000); //$NON-NLS-1$
		case ActionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///auth/storyboards.ecore?Action", StoryboardsElementTypes.Action_2006); //$NON-NLS-1$
		case StartNodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///auth/storyboards.ecore?StartNode", StoryboardsElementTypes.StartNode_2007); //$NON-NLS-1$
		case PropertyEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///auth/storyboards.ecore?Property", StoryboardsElementTypes.Property_2008); //$NON-NLS-1$
		case EndNodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///auth/storyboards.ecore?EndNode", StoryboardsElementTypes.EndNode_2009); //$NON-NLS-1$
		case ConditionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///auth/storyboards.ecore?Condition", StoryboardsElementTypes.Condition_2010); //$NON-NLS-1$
		case StoryboardEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///auth/storyboards.ecore?Storyboard", StoryboardsElementTypes.Storyboard_2012); //$NON-NLS-1$
		case ConditionPathEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///auth/storyboards.ecore?ConditionPath", StoryboardsElementTypes.ConditionPath_4001); //$NON-NLS-1$
		case ActionPropertiesEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///auth/storyboards.ecore?Action?properties", StoryboardsElementTypes.ActionProperties_4002); //$NON-NLS-1$
		case StartNodeFirstNodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///auth/storyboards.ecore?StartNode?firstNode", StoryboardsElementTypes.StartNodeFirstNode_4004); //$NON-NLS-1$
		case ActionNodeNextNodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///auth/storyboards.ecore?ActionNode?nextNode", StoryboardsElementTypes.ActionNodeNextNode_4006); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = StoryboardsDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null && StoryboardsElementTypes.isKnownElementType(elementType)) {
			image = StoryboardsElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof StoryboardsNavigatorGroup) {
			StoryboardsNavigatorGroup group = (StoryboardsNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof StoryboardsNavigatorItem) {
			StoryboardsNavigatorItem navigatorItem = (StoryboardsNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (StoryboardsVisualIDRegistry.getVisualID(view)) {
		case StoryboardDiagramEditPart.VISUAL_ID:
			return getStoryboardDiagram_1000Text(view);
		case ActionEditPart.VISUAL_ID:
			return getAction_2006Text(view);
		case StartNodeEditPart.VISUAL_ID:
			return getStartNode_2007Text(view);
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2008Text(view);
		case EndNodeEditPart.VISUAL_ID:
			return getEndNode_2009Text(view);
		case ConditionEditPart.VISUAL_ID:
			return getCondition_2010Text(view);
		case StoryboardEditPart.VISUAL_ID:
			return getStoryboard_2012Text(view);
		case ConditionPathEditPart.VISUAL_ID:
			return getConditionPath_4001Text(view);
		case ActionPropertiesEditPart.VISUAL_ID:
			return getActionProperties_4002Text(view);
		case StartNodeFirstNodeEditPart.VISUAL_ID:
			return getStartNodeFirstNode_4004Text(view);
		case ActionNodeNextNodeEditPart.VISUAL_ID:
			return getActionNodeNextNode_4006Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getStoryboardDiagram_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getAction_2006Text(View view) {
		IParser parser = StoryboardsParserProvider.getParser(StoryboardsElementTypes.Action_2006,
				view.getElement() != null ? view.getElement() : view,
				StoryboardsVisualIDRegistry.getType(ActionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StoryboardsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getStartNode_2007Text(View view) {
		IParser parser = StoryboardsParserProvider.getParser(StoryboardsElementTypes.StartNode_2007,
				view.getElement() != null ? view.getElement() : view,
				StoryboardsVisualIDRegistry.getType(StartNodePreconditionEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StoryboardsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getProperty_2008Text(View view) {
		IParser parser = StoryboardsParserProvider.getParser(StoryboardsElementTypes.Property_2008,
				view.getElement() != null ? view.getElement() : view,
				StoryboardsVisualIDRegistry.getType(PropertyNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StoryboardsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getEndNode_2009Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getCondition_2010Text(View view) {
		IParser parser = StoryboardsParserProvider.getParser(StoryboardsElementTypes.Condition_2010,
				view.getElement() != null ? view.getElement() : view,
				StoryboardsVisualIDRegistry.getType(ConditionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StoryboardsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getStoryboard_2012Text(View view) {
		IParser parser = StoryboardsParserProvider.getParser(StoryboardsElementTypes.Storyboard_2012,
				view.getElement() != null ? view.getElement() : view,
				StoryboardsVisualIDRegistry.getType(StoryboardNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StoryboardsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getConditionPath_4001Text(View view) {
		IParser parser = StoryboardsParserProvider.getParser(StoryboardsElementTypes.ConditionPath_4001,
				view.getElement() != null ? view.getElement() : view,
				StoryboardsVisualIDRegistry.getType(ConditionPathNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StoryboardsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getActionProperties_4002Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getStartNodeFirstNode_4004Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getActionNodeNextNode_4006Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return StoryboardDiagramEditPart.MODEL_ID.equals(StoryboardsVisualIDRegistry.getModelID(view));
	}

}
