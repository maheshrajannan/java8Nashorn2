/**
 * 
 */
package java8Group.java8Artifact.js.java;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
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
public class MustacheInvocationTest {
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
	}

	/**
	 * Test method for
	 * {@link java8Group.java8Artifact.js.java.MustacheInvocation#main(java.lang.String[])}
	 * .
	 * 
	 * @throws ScriptException
	 * @throws FileNotFoundException
	 * @throws NoSuchMethodException
	 */
	@Test
	public final void testMain()
			throws FileNotFoundException, ScriptException, NoSuchMethodException {
		String template = "Email addresses of {{contact.name}}:\n"
				+ "{{#contact.emails}}\n" + "- {{.}}\n" + "{{/contact.emails}}";
		Map<String,String> contact=new HashMap<String, String>();
		contact.put("name", "Mr A");
		contact.put("email1", "contact@some.tld");
		contact.put("email2", "sales@some.tld");
		String contactJson = "{" + "\"contact\": {" + "\"name\": \""
				+ contact.get("name")
				+ "\", \"emails\": ["
				+ "\""
				+ contact.get("email1")
				+ "\", \""
				+ contact.get("email2")
				+ "\"" + "]}}";
		String jsFile = "mustache.js-master/mustache.js";

		NashornInterface nashornInterface = NashornInterface.getInstance();
		nashornInterface.evaluateJsFile(jsFile);
		Invocable invocable = nashornInterface.getInvocable();
		String result = null;

		Object json = nashornInterface.getNashornEngine().eval("JSON");
		Object data = invocable.invokeMethod(json, "parse", contactJson);
		System.out.println("data " + data);

		Object mustache = nashornInterface.getNashornEngine().eval("Mustache");
		result = invocable.invokeMethod(mustache, "render", template, data)+"";
		
		System.out.println(
				"\n Evaluating template " + template +
				"\n With data " + contactJson +
				"\n With invocation invocable.invokeMethod(mustache, \"render\", template, data)"
				+ "\n with result as \n"+
				result
				);
		Assert.assertTrue("Contains name", result.contains(contact.get("name")));
		Assert.assertTrue("Contains email1", result.contains(contact.get("email1")));
		Assert.assertTrue("Contains email2", result.contains(contact.get("email2")));
	}

}
