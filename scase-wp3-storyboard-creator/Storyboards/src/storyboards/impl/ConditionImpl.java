/**
 */
package storyboards.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import storyboards.ActionNode;
import storyboards.Condition;
import storyboards.ConditionPath;
import storyboards.Node;
import storyboards.StoryboardsPackage;
import storyboards.util.StoryboardsValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link storyboards.impl.ConditionImpl#getName <em>Name</em>}</li>
 *   <li>{@link storyboards.impl.ConditionImpl#getConditionPaths <em>Condition Paths</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionImpl extends NodeImpl implements Condition {
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
	 * The cached value of the '{@link #getConditionPaths() <em>Condition Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<ConditionPath> conditionPaths;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoryboardsPackage.Literals.CONDITION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StoryboardsPackage.CONDITION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConditionPath> getConditionPaths() {
		if (conditionPaths == null) {
			conditionPaths = new EObjectContainmentEList<ConditionPath>(ConditionPath.class, this, StoryboardsPackage.CONDITION__CONDITION_PATHS);
		}
		return conditionPaths;
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
			if (getName().equals("")) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.CONDITION__VALIDATE, "There is a condition with no name!",
						new Object[] { this }));
			}
			if (getPreviousNodes().size() == 0) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.STORYBOARD__VALIDATE, "No connection to condition \'" + getName() + "\'",
						new Object[] { this }));
			}
			if (getConditionPaths().size() != 2) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.CONDITION__VALIDATE, "Condition \'" + getName()
								+ "\' must have exactly 2 condition paths!", new Object[] { this }));
			}
			HashSet<String> conditionPathNames = new HashSet<String>();
			HashSet<Node> conditionPathNodes = new HashSet<Node>();
			for (ConditionPath conditionPath : getConditionPaths()) {
				if (conditionPath.getName().equals("")) {
					valid = false;
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
							StoryboardsValidator.CONDITION__VALIDATE, "A condition path of condition \'" + getName()
									+ "\' does not have a name!", new Object[] { conditionPath }));
				}
				if (conditionPathNames.contains(conditionPath.getName())) {
					valid = false;
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
							StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE, "Condition \'" + getName()
									+ "\' has two condition paths with the same name \'" + conditionPath.getName()
									+ "\'", new Object[] { this }));
				} else {
					conditionPathNames.add(conditionPath.getName());
				}
				if (conditionPath.getNextConditionNode() == null) {
					valid = false;
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
							StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE, "Condition path \'"
									+ conditionPath.getName() + "\' of condition \'" + getName()
									+ "\' does not connect to any node!", new Object[] { conditionPath }));
				} else {
					if (conditionPathNodes.contains(conditionPath.getNextConditionNode())) {
						String nodename = "";
						if (conditionPath.getNextConditionNode() instanceof ActionNodeImpl)
							nodename = " \'" + ((ActionNode) conditionPath.getNextConditionNode()).getName() + "\'";
						else if (conditionPath.getNextConditionNode() instanceof ConditionImpl)
							nodename = " \'" + ((Condition) conditionPath.getNextConditionNode()).getName() + "\'";
						else if (conditionPath.getNextConditionNode() instanceof EndNodeImpl)
							nodename = ", the endnode";
						valid = false;
						diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
								StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE, "Condition \'" + getName()
										+ "\' has two condition paths that connect to the same node" + nodename,
								new Object[] { this }));
					} else {
						conditionPathNodes.add(conditionPath.getNextConditionNode());
					}
				}

			}
		}
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StoryboardsPackage.CONDITION__CONDITION_PATHS:
				return ((InternalEList<?>)getConditionPaths()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StoryboardsPackage.CONDITION__NAME:
				return getName();
			case StoryboardsPackage.CONDITION__CONDITION_PATHS:
				return getConditionPaths();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StoryboardsPackage.CONDITION__NAME:
				setName((String)newValue);
				return;
			case StoryboardsPackage.CONDITION__CONDITION_PATHS:
				getConditionPaths().clear();
				getConditionPaths().addAll((Collection<? extends ConditionPath>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StoryboardsPackage.CONDITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case StoryboardsPackage.CONDITION__CONDITION_PATHS:
				getConditionPaths().clear();
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
			case StoryboardsPackage.CONDITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case StoryboardsPackage.CONDITION__CONDITION_PATHS:
				return conditionPaths != null && !conditionPaths.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case StoryboardsPackage.CONDITION___VALIDATE__DIAGNOSTICCHAIN_MAP:
				return validate((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
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

} // ConditionImpl
