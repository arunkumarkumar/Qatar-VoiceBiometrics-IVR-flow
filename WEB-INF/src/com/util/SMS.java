package com.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import com.General.ApiCallFromExtJar;
import com.General.AppConstants;
import com.General.LoadApplicationProperties;
import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import flow.IProjectVariables;

public class SMS {
	
	
public void VBPreferEmail(SCESession mySession) {
		
	try {
		ApiCallFromExtJar apiCall = new ApiCallFromExtJar();
		HashMap<String,String>  App_Prop = new HashMap<String, String>();
		App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
		
		String FFPNumber = parseString(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue(), mySession);
		
		
			String requestBody = App_Prop.get("verifyemail").toString();
			requestBody = requestBody.replace("{FFPNumber}", FFPNumber);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t Request framed  \t|\t"+ requestBody,mySession);
			//apiCall.VBPreferEmail(mySession, requestBody);
			Boolean response = mySession.getVariableField("QA_WebService","Success").getBooleanValue();
			if(response) {
				HashMap<String,String> customerDetails = new HashMap<String,String>();
				customerDetails = (HashMap<String, String>) mySession.getVariableField("HashMap", "customerDetails").getObjectValue();
				
				String mobile = mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).getStringValue();
				if(mobile !=null && !"null".equalsIgnoreCase(mobile) && !"NA".equalsIgnoreCase(mobile) && !"".equalsIgnoreCase(mobile.trim())) {
					//checkCountryCode(mobile, mySession);
					mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).setValue("NA");
					mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).setValue("NA");
					customerDetails.put("Email",mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_EMAIL).getStringValue());
					customerDetails.put("Mobile",mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).getStringValue());

					if(mobile.contains("+")) {
						mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "+ is already present in mobile number obtained from VBPreferEmail api response");
						apiCall.checkCountryCode(mobile, mySession);
						
					}else {
						mobile = "+"+mobile;
						mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "+ is appened to mobile number obtained from VBPreferEmail api response");
						apiCall.checkCountryCode(mobile, mySession);
					}
					
					HashMap<String,String> Response =(HashMap<String, String>) mySession.getVariableField("MobileNumber").getObjectValue();
					String countryCode = Response.get("CountryCode");
					mobile =  Response.get("PhoneNumber");
					mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).setValue(countryCode);
					mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).setValue(mobile);
					
					customerDetails.put(GlobalConstant.CountryCode,mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).getStringValue());
					customerDetails.put("Mobile",mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).getStringValue());
					mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "Customer details updated for email , mobile and country code after  VBPreferEmail api and set in IVR : "+customerDetails.toString());
				}else {
					mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).setValue("NA");
					mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).setValue("NA");
				}

			}else {
				mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "VBPreferEmail api failed so sms and email will not be sent");
				mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).setValue("NA");
				mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).setValue("NA");
				mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_EMAIL).setValue("NA");
			}
			
			
	}
	catch(Exception e) {

		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "SMS"+"\t|\t"+"exception in VBPreferEmail unable to retrieve email and mobile number "+"\t|\t"+ Arrays.toString(e.getStackTrace()), mySession);
		mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR, "so sms and email will not be sent");
		mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).setValue("NA");
		mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).setValue("NA");
		mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_EMAIL).setValue("NA");
	}
	
	}

