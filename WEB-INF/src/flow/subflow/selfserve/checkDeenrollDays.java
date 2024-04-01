package flow.subflow.selfserve;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
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
 * Last generated by Orchestration Designer at: 2024-JAN-03  04:06:25 PM
 */
public class checkDeenrollDays extends com.avaya.sce.runtime.Data {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2024-JAN-03  04:06:25 PM
	 */
	public checkDeenrollDays() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * Returns the Next item which will forward application execution
	 * to the next form in the call flow.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:43 AM
	 */
	public com.avaya.sce.runtime.Next getNext(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Next next = null;
		return next;
	}
	/**
	 * Create a list of local variables used by items in the data node.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:43 AM
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
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:43 AM
	 */
	public boolean executeDataActions(com.avaya.sce.runtimecommon.SCESession mySession) throws Exception {
		java.util.Collection actions = null;

		actions = new java.util.ArrayList(1);
		if(evaluateActions(actions, mySession)) {
			return true;
		}
		actions = null;

		if(((com.avaya.sce.runtime.Condition)new com.avaya.sce.runtime.Condition("condition1", "vbstatuscode", com.avaya.sce.runtime.Expression.STRING_EQUAL_IGNORE, "ELG", false).setDebugId(68)).evaluate(mySession)) {
			actions = new java.util.ArrayList(1);
			actions.add(new com.avaya.sce.runtime.Next("selfserve-enroll", "Eligible").setDebugId(66));
			if(evaluateActions(actions, mySession)) {
				return true;
			}
			actions = null;

		} else {
			actions = new java.util.ArrayList(1);
			actions.add(new com.avaya.sce.runtime.Next("selfserve-callmethod", "NotEligible").setDebugId(70));
			if(evaluateActions(actions, mySession)) {
				return true;
			}
			actions = null;
		}


		// return false if the evaluation of actions did not cause a servlet forward or redirect
		return false;
	}
	
	@Override
	public void requestBegin(SCESession mySession) {
	
		mySession.getVariableField(IProjectVariables.VBSTATUSCODE).setValue(GlobalConstant.FAILURESHORTCODE);
		try {
		HashMap<String, String> CallHistory = new HashMap<String, String>();
		CallHistory = (HashMap<String, String>) mySession.getVariableField("HashMap", "CallHistory").getObjectValue();
		
		String MenuDescription = CallHistory.get("VC_MENU_DESCRIPTION");
		MenuDescription += "DE_ENROLL_STATUS_FOR_VB_ENROLLMENT" + "|";
		MenuDescription += mySession.getVariableField(IProjectVariables.VBSTATUS).getStringValue() + "|";
		
		CallHistory.put("VC_MENU_DESCRIPTION", MenuDescription);
		
		
		
		
		HashMap<String,String>  App_Prop = new HashMap<String, String>();
		App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
		if(App_Prop != null) {
			
			String VB_SELF_SERVE = mySession.getVariableField(IProjectVariables.VBSELFSERVE).getStringValue();
			String VB_DEENROLL_DAYS = mySession.getVariableField(IProjectVariables.DEENROLLDAYS).getStringValue();
			String VB_DEENROLL_DATE = mySession.getVariableField(IProjectVariables.VBSTATUSDATE).getStringValue();
			deenroll(VB_DEENROLL_DATE, VB_DEENROLL_DAYS,mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " called deenroll check " , mySession);
			
		} else {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " configuration file was not loaded properly | We Receive 'null' from Host Jar " , mySession);
		}
		
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " STATUS:  "+mySession.getVariableField(IProjectVariables.VBSTATUS).getStringValue() , mySession);
		}
		catch(Exception e) {
			mySession.getVariableField(IProjectVariables.VBSTATUSCODE).setValue(GlobalConstant.FAILURESHORTCODE);
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!Exception :::" + GetStackTrace.getMessage(e));
		}
		super.requestBegin(mySession);
	}
	
	
	void deenroll(String input, String dayscount,SCESession mySession) {
		try 
		{
			HashMap<String,String>  App_Prop = new HashMap<String, String>();
			App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
			mySession.getVariableField(IProjectVariables.DATEFORMAT).setValue(App_Prop.get("dateformat"));		
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, " date : "+input+" daysocunt : "+dayscount ,mySession);
		int days = Integer.parseInt(dayscount);
		//String input = "06-01-2024 23:00:01";
		//long DenrollDays = 3;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(mySession.getVariableField(IProjectVariables.DATEFORMAT).getStringValue());
		LocalDateTime customerTime = LocalDateTime.parse(input, formatter);
		LocalDateTime eligibleTime = customerTime.toLocalDate().plusDays(days).atStartOfDay();
		LocalDateTime currentTime = LocalDateTime.now();
		Duration duration = Duration.between(currentTime, eligibleTime);
		long daysDifference = duration.toDays();
		long hoursDifference = duration.toHours() % 24;
		long minutes = duration.toMinutes() % 60;
		long seconds = duration.getSeconds() % 60;
		if (daysDifference > 0) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Not eligible yet.",mySession);
		} else if (hoursDifference > 0) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Not eligible yet.",mySession);
		} else if (minutes > 0) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Not eligible yet.",mySession);
		} else if (seconds > 0) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Not eligible yet.",mySession);
		} else {
		if (currentTime.isAfter(eligibleTime)) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Eligible.",mySession);
			mySession.setProperty("DEELG", "YES");
			mySession.getVariableField(IProjectVariables.VBSTATUSCODE).setValue(GlobalConstant.ELIGIBILESHORTCODE);
		} else {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Not eligible yet.",mySession);
		}
		}
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Days difference: " + daysDifference,mySession);
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Hours difference: " + hoursDifference,mySession);
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "minutes difference: " + minutes,mySession);
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "seconds difference: " + seconds,mySession);
		}
		catch(Exception e) {
			mySession.getVariableField(IProjectVariables.VBSTATUSCODE).setValue(GlobalConstant.FAILURESHORTCODE);
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!Exception :::" + GetStackTrace.getMessage(e));
		}
	}
}
