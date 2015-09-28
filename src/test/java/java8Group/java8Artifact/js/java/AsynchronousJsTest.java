/**
 * 
 */
package java8Group.java8Artifact.js.java;

import java.io.FileNotFoundException;

import javax.script.ScriptException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java8Group.java8Artifact.common.NashornInterface;

/**
 * @author maheshrajannan
 *
 */
public class AsynchronousJsTest {
	private NashornInterface nashornInterface = null;

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
		nashornInterface = NashornInterface.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		nashornInterface = null;
	}

	/**
	 * Test method for
	 * {@link java8Group.java8Artifact.js.java.AsynchronousJs.java.AsynchronousJsTest#main(java.lang.String[])}
	 * .
	 * 
	 * INFO: "console.log("I just timed out!");" never gets called from nashorn
	 * javascript engine because the engine exits as soon as it gets evaluated.
	 * In short, the execution context of nashorn javascript engine, is short
	 * lived just like your java main thread. It therefore behaves differently
	 * from browser(web) context, because browser context is long lived than the
	 * nashorn(java) context. So java script is a single threaded event
	 * loop.Browser, plugs in a callback function, to the task queue and
	 * javascript's event loop executes it. This("console.log("I just timed
	 * out!");") does NOT happen from nashorn context.
	 * 
	 * @throws ScriptException
	 * @throws FileNotFoundException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testAsyncCall() throws FileNotFoundException, ScriptException,
			NoSuchMethodException {
		nashornInterface.evaluateJsFile(
				"src/main/java/java8Group/java8Artifact/js/asynchronous.js");
		String result = (String) nashornInterface.getInvocable()
				.invokeFunction("asynccall");
		System.out.println("Result of async call : " + result);
		Assert.assertEquals("Result is unexpected ", result, "SUCCESS");
	}

}
