/**
 */
package storyboards.tests;

import junit.textui.TestRunner;
import storyboards.StartNode;
import storyboards.StoryboardsFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Start Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link storyboards.StartNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class StartNodeTest extends NodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StartNodeTest.class);
	}

	/**
	 * Constructs a new Start Node test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StartNodeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Start Node test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected StartNode getFixture() {
		return (StartNode) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(StoryboardsFactory.eINSTANCE.createStartNode());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link storyboards.StartNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see storyboards.StartNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	public void testValidate__DiagnosticChain_Map() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} // StartNodeTest
