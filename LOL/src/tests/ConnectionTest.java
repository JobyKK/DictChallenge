package tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import logic.Program;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

/**test connection with Internet*/
public class ConnectionTest {

	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@ClassRule
	public static ExternalResource testRule = new ExternalResource(){
		@Override
		protected void before() throws Throwable{
			System.setOut(new PrintStream(outContent));
			
		};
		
		@Override
		protected void after(){
			System.setOut(null);
		};
	};
	
	//checkConnection()
	@Test
	public void testInternetConnection(){
		Program.checkConnection();
		assertEquals("Connection established\n", outContent.toString());
	}
	

}
