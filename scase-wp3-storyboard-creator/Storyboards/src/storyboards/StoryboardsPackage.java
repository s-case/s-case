/**
 */
package storyboards;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see storyboards.StoryboardsFactory
 * @model kind="package"
 * @generated
 */
public interface StoryboardsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "storyboards";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///auth/storyboards.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "auth.storyboards";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StoryboardsPackage eINSTANCE = storyboards.impl.StoryboardsPackageImpl.init();

	/**
	 * The meta object id for the '{@link storyboards.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.PropertyImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY___VALIDATE__DIAGNOSTICCHAIN_MAP = 0;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link storyboards.impl.StoryboardDiagramImpl <em>Storyboard Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.StoryboardDiagramImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getStoryboardDiagram()
	 * @generated
	 */
	int STORYBOARD_DIAGRAM = 1;

	/**
	 * The feature id for the '<em><b>Storyboardactions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM__STORYBOARDACTIONS = 0;

	/**
	 * The feature id for the '<em><b>Storyboardproperties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM__STORYBOARDPROPERTIES = 1;

	/**
	 * The feature id for the '<em><b>Storyboardconditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM__STORYBOARDCONDITIONS = 2;

	/**
	 * The feature id for the '<em><b>Storyboardstartnode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE = 3;

	/**
	 * The feature id for the '<em><b>Storyboardendnode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM__STORYBOARDENDNODE = 4;

	/**
	 * The feature id for the '<em><b>Storyboardstoryboards</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM__STORYBOARDSTORYBOARDS = 5;

	/**
	 * The number of structural features of the '<em>Storyboard Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM_FEATURE_COUNT = 6;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM___VALIDATE__DIAGNOSTICCHAIN_MAP = 0;

	/**
	 * The number of operations of the '<em>Storyboard Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_DIAGRAM_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link storyboards.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.NodeImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 3;

	/**
	 * The meta object id for the '{@link storyboards.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.ActionImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 2;

	/**
	 * The meta object id for the '{@link storyboards.ActionEnum <em>Action Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.ActionEnum
	 * @see storyboards.impl.StoryboardsPackageImpl#getActionEnum()
	 * @generated
	 */
	int ACTION_ENUM = 10;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link storyboards.impl.ActionNodeImpl <em>Action Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.ActionNodeImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getActionNode()
	 * @generated
	 */
	int ACTION_NODE = 9;

	/**
	 * The feature id for the '<em><b>Next Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE__NEXT_NODE = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE__NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Action Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Next Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NEXT_NODE = ACTION_NODE__NEXT_NODE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = ACTION_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__PROPERTIES = ACTION_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TYPE = ACTION_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = ACTION_NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION___VALIDATE__DIAGNOSTICCHAIN_MAP = ACTION_NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = ACTION_NODE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link storyboards.impl.ConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.ConditionImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getCondition()
	 * @generated
	 */
	int CONDITION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__NAME = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__CONDITION_PATHS = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION___VALIDATE__DIAGNOSTICCHAIN_MAP = NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_OPERATION_COUNT = NODE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link storyboards.impl.StartNodeImpl <em>Start Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.StartNodeImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getStartNode()
	 * @generated
	 */
	int START_NODE = 5;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE__PRECONDITION = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>First Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE__FIRST_NODE = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Start Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE___VALIDATE__DIAGNOSTICCHAIN_MAP = NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Start Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link storyboards.impl.EndNodeImpl <em>End Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.EndNodeImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getEndNode()
	 * @generated
	 */
	int END_NODE = 6;

	/**
	 * The number of structural features of the '<em>End Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_NODE___VALIDATE__DIAGNOSTICCHAIN_MAP = NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>End Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link storyboards.impl.ConditionPathImpl <em>Condition Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.ConditionPathImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getConditionPath()
	 * @generated
	 */
	int CONDITION_PATH = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_PATH__NAME = 0;

	/**
	 * The feature id for the '<em><b>Next Condition Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_PATH__NEXT_CONDITION_NODE = 1;

	/**
	 * The number of structural features of the '<em>Condition Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_PATH_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Condition Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_PATH_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link storyboards.impl.StoryboardImpl <em>Storyboard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.impl.StoryboardImpl
	 * @see storyboards.impl.StoryboardsPackageImpl#getStoryboard()
	 * @generated
	 */
	int STORYBOARD = 8;

	/**
	 * The feature id for the '<em><b>Next Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD__NEXT_NODE = ACTION_NODE__NEXT_NODE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD__NAME = ACTION_NODE__NAME;

	/**
	 * The number of structural features of the '<em>Storyboard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_FEATURE_COUNT = ACTION_NODE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD___VALIDATE__DIAGNOSTICCHAIN_MAP = ACTION_NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Storyboard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORYBOARD_OPERATION_COUNT = ACTION_NODE_OPERATION_COUNT + 1;

	/**
	 * Returns the meta object for class '{@link storyboards.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see storyboards.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link storyboards.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see storyboards.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the '{@link storyboards.Property#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see storyboards.Property#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getProperty__Validate__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link storyboards.StoryboardDiagram <em>Storyboard Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Storyboard Diagram</em>'.
	 * @see storyboards.StoryboardDiagram
	 * @generated
	 */
	EClass getStoryboardDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link storyboards.StoryboardDiagram#getStoryboardactions <em>Storyboardactions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Storyboardactions</em>'.
	 * @see storyboards.StoryboardDiagram#getStoryboardactions()
	 * @see #getStoryboardDiagram()
	 * @generated
	 */
	EReference getStoryboardDiagram_Storyboardactions();

	/**
	 * Returns the meta object for the containment reference list '{@link storyboards.StoryboardDiagram#getStoryboardproperties <em>Storyboardproperties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Storyboardproperties</em>'.
	 * @see storyboards.StoryboardDiagram#getStoryboardproperties()
	 * @see #getStoryboardDiagram()
	 * @generated
	 */
	EReference getStoryboardDiagram_Storyboardproperties();

	/**
	 * Returns the meta object for the containment reference list '{@link storyboards.StoryboardDiagram#getStoryboardconditions <em>Storyboardconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Storyboardconditions</em>'.
	 * @see storyboards.StoryboardDiagram#getStoryboardconditions()
	 * @see #getStoryboardDiagram()
	 * @generated
	 */
	EReference getStoryboardDiagram_Storyboardconditions();

	/**
	 * Returns the meta object for the containment reference '{@link storyboards.StoryboardDiagram#getStoryboardstartnode <em>Storyboardstartnode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Storyboardstartnode</em>'.
	 * @see storyboards.StoryboardDiagram#getStoryboardstartnode()
	 * @see #getStoryboardDiagram()
	 * @generated
	 */
	EReference getStoryboardDiagram_Storyboardstartnode();

	/**
	 * Returns the meta object for the containment reference '{@link storyboards.StoryboardDiagram#getStoryboardendnode <em>Storyboardendnode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Storyboardendnode</em>'.
	 * @see storyboards.StoryboardDiagram#getStoryboardendnode()
	 * @see #getStoryboardDiagram()
	 * @generated
	 */
	EReference getStoryboardDiagram_Storyboardendnode();

	/**
	 * Returns the meta object for the containment reference list '{@link storyboards.StoryboardDiagram#getStoryboardstoryboards <em>Storyboardstoryboards</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Storyboardstoryboards</em>'.
	 * @see storyboards.StoryboardDiagram#getStoryboardstoryboards()
	 * @see #getStoryboardDiagram()
	 * @generated
	 */
	EReference getStoryboardDiagram_Storyboardstoryboards();

	/**
	 * Returns the meta object for the '{@link storyboards.StoryboardDiagram#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see storyboards.StoryboardDiagram#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getStoryboardDiagram__Validate__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link storyboards.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see storyboards.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the reference list '{@link storyboards.Action#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Properties</em>'.
	 * @see storyboards.Action#getProperties()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Properties();

	/**
	 * Returns the meta object for the attribute '{@link storyboards.Action#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see storyboards.Action#getType()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Type();

	/**
	 * Returns the meta object for the '{@link storyboards.Action#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see storyboards.Action#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getAction__Validate__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link storyboards.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see storyboards.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for class '{@link storyboards.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see storyboards.Condition
	 * @generated
	 */
	EClass getCondition();

	/**
	 * Returns the meta object for the attribute '{@link storyboards.Condition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see storyboards.Condition#getName()
	 * @see #getCondition()
	 * @generated
	 */
	EAttribute getCondition_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link storyboards.Condition#getConditionPaths <em>Condition Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Condition Paths</em>'.
	 * @see storyboards.Condition#getConditionPaths()
	 * @see #getCondition()
	 * @generated
	 */
	EReference getCondition_ConditionPaths();

	/**
	 * Returns the meta object for the '{@link storyboards.Condition#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see storyboards.Condition#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getCondition__Validate__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link storyboards.StartNode <em>Start Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Node</em>'.
	 * @see storyboards.StartNode
	 * @generated
	 */
	EClass getStartNode();

	/**
	 * Returns the meta object for the attribute '{@link storyboards.StartNode#getPrecondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precondition</em>'.
	 * @see storyboards.StartNode#getPrecondition()
	 * @see #getStartNode()
	 * @generated
	 */
	EAttribute getStartNode_Precondition();

	/**
	 * Returns the meta object for the reference '{@link storyboards.StartNode#getFirstNode <em>First Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>First Node</em>'.
	 * @see storyboards.StartNode#getFirstNode()
	 * @see #getStartNode()
	 * @generated
	 */
	EReference getStartNode_FirstNode();

	/**
	 * Returns the meta object for the '{@link storyboards.StartNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see storyboards.StartNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getStartNode__Validate__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link storyboards.EndNode <em>End Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End Node</em>'.
	 * @see storyboards.EndNode
	 * @generated
	 */
	EClass getEndNode();

	/**
	 * Returns the meta object for the '{@link storyboards.EndNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see storyboards.EndNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getEndNode__Validate__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link storyboards.ConditionPath <em>Condition Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition Path</em>'.
	 * @see storyboards.ConditionPath
	 * @generated
	 */
	EClass getConditionPath();

	/**
	 * Returns the meta object for the attribute '{@link storyboards.ConditionPath#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see storyboards.ConditionPath#getName()
	 * @see #getConditionPath()
	 * @generated
	 */
	EAttribute getConditionPath_Name();

	/**
	 * Returns the meta object for the reference '{@link storyboards.ConditionPath#getNextConditionNode <em>Next Condition Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Next Condition Node</em>'.
	 * @see storyboards.ConditionPath#getNextConditionNode()
	 * @see #getConditionPath()
	 * @generated
	 */
	EReference getConditionPath_NextConditionNode();

	/**
	 * Returns the meta object for class '{@link storyboards.Storyboard <em>Storyboard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Storyboard</em>'.
	 * @see storyboards.Storyboard
	 * @generated
	 */
	EClass getStoryboard();

	/**
	 * Returns the meta object for the '{@link storyboards.Storyboard#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see storyboards.Storyboard#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getStoryboard__Validate__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link storyboards.ActionNode <em>Action Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Node</em>'.
	 * @see storyboards.ActionNode
	 * @generated
	 */
	EClass getActionNode();

	/**
	 * Returns the meta object for the reference '{@link storyboards.ActionNode#getNextNode <em>Next Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Next Node</em>'.
	 * @see storyboards.ActionNode#getNextNode()
	 * @see #getActionNode()
	 * @generated
	 */
	EReference getActionNode_NextNode();

	/**
	 * Returns the meta object for the attribute '{@link storyboards.ActionNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see storyboards.ActionNode#getName()
	 * @see #getActionNode()
	 * @generated
	 */
	EAttribute getActionNode_Name();

	/**
	 * Returns the meta object for enum '{@link storyboards.ActionEnum <em>Action Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Action Enum</em>'.
	 * @see storyboards.ActionEnum
	 * @generated
	 */
	EEnum getActionEnum();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StoryboardsFactory getStoryboardsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link storyboards.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.PropertyImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PROPERTY___VALIDATE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getProperty__Validate__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link storyboards.impl.StoryboardDiagramImpl <em>Storyboard Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.StoryboardDiagramImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getStoryboardDiagram()
		 * @generated
		 */
		EClass STORYBOARD_DIAGRAM = eINSTANCE.getStoryboardDiagram();

		/**
		 * The meta object literal for the '<em><b>Storyboardactions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORYBOARD_DIAGRAM__STORYBOARDACTIONS = eINSTANCE.getStoryboardDiagram_Storyboardactions();

		/**
		 * The meta object literal for the '<em><b>Storyboardproperties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORYBOARD_DIAGRAM__STORYBOARDPROPERTIES = eINSTANCE.getStoryboardDiagram_Storyboardproperties();

		/**
		 * The meta object literal for the '<em><b>Storyboardconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORYBOARD_DIAGRAM__STORYBOARDCONDITIONS = eINSTANCE.getStoryboardDiagram_Storyboardconditions();

		/**
		 * The meta object literal for the '<em><b>Storyboardstartnode</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORYBOARD_DIAGRAM__STORYBOARDSTARTNODE = eINSTANCE.getStoryboardDiagram_Storyboardstartnode();

		/**
		 * The meta object literal for the '<em><b>Storyboardendnode</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORYBOARD_DIAGRAM__STORYBOARDENDNODE = eINSTANCE.getStoryboardDiagram_Storyboardendnode();

		/**
		 * The meta object literal for the '<em><b>Storyboardstoryboards</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORYBOARD_DIAGRAM__STORYBOARDSTORYBOARDS = eINSTANCE.getStoryboardDiagram_Storyboardstoryboards();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation STORYBOARD_DIAGRAM___VALIDATE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getStoryboardDiagram__Validate__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link storyboards.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.ActionImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__PROPERTIES = eINSTANCE.getAction_Properties();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__TYPE = eINSTANCE.getAction_Type();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ACTION___VALIDATE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getAction__Validate__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link storyboards.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.NodeImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '{@link storyboards.impl.ConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.ConditionImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getCondition()
		 * @generated
		 */
		EClass CONDITION = eINSTANCE.getCondition();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__NAME = eINSTANCE.getCondition_Name();

		/**
		 * The meta object literal for the '<em><b>Condition Paths</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__CONDITION_PATHS = eINSTANCE.getCondition_ConditionPaths();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CONDITION___VALIDATE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getCondition__Validate__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link storyboards.impl.StartNodeImpl <em>Start Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.StartNodeImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getStartNode()
		 * @generated
		 */
		EClass START_NODE = eINSTANCE.getStartNode();

		/**
		 * The meta object literal for the '<em><b>Precondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_NODE__PRECONDITION = eINSTANCE.getStartNode_Precondition();

		/**
		 * The meta object literal for the '<em><b>First Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_NODE__FIRST_NODE = eINSTANCE.getStartNode_FirstNode();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation START_NODE___VALIDATE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getStartNode__Validate__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link storyboards.impl.EndNodeImpl <em>End Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.EndNodeImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getEndNode()
		 * @generated
		 */
		EClass END_NODE = eINSTANCE.getEndNode();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation END_NODE___VALIDATE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getEndNode__Validate__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link storyboards.impl.ConditionPathImpl <em>Condition Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.ConditionPathImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getConditionPath()
		 * @generated
		 */
		EClass CONDITION_PATH = eINSTANCE.getConditionPath();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION_PATH__NAME = eINSTANCE.getConditionPath_Name();

		/**
		 * The meta object literal for the '<em><b>Next Condition Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION_PATH__NEXT_CONDITION_NODE = eINSTANCE.getConditionPath_NextConditionNode();

		/**
		 * The meta object literal for the '{@link storyboards.impl.StoryboardImpl <em>Storyboard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.StoryboardImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getStoryboard()
		 * @generated
		 */
		EClass STORYBOARD = eINSTANCE.getStoryboard();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation STORYBOARD___VALIDATE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getStoryboard__Validate__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link storyboards.impl.ActionNodeImpl <em>Action Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.impl.ActionNodeImpl
		 * @see storyboards.impl.StoryboardsPackageImpl#getActionNode()
		 * @generated
		 */
		EClass ACTION_NODE = eINSTANCE.getActionNode();

		/**
		 * The meta object literal for the '<em><b>Next Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_NODE__NEXT_NODE = eINSTANCE.getActionNode_NextNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_NODE__NAME = eINSTANCE.getActionNode_Name();

		/**
		 * The meta object literal for the '{@link storyboards.ActionEnum <em>Action Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see storyboards.ActionEnum
		 * @see storyboards.impl.StoryboardsPackageImpl#getActionEnum()
		 * @generated
		 */
		EEnum ACTION_ENUM = eINSTANCE.getActionEnum();

	}

} // StoryboardsPackage
