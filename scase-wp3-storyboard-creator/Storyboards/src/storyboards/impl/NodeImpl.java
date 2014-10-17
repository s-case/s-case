/**
 */
package storyboards.impl;

import java.util.HashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import storyboards.Node;
import storyboards.StoryboardsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public abstract class NodeImpl extends MinimalEObjectImpl.Container implements Node {

	/**
	 * @generated NOT
	 */
	protected HashSet<Node> previousNodes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoryboardsPackage.Literals.NODE;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void addPreviousNode(Node node) {
		if (previousNodes == null)
			previousNodes = new HashSet<Node>();
		previousNodes.add(node);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void removePreviousNode(Node node) {
		if (previousNodes == null)
			previousNodes = new HashSet<Node>();
		if (previousNodes.contains(node))
			previousNodes.remove(node);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public HashSet<Node> getPreviousNodes() {
		if (previousNodes == null)
			previousNodes = new HashSet<Node>();
		return previousNodes;
	}

} // NodeImpl
