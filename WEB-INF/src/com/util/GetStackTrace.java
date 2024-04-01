package com.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GetStackTrace {
	
	public static String getMessage(Exception ex) {
		StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
	}
}