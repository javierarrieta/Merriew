package org.merriew.hg.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HgHelper {
	
	private Logger logger = Logger.getLogger( HgHelper.class.getCanonicalName() );
	
	private PrintStream os = System.out;
	
	private PrintStream es = System.err;
	
	private InputStream is = System.in;
	
	public HgHelper() {
		this("hg");
	}

	public HgHelper(String hgExe) {
		super();
		this.hgExe = hgExe;
	}

	private String hgExe;
	
	public int pull( String sourceRepo, File destDir, boolean update ) throws IOException, InterruptedException {
		return this.pull(sourceRepo, destDir, null, update );
	}
	
	public int pull( String sourceRepo, File destDir, String branch, boolean update ) throws IOException, InterruptedException {
		
		String branchOpt = " ";
		if( branch != null && branch.length() > 0 ) {
			branchOpt = " --branch " + branch;
		}
		
		String command = getHgExe() + " pull " + ( update ? " -u " : "" ) + 
					" --repository " + destDir.getCanonicalPath() + branchOpt + sourceRepo;
		
		if( logger.isLoggable(Level.FINER) ) {
			logger.finer("Executing command: " + command  );
		}
		
		Process p = Runtime.getRuntime().exec( command );

		ProcessHandler streamHandler = new ProcessHandler(p,os, es);
		return streamHandler.run();
		
	}
	
	public int init( String dir ) throws IOException, InterruptedException {
		
		Process p = Runtime.getRuntime().exec(getHgExe() + " init " + dir);

		ProcessHandler streamHandler = new ProcessHandler(p,os, es);
		return streamHandler.run();
	}
	
	public int clone( String sourceRepo, File destDir ) throws IOException, InterruptedException {
		return this.clone(sourceRepo, destDir, null);
	}
	
	public int clone( String sourceRepo, File destDir, String branch ) throws IOException, InterruptedException {
		
		Process p = Runtime.getRuntime().exec( getHgExe() + " clone " + destDir.getCanonicalPath() );

		ProcessHandler streamHandler = new ProcessHandler(p,os, es);
		return streamHandler.run();
	}

	public String getHgExe() {
		return hgExe;
	}

	public void setHgExe(String hgExe) {
		this.hgExe = hgExe;
	}
}
