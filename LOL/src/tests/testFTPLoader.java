package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import logic.FTPLoader;
import logic.Program;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class testFTPLoader {
	
	@Rule 
	public TemporaryFolder folder = new TemporaryFolder();
	@Test
	public void testFileDownloading() {
		try {
			folder.newFolder("dictionaries");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String localFile = "dictionaries/" + "test" + ".csv";
	    String remoteFile = "/files/dictionaries/" 
	            + "1" + "/"
	            + "1" + ".csv";
		assertTrue(FTPLoader.loadFile(remoteFile, localFile));
	}

}
