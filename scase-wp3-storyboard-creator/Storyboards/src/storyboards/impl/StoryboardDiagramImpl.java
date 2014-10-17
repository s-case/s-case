/**
 */
package storyboards.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractList;
import java.util.Collection;
import java.util.HashMap;
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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import storyboards.Action;
import storyboards.ActionNode;
import storyboards.Condition;
import storyboards.EndNode;
import storyboards.Property;
import storyboards.StartNode;
import storyboards.Storyboard;
import storyboards.StoryboardDiagram;
import storyboards.StoryboardsPackage;
import storyboards.util.StoryboardsValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Storyboard</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link storyboards.impl.StoryboardDiagramImpl#getStoryboardactions <em>Storyboardactions</em>}</li>
 * <li>{@link storyboards.impl.StoryboardDiagramImpl#getStoryboardproperties <em>Storyboardproperties</em>}</li>
 * <li>{@link storyboards.impl.StoryboardDiagramImpl#getStoryboardconditions <em>Storyboardconditions</em>}</li>
 * <li>{@link storyboards.impl.StoryboardDiagramImpl#getStoryboardstartnode <em>Storyboardstartnode</em>}</li>
 * <li>{@link storyboards.impl.StoryboardDiagramImpl#getStoryboardendnode <em>Storyboardendnode</em>}</li>
 * <li>{@link storyboards.impl.StoryboardDiagramImpl#getStoryboardstoryboards <em>Storyboardstoryboards</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class StoryboardDiagramImpl extends MinimalEObjectImpl.Container implements StoryboardDiagram {
	/**
	 * The cached value of the '{@link #getStoryboardactions() <em>Storyboardactions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStoryboardactions()
	 * @generated
	 * @ordered
	 */
	protected EList<Action> storyboardactions;

	/**
	 * The cached value of the '{@link #getStoryboardproperties() <em>Storyboardproperties</em>}' containment reference
	 * list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStoryboardproperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> storyboardproperties;

	/**
	 * The cached value of the '{@link #getStoryboardconditions() <em>Storyboardconditions</em>}' containment reference
	 * list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStoryboardconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Condition> storyboardconditions;

	/**
	 * The cached value of the '{@link #getStoryboardstartnode() <em>Storyboardstartnode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStoryboardstartnode()
	 * @generated
	 * @ordered
	 */
	protected StartNode storyboardstartnode;

	/**
	 * The cached value of the '{@link #getStoryboardendnode() <em>Storyboardendnode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStoryboardendnode()
	 * @generated
	 * @ordered
	 */
	protected EndNode storyboardendnode;

	/**
	 * The cached value of the '{@link #getStoryboardstoryboards() <em>Storyboardstoryboards</em>}' containment
	 * reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStoryboardstoryboards()
	 * @generated
	 * @ordered
	 */
	protected EList<Storyboard> storyboardstoryboards;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StoryboardDiagramImpl() {
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
		return StoryboardsPackage.Literals.STORYBOARD_DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Action> getStoryboardactions() {
		if (storyboardactions == null) {
			storyboardactions = new EObjectContainmentEList<Action>(Action.class, this,
					StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDACTIONS);
		}
		return storyboardactions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Property> getStoryboardproperties() {
		if (storyboardproperties == null) {
			storyboardproperties = new EObjectContainmentEList<Property>(Property.class, this,
					StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDPROPERTIES);
		}
		return storyboardproperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Condition> getStoryboardconditions() {
		if (storyboardconditions == null) {
			storyboardconditions = new EObjectContainmentEList<Condition>(Condition.class, this,
					StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDCONDITIONS);
		}
		return storyboardconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StartNode getStoryboardstartnode() {
		return storyboardstartnode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetStoryboardstartnode(StartNode newStoryboardstartnode, NotificationChain msgs) {
		StartNode oldStoryboardstartnode = storyboardstartnode;
		storyboardstartnode = newStoryboardstartnode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE, oldStoryboardstartnode,
					newStoryboardstartnode);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStoryboardstartnode(StartNode newStoryboardstartnode) {
		if (newStoryboardstartnode != storyboardstartnode) {
			NotificationChain msgs = null;
			if (storyboardstartnode != null)
				msgs = ((InternalEObject) storyboardstartnode).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE, null, msgs);
			if (newStoryboardstartnode != null)
				msgs = ((InternalEObject) newStoryboardstartnode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE, null, msgs);
			msgs = basicSetStoryboardstartnode(newStoryboardstartnode, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE, newStoryboardstartnode,
					newStoryboardstartnode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EndNode getStoryboardendnode() {
		return storyboardendnode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetStoryboardendnode(EndNode newStoryboardendnode, NotificationChain msgs) {
		EndNode oldStoryboardendnode = storyboardendnode;
		storyboardendnode = newStoryboardendnode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE, oldStoryboardendnode,
					newStoryboardendnode);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStoryboardendnode(EndNode newStoryboardendnode) {
		if (newStoryboardendnode != storyboardendnode) {
			NotificationChain msgs = null;
			if (storyboardendnode != null)
				msgs = ((InternalEObject) storyboardendnode).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE, null, msgs);
			if (newStoryboardendnode != null)
				msgs = ((InternalEObject) newStoryboardendnode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE, null, msgs);
			msgs = basicSetStoryboardendnode(newStoryboardendnode, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE, newStoryboardendnode,
					newStoryboardendnode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Storyboard> getStoryboardstoryboards() {
		if (storyboardstoryboards == null) {
			storyboardstoryboards = new EObjectContainmentEList<Storyboard>(Storyboard.class, this,
					StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTORYBOARDS);
		}
		return storyboardstoryboards;
	}

	/**
	 * @generated NOT
	 */
	public class CompositeUnmodifiableList extends AbstractList<ActionNode> {
		private final EList<Action> theactions;
		private final EList<Storyboard> thestoryboards;

		public CompositeUnmodifiableList(EList<Action> theactions, EList<Storyboard> thestoryboards) {
			this.theactions = theactions;
			this.thestoryboards = thestoryboards;
		}

		public ActionNode get(int index) {
			if (index < theactions.size()) {
				return theactions.get(index);
			}
			return thestoryboards.get(index - theactions.size());
		}

		public int size() {
			return theactions.size() + thestoryboards.size();
		}
	}

	/**
	 * @generated NOT
	 */
	public AbstractList<ActionNode> getStoryboardactions_and_storyboards() {
		return new CompositeUnmodifiableList(storyboardactions, storyboardstoryboards);
	}

	/**
	 * @generated NOT
	 */
	private HashSet<String> storyboardNames;

	/**
	 * @generated NOT
	 */
	public void setStoryboardNames(HashSet<String> storyboardNames) {
		this.storyboardNames = storyboardNames;
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
			if (getStoryboardstartnode() == null) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE,
						"The storyboard does not have a start node!", new Object[] { this }));
			}
			if (getStoryboardendnode() == null) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE, "The storyboard does not have an end node!",
						new Object[] { this }));
			}
			if (getStoryboardactions().size() == 0) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE,
						"The storyboard must have at least 1 action node!", new Object[] { this }));
			}
			HashSet<String> actionnames = new HashSet<String>();
			HashMap<Property, Action> propertiesactions = new HashMap<Property, Action>();
			boolean checkForCyclicDependencies = true;
			for (ActionNode action : getStoryboardactions_and_storyboards()) {
				if (actionnames.contains(action.getName())) {
					valid = false;
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
							StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE,
							"There are two or more actions with the same name \'" + action.getName() + "\'",
							new Object[] { action }));
				} else {
					actionnames.add(action.getName());
				}
				if (checkForCyclicDependencies) {
					ActionNode recaction = action;
					HashSet<String> actioncyclicdependencies = new HashSet<String>();
					actioncyclicdependencies.add(recaction.getName());
					while (recaction.getNextNode() instanceof ActionNodeImpl) {
						recaction = (ActionNode) recaction.getNextNode();
						if (actioncyclicdependencies.contains(recaction.getName())) {
							valid = false;
							String err = "Actions ";
							for (String actioncd : actioncyclicdependencies)
								err += "\'" + actioncd + "\' ";
							diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR,
									StoryboardsValidator.DIAGNOSTIC_SOURCE,
									StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE, err
											+ "form a cyclic dependency!", new Object[] { action }));
							break;
						}
						actioncyclicdependencies.add(recaction.getName());
					}
					checkForCyclicDependencies = false;
				}
				if (action instanceof StoryboardImpl && !storyboardNames.contains(action.getName())) {
					valid = false;
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
							StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE, "Storyboard \'" + action.getName()
									+ "\' has no corresponding storyboard diagram!", new Object[] { action }));
				}
				if (action instanceof ActionImpl) {
					for (Property property : ((ActionImpl) action).getProperties()) {
						if (propertiesactions.containsKey(property)) {
							valid = false;
							diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR,
									StoryboardsValidator.DIAGNOSTIC_SOURCE,
									StoryboardsValidator.STORYBOARD_DIAGRAM__VALIDATE, "Property \'"
											+ property.getName() + "\' belongs to two actions \'"
											+ propertiesactions.get(property).getName() + "\' and \'"
											+ action.getName() + "\'", new Object[] { property }));
						} else {
							propertiesactions.put(property, (Action) action);
						}
					}
				}
			}
			for (Property property : getStoryboardproperties()) {
				if (!propertiesactions.containsKey(property)) {
					valid = false;
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
							StoryboardsValidator.PROPERTY__VALIDATE, "Property \'" + property.getName()
									+ "\' does not belong to any action!", new Object[] { property }));
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDACTIONS:
			return ((InternalEList<?>) getStoryboardactions()).basicRemove(otherEnd, msgs);
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDPROPERTIES:
			return ((InternalEList<?>) getStoryboardproperties()).basicRemove(otherEnd, msgs);
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDCONDITIONS:
			return ((InternalEList<?>) getStoryboardconditions()).basicRemove(otherEnd, msgs);
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE:
			return basicSetStoryboardstartnode(null, msgs);
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE:
			return basicSetStoryboardendnode(null, msgs);
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTORYBOARDS:
			return ((InternalEList<?>) getStoryboardstoryboards()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDACTIONS:
			return getStoryboardactions();
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDPROPERTIES:
			return getStoryboardproperties();
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDCONDITIONS:
			return getStoryboardconditions();
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE:
			return getStoryboardstartnode();
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE:
			return getStoryboardendnode();
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTORYBOARDS:
			return getStoryboardstoryboards();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDACTIONS:
			getStoryboardactions().clear();
			getStoryboardactions().addAll((Collection<? extends Action>) newValue);
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDPROPERTIES:
			getStoryboardproperties().clear();
			getStoryboardproperties().addAll((Collection<? extends Property>) newValue);
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDCONDITIONS:
			getStoryboardconditions().clear();
			getStoryboardconditions().addAll((Collection<? extends Condition>) newValue);
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE:
			setStoryboardstartnode((StartNode) newValue);
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE:
			setStoryboardendnode((EndNode) newValue);
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTORYBOARDS:
			getStoryboardstoryboards().clear();
			getStoryboardstoryboards().addAll((Collection<? extends Storyboard>) newValue);
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
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDACTIONS:
			getStoryboardactions().clear();
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDPROPERTIES:
			getStoryboardproperties().clear();
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDCONDITIONS:
			getStoryboardconditions().clear();
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE:
			setStoryboardstartnode((StartNode) null);
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE:
			setStoryboardendnode((EndNode) null);
			return;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTORYBOARDS:
			getStoryboardstoryboards().clear();
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
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDACTIONS:
			return storyboardactions != null && !storyboardactions.isEmpty();
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDPROPERTIES:
			return storyboardproperties != null && !storyboardproperties.isEmpty();
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDCONDITIONS:
			return storyboardconditions != null && !storyboardconditions.isEmpty();
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE:
			return storyboardstartnode != null;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDENDNODE:
			return storyboardendnode != null;
		case StoryboardsPackage.STORYBOARD_DIAGRAM__STORYBOARDSTORYBOARDS:
			return storyboardstoryboards != null && !storyboardstoryboards.isEmpty();
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
		case StoryboardsPackage.STORYBOARD_DIAGRAM___VALIDATE__DIAGNOSTICCHAIN_MAP:
			return validate((DiagnosticChain) arguments.get(0), (Map<Object, Object>) arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
	}

} // StoryboardImpl
