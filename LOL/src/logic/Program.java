package logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Dictionary;
import model.Language;
import model.Statistics;
import model.User;
import model.UserDictionary;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Program {
	public static UserData user;
	private static HibernateUtil hibernateUtil;
	private static Boolean isConnectionToInternet = false;
	public static LanguageController languageController;
	public static TestProgressController testProgressController;
	
	/** Constructor */
	public Program(){
		user = new UserData();
		languageController = new LanguageController();
		testProgressController = new TestProgressController();
		if(checkConnection())
			 hibernateUtil = new HibernateUtil();
	}
	
	/**Test connection with Internet
	 * used in connection settings*/
	public static boolean checkConnection(){
		try {
			try {
				URL url = new URL("http://www.google.com");
				//System.out.println(url.getHost());
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.connect();
				if (con.getResponseCode() == 200){
					System.out.println("Connection established");
					isConnectionToInternet = true;
					return true;
				}
			} catch (Exception exception) {
				System.out.println("No Connection");
				isConnectionToInternet = false;
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Boolean isConnectedToInternet(){
		return isConnectionToInternet;
	}
	
	/**Use HibernateUtil.class */
	public static void closeSessionFactory(){
		if(!hibernateUtil.getSessionFactory().isClosed())
			hibernateUtil.closeSessionFactory();
	}
	
	/**Get list with users for testing*/
	public void getUsers(){
		Session session = hibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List<User> users = session.createQuery("FROM User").list(); 
	         for (User user : users){
	            System.out.println("First Name: " + user.getEmail()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	/**Created for user authentication in login procedure
	 * @return true - if user is in the database*/
	public static boolean checkUser(String email, String password){
		boolean flag = false;
		Session session = hibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			String query = "select u from User u where u.email like :email"; 
			tx = session.beginTransaction();
			List<User> users = session.createQuery(query).setParameter("email", email).list();
			for (User u : users)
				if(u.getPassword().equals(password)){
					flag = true;
					/*statistics = null;
					isSignedIn = true;*/
					user = new UserData(u);
				}
			tx.commit();
		}catch (HibernateException e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace(); 
	    }finally {
	    	session.close(); 
	    }
		return flag;
	}
	
	@Deprecated
	public void setUser(User user){
		//user.
	}
	/**Extract user's statistics.
	 * Used in menu controller.
	 * @return ObservableList of statistic's fields.*/
	public static ObservableList<String> getStatistics(){
		ObservableList<String> items = FXCollections.observableArrayList();
		if(user.getStatistics() == null){
			Statistics statistics = null;
			Session session = hibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try{
				String query = "select s from Statistics s where s.id like :id";
				tx = session.beginTransaction();
				List<Statistics> statisticsList = 
						session.createQuery(query).setParameter("id", user.getId()).list();
				for(Statistics s : statisticsList)
					statistics = s;
				user.setStatistics(statistics);
			}catch(HibernateException e){
				if(tx != null) tx.rollback();
			}finally{
				session.close();
			}
		}
		if(user.isSignedIn()){
			Statistics statistics = user.getStatistics();
			items.add("Max score - " + statistics.getMaxScore());
			items.add("Best time - " + statistics.getBestTime());
			items.add("Dictionaries - " + statistics.getDictionariesCount());
			items.add("Friends - " + statistics.getFriendsCount());
		}
		return items;
	}
	
	/**Initialize user's dictionaries.
	 * Used when program should download dictionaries. */
	public static void initDictionaries(){
		if(user.getDictionaries() == null){
			Session session = hibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try{
				String query = "select d from Dictionary d "
						+ "where d.id in "
						+ "(select ud.id.dictionaryid from UserDictionary ud "
						+ "where ud.id.userid like :id)";
				tx = session.beginTransaction();
						List<Dictionary> dictionariesList = 
							session.createQuery(query).setParameter("id", user.getId()).setTimeout(5).list();
						ArrayList<Dictionary> dictionaries = new ArrayList<Dictionary>();
						for(Dictionary dictionary : dictionariesList)
							dictionaries.add(dictionary);
						user.setDictionaries(dictionaries);
			}catch(HibernateException e){
				if(tx != null) tx.rollback();
			}
			finally{
				session.close();
			}
		}
	}
	/**Initialize user's languages.
	 * Used when user have chosen dictionary, and now program needs download languages*/
	public static void initLanguages(){
		Session session = hibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			String query = "select l from Language l "
					+ "where l.id like :id";
			System.out.println(user.getChosenDictionary().getLanguageByLanguage1().getId());
			List<Language> languages1List = 
					session.createQuery(query).setParameter("id", user.
							getChosenDictionary().getLanguageByLanguage1().getId()).list();
			List<Language> languages2List = 
					session.createQuery(query).setParameter("id", user.
							getChosenDictionary().getLanguageByLanguage2().getId()).list();
			for(Language language : languages1List)
				LanguageController.setLanguage1Net(language.getName());
			for(Language language : languages2List)
				LanguageController.setLanguage2Net(language.getName());
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
		}finally{
			session.close();
		}
	}
	/**Convert ArrayList to ObservableList*/
	public static ObservableList<String> getDictionaries(){
		//initDictionaries();
		ObservableList<String> items = FXCollections.observableArrayList();
		for(Dictionary dictionary : user.getDictionaries())
			items.add(dictionary.getName());
		return items;
	}
	
	public static void submitScore(Float score){
		Session session = hibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
		String query = "select ud from UserDictionary ud where ud.id.userid like :id and "
				+ "ud.id.dictionaryid like :dictid"; 
		tx = session.beginTransaction();
		UserDictionary ud = (UserDictionary) session.createQuery(query).setParameter("id", user.getId()).
				setParameter("dictid", user.getChosenDictionary().getId()).list().get(0);
		ud.setMaxScore(score);
		session.update(ud);
		tx.commit();
		}catch (HibernateException e) {
	         if (tx != null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
}
