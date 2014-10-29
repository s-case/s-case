/**
 */
package storyboards.tests;

import junit.textui.TestRunner;

import storyboards.EndNode;
import storyboards.StoryboardsFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>End Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link storyboards.EndNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class EndNodeTest extends NodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EndNodeTest.class);
	}

	/**
	 * Constructs a new End Node test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndNodeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this End Node test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EndNode getFixture() {
		return (EndNode)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(StoryboardsFactory.eINSTANCE.createEndNode());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link storyboards.EndNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see storyboards.EndNode#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	public void testValidate__DiagnosticChain_Map() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} // EndNodeTest
