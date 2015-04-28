/**
 */
package storyboards.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import storyboards.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see storyboards.StoryboardsPackage
 * @generated
 */
public class StoryboardsValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final StoryboardsValidator INSTANCE = new StoryboardsValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "storyboards";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__VALIDATE = 1;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Storyboard Diagram'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int STORYBOARD_DIAGRAM__VALIDATE = 2;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Action'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ACTION__VALIDATE = 3;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Condition'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CONDITION__VALIDATE = 4;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Start Node'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int START_NODE__VALIDATE = 5;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'End Node'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int END_NODE__VALIDATE = 6;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'Storyboard'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int STORYBOARD__VALIDATE = 7;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 7;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoryboardsValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return StoryboardsPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
		case StoryboardsPackage.PROPERTY:
			// return validateProperty((Property)value, diagnostics, context);
			return validateProperty_validate((Property) value, diagnostics, context);
		case StoryboardsPackage.STORYBOARD_DIAGRAM:
			// return validateStoryboardDiagram((StoryboardDiagram)value, diagnostics, context);
			return validateStoryboardDiagram_validate((StoryboardDiagram) value, diagnostics, context);
		case StoryboardsPackage.ACTION:
			// return validateAction((Action)value, diagnostics, context);
			return validateAction_validate((Action) value, diagnostics, context);
		case StoryboardsPackage.NODE:
			// return validateNode((Node)value, diagnostics, context);
			return true;
		case StoryboardsPackage.CONDITION:
			// return validateCondition((Condition)value, diagnostics, context);
			return validateCondition_validate((Condition) value, diagnostics, context);
		case StoryboardsPackage.START_NODE:
			// return validateStartNode((StartNode)value, diagnostics, context);
			return validateStartNode_validate((StartNode) value, diagnostics, context);
		case StoryboardsPackage.END_NODE:
			// return validateEndNode((EndNode)value, diagnostics, context);
			return validateEndNode_validate((EndNode) value, diagnostics, context);
		case StoryboardsPackage.CONDITION_PATH:
			// return validateConditionPath((ConditionPath)value, diagnostics, context);
			return true;
		case StoryboardsPackage.STORYBOARD:
			// return validateStoryboard((Storyboard)value, diagnostics, context);
			return validateStoryboard_validate((Storyboard) value, diagnostics, context);
		case StoryboardsPackage.ACTION_NODE:
			// return validateActionNode((ActionNode)value, diagnostics, context);
			return true;
		case StoryboardsPackage.ACTION_ENUM:
			// return validateActionEnum((ActionEnum)value, diagnostics, context);
			return true;
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(property, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_validate(property, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_validate(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStoryboardDiagram(StoryboardDiagram storyboardDiagram, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(storyboardDiagram, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(storyboardDiagram, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(storyboardDiagram, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(storyboardDiagram, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(storyboardDiagram, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(storyboardDiagram, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(storyboardDiagram, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(storyboardDiagram, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(storyboardDiagram, diagnostics, context);
		if (result || diagnostics != null) result &= validateStoryboardDiagram_validate(storyboardDiagram, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Storyboard Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStoryboardDiagram_validate(StoryboardDiagram storyboardDiagram, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return storyboardDiagram.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAction(Action action, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(action, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(action, diagnostics, context);
		if (result || diagnostics != null) result &= validateAction_validate(action, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAction_validate(Action action, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return action.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNode(Node node, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(node, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCondition(Condition condition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(condition, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(condition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(condition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(condition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(condition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(condition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(condition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(condition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(condition, diagnostics, context);
		if (result || diagnostics != null) result &= validateCondition_validate(condition, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCondition_validate(Condition condition, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return condition.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStartNode(StartNode startNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(startNode, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(startNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(startNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(startNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(startNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(startNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(startNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(startNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(startNode, diagnostics, context);
		if (result || diagnostics != null) result &= validateStartNode_validate(startNode, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Start Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStartNode_validate(StartNode startNode, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return startNode.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEndNode(EndNode endNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(endNode, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(endNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(endNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(endNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(endNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(endNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(endNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(endNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(endNode, diagnostics, context);
		if (result || diagnostics != null) result &= validateEndNode_validate(endNode, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>End Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEndNode_validate(EndNode endNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return endNode.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConditionPath(ConditionPath conditionPath, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(conditionPath, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStoryboard(Storyboard storyboard, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(storyboard, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(storyboard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(storyboard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(storyboard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(storyboard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(storyboard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(storyboard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(storyboard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(storyboard, diagnostics, context);
		if (result || diagnostics != null) result &= validateStoryboard_validate(storyboard, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>Storyboard</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStoryboard_validate(Storyboard storyboard, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return storyboard.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActionNode(ActionNode actionNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(actionNode, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActionEnum(ActionEnum actionEnum, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} // StoryboardsValidator
