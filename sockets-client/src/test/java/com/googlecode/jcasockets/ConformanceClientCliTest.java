/*
 * Copyright 2009 Mark Jeffrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.jcasockets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ConformanceClientCliTest {

	@Test 
	public void testCommandLineParsingDefaults() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		conformanceClient.parseArguments( ); 
		assertEquals( ConformanceClientCli.DEFAULT_EXECUTION_SECONDS,  conformanceClient.getExecutionSeconds( ) );
		assertEquals( ConformanceClientCli.DEFAULT_NUMBER_OF_THREAD,  conformanceClient.getNumberOfThreads( ) );
		assertEquals( ConformanceClientCli.DEFAULT_MIN_MESSAGE_SIZE,  conformanceClient.getMinimumMessageSize() );
		assertEquals( ConformanceClientCli.DEFAULT_MAX_MESSAGE_SIZE,  conformanceClient.getMaximumMessageSize() );
		assertEquals( false,  conformanceClient.isHelpRequested() );
	}
	@Test 
	public void testCommandLineParsingOfHelp() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_HELP);
		conformanceClient.parseArguments( argument); 
		assertTrue(  conformanceClient.isHelpRequested( ) );
	}
	@Test 
	public void testCommandLineParsingOfSinglePort() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_PORTS, " 9000");
		conformanceClient.parseArguments( argument); 
		assertEquals( Arrays.asList(9000),  conformanceClient.getPorts( ) );
	}
	@Test 
	public void testCommandLineParsingOfPorts() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_PORTS, "100,123,111");
		conformanceClient.parseArguments( argument); 
		assertEquals( Arrays.asList(100,123,111),  conformanceClient.getPorts( ) );
	}
	@Test 
	public void testCommandLineParsingOfExecutionSeconds() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_EXECUTION_SECONDS, " 5");
		conformanceClient.parseArguments( argument); 
		assertEquals( 5,  conformanceClient.getExecutionSeconds( ) );
	}
	@Test 
	public void testCommandLineParsingOfMinimumMessageSize() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_MIN_MESSAGE_SIZE, " 5");
		conformanceClient.parseArguments( argument); 
		assertEquals( 5,  conformanceClient.getMinimumMessageSize( ) );
	}
	@Test 
	public void testCommandLineParsingOfMaximumMessageSize() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_MAX_MESSAGE_SIZE, "200");
		conformanceClient.parseArguments( argument); 
		assertEquals( 200,  conformanceClient.getMaximumMessageSize( ) );
	}
	@Test 
	public void testCommandLineParsingOfNumberOfThreads() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_NUMBER_OF_THREAD, "3 ");
		conformanceClient.parseArguments( argument); 
		assertEquals( 3,  conformanceClient.getNumberOfThreads() );
	}
	@Test 
	public void testCommandLineParsingOfIpAddress() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_IP_ADDRESS, " myhost ");
		conformanceClient.parseArguments( argument); 
		assertEquals( "myhost",  conformanceClient.getIpAddress() );
	}

	@Test 
	public void testCommandLineParsingNoHelp() throws Exception{
		ConformanceClientCli conformanceClient = new ConformanceClientCli( );
		String argument = getOption(ConformanceClientCli.OPTION_EXECUTION_SECONDS, "3");
		conformanceClient.parseArguments( argument); 
		assertFalse(  conformanceClient.isHelpRequested( ) );
	}
	private String getOption(String option) {
		return "-" + option;
	}
	private String getOption(String option, String value ){
		return getOption( option ) + value; 
	}
}