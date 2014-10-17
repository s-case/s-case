/**
 */
package storyboards;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Node</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link storyboards.ActionNode#getNextNode <em>Next Node</em>}</li>
 * <li>{@link storyboards.ActionNode#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see storyboards.StoryboardsPackage#getActionNode()
 * @model abstract="true"
 * @generated
 */
public interface ActionNode extends Node {
	/**
	 * Returns the value of the '<em><b>Next Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Node</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Next Node</em>' reference.
	 * @see #setNextNode(Node)
	 * @see storyboards.StoryboardsPackage#getActionNode_NextNode()
	 * @model required="true"
	 * @generated
	 */
	Node getNextNode();

	/**
	 * Sets the value of the '{@link storyboards.ActionNode#getNextNode <em>Next Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Next Node</em>' reference.
	 * @see #getNextNode()
	 * @generated
	 */
	void setNextNode(Node value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see storyboards.StoryboardsPackage#getActionNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link storyboards.ActionNode#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ActionNode
