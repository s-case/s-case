/**
 */
package storyboards;

import java.util.Map;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Storyboard</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link storyboards.StoryboardDiagram#getStoryboardactions <em>Storyboardactions</em>}</li>
 * <li>{@link storyboards.StoryboardDiagram#getStoryboardproperties <em>Storyboardproperties</em>}</li>
 * <li>{@link storyboards.StoryboardDiagram#getStoryboardconditions <em>Storyboardconditions</em>}</li>
 * <li>{@link storyboards.StoryboardDiagram#getStoryboardstartnode <em>Storyboardstartnode</em>}</li>
 * <li>{@link storyboards.StoryboardDiagram#getStoryboardendnode <em>Storyboardendnode</em>}</li>
 * <li>{@link storyboards.StoryboardDiagram#getStoryboardstoryboards <em>Storyboardstoryboards</em>}</li>
 * </ul>
 * </p>
 * 
 * @see storyboards.StoryboardsPackage#getStoryboardDiagram()
 * @model
 * @generated
 */
public interface StoryboardDiagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Storyboardactions</b></em>' containment reference list.
	 * The list contents are of type {@link storyboards.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storyboardactions</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Storyboardactions</em>' containment reference list.
	 * @see storyboards.StoryboardsPackage#getStoryboardDiagram_Storyboardactions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getStoryboardactions();

	/**
	 * Returns the value of the '<em><b>Storyboardproperties</b></em>' containment reference list.
	 * The list contents are of type {@link storyboards.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storyboardproperties</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Storyboardproperties</em>' containment reference list.
	 * @see storyboards.StoryboardsPackage#getStoryboardDiagram_Storyboardproperties()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getStoryboardproperties();

	/**
	 * Returns the value of the '<em><b>Storyboardconditions</b></em>' containment reference list.
	 * The list contents are of type {@link storyboards.Condition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storyboardconditions</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Storyboardconditions</em>' containment reference list.
	 * @see storyboards.StoryboardsPackage#getStoryboardDiagram_Storyboardconditions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Condition> getStoryboardconditions();

	/**
	 * Returns the value of the '<em><b>Storyboardstartnode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storyboardstartnode</em>' containment reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Storyboardstartnode</em>' containment reference.
	 * @see #setStoryboardstartnode(StartNode)
	 * @see storyboards.StoryboardsPackage#getStoryboardDiagram_Storyboardstartnode()
	 * @model containment="true" required="true"
	 * @generated
	 */
	StartNode getStoryboardstartnode();

	/**
	 * Sets the value of the '{@link storyboards.StoryboardDiagram#getStoryboardstartnode <em>Storyboardstartnode</em>}'
	 * containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Storyboardstartnode</em>' containment reference.
	 * @see #getStoryboardstartnode()
	 * @generated
	 */
	void setStoryboardstartnode(StartNode value);

	/**
	 * Returns the value of the '<em><b>Storyboardendnode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storyboardendnode</em>' containment reference isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Storyboardendnode</em>' containment reference.
	 * @see #setStoryboardendnode(EndNode)
	 * @see storyboards.StoryboardsPackage#getStoryboardDiagram_Storyboardendnode()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EndNode getStoryboardendnode();

	/**
	 * Sets the value of the '{@link storyboards.StoryboardDiagram#getStoryboardendnode <em>Storyboardendnode</em>}'
	 * containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Storyboardendnode</em>' containment reference.
	 * @see #getStoryboardendnode()
	 * @generated
	 */
	void setStoryboardendnode(EndNode value);

	/**
	 * Returns the value of the '<em><b>Storyboardstoryboards</b></em>' containment reference list.
	 * The list contents are of type {@link storyboards.Storyboard}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storyboardstoryboards</em>' containment reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Storyboardstoryboards</em>' containment reference list.
	 * @see storyboards.StoryboardsPackage#getStoryboardDiagram_Storyboardstoryboards()
	 * @model containment="true"
	 * @generated
	 */
	EList<Storyboard> getStoryboardstoryboards();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // Storyboard
