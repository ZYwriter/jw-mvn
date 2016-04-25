package com.testlog;

import org.apache.log4j.Logger;

public class TestLog {
	private static final Logger file = Logger.getLogger("test1");  
    private static final Logger register = Logger.getLogger("test2");  
    
    public static void main(String[] args) {
		file.info("info===================================");
		file.warn("warn===================================");
		file.debug("debug===================================");
		file.error("error===================================");
		register.info("info===================================");
		register.warn("warn===================================");
		register.debug("debug===================================");
		register.error("error===================================");
	}
}
