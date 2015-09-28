/**
 * 
 */
package java8Group.java8Artifact.js.java;

import javax.script.ScriptException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java8Group.java8Artifact.common.NashornInterface;
import junit.framework.Assert;

/**
 * @author maheshrajannan
 *
 */
public class InlineJavaScriptExecutionTest {
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
	 * {@link java8Group.java8Artifact.js.java.InlineJavaScriptExecution#main(java.lang.String[])}
	 * This is just to test in-line execution of java script.
	 * @throws ScriptException 
	 * 
	 */
	@Test
	public final void testInlineJsExecution() throws ScriptException {
		String functionToEvaluate = "function sum(a, b) { return a + b; }";
		String functionCall = "sum(1, 2);";
		nashHornInterface.evaluateJs(functionToEvaluate);
		Object result = nashHornInterface.evaluateJs(functionCall);
		System.out.println("\n functionToEvaluate " + functionToEvaluate
				+ "\n functionCall " + functionCall + "\n return value "
				+ result);
		Assert.assertEquals("Expected 3 but got something else","3", result.toString());
	}

}
