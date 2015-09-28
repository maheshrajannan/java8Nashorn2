/**
 * 
 */
package java8Group.java8Artifact.js.java;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.script.Invocable;
import javax.script.ScriptException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java8Group.java8Artifact.common.NashornInterface;
import java8Group.java8Artifact.js.interfaces.Adder;
import junit.framework.Assert;

/**
 * @author maheshrajannan
 *
 */
public class InlineJsFunctionExecutionWithInvocableTest {
	NashornInterface nashHornInterface = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		nashHornInterface = NashornInterface.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		nashHornInterface = null;
	}

	/**
	 * Test method for
	 * {@link java8Group.java8Artifact.js.java.InlineJsFunctionExecutionWithInvocable#invokeInlineFunction()}
	 * .
	 * 
	 * @throws ScriptException
	 * @throws NoSuchMethodException
	 */
	@Test
	public final void testInvokeInlineFunction()
			throws ScriptException, NoSuchMethodException {
		String functionToEvaluate = "function sum(a, b) { return a + b; }";
		String functionCall = "sum";
		Object result = null;

		nashHornInterface.evaluateJs(functionToEvaluate);
		Invocable invocable = nashHornInterface.getInvocable();
		result = invocable.invokeFunction(functionCall, 10, 2);
		System.out.println("\n functionToEvaluate " + functionToEvaluate
				+ "\n functionCall " + functionCall + "(10,2)"
				+ "\n return value " + result);

		Assert.assertEquals("Expected 12 but got something else",
				new Double("12"), new Double(result.toString()));

	}

	/**
	 * Test method for
	 * {@link java8Group.java8Artifact.js.java.InlineJsFunctionExecutionWithInvocable#invokeJsFile()}
	 * .
	 * 
	 * @throws ScriptException
	 * @throws FileNotFoundException
	 */
	@Test
	public final void testInvokeJsFile()
			throws FileNotFoundException, ScriptException {
		nashHornInterface.evaluateJsFile(
				"src/main/java/java8Group/java8Artifact/js/" + "adder.js");
		Invocable invocable = nashHornInterface.getInvocable();
		Adder adder = invocable.getInterface(Adder.class);
		Object result = null;
		result = adder.sum(2, 3);
		Assert.assertEquals("Expected 5 but got something else", "5",
				result.toString());

	}

}
