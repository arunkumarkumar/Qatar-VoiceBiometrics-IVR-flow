package com.util;

import java.util.HashMap;

import com.util.GlobalConstant;


public class GlobalConstant {
	
	private GlobalConstant(){}
	
	//Token Generation details
	public static String TOKEN ="NA";
	//time format
	//yyyy-MM-dd HH:mm:ss
	public static String TOKEN_GENERATED_TIME="";
	public static final String TOKEN_VALIDITY_TIME_HOURS="03";
	public static final String TOKEN_VALIDITY_TIME_MINUTES="30";
	/**File Extensions*/
	public static final String EXTENSION_WAVE = ".wav";
	
	public static final String NA = "NA";
	public static final String NA_COLON = "NA:NA:NA";
	public static final String	SEPRATOR_COMMA = ",";
	public static String wsNamespace = "";
	
	//Customer Details
	public static final String FFPNumber = "FFPNumber";
	public static final String Email = "Email";
	public static final String CountryCode = "CountryCode";
	public static final String MemberTier = "MemberTier";
	public static final String CustomerTier = "CustomerTier";
	public static final String PreferredLanguage = "PreferredLanguage";
	public static final String PhoneNumber = "PhoneNumber";
	public static final String FirstName = "FirstName";
	public static final String CustomerName = "CustomerName";
	public static final String agent_id = "agent_id";
	public static final String VB_STATUS = "VB_Status";
	public static final String VB_STATUSDATE = "VB_Status_Date";
	public static final String CustomerNumber = "CustomerNumber";
	
	public static final String FFP = "FFP";
	
	
	public static final String VB_Status = "VB_Status";
	public static final String VB_Status_Date = "VB_Status_Date";
	public static final String VB_Enrollment_Failed = "VB_Enrollment_Failed";
	public static final String Verified_via_vb = "Verified_via_vb";
	public static final String De_enrolled_caller_from_Vb = "enrolled_caller_from_Vb";
	public static final String VB_enrollment_status = "VB_enrollment_status";
	public static final String VB_enrollment_status_date = "VB_enrollment_status_date";
	public static final String VB_verification_status = "VB_verification_status";
	public static final String VB_verification_status_date = "VB_verification_status_date";
	
	public static final String IVR = "IVR";
	public static final String AGENT_ASSIST = "AGENT_ASSIST";
	
	
	//boolean
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	
	/**String Constants*/
	public static final String SEPARATOR_EMPTY="";
	public static final String SEPARATOR_SPACE=" ";
	public static final String SEPARATOR_COMMA = ",";
	public static final String SEPARATOR_SINGLE_INVERTED_COMMA = "'";
	public static final String SEPARATOR_UNDERSCORE = "_";
	public static final String SEPARATOR_PLUS = "+";
	public static final String SEPARATOR_PIPE = "|";
	public static final String SEPARATOR_PIPE_WITH_SPACE = " | ";
	public static final String SEPARATOR_FORWARD_SLASH = "/";
	public static final String SEPARATOR_HYPHEN = "-";
	public static final String SEPARATOR_COLON = ":";
	public static final String SEPARATOR_PARENTHESIS_OPEN = "(";
	public static final String SEPARATOR_PARENTHESIS_CLOSE = ")";
	public static final String SEPARATOR_QUESTION_MARK = "?";
	public static final String SEPARATOR_NEXT_LINE = "\n";
	public static final String SEPARATOR_AT_THE_RATE = "@";
	public static final String SEPARATOR_EQUALS = "=";
	public static final String SEPARATOR_ASRERISK = "*";
	public static final String SEPARATOR_DOT = ".";
	
	public static final String VAR_COMPLEX_PHRASEVAR = "phrasevar";
	public static final String VAR_COMPLEX_PHRASEVAR_FIELD = "ph";
	public static final String DTMF_PROMPT = "univ-Press-";
	
	public static final String EVENT_NI = "NOINPUT";
	public static final String EVENT_NM = "NOMATCH";
	public static final String EVENT_EXCEED = "EXCEED";
	
	public static final String KEY_MENU_ROUTINE_1 = "MR1";
	public static final String KEY_MENU_ROUTINE_2 = "MR2";
	
	
	
	public static final String EXCEPTION_START = " | $$$$$$$$$$$$ CAUGHT EXCEPTION ";
	public static final String EXCEPTION_END = " $$$$$$$$$$$$ ";
	
	public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_2 = "yyyyMMddkkmmss";
	public static final String DATE_FORMAT_3 = "dd-MM-yyyy HH:mm:ss";
	public static final String DATE_FORMAT_4 = "MM-dd-yyyy HH:mm:ss";
	public static final String DATE_FORMAT_5 = "yyyyMMdd";
	
	
	/**DMENU return codes*/
	public static final String RETURN_CODE_ERROR = "ERROR";
	public static final String RETURN_CODE_SUCCESS = "SUCCESS";
	public static final String RETURN_CODE_NOMATCH = "NOMATCH";
	public static final String RETURN_CODE_NEGATIVECONF = "NEGATIVECONF";
	public static final String RETURN_CODE_NOINPUT = "NOINPUT";
	public static final String YES = "YES";
	public static final String NO = "NO";

