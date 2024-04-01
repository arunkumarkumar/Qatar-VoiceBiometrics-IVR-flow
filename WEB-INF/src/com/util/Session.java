package com.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.soap.SOAPException;

import org.json.JSONException;
import org.json.JSONObject;

import com.General.LoadApplicationProperties;
import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;

import flow.IProjectVariables;

public class Session {
	
public void endSession(SCESession mySession) {


	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_INFO, "UCID: "+mySession.getVariableField(IProjectVariables.SESSION, IProjectVariables.SESSION_FIELD_UCID).getStringValue());

	mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_EXCEPTION).setValue(true);
	mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_TRF).setValue(GlobalConstant.TRFHOSTDOWN);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String strDate ="";
	boolean exceptionFlag=true;
	String status = "FAILURE";
	String responseCode = "";
	
	Date date = new Date();
	strDate = formatter.format(date);

	HashMap<String, Object> map = new HashMap<>();
	map.put("sessionId", mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_SESSION_ID).getStringValue());
	map.put("configSetName", GlobalConstant.CONFIGSETNAME);
	JSONObject StartSessionResult;
	try {
		mySession.getVariableField(IProjectVariables.VB_INPUTS, IProjectVariables.VB_INPUTS_FIELD_WS_URL).setValue(GlobalConstant.PRIMARYURL);
		// Hit Nuance End point
		String method = LoadApplicationProperties.getProperty("EndSession", mySession);
		JSONObject object = NuanceConnector.startConnect(map, method , "",mySession);
		responseCode = mySession.getVariableField(IProjectVariables.RESPONSE_CODE).getStringValue();
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "Response Code for EndSession : "+responseCode);
		
		
	} catch (UnsupportedOperationException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!UnsupportedOperationException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (SOAPException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!SOAPException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (JSONException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!JSONException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (IOException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!IOException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (Exception e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!Exception :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	}


}


public void  startsess(SCESession mySession) {
	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_INFO, "UCID: "+mySession.getVariableField(IProjectVariables.SESSION, IProjectVariables.SESSION_FIELD_UCID).getStringValue());
	mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_SESSION_ID).setValue("NA");
	mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_EXCEPTION).setValue(true);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String strDate ="";
	boolean exceptionFlag=true;
	String status = "FAILURE";
	String responseCode = "";
	
	Date date = new Date();
	strDate = formatter.format(date);

	HashMap<String, Object> map = new HashMap<>();
	map.put("configSetName", GlobalConstant.CONFIGSETNAME);
	map.put("externalSessionId", mySession.getVariableField(IProjectVariables.SESSION,IProjectVariables.SESSION_FIELD_SESSIONID).getStringValue());//FFP Number
	JSONObject StartSessionResult;
	try {
		mySession.getVariableField(IProjectVariables.VB_INPUTS, IProjectVariables.VB_INPUTS_FIELD_WS_URL).setValue(GlobalConstant.PRIMARYURL);
		
		// Hit Nuance End point
		String method = LoadApplicationProperties.getProperty("StartSession", mySession);
		JSONObject object = NuanceConnector.startConnect(map, method, "",mySession);
		responseCode = mySession.getVariableField(IProjectVariables.RESPONSE_CODE).getStringValue();
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "Response Code : "+responseCode);
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "object : "+object);
		
		if((object != null)&& responseCode.equalsIgnoreCase("200")) {
		if(object.getJSONObject("soap:Envelope")!=null) {
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_INFO, " Has Envelope" );
        StartSessionResult = object.getJSONObject("soap:Envelope").getJSONObject("soap:Body")
            .getJSONObject("StartSessionResponse").getJSONObject("StartSessionResult");
		}else {
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_INFO, " does not have Envelope" );
        StartSessionResult = object.getJSONObject("soap:Body")
                .getJSONObject("StartSessionResponse").getJSONObject("StartSessionResult");
		}

        mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_INFO, " SessionId: " + StartSessionResult.get("SessionId"));
        mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_INFO, " IPAddress: " + StartSessionResult.get("IPAddress"));

        mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_SESSION_ID).setValue(StartSessionResult.get("SessionId"));
        mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_EXCEPTION).setValue(false);
        mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_TRF).setValue("NA");
        
        mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, " SessionId set is : " + mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_SESSION_ID).getStringValue());
        mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, " Exception set is : " + mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_EXCEPTION).getStringValue());
        exceptionFlag = false;
        status = "SUCCESS";
        mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, " Response Code : "+responseCode);
		
		}else {
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "object is null or response code is not 200 ");
		}
		
		
	} catch (UnsupportedOperationException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!UnsupportedOperationException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (SOAPException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!SOAPException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (JSONException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!JSONException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (IOException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!IOException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (Exception e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!Exception :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	}
	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "VB Status from Response : " + status);
	String endDate = formatter.format(new Date());

}


public void setApplicationData(SCESession mySession) {

	mySession.getVariableField(IProjectVariables.SET_APPLICATION_STATUS_WS, IProjectVariables.SET_APPLICATION_STATUS_WS_FIELD_EXPECTION).setValue(true);
	mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_TRF).setValue(GlobalConstant.TRFHOSTDOWN);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String strDate ="";
	boolean exceptionFlag=true;
	try {
		Date date = new Date();
		strDate = formatter.format(date);

		String sessionId = mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_SESSION_ID).getStringValue();
		String configSetName=GlobalConstant.LANG_ENGLISH; 
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("sessionId", sessionId);
		map.put("objectId", mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue());//relid or FFP Number
		map.put("objectType", "Speaker");
		map.put("attributeName", "Country");
		map.put("data", "UAE");//language
		map.put("configSetName", GlobalConstant.CONFIGSETNAME); //configsetname
		String method = LoadApplicationProperties.getProperty("SetApplicationData", mySession);
		JSONObject object = NuanceConnector.startConnect(map, method, "",mySession);
		String responseCode = mySession.getVariableField(IProjectVariables.RESPONSE_CODE).getStringValue();
		if("200".equalsIgnoreCase(responseCode)) {
		mySession.getVariableField(IProjectVariables.SET_APPLICATION_STATUS_WS, IProjectVariables.SET_APPLICATION_STATUS_WS_FIELD_EXPECTION).setValue(false);
		}
		mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_TRF).setValue("NA");
		exceptionFlag=false;
	} catch (UnsupportedOperationException e) {
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!UnsupportedOperationException ::: " + e, mySession);
	} catch (SOAPException e) {
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!SOAPException ::: " + e, mySession);
	} catch (JSONException e) {
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!JSONException ::: " + e, mySession);
	} catch (IOException e) {
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!IOException ::: " + e, mySession);
	} catch (Exception e) {
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!Exception ::: " + e, mySession);
	}
	String endDate = formatter.format(new Date());

}