//	public void sendSMS(String message,SCESession mySession) {
//		
//		ApiCallFromExtJar apiCall = new ApiCallFromExtJar();
//		
//		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tsms to be sent  \t|\t"+ message,mySession);
//		
//		String FFPNumber = parseString(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue(), mySession);
//		String countryCode = parseString(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).getStringValue(), mySession);
//		String PhoneNumber = parseString(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).getStringValue(), mySession);
//		
//		
//		String[] phonenumbers = null;
//		HashMap<String,String>  App_Prop = new HashMap<String, String>();
//		App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
//		
//		if(!"".equalsIgnoreCase(PhoneNumber) && PhoneNumber !=null && !"NA".equalsIgnoreCase(PhoneNumber) && !"null".equalsIgnoreCase(PhoneNumber)){
//			if(PhoneNumber.contains(",")) {
//				phonenumbers = PhoneNumber.split("[,]");
//			}else {
//				phonenumbers = new String[1];
//				phonenumbers[0]=PhoneNumber;
//			}
//			
//			int totalNumberofmobiles= phonenumbers.length;
//			if("true".equalsIgnoreCase(App_Prop.get("SMS_TO_ALL")!=null?App_Prop.get("SMS_TO_ALL").toString():"false")) {
//				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"true",mySession);
//				totalNumberofmobiles= phonenumbers.length;
//			}else {
//				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"false",mySession);
//				totalNumberofmobiles = 1;
//			}
//			
//			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tNumber of sms to be sent \t|\t"+ totalNumberofmobiles,mySession);
//			for(int mobilecount=0;mobilecount<totalNumberofmobiles;mobilecount++) {
//				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t sms to be sent to  \t|\t"+ phonenumbers[mobilecount],mySession);
//				
//				if("true".equalsIgnoreCase(App_Prop.get("COUNTRYCODESPLIT"))) {
//					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tcountry code needs to splited from mobile numbers  \t|\t",mySession);
//					apiCall.checkCountryCode(phonenumbers[mobilecount], mySession);
//					HashMap<String,String> Response = (HashMap<String, String>) mySession.getVariableField("MobileNumber").getObjectValue();
//					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tResponse obtained  \t|\t"+Response.toString(),mySession);
//					phonenumbers[mobilecount] = Response.get("PhoneNumber");
//					countryCode = Response.get("CountryCode");
//					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tcountry code obtaianed : \t|\t"+countryCode,mySession);
//					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tPhoneNumber obtained : \t|\t"+phonenumbers[mobilecount],mySession);
//				}else {
//					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tcountry code not to splited from mobile numbers. It is obtained from response \t|\t",mySession);
//				}
//				
//				if(phonenumbers[mobilecount].contains("[+]")  || countryCode.contains("[+]")) {
//					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t + is present in the number  \t|\t",mySession);
//				}else {
//					phonenumbers[mobilecount]="+"+phonenumbers[mobilecount];
//					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t + is appened to the number  \t|\t",mySession);
//				}
//			String requestBody = App_Prop.get("SMS_CONTENT").toString();
//			requestBody = requestBody.replace("{CountryCode}", countryCode);
//			requestBody = requestBody.replace("{MobileNumber}", phonenumbers[mobilecount]);
//			requestBody = requestBody.replace("{VBStatus}", message);
//			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t Request framed  \t|\t"+ requestBody,mySession);
//			apiCall.sendSMS(mySession, requestBody);	
//			}
//			
//			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tNumber of sms sent  \t|\t"+ phonenumbers.length,mySession);
//		}else {
//			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t PhoneNumber not found or found to be empty \t|\t",mySession);
//		}
//	}
//	
//	public void sendEmail(String message,SCESession mySession) {
//		
//		ApiCallFromExtJar apiCall = new ApiCallFromExtJar();
//		
//		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t ID to be sent as email is   \t|\t"+ message,mySession);
//		
//		String FFPNumber = mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue();
//		String email = mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_EMAIL).getStringValue();
//		String[] emails = null;
//		HashMap<String,String>  App_Prop = new HashMap<String, String>();
//		App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
//
//		if(!"".equalsIgnoreCase(email) && email !=null && !"NA".equalsIgnoreCase(email) && !"null".equalsIgnoreCase(email)){
//			if(email.contains(",")) {
//				emails = email.split("[,]");
//			}else {
//				emails = new String[1];
//				emails[0]=email;
//			}
//			int totalNumberofemails= emails.length;
//			if("true".equalsIgnoreCase(App_Prop.get("EMAIL_TO_ALL")!=null?App_Prop.get("EMAIL_TO_ALL").toString():"false")) {
//				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"true",mySession);
//				totalNumberofemails= emails.length;
//			}else {
//				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"false",mySession);
//				totalNumberofemails = 1;
//			}
//			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tNumber of emails numbers to be sent \t|\t"+ totalNumberofemails,mySession);		
//			for(int emailCount=0;emailCount<totalNumberofemails;emailCount++) {
//				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t email to be sent to  \t|\t"+ emails[emailCount],mySession);
//				
//			String requestBody = App_Prop.get("EMAIL_CONTENT").toString();
//			requestBody = requestBody.replace("{email}", emails[emailCount]);
//			requestBody = requestBody.replace("{subject}", "SUBJECT-NA");
//			requestBody = requestBody.replace("{id}", message);
//			requestBody = requestBody.replace("{body}", "SMS CONTENT-NA");
//			requestBody = requestBody.replace("{ffp}", mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue());
//			requestBody = requestBody.replace("{name}", mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_NAME).getStringValue());
//			requestBody = requestBody.replace("{date}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
//			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t Request framed for email \t|\t"+ requestBody,mySession);
//			apiCall.sendMail(mySession, requestBody);	
//			}
//			
//			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tNumber of emails sent  \t|\t"+ emails.length,mySession);
//		}else {
//			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t Email not found or found to be empty  \t|\t",mySession);
//		}
//	}
	
	
	
	public void sendSMS(String message,SCESession mySession) {
		try {
		
		ApiCallFromExtJar apiCall = new ApiCallFromExtJar();
		
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\tsms to be sent  \t|\t"+ message,mySession);
		
		String FFPNumber = parseString(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue(), mySession);
		String countryCode = parseString(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).getStringValue(), mySession);
		String PhoneNumber = parseString(mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).getStringValue(), mySession);
		HashMap<String,String>  App_Prop = new HashMap<String, String>();
		App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
		if("".equalsIgnoreCase(countryCode.trim()) || "NA".equalsIgnoreCase(countryCode) || "null".equalsIgnoreCase(countryCode) || countryCode ==null) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t sms not sent due to invalid country code  \t|\t",mySession);
		}else {
			if("".equalsIgnoreCase(PhoneNumber.trim()) || "NA".equalsIgnoreCase(PhoneNumber) || "null".equalsIgnoreCase(PhoneNumber) || PhoneNumber ==null) {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t sms not sent due to invalid phone number  \t|\t",mySession);
			}else {
				if(!countryCode.contains("+")) {
					countryCode = "+"+countryCode;
				}
					String requestBody = App_Prop.get("SMS_CONTENT").toString();
					requestBody = requestBody.replace("{CountryCode}", countryCode);
					requestBody = requestBody.replace("{MobileNumber}", PhoneNumber);
					requestBody = requestBody.replace("{VBStatus}", message);
					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t Request framed  \t|\t"+ requestBody,mySession);
					apiCall.sendSMS(mySession, requestBody);
			}
		}
			
		}
		catch(Exception e) {

			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "SMS"+"\t|\t"+"exception in send SMS unable to send sms"+"\t|\t"+ Arrays.toString(e.getStackTrace()), mySession);

		}
		
	}
	
	public void sendEmail(String message,SCESession mySession) {
		try {
		ApiCallFromExtJar apiCall = new ApiCallFromExtJar();
		
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t ID to be sent as email is   \t|\t"+ message,mySession);
		
		String FFPNumber = mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue();
		String email = mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_EMAIL).getStringValue();
		if("".equalsIgnoreCase(email.trim()) || "NA".equalsIgnoreCase(email) || "null".equalsIgnoreCase(email) || email ==null) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t email not sent due to invalid email  \t|\t"+ message,mySession);
		}else {
			HashMap<String,String>  App_Prop = new HashMap<String, String>();
			App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
					
				String requestBody = App_Prop.get("EMAIL_CONTENT").toString();
				
				if("NA".equalsIgnoreCase(App_Prop.get("mockemail").toString())) {
					requestBody = requestBody.replace("{email}", email);
					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t actual email  \t|\t",mySession);
				}else {
					requestBody = requestBody.replace("{email}", App_Prop.get("mockemail").toString());
					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t mock email  \t|\t",mySession);
				}
				
				if("vb_enr_success".equalsIgnoreCase(message))  {
					requestBody = requestBody.replace("{subject}", "VB Enrollment Success");	
				}else if("vb_enr_failed".equalsIgnoreCase(message)) {
					requestBody = requestBody.replace("{subject}", "VB Enrollment Failed");
				}
				
				requestBody = requestBody.replace("{id}", message);
				//requestBody = requestBody.replace("{body}", "SMS CONTENT-NA");
				requestBody = requestBody.replace("{ffp}", mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_FFPNUMBER).getStringValue());
				requestBody = requestBody.replace("{name}", mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_NAME).getStringValue());
				//requestBody = requestBody.replace("{date}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO,"\t|\t Request framed for email \t|\t"+ requestBody,mySession);
				apiCall.sendMail(mySession, requestBody);	
		}
		
		
		}
		
	catch(Exception e) {

		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "SMS"+"\t|\t"+"exception in send email unable to send email "+"\t|\t"+ Arrays.toString(e.getStackTrace()), mySession);

	}
			
	}
	
	public void checkCountryCode(String CLID,SCESession mySession) {

		mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).setValue("NA");
		mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).setValue("NA");
		
		HashMap<String,String> customerDetails = new HashMap<String,String>();
		customerDetails = (HashMap<String, String>) mySession.getVariableField("HashMap", "customerDetails").getObjectValue();
		
		String method = "checkCountryCode";
		String className = "checkCountryCode";


		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "Start", mySession);

		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		String countryCode ="";
		String phInput = "";
		HashMap<String,String> Response = new HashMap<String,String>();
		String AppServer = "";
		HashMap<String,String> CallHistory = new HashMap<String,String>();

		CallHistory = (HashMap<String, String>) mySession.getVariableField("HashMap", "CallHistory").getObjectValue();
		String appIP = CallHistory.get("VC_APP_SRVR_IP");
		String UKLocation = LoadApplicationProperties.getProperty("UKLocation", mySession);
		String QDCLocation = LoadApplicationProperties.getProperty("QDCLocation", mySession);
		String AMDLocation = LoadApplicationProperties.getProperty("AMDLocation", mySession);

		if(UKLocation.contains(appIP)) {
			AppServer = "UK";
		} else if(QDCLocation.contains(appIP)) {
			AppServer = "QDC";
		} else if(AMDLocation.contains(appIP)) {
			AppServer = "AMD";
		}

		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "Actual CLID | "+CLID, mySession);
		try {

			if(CLID.contains("+")) {

				PhoneNumber ph = phoneUtil.parse(CLID, "");

				phInput = String.valueOf(ph.getNationalNumber());
				countryCode = "+" + String.valueOf(ph.getCountryCode());

			} else {

				if(CLID.startsWith("0")) {

					CLID = CLID.replaceFirst("0", "");
					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "0 Replaced CLID | " + CLID, mySession);

				}

				if(AppServer.equalsIgnoreCase("UK")) {

					if(CLID.startsWith("0")) {

						CLID = CLID.replaceFirst("0", "");
						TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "0 Replaced CLID | UK | " + CLID, mySession);

					}

					String newCLID = "+" + CLID;
					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "newCLID | UK | " + newCLID, mySession);

					PhoneNumber ph2 = phoneUtil.parse(newCLID, "");

					phInput = String.valueOf(ph2.getNationalNumber());
					countryCode = "+" + String.valueOf(ph2.getCountryCode());

				} else if(AppServer.equalsIgnoreCase("QDC")) {

					if(CLID.startsWith("0")) {

						CLID = CLID.replaceFirst("0", "");
						TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "0 Replaced CLID | QDC| " + CLID, mySession);

						String newCLID = "+" + CLID;
						TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "newCLID | QDC | " + newCLID, mySession);

						PhoneNumber ph2 = phoneUtil.parse(newCLID, "");

						phInput = String.valueOf(ph2.getNationalNumber());
						countryCode = "+" + String.valueOf(ph2.getCountryCode());

					} else {

						countryCode = "+974";
						phInput = CLID;
						TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "newCLID | QDC | " + countryCode+phInput, mySession);

					}
					
				} else if(AppServer.equalsIgnoreCase("AMD")) {

					if(CLID.length() == 10) {

						System.out.println("Market is India");
						countryCode = "+91";
						phInput = CLID;
						TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "newCLID | AMD | " + countryCode+phInput, mySession);

					} else {

						String newCLID = "+" + CLID;
						TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "newCLID | AMD | " + newCLID, mySession);

						PhoneNumber ph2 = phoneUtil.parse(newCLID, "");

						phInput = String.valueOf(ph2.getNationalNumber());
						countryCode = "+" + String.valueOf(ph2.getCountryCode());

					}

				}

			}

			Response.put("PhoneNumber", phInput);
			Response.put("CountryCode", countryCode);

			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "phInput | "+phInput, mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "countryCode | "+countryCode, mySession);

			mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).setValue(countryCode);
			mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).setValue(phInput);
			
			
			
			customerDetails.put(GlobalConstant.CountryCode,mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_COUNTRYCODE).getStringValue());
			customerDetails.put("Mobile",mySession.getVariableField(IProjectVariables.CUSTOMERDETAILS,IProjectVariables.CUSTOMERDETAILS_FIELD_MOBILE).getStringValue());
			
	//		mySession.getVariableField("MobileNumber").setValue(Response);

		} catch(Exception e) {

			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, className+"\t|\t"+method+"\t|\t"+ AppConstants.EXCEPTION_1+e.getMessage()+AppConstants.EXCEPTION_2 , mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, className+"\t|\t"+method+"\t|\t"+ Arrays.toString(e.getStackTrace()), mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, className+"\t|\t"+method+"\t|\t"+ "CountrCode method failed due to an exception so mobile and country code is set as NA", mySession);

		}

		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, className +"\t|\t"+ method +"\t|\t"+ "End", mySession);

	
	}
	

	public String parseString(String key,SCESession mySession) {

		try 
		{
		String str = "NA";
		String value;

		if(key!=null) {

			value = key;

			if(value!=null && !value.isEmpty() && value.trim()!="null") {
				str = value.trim();
			}

		} else {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR,"parseStirngResponse | EXCEPTION | parseStringResponse | key is null or empty | "+key, mySession);
		}
		
		return str;
		}
		catch(Exception ex) {
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_ERROR,"Exception caught while checking the value:"+key+" "+GetStackTrace.getMessage(ex));
		}
		return null;
	}
	
	
	public static void main(String[] args) {
        // Assuming you have a JSON response stored in a string
        //String jsonResponse = "{\"CountryCode\": \"Hello, your mobile number is {mobile_number}.\", \"other_data\": \"some other data\"}";
        String jsonResponse = "{\"CountryCode\": \"{CountryCode}\", \"MobileNumber\": \"{MobileNumber}\", \"VBStatus\": \"{VBStatus}\"}";


        String countryCodeValue = "91";
        String mobileNumberValue = "989";
        String vbStatusValue = "EN";

        // Replace placeholders with actual values
        jsonResponse = jsonResponse.replace("{CountryCode}", countryCodeValue);
        jsonResponse = jsonResponse.replace("{MobileNumber}", mobileNumberValue);
        jsonResponse = jsonResponse.replace("{VBStatus}", vbStatusValue);
        
        // Print the updated JSON string
        System.out.println(jsonResponse);
        
        String s = "{ \"recipient\": \"testing.r@gmail.com\",  \"subject\": \"Email Testing\",  \"templateId\": \"notification\",  \"body\": \"Testing Mail\",  \"parameters\": {    \"ffp\": \"\",\"name\": \"\",\"date\": \"\"}}";
    }

	
}
