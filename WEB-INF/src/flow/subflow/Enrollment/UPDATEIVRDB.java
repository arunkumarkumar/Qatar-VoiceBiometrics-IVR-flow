package flow.subflow.Enrollment;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import com.General.Load;
import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;
import com.util.GlobalConstant;
import com.util.Nuance.VBHist;

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
 * Last generated by Orchestration Designer at: 2024-JAN-23  04:08:05 PM
 */
public class UPDATEIVRDB extends com.avaya.sce.runtime.Data {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2024-JAN-23  04:08:05 PM
	 */
	public UPDATEIVRDB() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * Returns the Next item which will forward application execution
	 * to the next form in the call flow.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:37 AM
	 */
	public com.avaya.sce.runtime.Next getNext(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Next next = new com.avaya.sce.runtime.Next("Enrollment-SMS_EMAIL_SUCCESS", "Default");
		next.setDebugId(532);
		return next;
	}
	/**
	 * Create a list of local variables used by items in the data node.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:37 AM
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
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:37 AM
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
		try
		{
		
			String ident_type = mySession.getVariableField("ident_type")!=null ? mySession.getVariableField("ident_type").getStringValue():"NA";
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"identified type is "+ident_type,mySession);
			
		VBHist vbhistory = new VBHist();
		vbhistory.setDT_STATUS_UPDATE_DATE(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
		if((GlobalConstant.TRUE).equalsIgnoreCase(mySession.getVariableField(IProjectVariables.CONNECTBACK).getStringValue())) {
			vbhistory.setVC_CHANNEL(GlobalConstant.AGENT_ASSIST);
			vbhistory.setVC_WORK_REQUEST_ID(mySession.getVariableField(IProjectVariables.CONTEXTID).getStringValue());
	 	}else {
	 		vbhistory.setVC_CHANNEL("VOICE");
	 		vbhistory.setVC_WORK_REQUEST_ID("");//obtained from agent
	 	}
		//default values
		vbhistory.setVC_DE_ENROLL_REASON(GlobalConstant.NA);
		vbhistory.setVC_DE_ENROLLED_AGENT_ID(GlobalConstant.NA);
		vbhistory.setVC_DE_ENROLLED_AGENT_ROLE(GlobalConstant.NA);
		
		vbhistory.setVC_FFP_NUMBER(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue());
		vbhistory.setVC_FUNCTION_NAME("ENROLL_SUCCESS");
		vbhistory.setVC_VB_REASON(mySession.getVariableField(IProjectVariables.VBREASON).getStringValue());
		vbhistory.setVC_IDENTIFY_TYPE(ident_type);//need to change to identified type
		vbhistory.setVC_SEGMENT(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MEMBERTIER).getStringValue());
		vbhistory.setVC_STATUS(GlobalConstant.YES);
		vbhistory.setVC_UCID(mySession.getVariableField("session", "ucid").getStringValue());
		
		vbhistory.setVC_TRANSFERRED_BY_AGENT_ID(mySession.getVariableField(IProjectVariables.VC__TRANSFERRED__BY__AGENT__ID).getStringValue());//obtained from agent
		vbhistory.setVC_TRANSFERRED_BY_AGENT_ROLE(mySession.getVariableField(IProjectVariables.VC__TRANSFERRED__BY__AGENT__ROLE).getStringValue());//obtained from agent
		
		
		
		mySession.setProperty("vbmap", vbhistory);
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "input sent from IVR : "+mySession.getProperty("vbmap").toString(),mySession);
		Load load = new Load();
		String method = "VBHistory";
		load.values(method, mySession);
		}
		catch(Exception e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "\t|\tExcepiton while updating IVR DB  after enrollment success \t|\t"+ Arrays.toString(e.getStackTrace()), mySession);
		}
		super.requestBegin(mySession);
	}
}
