/**
 */
package storyboards.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import storyboards.Condition;
import storyboards.ConditionPath;
import storyboards.Node;
import storyboards.StoryboardsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Condition Path</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link storyboards.impl.ConditionPathImpl#getName <em>Name</em>}</li>
 *   <li>{@link storyboards.impl.ConditionPathImpl#getNextConditionNode <em>Next Condition Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionPathImpl extends MinimalEObjectImpl.Container implements ConditionPath {

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNextConditionNode() <em>Next Condition Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextConditionNode()
	 * @generated
	 * @ordered
	 */
	protected Node nextConditionNode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionPathImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoryboardsPackage.Literals.CONDITION_PATH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		return name != null ? name : "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoryboardsPackage.CONDITION_PATH__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getNextConditionNode() {
		if (nextConditionNode != null && nextConditionNode.eIsProxy()) {
			InternalEObject oldNextConditionNode = (InternalEObject)nextConditionNode;
			nextConditionNode = (Node)eResolveProxy(oldNextConditionNode);
			if (nextConditionNode != oldNextConditionNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StoryboardsPackage.CONDITION_PATH__NEXT_CONDITION_NODE, oldNextConditionNode, nextConditionNode));
			}
		}
		return nextConditionNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetNextConditionNode() {
		return nextConditionNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNextConditionNode(Node newNextConditionNode) {
		Node oldNextConditionNode = nextConditionNode;
		nextConditionNode = newNextConditionNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoryboardsPackage.CONDITION_PATH__NEXT_CONDITION_NODE, oldNextConditionNode, nextConditionNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setNextConditionNode(Condition condition, Node newNextConditionNode) {
		Node oldNextConditionNode = nextConditionNode;
		nextConditionNode = newNextConditionNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StoryboardsPackage.CONDITION_PATH__NEXT_CONDITION_NODE, oldNextConditionNode, nextConditionNode));
		if (oldNextConditionNode != null)
			oldNextConditionNode.removePreviousNode(condition);
		if (nextConditionNode != null)
			nextConditionNode.addPreviousNode(condition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StoryboardsPackage.CONDITION_PATH__NAME:
				return getName();
			case StoryboardsPackage.CONDITION_PATH__NEXT_CONDITION_NODE:
				if (resolve) return getNextConditionNode();
				return basicGetNextConditionNode();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StoryboardsPackage.CONDITION_PATH__NAME:
			setName((String) newValue);
			return;
		case StoryboardsPackage.CONDITION_PATH__NEXT_CONDITION_NODE:
			setNextConditionNode((Condition) eContainer(), (Node) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case StoryboardsPackage.CONDITION_PATH__NAME:
			setName(NAME_EDEFAULT);
			return;
		case StoryboardsPackage.CONDITION_PATH__NEXT_CONDITION_NODE:
			setNextConditionNode((Condition) eContainer(), (Node) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StoryboardsPackage.CONDITION_PATH__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case StoryboardsPackage.CONDITION_PATH__NEXT_CONDITION_NODE:
				return nextConditionNode != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} // ConditionPathImpl
