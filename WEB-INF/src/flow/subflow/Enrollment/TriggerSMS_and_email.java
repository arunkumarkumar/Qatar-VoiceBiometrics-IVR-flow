package flow.subflow.Enrollment;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import com.General.ApiCallFromExtJar;
import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;
import com.util.GlobalConstant;
import com.util.SMS;
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
 * Last generated by Orchestration Designer at: 2023-DEC-26  08:31:48 AM
 */
public class TriggerSMS_and_email extends com.avaya.sce.runtime.Data {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2023-DEC-26  08:31:48 AM
	 */
	public TriggerSMS_and_email() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * Returns the Next item which will forward application execution
	 * to the next form in the call flow.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:39 AM
	 */
	public com.avaya.sce.runtime.Next getNext(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Next next = new com.avaya.sce.runtime.Next("Enrollment-BAU", "Default");
		next.setDebugId(84);
		return next;
	}
	/**
	 * Create a list of local variables used by items in the data node.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:39 AM
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
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:39 AM
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
		String StartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("Date : "+StartDate);
		
		
		
		mySession.setProperty("VB_ENROLLMENT_STATUS_DATE",StartDate);
		mySession.setProperty("VB_VERIFICATION_STATUS","NA");
		mySession.setProperty("VB_VERIFICATION_STATUS_DATE","NA");
		
		HashMap<String,String>  App_Prop = new HashMap<String, String>();
		App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
		
		String message = "ENF";
		ApiCallFromExtJar api = new ApiCallFromExtJar();
		api.VBPreferEmail(mySession);
		api.sendSMS(mySession, message);
		api.sendMail(mySession, "vb_enr_failed");
		

		HashMap<String,String> customerDetails = new HashMap<String,String>();
		customerDetails = (HashMap<String, String>) mySession.getVariableField("HashMap", "customerDetails").getObjectValue();
		
		String vbstatus = customerDetails.get(GlobalConstant.VB_enrollment_status).toString();
		String statusdate = customerDetails.get(GlobalConstant.VB_enrollment_status_date);
		mySession.getVariableField(IProjectVariables.ENROLLREJECT).setValue(true);	
		
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "VB enrollment status  : "+vbstatus,mySession);
		
		if("true".equalsIgnoreCase(mySession.getVariableField(IProjectVariables.CONNECTBACK).getStringValue()))//check if this is a transfer CAll
		{
			if( "NA".equalsIgnoreCase(vbstatus) || "null".equalsIgnoreCase(vbstatus)) {
				customerDetails.put(GlobalConstant.VB_enrollment_status,"Enroll Reject on Agent Assisted");
			}else if("Enroll Rejected on IVR".equalsIgnoreCase(vbstatus)) {
				customerDetails.put(GlobalConstant.VB_enrollment_status,"Enroll Reject on IVR and Agent Assisted");
			}else if("Enroll Reject on IVR and Agent Assisted".equalsIgnoreCase(vbstatus)) {
				customerDetails.put(GlobalConstant.VB_enrollment_status,"Enroll Reject on IVR and Agent Assisted");
			}
			else {
				customerDetails.put(GlobalConstant.VB_enrollment_status,"Enroll Reject on IVR and Agent Assisted");
			}
			
		}else {
			customerDetails.put(GlobalConstant.VB_enrollment_status,"Enroll Rejected on IVR");

		}
		customerDetails.put(GlobalConstant.VB_Enrollment_Failed, "YES");
		customerDetails.put(GlobalConstant.Verified_via_vb, "NA");
		customerDetails.put(GlobalConstant.De_enrolled_caller_from_Vb, "NO");
		//customerDetails.put(GlobalConstant.VB_enrollment_status, "NO");
		
		customerDetails.put(GlobalConstant.VB_enrollment_status_date, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
		customerDetails.put(GlobalConstant.VB_verification_status, "NA");
		customerDetails.put(GlobalConstant.VB_verification_status_date, "NA");
		customerDetails.put("VB_Status","RJ");
		customerDetails.put("VB_Status_Date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
		
		
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "customerDetails from IVR  after consent reject : "+customerDetails.toString(),mySession);
		}
		catch(Exception e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "\t|\tExcepiton while sending sms or email after consent reject \t|\t"+ Arrays.toString(e.getStackTrace()), mySession);
		}
		
		super.requestBegin(mySession);
	}
}
