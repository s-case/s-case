/**
 */
package storyboards.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import storyboards.Storyboard;
import storyboards.StoryboardsPackage;
import storyboards.util.StoryboardsValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Storyboard</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class StoryboardImpl extends ActionNodeImpl implements Storyboard {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StoryboardImpl() {
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
		return StoryboardsPackage.Literals.STORYBOARD;
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
						StoryboardsValidator.STORYBOARD__VALIDATE, "There is a storyboard with no name!",
						new Object[] { this }));
			}
			if (getNextNode() == null) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.STORYBOARD__VALIDATE, "Storyboard \'" + getName()
								+ "\' does not connect to any node!", new Object[] { this }));
			}
			if (getPreviousNodes().size() == 0) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.STORYBOARD__VALIDATE, "No connection to storyboard \'" + getName() + "\'",
						new Object[] { this }));
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
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
		case StoryboardsPackage.STORYBOARD___VALIDATE__DIAGNOSTICCHAIN_MAP:
			return validate((DiagnosticChain) arguments.get(0), (Map<Object, Object>) arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
	}

} // StoryboardImpl
