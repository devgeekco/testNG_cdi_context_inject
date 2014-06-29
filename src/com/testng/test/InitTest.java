package com.testng.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * Servlet implementation class InitTest
 */
@WebServlet("/InitTest")
public class InitTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "text/html");
		response.setHeader("success", "yes");
		PrintWriter writer = response.getWriter();
		writer.write("Testing Modified TestNG with Context!");
		runTest();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
	
	TestNG myTestNG;

	//	private void runTest(HashMap<String, ServletContext> contextInfo) {
	private void runTest() {
		//Create an instance on TestNG
		myTestNG = new TestNG();

		//Create an instance of XML Suite and assign a name for it.
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("Sample Suite");

		//Create an instance of XmlTest and assign a name for it.
		XmlTest myTest = new XmlTest(mySuite);
		myTest.setName("Sample Test");

		//Add any parameters that you want to set to the Test.
		//myTest.setParameters(contextInfo);

		//Create a list which can contain the classes that you want to run.
		List<XmlClass> myClasses = new ArrayList<XmlClass> ();
		myClasses.add(new XmlClass("com.testng.test.SampleTest"));

		//Assign that to the XmlTest Object created earlier.
		myTest.setXmlClasses(myClasses);

		//Create a list of XmlTests and add the Xmltest you created earlier to it.
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		myTests.add(myTest);

		//add the list of tests to your Suite.
		mySuite.setTests(myTests);

		//Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);

		//Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);

		//invoke run() - this will run your class.
		myTestNG.run();

	}
}

