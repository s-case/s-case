/**
 */
package storyboards;

import java.util.Map;
import org.eclipse.emf.common.util.DiagnosticChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Node</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link storyboards.StartNode#getPrecondition <em>Precondition</em>}</li>
 * <li>{@link storyboards.StartNode#getFirstNode <em>First Node</em>}</li>
 * </ul>
 * </p>
 * 
 * @see storyboards.StoryboardsPackage#getStartNode()
 * @model
 * @generated
 */
public interface StartNode extends Node {
	/**
	 * Returns the value of the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Precondition</em>' attribute.
	 * @see #setPrecondition(String)
	 * @see storyboards.StoryboardsPackage#getStartNode_Precondition()
	 * @model
	 * @generated
	 */
	String getPrecondition();

	/**
	 * Sets the value of the '{@link storyboards.StartNode#getPrecondition <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Precondition</em>' attribute.
	 * @see #getPrecondition()
	 * @generated
	 */
	void setPrecondition(String value);

	/**
	 * Returns the value of the '<em><b>First Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Node</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>First Node</em>' reference.
	 * @see #setFirstNode(Node)
	 * @see storyboards.StoryboardsPackage#getStartNode_FirstNode()
	 * @model required="true"
	 * @generated
	 */
	Node getFirstNode();

	/**
	 * Sets the value of the '{@link storyboards.StartNode#getFirstNode <em>First Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>First Node</em>' reference.
	 * @see #getFirstNode()
	 * @generated
	 */
	void setFirstNode(Node value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // StartNode