	public static final String INPUT_DEFAULT = "DEFAULT";
	
	public static String AT = "Agent_Transfer";
	public static String DS = "Disconnect";
	public static String LD = "Link_Down";
	public static String RMN = "MNU~RMN";
	public static String NRMN = "MNU~NRMN";
	public static String RMNAUTH = "RMNAUTH";
	
	
	public static final String BOOLEAN_TRUE = "true";
	public static final String BOOLEAN_FALSE = "false";
	
	public static final String CUSTOMERDEMOGRAPHIC = "customerDemographic";
	public static final String ISAUTHENTICATE = "isAuthenticate";
	public static final String ISRMN = "isRMN";
	public static final String MENUTRANSFER = "menuTransfer";
	public static final String LOGPATH = "logPath";
	public static final String SAVINGSACCOUNTAVAIL = "savingsAccountAvail";
	public static final String TDACCOUNTAVAIL = "tdAccountAvail";
	public static final String STANDARDAUDIOLOCATION = "standardAudioLocation";

	public static final String TRFHOSTDOWN = "HOSTDOWN";

	public static final String PRIMARY = "PRIMARY";
	public static final String SECONDARY = "SECONDARY";
	
	public static String PRESS = "PRE_00";
	public static String PRESS_ZERO = "PRE_ZERO_001";
	public static String PRESS_HASH = "PRE_HASH_002";
	public static String PRESS_STAR = "PRE_STAR_001";
	public static String EX = "Exception";
	


	public static String PRFLANG = "prefLang";
	public static String INTENT = "INTENT";

	//VB
	public static String PRIMARYURL="";
	//public static String SECONDARYURL="";
	public static String NUANCE_WINDOWS_SERVER_USERNAME ="";
	public static String NUANCE_WINDOWS_SERVER_PASSWORD ="";
	public static String NUANCE_WINDOWS_SERVER_DOMAIN ="";
	public static String NUANCE_WSNAMESPACE="";

	public static String CONFIGSETNAME = "QA_VP_EN";
	
	public static String VOICEPHRASE = "my voice is my password";
	public static String VOICEPRINTTAG = "IVRLANGUAGE";
	public static String AUDIOOK = "AudioOK";
	public static String AUDIOLOCATION = "audioLocation"; 
	
	public static String SEGMENTCODE60 = "60";
	public static String SEGMENTCODE61 = "61";
	public static String SEGMENTCODE65 = "65";
	public static String SEGMENTCODE66 = "66";
	public static String SEGMENTCODE57 = "57";
	
	public static String LANGUAGE = "language"; 
	public static String LANG_ENGLISH = "English";
	public static String LANG_CANTONESE = "Cantonese";
	public static String LANG_MANDARIN = "Mandarin";
	
	public static String RELID = "relID";
	public static String SEGMENTCODE = "segmentCode";
	public static String CALLBACK = "callBack";
	public static String FLOWNAME = "flowName";
	public static String NEXTNODE = "nextNode";
	public static String MAINMENU = "mainMenu";
	public static String DISCONNECT = "disConnect";
	public static String AGENTTRANSFER = "agentTransfer";
	public static String TRF = "TRF";
	public static String VOICEID = "voiceID";
	public static String VOICEINDICATORSTATUS = "voiceIndicatorStatus";
	public static String ISENROLLED = "IsEnrolled";
	public static String ISAUTHENTICATED = "IsAuthenticated";
	public static String INCONCLUSIVE = "InConclusive";
	public static String VBASSISTED = "VBASSISTED";
	
	public final static String ENROLLMENT = "Enrollment";
	public final static String VERIFY = "Verify";
	public final static String AGENTDRIVENENROLLMENT = "AgentdrivenEnrollment";
	public final static String AGENTDRIVENENVERIFY = "AgentdrivenVerify";
	public final static String AGENTDRIVENCONFERENCEVERIFY = "AgentdrivenConferenceVerify";
	public final static String AGENTDRIVENENDEREGISTER = "AgentdrivenDeregister";
	public final static String AGENTDRIVENENUNLOCK = "AgentdrivenUnlock";
	public final static String VBSTATUS = "VBStatus";
	
	/**TRF Short Code**/
	public static HashMap<String, String> authenticateResponseMap = null;
	
	public static String SUCCESSSHORTCODE = "SC";
	public static String FAILURESHORTCODE = "FA";
	