public void deleteAllEnrollSegments(SCESession mySession) {


	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_INFO, "UCID: "+mySession.getVariableField(IProjectVariables.SESSION, IProjectVariables.SESSION_FIELD_UCID).getStringValue());

	mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_EXCEPTION).setValue(true);
	mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_TRF).setValue(GlobalConstant.TRFHOSTDOWN);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String strDate ="";
	boolean exceptionFlag=true;
	String status = "FAILURE";
	String responseCode = "";
	
	Date date = new Date();
	strDate = formatter.format(date);

	HashMap<String, Object> map = new HashMap<>();
	map.put("sessionId", mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_SESSION_ID).getStringValue());
	map.put("speakerId", mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue());//FFP Number
	map.put("voiceprintTag",GlobalConstant.VOICEPRINTTAG);
	map.put("configSetName", GlobalConstant.CONFIGSETNAME);

	JSONObject StartSessionResult;
	try {
		mySession.getVariableField(IProjectVariables.VB_INPUTS, IProjectVariables.VB_INPUTS_FIELD_WS_URL).setValue(GlobalConstant.PRIMARYURL);
		// Hit Nuance End point
		String method = LoadApplicationProperties.getProperty("DeleteAllEnrollSegments", mySession);
		JSONObject object = NuanceConnector.startConnect(map, method, "",mySession);
		responseCode = mySession.getVariableField(IProjectVariables.RESPONSE_CODE).getStringValue();
//		if(object == null || responseCode == null || !responseCode.equalsIgnoreCase("200") || responseCode.isEmpty()) {
//			mySession.getVariableField(IProjectVariables.VB_INPUTS, IProjectVariables.VB_INPUTS_FIELD_WS_URL).setValue(GlobalConstant.SECONDARYURL);
//            object = NuanceConnector.startConnect(map, method, "",mySession);
//            responseCode = mySession.getVariableField(IProjectVariables.RESPONSE_CODE).getStringValue();
//            if(object == null || responseCode == null || !responseCode.equalsIgnoreCase("200") || responseCode.isEmpty()) {
//            	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "Host Down or Unable to execute api correctly using both the url ");
//            }else {
//            	mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_EXCEPTION).setValue(false);
//            	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_INFO, " Executed using Secondary URL: ");
//            	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "Executed using Secondary URL: ");
//            }
//		}
//		else {
//			if(object == null || responseCode == null || !responseCode.equalsIgnoreCase("200") || responseCode.isEmpty()) {
//            	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "Host Down or Unable to execute api correctly using both the url ");
//            }else {
//            	mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_EXCEPTION).setValue(false);
//            	mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "Executed using primary URL");
//            }
//			
//        }
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "Response Code for DeleteAllEnrollSegments: "+responseCode);
		
	} catch (UnsupportedOperationException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!UnsupportedOperationException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (SOAPException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!SOAPException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (JSONException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!JSONException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (IOException e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!IOException :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	} catch (Exception e) {
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!Exception :::" + GetStackTrace.getMessage(e));
		exceptionFlag=true;
		status = "EXCEPTION";
	}

}

}
