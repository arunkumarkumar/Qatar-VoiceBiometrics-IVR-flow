package flow.subflow.connectback;

import java.util.HashMap;

import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.IVariable;
import com.avaya.sce.runtimecommon.SCESession;
import com.util.GetStackTrace;
import com.util.GlobalConstant;

import flow.IProjectVariables;

/**
 * The Data class handles many types of server-side operations including data
 * collection (from a data sources such as a database, or web service), variable
 * assignments and operations (like copying variable values, performing mathematic
 * operations, and collection iteration), conditional evaluation to control callflow
 * execution based on variable values, and logging/tracing statements.
 * 
 * Items created in the getDataActions() method are executed/evaluated in order
 * and if a condional branch condition evaluates to "true" then the branch is
 * activated and the execution of data actions is halted.  If no "true" conditions
 * are encountered, then all data actions will be executed/evaluated and the 
 * application will proceed to the "Default" servlet.
 * Last generated by Orchestration Designer at: 2023-DEC-25  05:31:59 AM
 */
public class getData extends com.avaya.sce.runtime.Data {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2023-DEC-25  05:31:59 AM
	 */
	public getData() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * Returns the Next item which will forward application execution
	 * to the next form in the call flow.
	 * Last generated by Orchestration Designer at: 2024-FEB-02  05:02:37 PM
	 */
	public com.avaya.sce.runtime.Next getNext(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Next next = new com.avaya.sce.runtime.Next("connectback-LoadVBConfigValuesFromHost", "Default");
		next.setDebugId(73);
		return next;
	}
	/**
	 * Create a list of local variables used by items in the data node.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-FEB-02  05:02:37 PM
	 */
	public java.util.Collection<VariableInfo> getLocalVariables(){
		java.util.Collection<VariableInfo> variables = new java.util.ArrayList<VariableInfo>();

		return variables;
	}
	/**
	 * Creates and conditionally executes operations that have been configured
	 * in the Callflow.  This method will build a collection of operations and
	 * have the framework execute the operations by calling evaluateActions().
	 * If the evaluation causes the framework to forward to a different servlet
	 * then execution stops.
	 * Returning true from this method means that the framework has forwarded the
	 * request to a different servlet.  Returning false means that the default
	 * Next will be invoked.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-FEB-02  05:02:37 PM
	 */
	public boolean executeDataActions(com.avaya.sce.runtimecommon.SCESession mySession) throws Exception {
		java.util.Collection actions = null;

		actions = new java.util.ArrayList(1);
		if(evaluateActions(actions, mySession)) {
			return true;
		}
		actions = null;

		// return false if the evaluation of actions did not cause a servlet forward or redirect
		return false;
	}
	@Override
	public void requestBegin(SCESession mySession) {

		
		try {
			mySession.getVariableField(IProjectVariables.SESSION,IProjectVariables.SESSION_FIELD_ANI).setValue(mySession.getVariableField(IProjectVariables.SESSION,IProjectVariables.SESSION_FIELD_ANI).getStringValue().replaceAll("[+]", ""));
			
			IVariable session =  mySession.getVariable(IProjectVariables.SESSION);
			String language = "";
			String AUDIOLOCATION = "";
			String FFPNUMBER ="";
			String SEGMENTCODE ="";
			String FLOWNAME= "";
			
			HashMap<String,String>  App_Prop = new HashMap<String, String>();
			App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
			String sudioPath = App_Prop.get("AudioLocation").toString();
			GlobalConstant.PRIMARYURL = App_Prop.get("PRIMARYURL").toString();
			//GlobalConstant.SECONDARYURL = App_Prop.get("SECONDARYURL").toString();
			GlobalConstant.NUANCE_WINDOWS_SERVER_USERNAME = App_Prop.get("NUANCE_WINDOWS_SERVER_USERNAME").toString();
			GlobalConstant.NUANCE_WINDOWS_SERVER_PASSWORD = App_Prop.get("NUANCE_WINDOWS_SERVER_PASSWORD").toString();
			GlobalConstant.NUANCE_WINDOWS_SERVER_DOMAIN = App_Prop.get("NUANCE_WINDOWS_SERVER_DOMAIN").toString();
			GlobalConstant.NUANCE_WSNAMESPACE = App_Prop.get("NUANCE_WSNAMESPACE").toString();
			GlobalConstant.CONFIGSETNAME = App_Prop.get("CONFIGSETNAME").toString();
			
			
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " ANI: " + session.getComplexVariable().getField(IProjectVariables.SESSION_FIELD_ANI), mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " UCID: " + session.getComplexVariable().getField(IProjectVariables.SESSION_FIELD_UCID), mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " DNIS: " + session.getComplexVariable().getField(IProjectVariables.SESSION_FIELD_DNIS), mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " FFPNUMBER: " +FFPNUMBER , mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " SEGMENTCODE: " + SEGMENTCODE, mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " FLOWNAME: " + FLOWNAME, mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " LANGUAGE: " + language, mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " AUDIOLOCATION: " + AUDIOLOCATION, mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " DEREGISTERREASON: " +"",mySession);
			
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " CURRENTLANGUAGE: " + session.getComplexVariable().getField(IProjectVariables.SESSION_FIELD_CURRENTLANGUAGE), mySession);
			
			mySession.getVariableField(IProjectVariables.VB_INPUTS, IProjectVariables.VB_INPUTS_FIELD_CALL_FLOW_NAME).setValue(FLOWNAME);
				
				if(SEGMENTCODE !=null) {
					
					mySession.getVariableField(IProjectVariables.VB_INPUTS, IProjectVariables.VB_INPUTS_FIELD_SEGMENT_CODE).setValue("GMMN");
					//Segment
					if(!"NA".equalsIgnoreCase((SEGMENTCODE))) {
					mySession.getVariableField(IProjectVariables.VB_INPUTS , IProjectVariables.VB_INPUTS_FIELD_SEGMENT_CODE).setValue(SEGMENTCODE);
					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " SEGMENT CODE "+SEGMENTCODE+" Receieved from Main IVR", mySession);
					}else {
						mySession.getVariableField(IProjectVariables.VB_INPUTS , IProjectVariables.VB_INPUTS_FIELD_SEGMENT_CODE).setValue("GMMN");
						TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " SEGMENT CODE GMMN setas default", mySession);
					}

				}
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " SEGMENT_CODE: " +mySession.getVariableField(IProjectVariables.VB_INPUTS , IProjectVariables.VB_INPUTS_FIELD_SEGMENT_CODE).getStringValue(), mySession);
				
				
		}
		catch (Exception ex) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " Exception : " + GetStackTrace.getMessage(ex),mySession);
		}
			
		super.requestBegin(mySession);
	
	}
}
