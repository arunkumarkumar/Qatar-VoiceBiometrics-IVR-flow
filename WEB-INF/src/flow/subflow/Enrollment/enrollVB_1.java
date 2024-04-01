package flow.subflow.Enrollment;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.General.LoadApplicationProperties;
import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;
import com.util.EncodeFileToBase64Binary;
//import com.scb.custom.HostDBLogging;
import com.util.GetStackTrace;
import com.util.GlobalConstant;
import com.util.NuanceConnector;
import flow.IProjectVariables;
import javax.xml.soap.SOAPException;

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
 * Last generated by Orchestration Designer at: 2021-FEB-19  12:32:24 PM
 */
public class enrollVB_1 extends com.avaya.sce.runtime.Data {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2021-FEB-19  12:32:24 PM
	 */
	public enrollVB_1() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}
	
	@Override
	public void requestBegin(SCESession mySession) {
		try
		{
		mySession.setProperty(GlobalConstant.ISENROLLED, "false");
		mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_VOICE_INDICATOR_STATUS).setValue(GlobalConstant.FAILURESHORTCODE);
		mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_TRF).setValue(GlobalConstant.TRFHOSTDOWN);
		/**VOICE ID SHOULD BE ELIGIBLE TILL HE COMPLETE THE ENROLLMENT PROCESS**/
		mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_VOICE_ID).setValue(GlobalConstant.ELIGIBILESHORTCODE);// ELIGIBILE
		/**This field used to update IVR DB**/
		mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_VB_ASSISTED).setValue("Enroll");
		mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_EXCEPTION).setValue(true);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(new Date());
		
		String sessionId = mySession.getVariableField(IProjectVariables.START_SESSION_WS, IProjectVariables.START_SESSION_WS_FIELD_SESSION_ID).getStringValue();
		
		String[] fileName = mySession.getVariableField(IProjectVariables.ENROLL_VOICE_PC, IProjectVariables.ENROLL_VOICE_PC_FIELD_VALUE).getStringValue().split("/");
		String audio = fileName[fileName.length-1];
		
		if(!(checkAudioWavFileName(mySession, audio))) {
		
		try {
			
			String IP = InetAddress.getLocalHost().getHostAddress();
			String URL = audio.replace("localhost", IP);
			File file = new File(URL);
			String ENC_URL = "";

			String configSetName = "";//mySession.getVariableField(IProjectVariables.CONFIG_SET_NAME, IProjectVariables.CONFIG_SET_NAME_FIELD_LANGUAGE_SPECIFIC_CONFIG_SET_NAME).getStringValue();

			String textPhrase = GlobalConstant.VOICEPHRASE;
			if(configSetName.equalsIgnoreCase(GlobalConstant.CONFIGSETNAME)) {
				textPhrase= GlobalConstant.VOICEPHRASE;
			}

			ENC_URL = EncodeFileToBase64Binary.encodeFileToBase64Binary(mySession.getAbsoluteTempDirPath().concat("/").concat(file.getName()));
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("sessionId", sessionId);
			map.put("speakerId", mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue());//mySession.getVariableField(IProjectVariables.VB_INPUTS, IProjectVariables.VB_INPUTS_FIELD_REL_ID).toString());
			map.put("voiceprintTag",GlobalConstant.VOICEPRINTTAG);
			map.put("configSetName", GlobalConstant.CONFIGSETNAME);
			map.put("audio", ENC_URL);
			map.put("text", textPhrase);

			String method = LoadApplicationProperties.getProperty("Enroll", mySession);
			JSONObject object = NuanceConnector.startConnect(map, method, "",mySession);
			JSONObject enrollResult;

			enrollResult = object.getJSONObject("soap:Envelope").getJSONObject("soap:Body")
					.getJSONObject("EnrollResponse").getJSONObject("EnrollResult");

			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "EnrollStatus: "+enrollResult.get("EnrollStatus"), mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG,"RequestId: "+ enrollResult.get("RequestId"), mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG,"AudioSegmentId: "+enrollResult.get("AudioSegmentId"), mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG,"IsSegmentValid: "+enrollResult.get("IsSegmentValid"), mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG,"Reason: "+enrollResult.get("Reason"), mySession);

			mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_AUDIO_SEGMENT_ID).setValue(enrollResult.get("AudioSegmentId"));
			mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_ENROLL_STATUS).setValue(enrollResult.get("EnrollStatus"));
			mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_IS_SEGMENT_VALID).setValue(enrollResult.get("IsSegmentValid"));
			mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_REASON).setValue(enrollResult.get("Reason"));
			mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_REQUEST_ID).setValue(enrollResult.get("RequestId"));

			if(mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_REASON).getStringValue().equalsIgnoreCase(GlobalConstant.AUDIOOK)) {
				int i = mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_AUDIO_OKCOUNT).getIntValue();
				mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_AUDIO_OKCOUNT).setValue(i+1);
			}
			int audioCount = mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_AUDIO_OKCOUNT).getIntValue();

			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "AUDIO_OKCOUNT: " +audioCount, mySession);

			if(audioCount==1) {
				mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_PLAY_PHRASE_FILE).setValue("2");
			}
			if(audioCount==2) {
				mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_PLAY_PHRASE_FILE).setValue("3");
			}

			mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_EXCEPTION).setValue(false);
			mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_TRF).setValue("NA");
			
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "VB Status from Response : " + enrollResult.get("Reason"));
			
			//VBREASON
			mySession.getVariableField(IProjectVariables.VBREASON).setValue(mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_REASON).getStringValue());
			
		}  catch (Exception ex) {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "!!!!!!IOException ::: " + GetStackTrace.getMessage(ex), mySession);
				mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_EXCEPTION).setValue(false);
				mySession.getVariableField(IProjectVariables.VBREASON).setValue("EXCEPTION");
			} 
		
		
		
		
	} else {
		mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_EXCEPTION).setValue(false);
		mySession.getVariableField(IProjectVariables.VB_CONTEXT_STORE, IProjectVariables.VB_CONTEXT_STORE_FIELD_TRF).setValue("NA");
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "***************** Audio File Name is as same of Previous sample Skip Nuance Hit ******************", mySession);
	}
		}
		catch(Exception e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "Error\t|\t enroll status check \t|\t"+ Arrays.toString(e.getStackTrace()), mySession);
		}
		super.requestBegin(mySession);
	}
	

	public boolean checkAudioWavFileName(SCESession mySession, String audioName) {
		
		boolean status = false;
		
		String previousAttemptWavFileName = mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_ENROLL_VOICE_WAV_FILE_NAME).getStringValue();
		
		if(!previousAttemptWavFileName.equalsIgnoreCase("NA")) {
			if(audioName.equalsIgnoreCase(previousAttemptWavFileName)) {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "Previous Audio File Name: "+previousAttemptWavFileName+"are same as Current Audio File Name: "+audioName, mySession);
				status = true;
			} else {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "Audio File Name is unique : " + audioName, mySession);
			}
		} else {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_DEBUG, "Skip Audio File Name Verify due to 1st Attempt of Voice Sample Collect", mySession);
		}
		
		mySession.getVariableField(IProjectVariables.ENROLL_WS, IProjectVariables.ENROLL_WS_FIELD_ENROLL_VOICE_WAV_FILE_NAME).setValue(audioName);
		
		return status;
	}

	/**
	 * Returns the Next item which will forward application execution
	 * to the next form in the call flow.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:37 AM
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

		if(((com.avaya.sce.runtime.Condition)new com.avaya.sce.runtime.Condition("condition1", "EnrollWS:exception", com.avaya.sce.runtime.Expression.IS_TRUE).setDebugId(283)).evaluate(mySession)) {
			actions = new java.util.ArrayList(1);
			actions.add(new com.avaya.sce.runtime.Next("Enrollment-networkfailureannouncement", "hostDownRexception").setDebugId(284));
			if(evaluateActions(actions, mySession)) {
				return true;
			}
			actions = null;

		}


		if(((com.avaya.sce.runtime.Condition)new com.avaya.sce.runtime.Condition("condition2", "EnrollWS:enrollCount", com.avaya.sce.runtime.Expression.INT_LESS_THAN_EQUAL, "5", false).setDebugId(285)).evaluate(mySession)) {
			actions = new java.util.ArrayList(1);
			actions.add(new com.avaya.sce.runtime.varoperations.Increment("EnrollWS:enrollCount").setDebugId(286));
			if(evaluateActions(actions, mySession)) {
				return true;
			}
			actions = null;

			if(((com.avaya.sce.runtime.Condition)new com.avaya.sce.runtime.Condition("condition3", "EnrollWS:audioOKCount", com.avaya.sce.runtime.Expression.INT_EQUAL, "3", false).setDebugId(287)).evaluate(mySession)) {
				actions = new java.util.ArrayList(1);
				actions.add(new com.avaya.sce.runtime.Next("Enrollment-completeTrain", "trainVoice").setDebugId(288));
				if(evaluateActions(actions, mySession)) {
					return true;
				}
				actions = null;

			}


			if(((com.avaya.sce.runtime.Condition)new com.avaya.sce.runtime.Condition("condition4", "EnrollWS:reason", com.avaya.sce.runtime.Expression.STRING_EQUAL_IGNORE, "AudioOk", false).setDebugId(289)).evaluate(mySession)) {
				actions = new java.util.ArrayList(2);
				actions.add(new com.avaya.sce.runtime.tracking.TraceInfo(com.avaya.sce.runtimecommon.ITraceInfo.TRACE_LEVEL_DEBUG, "Collect Next Sample", "").setDebugId(290));
				actions.add(new com.avaya.sce.runtime.Next("Enrollment-enrollcountcheck", "collectSample").setDebugId(291));
				if(evaluateActions(actions, mySession)) {
					return true;
				}
				actions = null;

			} else {

				if(((com.avaya.sce.runtime.Condition)new com.avaya.sce.runtime.Condition("condition5", "EnrollWS:enrollWrongPhraseCount", com.avaya.sce.runtime.Expression.INT_GREATER_THAN, "2", false).setDebugId(293)).evaluate(mySession)) {
					actions = new java.util.ArrayList(1);
					actions.add(new com.avaya.sce.runtime.Next("Enrollment-menunameenroll", "wrongPhraseTriesExceeded").setDebugId(294));
					if(evaluateActions(actions, mySession)) {
						return true;
					}
					actions = null;

				}

				actions = new java.util.ArrayList(2);
				actions.add(new com.avaya.sce.runtime.varoperations.Increment("EnrollWS:enrollWrongPhraseCount").setDebugId(295));
				actions.add(new com.avaya.sce.runtime.Next("Enrollment-menunameenroll", "wrongPhrase").setDebugId(296));
				if(evaluateActions(actions, mySession)) {
					return true;
				}
				actions = null;
			}


		} else {
			actions = new java.util.ArrayList(1);
			actions.add(new com.avaya.sce.runtime.Next("Enrollment-maxtriesannounce", "maxTriesExceeded").setDebugId(298));
			if(evaluateActions(actions, mySession)) {
				return true;
			}
			actions = null;
		}


		// return false if the evaluation of actions did not cause a servlet forward or redirect
		return false;
	}
}
