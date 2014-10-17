package storyboards.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import storyboards.StoryboardsPackage;
import storyboards.diagram.edit.parts.ActionNameEditPart;
import storyboards.diagram.edit.parts.ConditionNameEditPart;
import storyboards.diagram.edit.parts.ConditionPathNameEditPart;
import storyboards.diagram.edit.parts.PropertyNameEditPart;
import storyboards.diagram.edit.parts.StartNodePreconditionEditPart;
import storyboards.diagram.edit.parts.StoryboardNameEditPart;
import storyboards.diagram.parsers.MessageFormatParser;
import storyboards.diagram.part.StoryboardsVisualIDRegistry;

/**
 * @generated
 */
public class StoryboardsParserProvider extends AbstractProvider implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser actionName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getActionName_5005Parser() {
		if (actionName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { StoryboardsPackage.eINSTANCE.getActionNode_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			actionName_5005Parser = parser;
		}
		return actionName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser storyboardName_5010Parser;

	/**
	 * @generated
	 */
	private IParser getStoryboardName_5010Parser() {
		if (storyboardName_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { StoryboardsPackage.eINSTANCE.getActionNode_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			storyboardName_5010Parser = parser;
		}
		return storyboardName_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser startNodePrecondition_5006Parser;

	/**
	 * @generated
	 */
	private IParser getStartNodePrecondition_5006Parser() {
		if (startNodePrecondition_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { StoryboardsPackage.eINSTANCE.getStartNode_Precondition() };
			MessageFormatParser parser = new MessageFormatParser(features);
			startNodePrecondition_5006Parser = parser;
		}
		return startNodePrecondition_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser propertyName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getPropertyName_5007Parser() {
		if (propertyName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { StoryboardsPackage.eINSTANCE.getProperty_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			propertyName_5007Parser = parser;
		}
		return propertyName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser conditionName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getConditionName_5008Parser() {
		if (conditionName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { StoryboardsPackage.eINSTANCE.getCondition_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			conditionName_5008Parser = parser;
		}
		return conditionName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser conditionPathName_6001Parser;

	/**
	 * @generated
	 */
	private IParser getConditionPathName_6001Parser() {
		if (conditionPathName_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { StoryboardsPackage.eINSTANCE.getConditionPath_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			conditionPathName_6001Parser = parser;
		}
		return conditionPathName_6001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ActionNameEditPart.VISUAL_ID:
			return getActionName_5005Parser();
		case StoryboardNameEditPart.VISUAL_ID:
			return getStoryboardName_5010Parser();
		case StartNodePreconditionEditPart.VISUAL_ID:
			return getStartNodePrecondition_5006Parser();
		case PropertyNameEditPart.VISUAL_ID:
			return getPropertyName_5007Parser();
		case ConditionNameEditPart.VISUAL_ID:
			return getConditionName_5008Parser();
		case ConditionPathNameEditPart.VISUAL_ID:
			return getConditionPathName_6001Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * 
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(StoryboardsVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(StoryboardsVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (StoryboardsElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
