package com.anz.creditcardfrauddeductionservice.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

/**
 * @author rdavis
 * 
 * This class written to generate unique id for message key.
 *
 */

@Service
public class SequenceManager {
	
	private static AtomicLong SEQUENCE = new AtomicLong();
	
	public static long getNext() throws Exception {
		
		
		return SEQUENCE.incrementAndGet();
		
		
	}

}
