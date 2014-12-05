/**
 */
package storyboards;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link storyboards.ConditionPath#getName <em>Name</em>}</li>
 *   <li>{@link storyboards.ConditionPath#getNextConditionNode <em>Next Condition Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see storyboards.StoryboardsPackage#getConditionPath()
 * @model
 * @generated
 */
public interface ConditionPath extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see storyboards.StoryboardsPackage#getConditionPath_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link storyboards.ConditionPath#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Next Condition Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Condition Node</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Condition Node</em>' reference.
	 * @see #setNextConditionNode(Node)
	 * @see storyboards.StoryboardsPackage#getConditionPath_NextConditionNode()
	 * @model required="true"
	 * @generated
	 */
	Node getNextConditionNode();

	/**
	 * Sets the value of the '{@link storyboards.ConditionPath#getNextConditionNode <em>Next Condition Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next Condition Node</em>' reference.
	 * @see #getNextConditionNode()
	 * @generated
	 */
	void setNextConditionNode(Node value);

	/**
	 * Sets the value of the '{@link storyboards.ConditionPath#getNextConditionNode <em>Next Condition Node</em>}'
	 * reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Next Condition Node</em>' reference.
	 * @see #getNextConditionNode()
	 * @generated NOT
	 */
	void setNextConditionNode(Condition condition, Node value);

} // ConditionPath
