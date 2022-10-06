package clusterizacao;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
	
	public Parser()
	{
		
	}
	
	public void parserFile(String filePathTraining)
	{
		try
		{	
			try {
				String filePathInterestAllTemp;
				File inputFile = new File(filePathTraining);
				//filePathInterestAllTemp = 	filePathTraining.substring(0, filePathTraining.indexOf(".")) + "Temp" + 
				//						filePathTraining.substring(filePathTraining.indexOf("."), filePathTraining.length());
				
				filePathInterestAllTemp = 	filePathTraining.substring(0, filePathTraining.indexOf(".")) + ".arff";
				
				System.out.println(filePathInterestAllTemp);
				File tempFile = new File( filePathInterestAllTemp );

				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
				
				writer.write( "@relation interest" + System.getProperty("line.separator") + System.getProperty("line.separator") );

				String currentLine;
				
				String newData = "";
				
				String[] lineHead = null;

				while((currentLine = reader.readLine()) != null) {
				    // trim newline when comparing with lineToRemove
				    String trimmedLine = currentLine.trim();
				    
				    String[] lineSplited = trimmedLine.split(",");
				    
				    if( lineHead == null)
				    {
				    	lineHead = new String[lineSplited.length];
				    	lineHead = trimmedLine.split(",");
				    	
				    	for(int count=0; count<lineHead.length; count++)
				    	{
				    		writer.write( 	"@attribute " + lineHead[count] + " numeric" + 
				    						 
				    						System.getProperty("line.separator") );
				    	}
				    	
				    	writer.write( "@attribute class {" + trimmedLine + "}" + System.getProperty("line.separator") );
				    	
				    	writer.write( "@attribute NodeNumberAttribute string" + System.getProperty("line.separator") );
				    	
				    	writer.write( "@data" + System.getProperty("line.separator") );
				    }
				    
				    int sizeAttributos = lineSplited.length-1;
				    
				    int countUns = 0;
				    for( int count = 0; count < sizeAttributos; count++)
				    {
				    	if( lineSplited[count].trim().equals("1.0") || lineSplited[count].trim().equals("1") )
				    	{
				    		countUns++;
				    	}
				    	System.out.print( lineSplited[count] );
				    }
				    
				    for( int count = 0; count < sizeAttributos; count++)
				    {
				    	if( lineSplited[count].trim().equals("1.0") || lineSplited[count].trim().equals("1") )
				    	{
				    		String newLine = "";
				    		
						    for( int count2 = 0; count2 < sizeAttributos; count2++)
						    {
						    	if( count == count2)
						    	{
						    		newLine += "1,";	
						    	}
						    	else
						    	{
						    		newLine += "0,";
						    	}
						    }
						    
						    newLine += lineHead[count].trim() + "," +"'" + lineSplited[lineSplited.length-1].trim() + "'";
						    
						    writer.write(newLine + System.getProperty("line.separator"));
				    	}
				    	System.out.print( lineSplited[count] );
				    }
				    
				    System.out.println();
				    System.out.println( countUns );
				    
				    /*
				    if(trimmedLine.indexOf( lineToRemove ) > -1){
				    	
						for(int count=0; count< data.size(); count++)
						{
							newData += Double.parseDouble( Integer.toString( data.get(count) ) ) + ", ";
						}
						
						newData += "?, ?";
				    	
				    	writer.write(newData + System.getProperty("line.separator"));
				    	continue;
				    }
				    writer.write(currentLine + System.getProperty("line.separator"));
				    
				    */
				}
				
				if( newData.length() == 0)
				{	
					/*
					for(int count=0; count< data.size(); count++)
					{
						newData += Double.parseDouble( Integer.toString( data.get(count) ) ) + ", ";
					}
					
					newData += "?, ?";
					writer.write(newData + System.getProperty("line.separator"));
					*/
				}
				
				writer.close(); 
				reader.close(); 
				
				//Files.move( tempFile.toPath(), inputFile.toPath(), REPLACE_EXISTING);
				
			} finally {
			}
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
	}
	
	public void parserFiletoEM(String filePathTraining, int durationDivision, int reencounterDivision, int numberOfLines)
	{
		//Map<String,Host> map = new HashMap<String,Host>();
		List<Host> hostsUp = new ArrayList<Host>();
		List<Host> hostsDown = new ArrayList<Host>();
		
		
		try
		{	
			try {
				String filePathInterestAllTemp;
				File inputFile = new File(filePathTraining);
				filePathInterestAllTemp = 	filePathTraining.substring(0, filePathTraining.indexOf(".")) + "Temp" + 
										filePathTraining.substring(filePathTraining.indexOf("."), filePathTraining.length());
				
				System.out.println(filePathInterestAllTemp);
				File tempFile = new File( filePathInterestAllTemp );

				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

				String currentLine;
				
				String newData = "";
				
				int index = 0;
				double biggerDeltaTime = 0;
				
				int countLines=0;
				
				Clusters clusters = new Clusters();

				while( ( (currentLine = reader.readLine()) != null) && (countLines<numberOfLines)) {
				    // trim newline when comparing with lineToRemove
				    String trimmedLine = currentLine.trim();
				    
				    String[] lineSplited = trimmedLine.split(" ");
				    
				    int sizeAttributos = lineSplited.length;
				    
				    int countUns = 0;
				    for( int count = 0; count < sizeAttributos; count++)
				    {
				    	System.out.print( lineSplited[count] + " " );
				    }
				    System.out.println();
				    
				    Host host = new Host();
				    
				    host.setOrigin( Integer.parseInt( lineSplited[2] ) );
				    host.setDestination( Integer.parseInt( lineSplited[3] ) );
				    host.setTime( Double.parseDouble( lineSplited[0]  ) );
				    
				    if( lineSplited[4].trim().equals( "up" ) )
				    {
				    	hostsUp.add( host );
				    	
				    	clusters.put( host.getOrigin(), host.getDestination(), "" );
				    }
				    else
				    {
				    	hostsDown.add( host );
				    	
				    	index++;
				    }
				    
				    countLines++;
				}

				
				for(int countUp=0; countUp<hostsUp.size(); countUp++)
				{
					for(int countDown=0; countDown<hostsDown.size(); countDown++)
					{
				    	double deltaTime=0;
				    	
				    	if( hostsUp.get(countUp).getOrigin() == hostsDown.get(countDown).getOrigin() &&
				    		hostsUp.get(countUp).getDestination() == hostsDown.get(countDown).getDestination() )
				    	{
				    		deltaTime = hostsDown.get(countDown).getTime() - hostsUp.get(countUp).getTime();
				    		hostsUp.get(countUp).setDeltaTime(deltaTime);
				    		hostsUp.get(countUp).setDownTime( hostsDown.get(countDown).getTime() );
				    				 
				    		hostsDown.remove(countDown);
				    		
					    	if( biggerDeltaTime < deltaTime )
					    	{
					    		biggerDeltaTime = deltaTime;
					    	}
					    	
					    	break;
				    	}
					}
				}
				
				double unitTime=0;
				
				System.out.println( "Size:"+hostsUp.size() );
				
				for(int countSplit=0; countSplit<durationDivision; countSplit++)
				{
					System.out.print( countSplit + "TimeDuration," );
				}
				System.out.println();
				
				for(int countUp=0; countUp<hostsUp.size(); countUp++)
				{
				    		String timeClass="";
				    		
				    		unitTime = biggerDeltaTime/durationDivision;
				    		
				    		double deltaTime = hostsUp.get(countUp).getDeltaTime();
				    		
				    		//System.out.println(" deltaTime "+ deltaTime);
				    		
				    		for(int countSplit=0; countSplit<durationDivision; countSplit++)
				    		{
				    			if( (deltaTime> (countSplit*unitTime) ) && (deltaTime<= ( (countSplit+1)*unitTime)) )
					    		{
					    			timeClass = countSplit + "TimeDuration";
					    			break;
					    		}	    			
				    		}
				    		
			    			if( (deltaTime > (durationDivision*unitTime)) )
				    		{
				    			timeClass = durationDivision + "TimeDuration";
				    		}
			    			
			    			if( deltaTime == 0 )
				    		{
				    			timeClass = "0TimeDuration";
				    		}
				    		
				    		hostsUp.get(countUp).setClassTime(timeClass);
				    		
				}
				
				System.out.println(" Biggest " + biggerDeltaTime);
				System.out.println(" Quarter " + biggerDeltaTime/4);
				System.out.println(" 1/10 " + unitTime);
				
				//reencounter
				
				double bigTimeReencounter = 0;

				for(int countUp=0; countUp<hostsUp.size(); countUp++)
				{
					for(int countReencounter=countUp+1; countReencounter<hostsUp.size() && hostsUp.get(countUp).getReencounter().equals(""); 
							countReencounter++)
					{
						if( ( ( ( hostsUp.get(countUp).getOrigin() == hostsUp.get(countReencounter).getOrigin() ) &&
							  ( hostsUp.get(countUp).getDestination() == hostsUp.get(countReencounter).getDestination() ) ) ||
							( ( hostsUp.get(countUp).getOrigin() == hostsUp.get(countReencounter).getDestination() ) &&
							  ( hostsUp.get(countUp).getDestination() == hostsUp.get(countReencounter).getOrigin() ) )
							)
							&&
							(hostsUp.get(countReencounter).getReencounter().equals(""))
						)
						{
							double timeReencounter = hostsUp.get(countReencounter).getTime() - hostsUp.get(countUp).getTime();
							
							if( bigTimeReencounter < timeReencounter)
							{
								bigTimeReencounter = timeReencounter;
							}
						}
					}
				}
				
				System.out.println("Bigtime reencounter "+bigTimeReencounter);
				
				double reencounterUnit = 0;
				
				reencounterUnit = bigTimeReencounter/reencounterDivision;
				
	    		for(int countSplit=0; countSplit<=reencounterDivision; countSplit++)
	    		{
	    			System.out.print( countSplit + "Reencounter," );
		    	}
	    		System.out.println();
				
				for(int countUp=0; countUp<hostsUp.size(); countUp++)
				{
					for(int countReencounter=countUp+1; countReencounter<hostsUp.size() && hostsUp.get(countUp).getReencounter().equals(""); 
							countReencounter++)
					{
						if( ( ( ( hostsUp.get(countUp).getOrigin() == hostsUp.get(countReencounter).getOrigin() ) &&
							  ( hostsUp.get(countUp).getDestination() == hostsUp.get(countReencounter).getDestination() ) ) ||
							( ( hostsUp.get(countUp).getOrigin() == hostsUp.get(countReencounter).getDestination() ) &&
							  ( hostsUp.get(countUp).getDestination() == hostsUp.get(countReencounter).getOrigin() ) )
							)
							&&
							(hostsUp.get(countReencounter).getReencounter().equals(""))
						)
						{
							double timeReencounter = hostsUp.get(countReencounter).getTime() - hostsUp.get(countUp).getTime();
							String reencounterClass = "";
							
							
				    		for(int countSplit=0; countSplit<reencounterDivision; countSplit++)
				    		{
				    			if( (timeReencounter> (countSplit*reencounterUnit) ) && (timeReencounter<= ( (countSplit+1)*reencounterUnit)) )
					    		{
				    				reencounterClass = countSplit + "Reencounter";
					    			break;
					    		}	    			
				    		}
				    		
			    			if( (timeReencounter > (reencounterDivision*unitTime)) )
				    		{
			    				reencounterClass = reencounterDivision + "Reencounter";
				    		}
			    			
			    			if( timeReencounter == 0 )
				    		{
			    				reencounterClass = "0Reencounter";
				    		} 
							
							hostsUp.get(countReencounter).setReencounter( reencounterClass );
							
							break;
						}
						
					}
					
					if( !hostsUp.get(countUp).getReencounter().equals(""))
					{
						writer.write( 	hostsUp.get(countUp).getOrigin() + "," +
				  				hostsUp.get(countUp).getDestination() + "," +
				  				hostsUp.get(countUp).getClassTime() + "," +
				   				hostsUp.get(countUp).getReencounter() +
			    				System.getProperty("line.separator"));	
					}
				}
				
				//check transitivity
				System.out.println( " Clusters Size: " + clusters.getAllClusters().size() );

				Map<Integer, Cluster> map = clusters.getAllClusters();
				for (Map.Entry<Integer, Cluster> entry : map.entrySet() )
				{
				     System.out.print(entry.getKey()+": ");
				     
				     for(int count=0; count< entry.getValue().getAll().size(); count++)
				     {
				    	 //entry.getValue().getAll().get(count);
				    	 
				    	 System.out.print( entry.getValue().getAll().get(count) + "," );
				    	 
				    	 //find neighboard
				    	 Map<Integer, Cluster> mapNeigh = clusters.getAllClusters();
				    	 for (Map.Entry<Integer, Cluster> entryNeigh : mapNeigh.entrySet() )
				    	 {
				    		 if( entryNeigh.getKey() == entry.getValue().getAll().get(count) )
				    		 {
							     for(int countNeigh=0; countNeigh< entryNeigh.getValue().getAll().size(); countNeigh++)
							     {
										writer.write( 	entry.getKey() + "," +
														entryNeigh.getValue().getAll().get(countNeigh) + "," +
								  						"-1TimeDuration," +
								  						"-1Reencounter" +
								  						System.getProperty("line.separator"));								    	 
							     }
				    		 }
				    	 }
				    	 
				     }
				     System.out.println();
				}
				
				writer.close(); 
				reader.close();
				
			} finally {
			}
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
	}
}
