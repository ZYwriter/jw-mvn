package com.testlog;

import org.apache.log4j.Logger;

public class TestLog {
	private static final Logger test1 = Logger.getLogger("test1");  
    private static final Logger test2 = Logger.getLogger("test2");  
    
    public static void main(String[] args) {
		test1.info("info===================================");
		test1.warn("warn===================================");
		test1.debug("debug===================================");
		test1.error("error===================================");
		test2.info("info===================================");
		test2.warn("warn===================================");
		test2.debug("debug===================================");
		test2.error("error===================================");
	}
}
