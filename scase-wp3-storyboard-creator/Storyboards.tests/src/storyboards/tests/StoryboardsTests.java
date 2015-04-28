/**
 */
package storyboards.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>storyboards</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class StoryboardsTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new StoryboardsTests("storyboards Tests");
		suite.addTestSuite(PropertyTest.class);
		suite.addTestSuite(StoryboardDiagramTest.class);
		suite.addTestSuite(ActionTest.class);
		suite.addTestSuite(ConditionTest.class);
		suite.addTestSuite(StartNodeTest.class);
		suite.addTestSuite(EndNodeTest.class);
		suite.addTestSuite(StoryboardTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoryboardsTests(String name) {
		super(name);
	}

} // StoryboardsTests
