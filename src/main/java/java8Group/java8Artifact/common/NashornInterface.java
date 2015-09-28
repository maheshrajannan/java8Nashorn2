/**
 * 
 */
package java8Group.java8Artifact.common;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author maheshrajannan 
 * Non singleton, delegate to nashornEngine. Each
 * instance of interface holds an instance of nashHorn Engine.
 * This is to prevent re-initialization of nashorn engine, invocable etc.
 *  
 */
public class NashornInterface {
	/**
	 * has one instance of engine manager. 
	 */
	ScriptEngineManager engineManager = null;
	/**
	 * has one instance of engine
	 */
	ScriptEngine engine = null;
	
	/**
	 * This is for java function invocation of java script functions.
	 */
	Invocable invocable = null;

	/**
	 * @return
	 */
	public static NashornInterface getInstance() {
		return new NashornInterface();
	}

	/**
	 * Initializes script engine if necessary and returns
	 * it.
	 * 
	 * @return
	 */
	public ScriptEngine getNashornEngine() {
		if (engineManager == null) {
			engineManager = new ScriptEngineManager();
		}
		if (engine == null) {
			engine = engineManager.getEngineByName("nashorn");
		}
		return engine;
	}

	/**
	 * Initializes invocable if necessary and returns it.
	 * @return
	 */
	public Invocable getInvocable() {
		if(invocable == null) {
			invocable = (Invocable) getNashornEngine();
		}
		return invocable;
	}

	/**
	 * Evaluates any in-line java script function and returns any 
	 * return arguments associated with it.
	 * 
	 * @param engine
	 * @param js
	 * @return
	 * @throws ScriptException
	 */
	public Object evaluateJs(ScriptEngine engine, String js) throws ScriptException {
		return engine.eval(js);
	}

	/**
	 * Evaluates any in-line java script function and returns any 
	 * return arguments associated with it.
	 * 
	 * @param js
	 * @return
	 * @throws ScriptException
	 */
	public Object evaluateJs(String js) throws ScriptException {
		return evaluateJs(getNashornEngine(), js);
	}

	/**
	 * Evaluates any java script file.
	 * Usually does not result in a return argument.
	 * 
	 * @param nashornEngine
	 * @param fileReader
	 * @return
	 * @throws ScriptException
	 */
	private Object evaluateJs(ScriptEngine nashornEngine, FileReader fileReader)
			throws ScriptException {
		return nashornEngine.eval(fileReader);
	}
	
	/**
	 * Evaluates any java script file.
	 * Usually does not result in a return argument.
	 * 
	 * @param fileReader
	 * @return
	 * @throws ScriptException
	 */
	public Object evaluateJs(FileReader fileReader) throws ScriptException {
		return evaluateJs(getNashornEngine(), fileReader);
	}

	/**
	 * Evaluates any java script file.
	 * Usually does not result in a return argument.
	 * 
	 * @param filePath
	 * @return
	 * @throws ScriptException
	 * @throws FileNotFoundException
	 */
	public Object evaluateJsFile(String filePath)
			throws ScriptException, FileNotFoundException {
		return evaluateJs(getNashornEngine(), new FileReader(filePath));
	}
	
}
