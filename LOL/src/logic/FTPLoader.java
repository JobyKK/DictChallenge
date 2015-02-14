package logic;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPLoader{
	/**FTP connection settings*/
	private static FTPClient createFTPConnection(){
		final String server = "";
        final int port = 21;
        final String userName = "";
        final String pass = "";
        FTPClient ftpClient = new FTPClient();
        try {
	        ftpClient.connect(server, port);
            ftpClient.login(userName, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setAutodetectUTF8(true);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient;
        }catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
	}
	/**Close FTP connection*/
	private static void closeFTPConnection(FTPClient ftpClient){
		 try {
             if (ftpClient.isConnected()) {
                 ftpClient.logout();
                 ftpClient.disconnect();
             }
         } catch (IOException ex) {
             ex.printStackTrace();
         }
	}
	public static boolean loadFile(String remoteFile, String localFile){
		boolean flag = false;
        FileOutputStream fos = null;  
        FTPClient ftpClient = createFTPConnection();
        if(ftpClient != null)
	        try {
	        	//local path
	            fos = new FileOutputStream(localFile);  
	            //remote path
	            String firstRemoteFile = remoteFile;
	            System.out.println("Start uploading file");
	            boolean done = ftpClient.retrieveFile(firstRemoteFile, fos);  
	            if (done) {
	                System.out.println("The file is uploaded successfully.");
	                flag = true;
	            }
	        } catch (IOException ex) {
	            System.out.println("Error: " + ex.getMessage());
	            ex.printStackTrace();
	        } finally {
	           closeFTPConnection(ftpClient);
	        }
		return flag;
		}
	/**Download dictionary in 
	 * @param number of the dictiona*/
	public static boolean loadDictionary(Integer index) {
		String localFile = "dictionaries/" + 
				Program.user.getDictionaries().get(index).getName() + ".csv";  
	            //remote path
	    String remoteFile = "/files/dictionaries/" 
	            + Program.user.getId() + "/"
	            + Program.user.getDictionaries().get(index).getId() + ".csv";
	    return loadFile(remoteFile, localFile);
	}
	
}
