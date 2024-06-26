package flow;

import java.util.Arrays;
import java.util.HashMap;
import com.General.AppConstants;
import com.General.Load;
import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;

/**
 * A basic servlet which allows a user to define their code, generate
 * any output, and to select where to transition to next.
 * Last generated by Orchestration Designer at: 2021-JUN-08  02:08:09 PM
 */
public class LoadInitValues extends com.avaya.sce.runtime.BasicServlet {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2021-JUN-08  02:08:09 PM
	 */
	public LoadInitValues() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * This method allows for custom integration with other Java components.
	 * You may use Java for sophisticated logic or to integrate with custom
	 * connectors (i.e. JMS, custom web services, sockets, XML, JAXB, etc.)
	 *
	 * Any custom code added here should work as efficiently as possible to prevent delays.
	 * It's important to design your callflow so that the voice browser (Experienve Portal/IR)
	 * is not waiting too long for a response as this can lead to a poor caller experience.
	 * Additionally, if the response to the client voice browser exceeds the configured
	 * timeout, the platform may throw an "error.badfetch". 
	 *
	 * Using this method, you have access to all session variables through the 
	 * SCESession object.
	 *
	 * The code generator will *** NOT *** overwrite this method in the future.
	 * Last generated by Orchestration Designer at: 2021-JUN-08  02:08:09 PM
	 */
	public void servletImplementation(com.avaya.sce.runtimecommon.SCESession mySession) {

		String method = null;

		try {

			Load load = new Load();

			String path = getServletContext().getInitParameter("PropertyFile").trim();
			mySession.getVariableField(IProjectVariables.PROPERTY_FILE_LOCATION).setValue(path);

			HashMap<String,String> App_Prop = new HashMap<String,String>();
			App_Prop.put("0", "0");
			mySession.getVariableField("HashMap", "PropertyValue").setValue(App_Prop);
			mySession.getVariableField("HashMap", "AppProperties").setValue(App_Prop);
			mySession.getVariableField("HashMap", "Language").setValue(App_Prop);
			mySession.getVariableField("HashMap", "Node").setValue(App_Prop);

			method = getServletContext().getInitParameter("InitialMethod").trim();

			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "LoadInitValues\t|\tservletImplementation\t|\tmethod\t|\t"+method, mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "LoadInitValues\t|\servletImplementation\t|\t intial values set",mySession);	
			load.values(method, mySession);
			
		} catch(Exception e) {

			mySession.getVariableField(IProjectVariables.ERROR).setValue(true);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "LoadInitValues\t|\servletImplementation\t|\t"+ AppConstants.EXCEPTION_1+ e.getMessage() +AppConstants.EXCEPTION_2 , mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "LoadInitValues\t|\servletImplementation\t|\t"+ Arrays.toString(e.getStackTrace()), mySession);
		}

	}
	
	/**
	 * Builds the list of branches that are defined for this servlet object.
	 * This list is built automatically by defining Goto nodes in the call flow editor.
	 * It is the programmer's responsibilty to provide at least one enabled Goto.<BR>
	 *
	 * The user should override updateBranches() to determine which Goto that the
	 * framework will activate.  If there is not at least one enabled Goto item, 
	 * the framework will throw a runtime exception.<BR>
	 *
	 * This method is generated automatically and changes to it may
	 * be overwritten next time code is generated.  To modify the list
	 * of branches for the flow item, override:
	 *     <code>updateBranches(Collection branches, SCESession mySession)</code>
	 *
	 * @return a Collection of <code>com.avaya.sce.runtime.Goto</code>
	 * objects that will be evaluated at runtime.  If there are no gotos
	 * defined in the Servlet node, then this returns null.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:43 AM
	 */
	public java.util.Collection getBranches(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List list = null;
		com.avaya.sce.runtime.Goto aGoto = null;
		list = new java.util.ArrayList(1);

		aGoto = new com.avaya.sce.runtime.Goto("DynamicFetchNode", 0, true, "Default");
		list.add(aGoto);

		return list;
	}
}
