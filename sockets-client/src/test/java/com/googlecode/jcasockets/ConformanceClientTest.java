package com.googlecode.jcasockets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ConformanceClientTest {
	@Test
	public void testSmallMessageSize() throws Exception{
		ConformanceClient conformanceClient = new ConformanceClient( "-s 1 -m 3 -M15");
		MockSocketSender socketSender = new MockSocketSender();
		
		conformanceClient.setSender( socketSender );
		conformanceClient.execute();
		ExecutionStatistics executionStatistics = conformanceClient.getExecutionStatistics();
		
		int messageCount = executionStatistics.getMessagesSent();
		assertTrue(messageCount > 0 );

		// TODO in fact this is a bit flakey it is possible (but unlikely) for this to fail
		// maybe the first 2 executions should not use random but max/min
		assertEquals( 6, executionStatistics.getMinimumMessageSize() );
		assertEquals( 30, executionStatistics.getMaximumMessageSize() );
		
	}

	@Test
	public void testMultipleThread() throws Exception{
		ConformanceClient conformanceClient = new ConformanceClient( "-s 1 -t2");
		MockSocketSender socketSender = new MockSocketSender();
		
		conformanceClient.setSender( socketSender );
		conformanceClient.execute();
		fail();
	}
	
}
