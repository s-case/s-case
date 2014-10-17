/**
 */
package storyboards.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import storyboards.Action;
import storyboards.ActionEnum;
import storyboards.Property;
import storyboards.StoryboardsPackage;
import storyboards.util.StoryboardsValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link storyboards.impl.ActionImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link storyboards.impl.ActionImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ActionImpl extends ActionNodeImpl implements Action {
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ActionEnum TYPE_EDEFAULT = ActionEnum.CREATE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ActionEnum type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ActionImpl() {
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
		return StoryboardsPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectResolvingEList<Property>(Property.class, this,
					StoryboardsPackage.ACTION__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ActionEnum getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setType(ActionEnum newType) {
		ActionEnum oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoryboardsPackage.ACTION__TYPE, oldType, type));
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
						StoryboardsValidator.ACTION__VALIDATE, "There is an action with no name!",
						new Object[] { this }));
			}
			if (getNextNode() == null) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.ACTION__VALIDATE, "Action \'" + getName()
								+ "\' does not connect to any node!", new Object[] { this }));
			}
			if (getPreviousNodes().size() == 0) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.ACTION__VALIDATE, "No connection to action \'" + getName() + "\'",
						new Object[] { this }));
			}
			Set<String> propertynames = new HashSet<String>();
			for (Property property : getProperties()) {
				if (propertynames.contains(property.getName())) {
					valid = false;
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
							StoryboardsValidator.ACTION__VALIDATE, "Action \'" + getName()
									+ "\' has two or more properties with the same name \'" + property.getName()
									+ "\'!", new Object[] { this }));
				} else {
					propertynames.add(property.getName());
				}
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
		case StoryboardsPackage.ACTION__PROPERTIES:
			return getProperties();
		case StoryboardsPackage.ACTION__TYPE:
			return getType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StoryboardsPackage.ACTION__PROPERTIES:
			getProperties().clear();
			getProperties().addAll((Collection<? extends Property>) newValue);
			return;
		case StoryboardsPackage.ACTION__TYPE:
			setType((ActionEnum) newValue);
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
		case StoryboardsPackage.ACTION__PROPERTIES:
			getProperties().clear();
			return;
		case StoryboardsPackage.ACTION__TYPE:
			setType(TYPE_EDEFAULT);
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
		case StoryboardsPackage.ACTION__PROPERTIES:
			return properties != null && !properties.isEmpty();
		case StoryboardsPackage.ACTION__TYPE:
			return type != TYPE_EDEFAULT;
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
		case StoryboardsPackage.ACTION___VALIDATE__DIAGNOSTICCHAIN_MAP:
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} // ActionImpl
