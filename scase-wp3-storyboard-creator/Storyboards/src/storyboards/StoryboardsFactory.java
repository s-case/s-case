/**
 */
package storyboards;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see storyboards.StoryboardsPackage
 * @generated
 */
public interface StoryboardsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StoryboardsFactory eINSTANCE = storyboards.impl.StoryboardsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property</em>'.
	 * @generated
	 */
	Property createProperty();

	/**
	 * Returns a new object of class '<em>Storyboard Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Storyboard Diagram</em>'.
	 * @generated
	 */
	StoryboardDiagram createStoryboardDiagram();

	/**
	 * Returns a new object of class '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action</em>'.
	 * @generated
	 */
	Action createAction();

	/**
	 * Returns a new object of class '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Condition</em>'.
	 * @generated
	 */
	Condition createCondition();

	/**
	 * Returns a new object of class '<em>Start Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Node</em>'.
	 * @generated
	 */
	StartNode createStartNode();

	/**
	 * Returns a new object of class '<em>End Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>End Node</em>'.
	 * @generated
	 */
	EndNode createEndNode();

	/**
	 * Returns a new object of class '<em>Condition Path</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Condition Path</em>'.
	 * @generated
	 */
	ConditionPath createConditionPath();

	/**
	 * Returns a new object of class '<em>Storyboard</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Storyboard</em>'.
	 * @generated
	 */
	Storyboard createStoryboard();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StoryboardsPackage getStoryboardsPackage();

} // StoryboardsFactory
