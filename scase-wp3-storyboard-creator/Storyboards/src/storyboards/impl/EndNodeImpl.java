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
import storyboards.EndNode;
import storyboards.StoryboardsPackage;
import storyboards.util.StoryboardsValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>End Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class EndNodeImpl extends NodeImpl implements EndNode {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EndNodeImpl() {
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
		return StoryboardsPackage.Literals.END_NODE;
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
			if (getPreviousNodes().size() == 0) {
				valid = false;
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, StoryboardsValidator.DIAGNOSTIC_SOURCE,
						StoryboardsValidator.END_NODE__VALIDATE, "Nothing is connected to the end node!",
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
		case StoryboardsPackage.END_NODE___VALIDATE__DIAGNOSTICCHAIN_MAP:
			return validate((DiagnosticChain) arguments.get(0), (Map<Object, Object>) arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
	}

} // EndNodeImpl
