package tests;

import static org.junit.Assert.*;
import logic.HibernateUtil;
import logic.Program;

import org.junit.Test;

public class SessionTest {

	//openSessionFactory()
	@Test
	public void testSessionConnection(){
		HibernateUtil util = new HibernateUtil();
		util.closeSessionFactory();
	}

}
