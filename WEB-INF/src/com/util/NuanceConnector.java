package com.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


//import jakarta.xml.soap.MessageFactory;
//import jakarta.xml.soap.MimeHeaders;
//import jakarta.xml.soap.SOAPBody;
//import jakarta.xml.soap.SOAPConnectionFactory;
//import jakarta.xml.soap.SOAPConstants;
//import jakarta.xml.soap.SOAPElement;
//import jakarta.xml.soap.SOAPEnvelope;
//import jakarta.xml.soap.SOAPException;
//import jakarta.xml.soap.SOAPMessage;
//import jakarta.xml.soap.SOAPPart;

import javax.xml.soap.SOAPException;
import org.json.JSONException;
import org.json.JSONObject;
import com.avaya.sce.runtimecommon.SCESession;
//import com.util.Nuance.NuanceConnector;

import com.General.Load;

import flow.IProjectVariables;


public class NuanceConnector {
	
	
	public static JSONObject startConnect(HashMap<String, Object> map, String method, String setMultiDataRequestXML, SCESession mySession) 
			throws UnsupportedOperationException, SOAPException, IOException, JSONException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		mySession.getVariableField(IProjectVariables.NUANCEMETHOD).setValue(method);
		mySession.setProperty("map",map );
		Load load = new Load();
		String method1 = "startConnect";
		load.values(method1, mySession);
		JSONObject response = (JSONObject) mySession.getProperty("xmlJSONObj"+method);
		return response;
	}

}
