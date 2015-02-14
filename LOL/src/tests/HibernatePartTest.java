package tests;

import static org.junit.Assert.*;
import logic.Program;
import logic.UserData;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

public class HibernatePartTest {

	String email = "123@m.com";
	String password = "123";
	
	@ClassRule
	public static ExternalResource TestRule = new ExternalResource() {
		@Override
		public void before(){
			new Program();
		}
		@Override
		public void after(){
			Program.closeSessionFactory();
		}
	};
	
	//checkUser()
	/*test valid user
	 * not sure i am right here*/
	@Test
	public void testValidUser() {
		//data can be changed
		assertEquals(true, Program.checkUser(email, password));
	}
	@Test
	public void testUnvalidUser(){
		//can't blank
		assertEquals(false, Program.checkUser("", ""));
	}

	@Test
	public void testReceiveStatisticsList(){
		assertNotNull(Program.getStatistics());
	}
	
	@Test 
	public void testRecieveDictionaryList(){
		Program.initDictionaries();
		assertNotNull(Program.user.getDictionaries());
	}
}
