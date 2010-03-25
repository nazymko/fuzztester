/*
 * Copyright (c) 2010. Agwego Enterprises Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * If you modify this software a credit would be nice
 */

package com.agwego.fuzz;

import com.agwego.common.StringHelper;

/**
 * Models the test case object, this is the Java
 * representation of the JSON test case object.
 * <pre>
       {
           "Comment" : "Test accept",
            "MethodName" : "construtctor",
            "Tests" : [
                <b>{
                    "name" : "Test consturctor",
                    "args" : [ "prefix", "xml" ]
                },
                {
                    "pass" : fail
                    "name" : "Test consturctor",
                    "args" : [ "prefix", null ]
                },
                {
                    "skip" : true
                    "name" : "Test consturctor",
                    "args" : [ "prefix", "xml" ]
                },
                {
                    "exceptionThrown" : "java.lang.RuntimeException",
                    "exceptionMessage" : "Prefix is required",
                    "name" : "Test consturctor with null prefix",
                    "args" : [ null, "postfix" ]
                }</b>
            ]
 * </pre>
 *
 * @author Tim Desjardins
 * @version $Rev$
 * $Id: $
 */
public class FuzzTestCase
{
	private String methodName;
	private String name;
	private String comment;
	private String[] args;
	private String exceptionThrown;
	private String exceptionMessage;
	private int number;
	private boolean pass = true;
	private boolean skip = false;

	/**
	 * Get the test case name
	 *
	 * @return test case name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the test case name
	 *
	 * @param name test case name
	 */
	public void setName( String name )
	{
		this.name = name;
	}

	/**
	 * Does the test pass
	 *
	 * @return boolean
	 */
	public boolean isPass()
	{
		return pass;
	}

	/**
	 * Does the test fail
	 *
	 * @return boolean
	 */
	public boolean isFail()
	{
		return !pass;
	}

	/**
	 * Does the test pass
	 *
	 * @return boolean
	 */
	public boolean getPass()
	{
		return isPass();
	}

	/**
	 * set whether the test passes or fails
	 *
	 * @param pass does the test pass
	 */
	public void setPass( boolean pass )
	{
		this.pass = pass;
	}

	/**
	 * Get the test's comment
	 *
	 * @return comment
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * Set the test's comment
	 *
	 * @param comment -
	 */
	public void setComment( String comment )
	{
		this.comment = comment;
	}

	/**
	 * Get the args to call the test method with (via reflection)
	 *
	 * @return String[] of method arguments
	 */
	public String[] getArgs()
	{
		return args;
	}

	/**
	 * Set the arguments to call the test method with
	 *
	 * @param args String[]
	 */
	public void setArgs( String[] args )
	{
		this.args = args;
	}

	/**
	 * Get the test number, set by FuzzTester
	 *
	 * @return int test number
	 */
	public int getNumber()
	{
		return number;
	}

	/**
	 * Set the test number, with a fluent interface
	 *
	 * @param number the test number (set by FuzzTester)
	 * @return this (fluent interface)
	 */
	public FuzzTestCase setNumber( int number )
	{
		this.number = number;
		return this;
	}

	/**
	 * Get the test method name
	 * @return String
	 */
	public String getMethodName()
	{
		return methodName;
	}

	/**
	 * Set the test method name, with a fluent interface
	 * @param methodName the method name for this testcase
	 * @return this (fluent interface)
	 */
	public FuzzTestCase setMethodName( String methodName )
	{
		this.methodName = methodName;
		return this;
	}

	/**
	 * Return the thrown exception for this test case (if there is one)
	 * @return String representation of the Exception e.g. "java.lang.Exception"
	 */
	public String getExceptionThrown()
	{
		return exceptionThrown;
	}

	/**
	 * Set the exception expected to be thrown by this test case
	 *
	 * @param exceptionThrown String representation of the exception e.g. "java.lang.Exception"
	 */
	public void setExceptionThrown( String exceptionThrown )
	{
		this.exceptionThrown = exceptionThrown;
	}

	/**
	 * Has the Test Exception been set
	 *
	 * @return boolean is an exception expected
	 */
	public boolean isTestException()
	{
		return exceptionThrown != null;
	}

	/**
	 *  Test if the exception thrown matches that of the test case
	 *
	 * @param testException class
	 * @return do the exceptions match
	 */
	public boolean matchTestException( Class testException )
	{
		return StringHelper.unnulled( exceptionThrown ).equals( testException.getName() );
	}

	/**
	 * Get the excepted exception message
	 *
	 * @return String the exception message
	 */
	public String getExceptionMessage()
	{
		return exceptionMessage;
	}

	/**
	 * Match the exception message thrown for the test with the expected exception message in the test case
	 *
	 * @param testExceptionMessage the exception message
	 * @return does the exected exception message match the thrown message
	 */
	public boolean matchTestExceptionMessage( String testExceptionMessage )
	{
		return StringHelper.unnulled( exceptionMessage ).compareToIgnoreCase( testExceptionMessage ) == 0;
	}

	/**
	 * Has the test exception message been set (i.e. should we check for the message
	 * in FuzzTester)
	 * @return is it set
	 */
	public boolean isTestExceptionMessage()
	{
		return !StringHelper.isEmpty( exceptionMessage );
	}

	/**
	 * Set the exception message
	 *
	 * @param exceptionMessage the exception message expected
	 */
	public void setExceptionMessage( String exceptionMessage )
	{
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * @return skip this test?
	 */
	public boolean isSkip()
	{
		return skip;
	}

	/**
	 * @param skip skip the test?
	 */
	public void setSkip( boolean skip )
	{
		this.skip = skip;
	}

	/**
	 * The string version, used by the JUnit test runner
	 *
	 * @return the test case identity
	 */
	public String toString()
	{
		return String.format( "%s, #%d, Pass: %b %s", methodName, number, pass, ( exceptionThrown != null ? exceptionThrown : "" ) );
	}
}