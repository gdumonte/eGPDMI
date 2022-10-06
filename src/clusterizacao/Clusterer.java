package clusterizacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static java.nio.file.StandardCopyOption.*;

import weka.clusterers.Canopy;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Cobweb;
import weka.clusterers.SimpleKMeans;
import weka.clusterers.EM;
import weka.clusterers.FarthestFirst;
import weka.clusterers.FilteredClusterer;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.MakeDensityBasedClusterer;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Clusterer {
	
	Clusters clusters = new Clusters();

	public Clusterer()
	{
	}
	
	/**
	 * 
	 * numAttribures -- 36 
	 * 
	 * @param filePathTraining
	 * @param filePathTest
	 * @return
	 */
	public String getKMeansCluster(String filePathTraining, String filePathTest, int numAttribures)
	{
		ClusterEvaluation eval = new ClusterEvaluation();
		String result = "";
		
 		try {
		Instances dataTraining = loadInstance( filePathTraining, false, 5 );
 		
 		Map<Integer, String> map = new HashMap<Integer, String>();
 		
 		for(int i = 2; i<=numAttribures; i++){	//Original
	 		SimpleKMeans mySKMeans = new SimpleKMeans(); //Original
	 		
	 		mySKMeans.setSeed(17);
	 		
	 		//mySKMeans.setPreserveInstancesOrder(true); //Original
	 		
	 		mySKMeans.setCanopyMaxNumCanopiesToHoldInMemory(100);
			mySKMeans.setCanopyMinimumCanopyDensity(2.0);
			mySKMeans.setCanopyPeriodicPruningRate(10000);
			mySKMeans.setCanopyT1(-1.25);
			mySKMeans.setCanopyT2(-1.0);
			mySKMeans.setDebug(false);
			mySKMeans.setDisplayStdDevs(false);
			
			weka.core.EuclideanDistance eucliddeanDistance = new weka.core.EuclideanDistance();
			eucliddeanDistance.setAttributeIndices("first-last");
			eucliddeanDistance.setDontNormalize(false);
			eucliddeanDistance.setInvertSelection(false);
			
			mySKMeans.setDistanceFunction( eucliddeanDistance );
			mySKMeans.setDoNotCheckCapabilities(false);
			mySKMeans.setDontReplaceMissingValues(false);
			mySKMeans.setFastDistanceCalc(false);
			
			SelectedTag selectedTag = new SelectedTag( SimpleKMeans.KMEANS_PLUS_PLUS, SimpleKMeans.TAGS_SELECTION);
			
			mySKMeans.setInitializationMethod( selectedTag );
			mySKMeans.setMaxIterations(500);
	 		mySKMeans.setNumClusters(i); //Original
	 		mySKMeans.setNumExecutionSlots(1);
	 		mySKMeans.setPreserveInstancesOrder(true);
	 		mySKMeans.setReduceNumberOfDistanceCalcsViaCanopies(false);
	 		mySKMeans.setSeed(10);	
	 		
	 		//mySKMeans.setOptions( options );
	 		mySKMeans.buildClusterer(dataTraining);

	 		eval.setClusterer( mySKMeans );
	 		//eval.evaluateClusterer( dataTest );
	 		//System.out.println( "Num clusters new data: " + eval.getNumClusters() );
	 		
		 	// This array returns the cluster number (starting with 0) for each instance
		 	// The array has as many elements as the number of instances
		 	int[] assignments = mySKMeans.getAssignments();
	

		 	int j=0;
		 	for(int clusterNum : assignments) {
		 	    
		 	    if( !map.containsKey( clusterNum ) )
		 	    {
				 	Instance instance = dataTraining.get( j );
				 	map.put( clusterNum, instance.stringValue( numAttribures ) );
		 	    }
		 	    
		 	    j++;
		 	} 
		 	
		 	if( i == numAttribures )
		 	{	
			 	result = map.get( assignments[assignments.length-1] );
		 	}		 
	 		
	 		mySKMeans = null;
	 		
		 }	//Original
		 
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
 		
 		return result;
	}
	
	/**
	 * Receive a array of interest binary data and return the group of node
	 * 
	 * @param filePathTraining file of data and last line to define group type
	 * @param data a list of binaries interests
	 * @return interest group
	 */
	public String evaluateInstanceKMeans( String filePathTraining, ArrayList<Integer> data, int numClusters )
	{
		ClusterEvaluation eval = new ClusterEvaluation();
		String result = "";
 		int goalCluster=0;
		
		setFileWithData(filePathTraining, data);
		
 		try {
 		
 	 	Map<Integer, String> clustersMaps = new HashMap<Integer, String>();
 	 	
		//Instances dataTraining = loadInstance( filePathTraining, false, 5 );
 	 	Instances dataTrainingNoFilter = loadInstance( filePathTraining, false, 37, 38 );
 	 	Instances dataTraining = loadInstance( filePathTraining, true, 37, 38 );
 		
 		System.out.println(" dataTraining "+ dataTraining.numAttributes());
 		
 		int[] assignments = null;
 		
 		//for(int i = 2; i <= dataTraining.numAttributes()-1; i++){	//Original
 		//for(int i = dataTraining.numAttributes()-1; i <= dataTraining.numAttributes()-1; i++) 
 		//{	//Original
 			clustersMaps = new HashMap<Integer, String>();
 			
	 		SimpleKMeans mySKMeans = new SimpleKMeans(); //Original
	 		
	 		mySKMeans.setSeed(17);
	 		
	 		//mySKMeans.setPreserveInstancesOrder(true); //Original
	 		
	 		mySKMeans.setCanopyMaxNumCanopiesToHoldInMemory(100);
			mySKMeans.setCanopyMinimumCanopyDensity(2.0);
			mySKMeans.setCanopyPeriodicPruningRate(10000);
			mySKMeans.setCanopyT1(-1.25);
			mySKMeans.setCanopyT2(-1.0);
			mySKMeans.setDebug(false);
			mySKMeans.setDisplayStdDevs(false);
			
			weka.core.EuclideanDistance eucliddeanDistance = new weka.core.EuclideanDistance();
			eucliddeanDistance.setAttributeIndices("first-last");
			eucliddeanDistance.setDontNormalize(false);
			eucliddeanDistance.setInvertSelection(false);
			
			mySKMeans.setDistanceFunction( eucliddeanDistance );
			mySKMeans.setDoNotCheckCapabilities(false);
			mySKMeans.setDontReplaceMissingValues(false);
			mySKMeans.setFastDistanceCalc(false);
			
			SelectedTag selectedTag = new SelectedTag( SimpleKMeans.KMEANS_PLUS_PLUS, SimpleKMeans.TAGS_SELECTION);
			
			mySKMeans.setInitializationMethod( selectedTag );
			mySKMeans.setMaxIterations(500);
	 		mySKMeans.setNumClusters(numClusters); //Original
	 		mySKMeans.setNumExecutionSlots(1);
	 		mySKMeans.setPreserveInstancesOrder(true);
	 		mySKMeans.setReduceNumberOfDistanceCalcsViaCanopies(false);
	 		mySKMeans.setSeed(10);	
	 		
	 		//mySKMeans.setOptions( options );
	 		mySKMeans.buildClusterer(dataTraining);

	 		eval.setClusterer( mySKMeans );
	 		eval.evaluateClusterer( dataTraining );
	 		//System.out.println( "Num clusters new data: " + eval.getNumClusters() );
	 		
		 	// This array returns the cluster number (starting with 0) for each instance
		 	// The array has as many elements as the number of instances
		 	assignments = mySKMeans.getAssignments();
	

		 	int j=0;
		 	
		 	for(int clusterNum : assignments) {
		 		
		 		Instance instanceNoFilter = dataTrainingNoFilter.get( j );
		 		
		 		if( !instanceNoFilter.stringValue( dataTrainingNoFilter.numAttributes()-2).equals( "?" ) )
		 		{
		 			
			 	    if( !clustersMaps.containsKey( clusterNum ) )
			 	    {
			 	    	clustersMaps.put( clusterNum, instanceNoFilter.stringValue( dataTrainingNoFilter.numAttributes()-2 ).trim() );
			 	    }
		 		}
		 		else
		 		{
		 			goalCluster = clusterNum; 
		 		}
		 	    
		 	    j++;
		 	} 
	 		
	 		mySKMeans = null;
	 		
		//}	//Original
 		
	 	result = clustersMaps.get( goalCluster );
		 
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
 		
 		return result;		
	}
	
	public String evaluateInstanceEM( String filePathTraining, ArrayList<Integer> data, Integer teste1, Integer teste2 )
	{
		String result = "";
		int goalCluster=0;
	 	String area = "";
	 	Map<Integer, String> clustersMaps = new HashMap<Integer, String>();
	 	
	 	setFileWithData(filePathTraining, data);
		
		ClusterEvaluation eval = new ClusterEvaluation();
		
		try{
			 
			//Instances structure = loadInstance( filePath, false, 6 );
			//Instances structureNoFilter = loadInstance( filePathTraining, false, 6 );
			Instances structureNoFilter = loadInstance( filePathTraining, false, teste1 );
			System.out.println( "Size: " + structureNoFilter.size() );
			System.out.println( "Attributes: " + structureNoFilter.numAttributes() );
			
	 	 	Map<Integer, String> nodeIds = new HashMap<Integer, String>();
	 	 	
	 	 	for( int count=0; count<structureNoFilter.numInstances(); count++  )
	 	 	{
	 	 		Instance instance = structureNoFilter.get( count );
	 	 		//System.out.println(" instance.stringValue( dataTraining.numAttributes()-1 ) " + instance.stringValue( dataTrainingNoFilter.numAttributes()-1 ));
	 	 		nodeIds.put(count, instance.stringValue( structureNoFilter.numAttributes()-1 ) );
	 	 	}
			
			//structure = loadInstance( filePath, true, 5 );
//			structure = loadInstance( filePath, true, 38 );
			//Instances structure = loadInstance( filePathTraining, true, 38 );
			Instances structure = loadInstance( filePathTraining, true, teste2 );
			System.out.println( "Size: " + structure.size() );
			System.out.println( "Attributes: " + structure.numAttributes() );
			
			 
			EM clusterer = new EM();   // new instance of clusterer
			//clusterer.setOptions(options);     // set the options
			
			clusterer.setDebug(false);
			clusterer.setDisplayModelInOldFormat(false);
			clusterer.setDoNotCheckCapabilities(false);
			clusterer.setMaxIterations(100);
			clusterer.setMaximumNumberOfClusters(-1);
			clusterer.setMinLogLikelihoodImprovementCV(1.0E-6);
			clusterer.setMinLogLikelihoodImprovementIterating(1.0E-6);
			clusterer.setMinStdDev(1.0E-6);
			clusterer.setNumClusters(-1);
			clusterer.setNumExecutionSlots(1);
			clusterer.setNumFolds(10);
			clusterer.setNumKMeansRuns(10);
			clusterer.setSeed(100);
			
			clusterer.buildClusterer(structure);    // build the clusterer
			
			eval.setClusterer(clusterer);                                   // the cluster to evaluate

			eval.evaluateClusterer(structure);                                // data to evaluate the clusterer on
			
			System.out.println( "Number clusters EM: " + clusterer.getNumClusters() );
			
			System.out.println("# of clusters: " + eval.getNumClusters());
		 	
		 	////////////////////////////////////////////////////////////////////////////////////
		 	Enumeration clusteredInst = structure.enumerateInstances();
		 	Dictionary<Integer, ArrayList<Instance>> clusteredSamples = new Hashtable<>();
		 	
		 	//while (clusteredInst.hasMoreElements()) {
 			clusters = new Clusters();
		 	int countElements=0;
		 	while ( clusteredInst.hasMoreElements() ) {
		 		
		 	            Instance ins = (Instance) clusteredInst.nextElement(); 
		 	            int clusterNumb = clusterer.clusterInstance(ins);
		 	            ArrayList<Instance> cls = null;
		 	            cls = clusteredSamples.get(clusterNumb); 
		 	            
		 	            Instance instanceNoFilter = structureNoFilter.get( countElements );
		 	            
		 	            area = instanceNoFilter.stringValue( instanceNoFilter.numAttributes()-2 );
		 	            
		 	            if(!area.trim().equals("?"))
		 	            {
			 	            if (cls != null) 
			 	            {
			 	                cls.add(ins);
			 	               
					 			clusters.putEM(	clusterNumb, 
					 							(int)( Double.parseDouble( nodeIds.get(countElements) ) ),
					 							area );
			 	            } else {
			 	                cls = new ArrayList<>(); 
			 	                cls.add(ins);
			 	                //you add elements to dictionary using put method<br> 
			 	                //put(key, value)<br> 
			 	               
					 			clusters.putEM(	clusterNumb, 
					 							(int)( Double.parseDouble( nodeIds.get(countElements) ) ),
					 							area );
			 	                //clusteredSamples.put(clusterNumb, cls); 
			 	            }
			 	            
			 	           clustersMaps.put(clusterNumb, area);
		 	            }
		 	            else
		 	            {
		 	            	goalCluster = clusterNumb;
		 	            }
		 	            
		 	           countElements++;
		 	}
		
		 	//result = clusters.getAreas(goalCluster);
		 	result = clustersMaps.get(goalCluster);
		 	
		 	//result = area;
		 
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}		
		
		return result;
	}
	
	public ArrayList<Integer> getKMeansClusterElements_Measured(String filePathTraining, String area, int numClusters, boolean removeLast)
	{
		ClusterEvaluation eval = new ClusterEvaluation();
 		
		try {
 	 	 	Instances dataTrainingNoFilter = loadInstance( filePathTraining, false, 5 );

 	 	 	Map<Integer, String> nodeIds = new HashMap<Integer, String>();
 	 	 	
 	 	 	int numAttributesOriginal = dataTrainingNoFilter.numAttributes();
 	 	 	
 	 	 	for( int count=0; count<dataTrainingNoFilter.numInstances(); count++  )
 	 	 	{
 	 	 		Instance instance = dataTrainingNoFilter.get( count );
 	 	 		//System.out.println(" instance.stringValue( dataTraining.numAttributes()-1 ) " + instance.stringValue( dataTrainingNoFilter.numAttributes()-1 ));
 	 	 		//nodeIds.put(count, instance.stringValue( dataTrainingNoFilter.numAttributes()-1 ) );
 	 	 		//String valueStr = Double.toString( instance.value(0) );
 	 	 		
 	 	 		//String valueStr = Double.toString( instance.value( numAttributesOriginal-1 ) );
 	 	 		String valueStr;
 	 	 		
 	 	 		if(!removeLast)
 	 	 		{
 	 	 			valueStr = Double.toString( instance.value( numAttributesOriginal-1 ) );
 	 	 		}
 	 	 		else
 	 	 		{
 	 	 			valueStr = Double.toString( instance.value( numAttributesOriginal-2 ) );
 	 	 		}
 	 	 		
 	 	 		nodeIds.put(count, valueStr );
 	 	 	}
 	 	 	
 	 	 	Instances dataTraining = loadInstance( filePathTraining, removeLast, numAttributesOriginal );
 	 	 	
 	 	 	File measuredFile = new File( filePathTraining.substring(0, filePathTraining.indexOf(".")) + "_KMeans_Measured" + 
					filePathTraining.substring(filePathTraining.indexOf("."), filePathTraining.length() ) );
 	 	 	
 	 	 	BufferedWriter writer = new BufferedWriter(new FileWriter( measuredFile ));
 	 	 	
 	 	 	writer.write( "@relation interest" + System.getProperty("line.separator") + System.getProperty("line.separator") );
 	 	 	
 	 	 	String clustersSTR = "";
 	 	 	for(int count=0; count<numClusters; count++)
 	 	 	{
 	 	 		clustersSTR = clustersSTR + count + ",";
 	 	 	}
 	 	 	clustersSTR = clustersSTR.substring(0, clustersSTR.length()-1);
 	 	 	
 	 	 	writer.write( "@attribute cluster {"+clustersSTR+"}" + System.getProperty("line.separator") );
 	 	 	writer.write( "@attribute class {NoInterest,AdHocMobileNetworksOption,AddressingLocationManagement,BroadbandAccessTechnologies,CapacityPlanning,CellularBroadbandWirelessNets, ContentDistribution, DenialOfService, IPv6IPv6Transition, MulticastAnycast, MultimediaProtocols, MobilityModelsSystems, NetworkApplicationsServices, NetworkArchitectures, NetworkControlByPricing, NetworkManagement, NovelNetworkSrchitectures, OpticalNetworks, PeerToPeerCommunications, PerformanceEvaluation, PowerControl, PricingBilling, QualityService, ResourceAllocationManagement, RoutingProtocols, SchedulingBufferManagement, SecurityTrustPrivacy, SelfOrganizingNetworks, SensorNetsEmbeddedSystems, ServiceOverlays, SwitchesSwitching, TopologyCharacterizationInference, TrafficAnalysisEngineeringControl, VirtualOverlayNetworks, WebServicesPerformance, WirelessNetworksProtocols}" + System.getProperty("line.separator") );
 	 	 	writer.write( "@data" + System.getProperty("line.separator") );
 	 	 	
 	 	 	
 	 		//for(int i = 2; i <= dataTraining.numAttributes()-1 ; i++){	//Original
 	 	 	
 	 			clusters = new Clusters();
 	 			
 		 		SimpleKMeans mySKMeans = new SimpleKMeans(); //Original
 		 		
 		 		mySKMeans.setCanopyMaxNumCanopiesToHoldInMemory(100);
 				mySKMeans.setCanopyMinimumCanopyDensity(2.0);
 				mySKMeans.setCanopyPeriodicPruningRate(10000);
 				mySKMeans.setCanopyT1(-1.25);
 				mySKMeans.setCanopyT2(-1.0);
 				mySKMeans.setDebug(false);
 				mySKMeans.setDisplayStdDevs(false);
 				
 				weka.core.EuclideanDistance eucliddeanDistance = new weka.core.EuclideanDistance();
 				eucliddeanDistance.setAttributeIndices("first-last");
 				eucliddeanDistance.setDontNormalize(false);
 				eucliddeanDistance.setInvertSelection(false);
 				
 				mySKMeans.setDistanceFunction( eucliddeanDistance );
 				mySKMeans.setDoNotCheckCapabilities(false);
 				mySKMeans.setDontReplaceMissingValues(false);
 				mySKMeans.setFastDistanceCalc(false);
 				
 				SelectedTag selectedTag = new SelectedTag( SimpleKMeans.KMEANS_PLUS_PLUS, SimpleKMeans.TAGS_SELECTION);
 				//selectedTag.
 				
 				mySKMeans.setInitializationMethod( selectedTag );
 				mySKMeans.setMaxIterations(500);
 		 		mySKMeans.setNumClusters( numClusters ); //Original
 		 		mySKMeans.setNumExecutionSlots(1);
 		 		mySKMeans.setPreserveInstancesOrder(true);
 		 		mySKMeans.setReduceNumberOfDistanceCalcsViaCanopies(false);
 		 		mySKMeans.setSeed(10);	
 		 		
 		 		//mySKMeans.setOptions( options );
 		 		mySKMeans.buildClusterer(dataTraining);

 		 		eval.setClusterer( mySKMeans );
 		 		eval.evaluateClusterer( dataTraining );
 		 		System.out.println( "Num clusters new data: " + eval.getNumClusters() );
 		 		
 			 	// This array returns the cluster number (starting with 0) for each instance
 			 	// The array has as many elements as the number of instances
 			 	int[] assignments = mySKMeans.getAssignments();
 		

 			 	int j=0;
 			 	for(int clusterNum : assignments) {

 				 	Instance instance = dataTraining.get( j );
 				 	Instance instanceNoFilter = dataTrainingNoFilter.get( j );
 				 	
 				 	int numAttributes = instanceNoFilter.numAttributes();
 				 	int node = (int)( Double.parseDouble( nodeIds.get(j) ) );
					String areaStr;
						
						if(!removeLast)
						{
							areaStr = instanceNoFilter.stringValue( numAttributes-1 );
						}
						else
						{
							areaStr = instanceNoFilter.stringValue( numAttributes-2 );
						}
 				 		clusters.put(	clusterNum, 
 				 						node, 
 				 						areaStr );	
 				 		
 				 		writer.write( 	clusterNum + "," +
				  						areaStr +
				  						System.getProperty("line.separator"));	
 			 	    j++;
 			 	    
 			 	}//end for
 		 		
 		 		mySKMeans = null;
 		 		
 		 		writer.close(); 
 		 		
 			 //}//end  for	//Original 	 	 	
 	 	 	
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
 		
 		return clusters.getCluster(area); 	 	 	
	}
	
	public ArrayList<Integer> getEMClusterElements_Measured(String filePathTraining, String area, boolean removeLast)
	{
		ClusterEvaluation eval = new ClusterEvaluation();
		
		try{
			 
			//Instances structure = loadInstance( filePath, false, 6 );
			Instances structureNoFilter = loadInstance( filePathTraining, false, 6 );
			System.out.println( "Size: " + structureNoFilter.size() );
			System.out.println( "Attributes: " + structureNoFilter.numAttributes() );
			
	 	 	Map<Integer, String> nodeIds = new HashMap<Integer, String>();
	 	 	
	 	 	for( int count=0; count<structureNoFilter.numInstances(); count++  )
	 	 	{
	 	 		Instance instance = structureNoFilter.get( count );
	 	 		//System.out.println(" instance.stringValue( dataTraining.numAttributes()-1 ) " + instance.stringValue( dataTrainingNoFilter.numAttributes()-1 ));
	 	 		nodeIds.put(count, instance.stringValue( structureNoFilter.numAttributes()-1 ) );
	 	 	}
			
			//structure = loadInstance( filePath, true, 5 );
//			structure = loadInstance( filePath, true, 38 );
			Instances structure = loadInstance( filePathTraining, true, structureNoFilter.numAttributes() );
			System.out.println( "Size: " + structure.size() );
			System.out.println( "Attributes: " + structure.numAttributes() );
			
			 
			EM clusterer = new EM();   // new instance of clusterer
			//clusterer.setOptions(options);     // set the options
			
			clusterer.setDebug(false);
			clusterer.setDisplayModelInOldFormat(false);
			clusterer.setDoNotCheckCapabilities(false);
			clusterer.setMaxIterations(100);
			clusterer.setMaximumNumberOfClusters(-1);
			clusterer.setMinLogLikelihoodImprovementCV(1.0E-6);
			clusterer.setMinLogLikelihoodImprovementIterating(1.0E-6);
			clusterer.setMinStdDev(1.0E-6);
			clusterer.setNumClusters(-1);
			clusterer.setNumExecutionSlots(1);
			clusterer.setNumFolds(10);
			clusterer.setNumKMeansRuns(10);
			clusterer.setSeed(100);
			
			clusterer.buildClusterer(structure);    // build the clusterer
			
			eval.setClusterer(clusterer);                                   // the cluster to evaluate

			eval.evaluateClusterer(structure);                                // data to evaluate the clusterer on
			
			System.out.println( "Number clusters EM: " + clusterer.getNumClusters() );
			
			System.out.println("# of clusters: " + eval.getNumClusters());
		 	
		 	////////////////////////////////////////////////////////////////////////////////////
		 	Enumeration clusteredInst = structure.enumerateInstances();
		 	Dictionary<Integer, ArrayList<Instance>> clusteredSamples = new Hashtable<>();
		 	
 	 	 	File measuredFile = new File( filePathTraining.substring(0, filePathTraining.indexOf(".")) + "_EM_Measured" + 
					filePathTraining.substring(filePathTraining.indexOf("."), filePathTraining.length() ) );
 	 	 	
 	 	 	BufferedWriter writer = new BufferedWriter(new FileWriter( measuredFile ));
 	 	 	
 	 	 	writer.write( "@relation interest" + System.getProperty("line.separator") + System.getProperty("line.separator") );
 	 	 	
 	 	 	String clustersSTR = "";
 	 	 	for(int count=0; count<eval.getNumClusters(); count++)
 	 	 	{
 	 	 		clustersSTR = clustersSTR + count + ",";
 	 	 	}
 	 	 	clustersSTR = clustersSTR.substring(0, clustersSTR.length()-1);
 	 	 	
 	 	 	writer.write( "@attribute cluster {"+clustersSTR+"}" + System.getProperty("line.separator") );
 	 	 	writer.write( "@attribute class {NoInterest,AdHocMobileNetworksOption,AddressingLocationManagement,BroadbandAccessTechnologies,CapacityPlanning,CellularBroadbandWirelessNets, ContentDistribution, DenialOfService, IPv6IPv6Transition, MulticastAnycast, MultimediaProtocols, MobilityModelsSystems, NetworkApplicationsServices, NetworkArchitectures, NetworkControlByPricing, NetworkManagement, NovelNetworkSrchitectures, OpticalNetworks, PeerToPeerCommunications, PerformanceEvaluation, PowerControl, PricingBilling, QualityService, ResourceAllocationManagement, RoutingProtocols, SchedulingBufferManagement, SecurityTrustPrivacy, SelfOrganizingNetworks, SensorNetsEmbeddedSystems, ServiceOverlays, SwitchesSwitching, TopologyCharacterizationInference, TrafficAnalysisEngineeringControl, VirtualOverlayNetworks, WebServicesPerformance, WirelessNetworksProtocols}" + System.getProperty("line.separator") );
 	 	 	writer.write( "@data" + System.getProperty("line.separator") );

		 	//while (clusteredInst.hasMoreElements()) {
 			clusters = new Clusters();
		 	int countElements=0;
		 	while ( clusteredInst.hasMoreElements() ) {
		 		
		 	            Instance ins = (Instance) clusteredInst.nextElement(); 
		 	            int clusterNumb = clusterer.clusterInstance(ins);
		 	            ArrayList<Instance> cls = null;
		 	            cls = clusteredSamples.get(clusterNumb); 
		 	            
		 	            Instance instanceNoFilter = structureNoFilter.get( countElements );
		 	            
		 	            String lineArea = instanceNoFilter.stringValue( instanceNoFilter.numAttributes()-2 );
		 	            
		 	            if (cls != null) 
		 	            {
		 	                cls.add(ins);
		 	               
				 			clusters.putEM(	clusterNumb, 
				 							(int)( Double.parseDouble( nodeIds.get(countElements) ) ),
				 							lineArea );
				 			
				 			
				 			writer.write( 	clusterNumb + "," +
				 							lineArea +
			  								System.getProperty("line.separator"));
				 			
		 	            } else {
		 	                cls = new ArrayList<>(); 
		 	                cls.add(ins);
		 	                //you add elements to dictionary using put method<br> 
		 	                //put(key, value)<br> 
		 	               
		 	               if (!lineArea.trim().equals("?"))
		 	               {
					 			clusters.putEM(	clusterNumb, 
					 							(int)( Double.parseDouble( nodeIds.get(countElements) ) ),
					 							lineArea );
					 			writer.write( 	clusterNumb + "," +
			 									lineArea +
			 									System.getProperty("line.separator"));
		 	               }
		 	            }
		 	           countElements++;
		 	 }
		 	
		 	System.out.println(" Count Elements: "+ countElements);
		 	
		 	writer.close();
		 	////////////////////////////////////////////////////////////////////////////////////
		 
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
		
	 	//return clusters.getClusterEM(area);
	 	return null;
	}
	
	public ArrayList<Integer> getKMeansClusterElements(String filePathTraining, String area, int numClusters)
	{
		ClusterEvaluation eval = new ClusterEvaluation();
		//Clusters clusters = null;
		
 		try {
 	 	Instances dataTrainingNoFilter = loadInstance( filePathTraining, false, 5 );
 		//Instances dataTraining = loadInstance( filePathTraining, true, 5 );
		//Instances dataTest = loadInstance( filePathTest, false, 5 );
 	 	
 	 	Map<Integer, String> nodeIds = new HashMap<Integer, String>();
 	 	
 	 	for( int count=0; count<dataTrainingNoFilter.numInstances(); count++  )
 	 	{
 	 		Instance instance = dataTrainingNoFilter.get( count );
 	 		//System.out.println(" instance.stringValue( dataTraining.numAttributes()-1 ) " + instance.stringValue( dataTrainingNoFilter.numAttributes()-1 ));
 	 		nodeIds.put(count, instance.stringValue( dataTrainingNoFilter.numAttributes()-1 ) );
 	 	}
 	 	
		Instances dataTraining = loadInstance( filePathTraining, true, 37, 38 );
 		
		//System.out.println(" dataTraining.numAttributes() "+dataTraining.numAttributes());
		
 		//for(int i = 2; i <= dataTraining.numAttributes()-1 ; i++){	//Original
 		//	clusters = new Clusters();
 			
	 		SimpleKMeans mySKMeans = new SimpleKMeans(); //Original
	 		
	 		mySKMeans.setCanopyMaxNumCanopiesToHoldInMemory(100);
			mySKMeans.setCanopyMinimumCanopyDensity(2.0);
			mySKMeans.setCanopyPeriodicPruningRate(10000);
			mySKMeans.setCanopyT1(-1.25);
			mySKMeans.setCanopyT2(-1.0);
			mySKMeans.setDebug(false);
			mySKMeans.setDisplayStdDevs(false);
			
			weka.core.EuclideanDistance eucliddeanDistance = new weka.core.EuclideanDistance();
			eucliddeanDistance.setAttributeIndices("first-last");
			eucliddeanDistance.setDontNormalize(false);
			eucliddeanDistance.setInvertSelection(false);
			
			mySKMeans.setDistanceFunction( eucliddeanDistance );
			mySKMeans.setDoNotCheckCapabilities(false);
			mySKMeans.setDontReplaceMissingValues(false);
			mySKMeans.setFastDistanceCalc(false);
			
			SelectedTag selectedTag = new SelectedTag( SimpleKMeans.KMEANS_PLUS_PLUS, SimpleKMeans.TAGS_SELECTION);
			//selectedTag.
			
			mySKMeans.setInitializationMethod( selectedTag );
			mySKMeans.setMaxIterations(500);
	 		mySKMeans.setNumClusters(numClusters); //Original
	 		mySKMeans.setNumExecutionSlots(1);
	 		mySKMeans.setPreserveInstancesOrder(true);
	 		mySKMeans.setReduceNumberOfDistanceCalcsViaCanopies(false);
	 		mySKMeans.setSeed(10);	
	 		
	 		//mySKMeans.setOptions( options );
	 		mySKMeans.buildClusterer(dataTraining);

	 		eval.setClusterer( mySKMeans );
	 		//eval.evaluateClusterer( dataTest );
	 		//System.out.println( "Num clusters new data: " + eval.getNumClusters() );
	 		
		 	// This array returns the cluster number (starting with 0) for each instance
		 	// The array has as many elements as the number of instances
		 	int[] assignments = mySKMeans.getAssignments();
	

		 	int j=0;
		 	for(int clusterNum : assignments) {

			 	Instance instanceNoFilter = dataTrainingNoFilter.get( j );
			 	
			 	if( !instanceNoFilter.stringValue( dataTrainingNoFilter.numAttributes()-2).equals( "?" ) )
			 	{
			 		clusters.put(	clusterNum, 
	 						(int)( Double.parseDouble( nodeIds.get(j) ) ), 
	 						instanceNoFilter.stringValue( instanceNoFilter.numAttributes()-2 ) );	
			 	}
			 	else
			 	{
			 		//System.out.println(" clusterNum " + clusterNum + " id " + ( Double.parseDouble( nodeIds.get(j) )  ) + " name "+ clusters.getClusterAttribute(clusterNum));
			 		if( !nodeIds.get(j).trim().equals("?"))
			 		{
				 		clusters.put(	clusterNum, 
		 						(int)( Double.parseDouble( nodeIds.get(j) ) ), 
		 						clusters.getClusterAttribute(clusterNum) );	
			 		}
			 	}
		 	    
		 	    j++;
		 	}
	 		
	 		mySKMeans = null;
	 		
		 //}	//Original
		 
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
 		
 		return clusters.getCluster(area);
	}
	
	public ArrayList<Integer> getEMClusterElements(String filePath, String area, int teste, int teste2){
		ClusterEvaluation eval = new ClusterEvaluation();
		
		try{
			 
			//Instances structure = loadInstance( filePath, false, 6 );
			//Instances structureNoFilter = loadInstance( filePath, false, 6 );
			Instances structureNoFilter = loadInstance( filePath, false, teste );
			System.out.println( "Size: " + structureNoFilter.size() );
			System.out.println( "Attributes: " + structureNoFilter.numAttributes() );
			
	 	 	Map<Integer, String> nodeIds = new HashMap<Integer, String>();
	 	 	
	 	 	for( int count=0; count<structureNoFilter.numInstances(); count++  )
	 	 	{
	 	 		Instance instance = structureNoFilter.get( count );
	 	 		//System.out.println(" instance.stringValue( dataTraining.numAttributes()-1 ) " + instance.stringValue( dataTrainingNoFilter.numAttributes()-1 ));
	 	 		nodeIds.put(count, instance.stringValue( structureNoFilter.numAttributes()-1 ) );
	 	 	}
			
			//structure = loadInstance( filePath, true, 5 );
//			structure = loadInstance( filePath, true, 38 );
		//	Instances structure = loadInstance( filePath, true, 38 );
			Instances structure = loadInstance( filePath, true, teste2 );
			System.out.println( "Size: " + structure.size() );
			System.out.println( "Attributes: " + structure.numAttributes() );
			
			 
			EM clusterer = new EM();   // new instance of clusterer
			//clusterer.setOptions(options);     // set the options
			
			clusterer.setDebug(false);
			clusterer.setDisplayModelInOldFormat(false);
			clusterer.setDoNotCheckCapabilities(false);
			clusterer.setMaxIterations(100);
			clusterer.setMaximumNumberOfClusters(-1);
			clusterer.setMinLogLikelihoodImprovementCV(1.0E-6);
			clusterer.setMinLogLikelihoodImprovementIterating(1.0E-6);
			clusterer.setMinStdDev(1.0E-6);
			clusterer.setNumClusters(-1);
			clusterer.setNumExecutionSlots(1);
			clusterer.setNumFolds(10);
			clusterer.setNumKMeansRuns(10);
			clusterer.setSeed(100);
			
			clusterer.buildClusterer(structure);    // build the clusterer
			
			eval.setClusterer(clusterer);                                   // the cluster to evaluate

			eval.evaluateClusterer(structure);                                // data to evaluate the clusterer on
			
			System.out.println( "Number clusters EM: " + clusterer.getNumClusters() );
			
			System.out.println("# of clusters: " + eval.getNumClusters());
		 	
		 	////////////////////////////////////////////////////////////////////////////////////
		 	Enumeration clusteredInst = structure.enumerateInstances();
		 	Dictionary<Integer, ArrayList<Instance>> clusteredSamples = new Hashtable<>();

		 	//while (clusteredInst.hasMoreElements()) {
 			clusters = new Clusters();
		 	int countElements=0;
		 	while ( clusteredInst.hasMoreElements() ) {
		 		
		 	            Instance ins = (Instance) clusteredInst.nextElement(); 
		 	            int clusterNumb = clusterer.clusterInstance(ins);
		 	            ArrayList<Instance> cls = null;
		 	            cls = clusteredSamples.get(clusterNumb); 
		 	            
		 	            Instance instanceNoFilter = structureNoFilter.get( countElements );
		 	            
		 	            String lineArea = instanceNoFilter.stringValue( instanceNoFilter.numAttributes()-2 );
		 	            
		 	            if (cls != null) 
		 	            {
		 	                cls.add(ins);
		 	               
				 			clusters.putEM(	clusterNumb, 
				 							(int)( Double.parseDouble( nodeIds.get(countElements) ) ),
				 							lineArea );
		 	            } else {
		 	                cls = new ArrayList<>(); 
		 	                cls.add(ins);
		 	                //you add elements to dictionary using put method<br> 
		 	                //put(key, value)<br> 
		 	               
		 	               if (!lineArea.trim().equals("?"))
		 	               {
					 			clusters.putEM(	clusterNumb, 
					 							(int)( Double.parseDouble( nodeIds.get(countElements) ) ),
					 							lineArea );   
		 	               }
		 	            }
		 	           countElements++;
		 	 }
		 	
		 	System.out.println(" Count Elements: "+ countElements);
		 	////////////////////////////////////////////////////////////////////////////////////
		 
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
		
	 	return clusters.getClusterEM(area);
	}
	
	public void runDensityBased( String filePathTraining )
	{
		try
		{
			MakeDensityBasedClusterer density = new MakeDensityBasedClusterer();
			
	 		SimpleKMeans sKMeans = new SimpleKMeans();			
			
			density.setClusterer( sKMeans );
			density.setDebug(false);
			density.setDoNotCheckCapabilities(false);
			density.setMinStdDev(1.0E-6);
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
	}
	
	public void runCanopy( String filePathTraining )
	{
		try
		{
			Canopy canopy = new Canopy();
			
			canopy.setDebug(false);
			canopy.setDoNotCheckCapabilities(false);
			canopy.setDontReplaceMissingValues(false);
			canopy.setMaxNumCandidateCanopiesToHoldInMemory(100);
			canopy.setMinimumCanopyDensity(2);
			canopy.setNumClusters(-1);
			canopy.setPeriodicPruningRate(10000);
			canopy.setSeed(1);
			canopy.setT1(-1.25);
			canopy.setT2(-1.0);
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}		
	}
	
	public void runCobWeb( String filePathTraining )
	{
		try
		{
			Cobweb cob = new Cobweb();
			cob.setAcuity(1.0);
			cob.setCutoff(0.0028209479177387815);
			cob.setDebug(false);
			cob.setDoNotCheckCapabilities(false);
			cob.setSaveInstanceData(false);
			cob.setSeed(42);
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
	}
	
	public void runFarthest( String filePathTraining )
	{
		try
		{
			FarthestFirst farthest = new FarthestFirst();
			
			farthest.setDebug(false);
			farthest.setDoNotCheckCapabilities(false);
			farthest.setNumClusters(2);
			farthest.setSeed(1);
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
	}
	
	public void runFiltered( String filePathTraining )
	{
		FilteredClusterer filtered = new FilteredClusterer();
		
		SimpleKMeans sKMeans = new SimpleKMeans();
		
		filtered.setClusterer( sKMeans );
		filtered.setDebug(false);
		filtered.setDoNotCheckCapabilities(false);
		
		//Filter filter = new Filter(); 
		weka.filters.unsupervised.attribute.Remove filter = new weka.filters.unsupervised.attribute.Remove();
		
		filtered.setFilter( filter );
	}
	
	public void runHierarchical( String filePathTraining )
	{
		try
		{
			Instances dataTrainingNoFilter = loadInstance( filePathTraining, false, 3 );
			
			HierarchicalClusterer hierarchical = new HierarchicalClusterer();
			
			hierarchical.setDebug(false);
			
			weka.core.EuclideanDistance eucliddeanDistance = new weka.core.EuclideanDistance();
			eucliddeanDistance.setAttributeIndices("first-last");
			eucliddeanDistance.setDontNormalize(false);
			eucliddeanDistance.setInvertSelection(false);
			
			hierarchical.setDistanceFunction( eucliddeanDistance );
			
			hierarchical.setDistanceIsBranchLength(false);
			hierarchical.setDoNotCheckCapabilities(false);
			
			SelectedTag linkType = new SelectedTag(0, weka.clusterers.HierarchicalClusterer.TAGS_LINK_TYPE);
			
			hierarchical.setLinkType( linkType );
			
			hierarchical.setNumClusters(2);
			hierarchical.setPrintNewick(true);
	
			hierarchical.buildClusterer( dataTrainingNoFilter );
			
			System.out.println(" Number of clusters: "+ hierarchical.numberOfClusters());
			
			System.out.println( hierarchical.toString() );
			
			//hierarchical.
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
	}

	public Clusters getClusters() {
		return clusters;
	}

	public Instances loadInstance( String filePath, Boolean filter, int removeParameter, int removeParameter2 ){
		//load data from arguments
		Instances structure=null;
		Remove remove;
		try{
			
	 		ArffLoader loader = new ArffLoader();
			loader.setFile(new File(filePath));
			
			String[] options = new String[2];
			options[0]="-R";
			options[1]=Integer.toString( removeParameter ) + "-" +Integer.toString( removeParameter2 );
			//options[1]="1";
			
			structure = loader.getDataSet();
			
			remove = new Remove();
			remove.setOptions(options);
			remove.setInputFormat( structure );
			
			if(filter){
				structure = Filter.useFilter( structure, remove );				
			}
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
		
		return structure;
	}
	
	public Instances loadInstance( String filePath, Boolean filter, int removeParameter ){
		//load data from arguments
		Instances structure=null;
		Remove remove;
		try{
			
	 		ArffLoader loader = new ArffLoader();
			loader.setFile(new File(filePath));
			
			String[] options = new String[2];
			options[0]="-R";
			options[1]=Integer.toString( removeParameter );
			//options[1]="1";
			//options[1]="1-1";
			//options[1]="0-37";
			//options[1]="38";
			
			structure = loader.getDataSet();
			
			if(filter){
				remove = new Remove();
				remove.setOptions(options);
				remove.setInputFormat( structure );
				
				structure = Filter.useFilter( structure, remove );				
			}
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
		
		return structure;
	}
	
	public void setFileWithData(String filePathTraining, ArrayList<Integer> data)
	{
		try
		{	
			try {

				String filePathTrainingTemp;
				File inputFile = new File(filePathTraining);
				filePathTrainingTemp = 	filePathTraining.substring(0, filePathTraining.indexOf(".")) + "Temp" + 
										filePathTraining.substring(filePathTraining.indexOf("."), filePathTraining.length());
				
				System.out.println(filePathTrainingTemp);
				File tempFile = new File( filePathTrainingTemp );

				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

				String lineToRemove = "?";
				String currentLine;
				
				String newData = "";

				while((currentLine = reader.readLine()) != null) {
				    // trim newline when comparing with lineToRemove
				    String trimmedLine = currentLine.trim();
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
				}
				
				if( newData.length() == 0)
				{	
					for(int count=0; count< data.size(); count++)
					{
						newData += Double.parseDouble( Integer.toString( data.get(count) ) ) + ", ";
					}
					
					newData += "?, ?";
					writer.write(newData + System.getProperty("line.separator"));
				}
				
				writer.close(); 
				reader.close(); 
				
				Files.move( tempFile.toPath(), inputFile.toPath(), REPLACE_EXISTING);
				
			} finally {
			}
			
		} catch (Exception e) {
			System.out.println( "Error" );
			e.printStackTrace();
		}
	}
}

