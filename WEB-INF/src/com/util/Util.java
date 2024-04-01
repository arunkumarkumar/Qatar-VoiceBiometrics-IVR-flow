package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.avaya.sce.runtimecommon.SCESession;

public class Util {

	public static boolean isStringBlank(String inputString) {
		if (inputString == null || inputString.isEmpty()) {
			return true;
		}
		return false;
	}


	public static String parseDueDate(String date) {
		String retDate = "";
		Date date1;
		try {

			date1 = new SimpleDateFormat("yyyyMMdd").parse(date);
			SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM");

			retDate = gmtDateFormat.format(date1);
			retDate = retDate + "-25";
		}

		catch (ParseException e) {

			e.printStackTrace();
		}

		return retDate;
	}

	public static String pointZeroZeroRemove(String bal) {
		String bal1 = "";
		if (bal.contains(".")) {
			String split_key[] = bal.split("\\.");
			String key = split_key[1];

			int balance = Integer.parseInt(key);
			if (balance == 0) {
				bal1 = split_key[0];
			}

			else {
				bal1 = bal;
			}

		} else {
			bal1 = bal;
		}
		return bal1;
	}

	public static String maskNumber(String number, String mask) {

		int index = 0;
		StringBuilder masked = new StringBuilder();
		for (int i = 0; i < mask.length(); i++) {
			char c = mask.charAt(i);
			if (c == '#') {
				masked.append(number.charAt(index));
				index++;
			} else if (c == 'x') {
				masked.append(c);
				index++;
			} else {
				masked.append(c);
			}
		}
		return masked.toString();
	}


	public static String hexToAscii(String hexStr) {
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < hexStr.length(); i += 2) {
			String str = hexStr.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}
		return output.toString();
	}


	public static boolean checkTimeDifference(String startTime, String endTime, String currentTime,SCESession mySession) {
		
//
//		Logger sessionLoggerr = CustomLogger
//				.getLogger(mySession.getSessionId().substring(0, mySession.getSessionId().indexOf(":")));
//
//		sessionLoggerr.info("startTime :" + startTime);
//		sessionLoggerr.info("endTime :" + endTime);
//		sessionLoggerr.info("currentTime :" + currentTime);
//		mySession.getVariableField(IProjectVariables.BUSINESS_START_TIME).setValue(startTime);
//		mySession.getVariableField(IProjectVariables.BUSINESS_END_TIME).setValue(endTime);
//
//		boolean status = false;
//
//		try {
//
//			Date time1 = new SimpleDateFormat("HH:mm:ss").parse(startTime);
//			Calendar calendar1 = Calendar.getInstance();
//			calendar1.setTime(time1);
//			calendar1.add(Calendar.DATE, 1);
//
//			Date time2 = new SimpleDateFormat("HH:mm:ss").parse(endTime);
//			Calendar calendar2 = Calendar.getInstance();
//			calendar2.setTime(time2);
//			calendar2.add(Calendar.DATE, 1);
//
//			Date d = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
//			Calendar calendar3 = Calendar.getInstance();
//			calendar3.setTime(d);
//			calendar3.add(Calendar.DATE, 1);
//
//			Date x = calendar3.getTime();
//
//			if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
//				status = true;
//				sessionLoggerr.info("Customer Called During Business Hours");
//			} else {
//				status = false;
//				sessionLoggerr.info("Customer Called During Non-Business Hours");
//			}
//		}
//
//		catch (Exception e) {
//			sessionLoggerr.info("$$$ Exception on check Time Difference $$$");
//			status = false;
//		}
//
//		return status;
		return false;
		}

//	public String readPlainTextFile(com.avaya.sce.runtimecommon.SCESession mySession, String filePath) {
//		Logger sessionLoggerr = CustomLogger
//				.getLogger(mySession.getSessionId().substring(0, mySession.getSessionId().indexOf(":")));
//
//		try (BufferedReader bufferReader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
//			StringBuilder stringBuffer = new StringBuilder();
//			String line = bufferReader.readLine();
//			while (line != null) {
//				stringBuffer.append(line);
//				stringBuffer.append(System.lineSeparator());
//				line = bufferReader.readLine();
//			}
//			String fileContent = stringBuffer.toString();
//			return (fileContent.length() > 0) ? fileContent : "NA";
//		} catch (Exception ex) {
//			sessionLoggerr.info("Excception while reading file : " + ex);
//			return null;
//		}
//	}
}
