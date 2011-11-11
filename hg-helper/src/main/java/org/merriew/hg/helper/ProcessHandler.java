package org.merriew.hg.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ProcessHandler {
	
	private static class StreamGobbler extends Thread
	{
	    private PrintStream output;
	    private InputStream is;
	    
	    private StreamGobbler( PrintStream output, InputStream is )
	    {
	        this.output = output;
	        this.is = is;
	    }
	    
	    public void run()
	    {
	        try
	        {
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String line=null;
	            while ( (line = br.readLine()) != null)
	                output.println( line );    
	            } catch (IOException ioe)
	              {
	                ioe.printStackTrace();  
	              }
	    }
	}

	private StreamGobbler outputGobbler;
	private StreamGobbler errorGobbler;
	private Process process;
	
	
	public ProcessHandler( Process process, PrintStream outputStream, PrintStream errorStream ) {
		
		this.process = process;
		this.outputGobbler = new StreamGobbler( outputStream, process.getInputStream() );
		this.errorGobbler  = new StreamGobbler( errorStream, process.getErrorStream() );
	}
	
	public int run() throws InterruptedException {
		
		outputGobbler.run();
		errorGobbler.run();
		
		return process.waitFor();
		
	}
}