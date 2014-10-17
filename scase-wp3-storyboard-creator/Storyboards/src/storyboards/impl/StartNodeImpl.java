/**
 */
package storyboards.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import storyboards.Node;
import storyboards.StartNode;
import storyboards.StoryboardsPackage;
import storyboards.util.StoryboardsValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link storyboards.impl.StartNodeImpl#getPrecondition <em>Precondition</em>}</li>
 * <li>{@link storyboards.impl.StartNodeImpl#getFirstNode <em>First Node</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class StartNodeImpl extends NodeImpl implements StartNode {
	/**
	 * The default value of the '{@link #getPrecondition() <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected static final String PRECONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected String precondition = PRECONDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFirstNode() <em>First Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getFirstNode()
	 * @generated
	 * @ordered
	 */
	protected Node firstNode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StartNodeImpl() {
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
		return StoryboardsPackage.Literals.START_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPrecondition(String newPrecondition) {
		String oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoryboardsPackage.START_NODE__PRECONDITION,
					oldPrecondition, precondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Node getFirstNode() {
		if (firstNode != null && firstNode.eIsProxy()) {
			InternalEObject oldFirstNode = (InternalEObject) firstNode;
			firstNode = (Node) eResolveProxy(oldFirstNode);
			if (firstNode != oldFirstNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							StoryboardsPackage.START_NODE__FIRST_NODE, oldFirstNode, firstNode));
			}
		}
		return firstNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Node basicGetFirstNode() {
		return firstNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setFirstNode(Node newFirstNode) {
		Node oldFirstNode = firstNode;
		firstNode = newFirstNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoryboardsPackage.START_NODE__FIRST_NODE,
					oldFirstNode, firstNode));
		if (oldFirstNode != null)
			oldFirstNode.removePreviousNode((Node) this);
		if (firstNode != null)
			firstNode.addPreviousNode((Node) this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		boolean valid = true;
		if (diagnostic != null) {
			if (getFirstNode() == null) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.START_NODE__VALIDATE, "The start node connects to no action!",
						new Object[] { this }));
			}
			if (getPreviousNodes().size() > 0) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.START_NODE__VALIDATE,
						"The start node cannot have an action connected to it!", new Object[] { this }));
			}
		}
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case StoryboardsPackage.START_NODE__PRECONDITION:
			return getPrecondition();
		case StoryboardsPackage.START_NODE__FIRST_NODE:
			if (resolve)
				return getFirstNode();
			return basicGetFirstNode();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StoryboardsPackage.START_NODE__PRECONDITION:
			setPrecondition((String) newValue);
			return;
		case StoryboardsPackage.START_NODE__FIRST_NODE:
			setFirstNode((Node) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case StoryboardsPackage.START_NODE__PRECONDITION:
			setPrecondition(PRECONDITION_EDEFAULT);
			return;
		case StoryboardsPackage.START_NODE__FIRST_NODE:
			setFirstNode((Node) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case StoryboardsPackage.START_NODE__PRECONDITION:
			return PRECONDITION_EDEFAULT == null ? precondition != null : !PRECONDITION_EDEFAULT.equals(precondition);
		case StoryboardsPackage.START_NODE__FIRST_NODE:
			return firstNode != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
		case StoryboardsPackage.START_NODE___VALIDATE__DIAGNOSTICCHAIN_MAP:
			return validate((DiagnosticChain) arguments.get(0), (Map<Object, Object>) arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Precondition: ");
		result.append(precondition);
		result.append(')');
		return result.toString();
	}

} // StartNodeImpl
