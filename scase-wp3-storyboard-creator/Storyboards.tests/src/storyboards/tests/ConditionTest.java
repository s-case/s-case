/**
 */
package storyboards.tests;

import junit.textui.TestRunner;

import storyboards.Condition;
import storyboards.StoryboardsFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link storyboards.Condition#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Validate
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ConditionTest extends NodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ConditionTest.class);
	}

	/**
	 * Constructs a new Condition test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ConditionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Condition test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Condition getFixture() {
		return (Condition) fixture;
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
		setFixture(StoryboardsFactory.eINSTANCE.createCondition());
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
	 * Tests the '{@link storyboards.Condition#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see storyboards.Condition#validate(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	public void testValidate__DiagnosticChain_Map() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} // ConditionTest