	public final static String SUCCESS = "Success";
	public final static String FAILURE = "Failure";
	
	/** Short Code**/
	public static String COOLINGPERIODSHORTCODE = "CPD";
	public static String LOCKEDSHORTCODE = "LOC";
	public static String ELIGIBILESHORTCODE = "ELG";
	public static String ENROLLEDSHORTCODE = "ENR";
	public static String OPTOUTSHORTCODE = "NEO";
	
	/**  VB Status IN DB  **/
	public final static String ENROLL = "Enroll";
	public final static String VERIFICATION = "Verification";
	public final static String CONSENT = "Consent";
	public final static String LATER = "LATER";
	public static String LOCK = "Lock";
	
	/**TRF Short Code**/
	public static String TRFAUTHMAXTRIES = "Authen MaxTries";
	public static String TRFENROLLMAXTRIES = "Enroll MaxTries";
	
	/** VB Deregister **/
	public static String LOCKED_DEREGISTER = "LOCKED";
	public static String ENROLLED_DEREGISTER = "Enrolled";
	
	/** VoiceID Indicator Status Short Code **/
	public static HashMap<String, String> enrollResponseMap = null;	
	public static void putEnrollResponseShortCode () {
	enrollResponseMap.put("NS","NotSet");
	enrollResponseMap.put("IE","InternalError");
	enrollResponseMap.put("AO","AudioOK");
	enrollResponseMap.put("NEA","NotEnoughAudio");
	enrollResponseMap.put("IA","InvalidAudio");
	enrollResponseMap.put("ATSH","AudioTooShort");
	enrollResponseMap.put("ATSF","AudioTooSoft");
	enrollResponseMap.put("ATL","AudioTooLoud");
	enrollResponseMap.put("ATN","AudioTooNoisy");
	enrollResponseMap.put("PBI","PlaybackIndication");
	enrollResponseMap.put("WPP","WrongPassphrase");
	enrollResponseMap.put("MSD","MultiSpeakersDetected");
	enrollResponseMap.put("SSD","SyntheticSpeechDetected");
	enrollResponseMap.put("FI","FraudsterIndication");
	
	/**Yet to update in Context store sheet**/
	enrollResponseMap.put("NRTT","NotReadyToTrain");
	enrollResponseMap.put("INC","Inconsistency");
	enrollResponseMap.put("TD","ToneDetected");		
	enrollResponseMap.put("UDT","UnreliableDecisionThresholds");		
	enrollResponseMap.put("FDL","FraudDetectionLogic");			
	enrollResponseMap.put("VPI","VoiceprintInconclusive");		
	enrollResponseMap.put("VPM","VoiceprintMatch");		
	enrollResponseMap.put("VPMM","VoiceprintMismatch");
	enrollResponseMap.put("FA","FA");
	
	}	
	public static String getEnrollResponseShortCode (String key) {
	if(enrollResponseMap == null) {
	enrollResponseMap = new HashMap<>();
	GlobalConstant.putEnrollResponseShortCode();
	}
	return enrollResponseMap.get(key);
	}	
	
//	public static String getAuthenticateResponseShortCode (String key) {
//		if(authenticateResponseMap == null) {
//			authenticateResponseMap = new HashMap<>();
//			GlobalConstant.putAuthenticateResponseShortCode();
//		}
//		return authenticateResponseMap.get(key);
//	}
//	
//	
//	public static void putAuthenticateResponseShortCode () {
//		authenticateResponseMap.put("AudioOK","AO");
//		authenticateResponseMap.put("AudioTooLoud","ATL");
//		authenticateResponseMap.put("AudioTooNoisy","ATN");
//		authenticateResponseMap.put("AudioTooShort","ATSH");
//		authenticateResponseMap.put("AudioTooSoft","ATSF");
//		authenticateResponseMap.put("InternalError","IE");
//		authenticateResponseMap.put("InvalidAudio","IA");
//		authenticateResponseMap.put("NotSet","NS");
//		authenticateResponseMap.put("NotEnoughAudio","NEA");
//		authenticateResponseMap.put("MultiSpeakersDetected","MSD");
//		authenticateResponseMap.put("SyntheticSpeechDetected","SSD");
//		authenticateResponseMap.put("ToneDetected","TD");
//		authenticateResponseMap.put("FraudsterIndication","FI");
//		authenticateResponseMap.put("UnreliableDecisionThresholds","UDT");
//		authenticateResponseMap.put("FraudDetectionLogic","FDL");
//		authenticateResponseMap.put("PlaybackIndication","PBI");
//		authenticateResponseMap.put("VoiceprintInconclusive","VPI");
//		authenticateResponseMap.put("VoiceprintMatch","VPM");
//		authenticateResponseMap.put("VoiceprintMismatch","VPMM");
//		authenticateResponseMap.put("WrongPassphrase","WPP");
//	}

}

