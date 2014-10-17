/**
 */
package storyboards;

import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see storyboards.StoryboardsPackage#getNode()
 * @model abstract="true"
 * @generated
 */
public interface Node extends EObject {

	/**
	 * @generated NOT
	 */
	void addPreviousNode(Node node);

	/**
	 * @generated NOT
	 */
	void removePreviousNode(Node node);

	/**
	 * @generated NOT
	 */
	HashSet<Node> getPreviousNodes();

} // Node
