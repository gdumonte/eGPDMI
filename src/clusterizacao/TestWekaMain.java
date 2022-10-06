package clusterizacao;

import java.util.ArrayList;
import java.util.Map;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestWekaMain {

	public static void main(String[] args) throws Exception {
		
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_fake_trainning.arff";
//		String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_all_trainning.arff";
//		String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_all_trainning_4mil.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality5.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality5_Simple.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality5_Very_Simple.arff";
		
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\one_reality_simpleTemp.arff";
		
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo_Clusterizacao\\fromItalyTraining.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo_Clusterizacao\\realityDataTraining.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom5.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom10.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom15.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom20.arff";
		
		String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality5.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality10.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality15.arff";
		//String filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality20.arff";
		
		//String InterestToParse = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_all_data.txt";
		//String InterestToParse = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_4mil.txt";
//		String InterestToParse = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality36.txt";
		String InterestToParseEMAllClasses = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\one_reality_simple.txt";
		
		//String InterestToParseEM = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\one_reality.txt";
		//String InterestToParseEM = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\one_reality.txt";
		String InterestToParseEM = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\one_reality_simple.txt";
		//String InterestToParseEM = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\one_reality_simple_test_time_duration.txt";
		
		String InterestToParseEMSimple = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\one_reality_simple.txt";
		
		String InterestEM = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_EM_Simple.arff";

		//String InterestEMComplete = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_EM_Complete.arff";
		String InterestEMWeight = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_EM_Simple_Weight_Reencounter.arff";
		//String InterestEMWeight = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_EM_Simple_Weight.arff";		
		
//		String InterestEMWeight = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_EM_Simple_Weight_Little_Data.arff";
		
		
		Clusterer clusterer = new Clusterer();
		
		// Exemplo de como obter o cluster a partir de um array de inteiros
		ArrayList<Integer> data = new ArrayList<Integer>();
		
		data.add(1);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);		
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0); //10
		
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0); //10
		
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0); //10
		
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0); //6
		
		Boolean runParser = true;
		
		if(!runParser)
		{
			//filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom10_Simple.arff";
			filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom1_eric.arff";
			
			System.out.println( "Cluster: " + clusterer.evaluateInstanceKMeans( filePathTrainning, data, 1) );
	
			//Exemplo de como obter os elementos a partir do nome do cluster
//			ArrayList<Integer> cluster = clusterer.getKMeansClusterElements( filePathTrainning, "NoInterest" );
			//System.out.println("�rea: BroadbandAccessTechnologies ");
//			ArrayList<Integer> cluster = clusterer.getKMeansClusterElements( filePathTrainning, "BroadbandAccessTechnologies" );
			
//			filePathTrainning = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality5.arff";
			
//			ArrayList<Integer> cluster = clusterer.getKMeansClusterElements_Measured( filePathTrainning, "BroadbandAccessTechnologies", 5, true );
			
//			ArrayList<Integer> cluster = clusterer.getKMeansClusterElements( filePathTrainning, "a" );
			
//			for(int count=0; count< cluster.size(); count++)
//			{
//				System.out.println( "Element: " + cluster.get(count) );
				//System.out.println( "Element: " + cluster.get(count) + cluster. );
//			}
			
			//Clusters clusters = clusterer.getClusters();

/*			System.out.println("All clusters");
			Map<Integer, Cluster> map = clusterer.getClusters().getAllClusters();
			for (Map.Entry<Integer, Cluster> entry : map.entrySet())
			{
			    Cluster localCluster = (Cluster) entry.getValue();
			    
			    System.out.println("Cluster " + entry.getKey() + " : " + localCluster.getArea());
			    
				for(int count=0; count< localCluster.getAll().size(); count++)
				{
					System.out.println( "Element: " + localCluster.getAll().get(count) );
					//System.out.println( "Element: " + cluster.get(count) w+ cluster. );
				}
			} */
		}
		else
		{
			Parser parser = new Parser();
			int numInterest = 10;
			
			filePathTrainning = System.getProperty("user.dir") + "\\interestNodes\\interesses_reality\\interesses_reality"+numInterest+".arff";
			
		//	String grupo_intermediario = clusterer.evaluateInstanceEM( filePathTrainning, data); -- gilmara comentado porque adicionei paramentros
			
		//	ArrayList clusterAtual = clusterer.getEMClusterElements( filePathTrainning, grupo_intermediario);
			
		///	System.out.println(" Test bug, N�mero interesse: "+ numInterest + " grupo intermediario " + grupo_intermediario);
			
		//	System.out.println(" Size of interests elements "+clusterAtual.size());
			
			//ArrayList<Integer> cluster = clusterer.getKMeansClusterElements_Measured( filePathTrainning, "BroadbandAccessTechnologies", numInterest, true );
			//ArrayList<Integer> cluster = clusterer.getEMClusterElements_Measured( filePathTrainning, "BroadbandAccessTechnologies", true );
			
			Clusters clusters = new Clusters();
			
			//ArrayList<Integer> cluster = clusterer.runEM( filePathTrainning, "BroadbandAccessTechnologies" );
			
			//for(int count=0; count< groupsEM.size(); count++)
			//{
			//	System.out.println( "Element Areas: " + groupsEM.get(count) );
			//}
			
			/*
			int numberOfClusters = 5;
			
			ArrayList<Integer> cluster = new ArrayList<Integer>(); 
			
			String areaKMeans = clusterer.evaluateInstanceKMeans( filePathTrainning, data, numberOfClusters );
			
			System.out.println( "KMeans Group: " + areaKMeans );
			
			cluster = clusterer.getKMeansClusterElements( filePathTrainning, areaKMeans, numberOfClusters );
			
			for(int count=0; count< cluster.size(); count++)
			{
				System.out.println( "Element Cluster KMeans: " + cluster.get(count) );
				//System.out.println( "Element: " + cluster.get(count) + cluster. );
			}
			*/
			
			/*
			String groupsEM = clusterer.evaluateInstanceEM( filePathTrainning, data );
			
			System.out.println("Area Group EM: "+ groupsEM );
			
			cluster = clusterer.getEMClusterElements( filePathTrainning, groupsEM );
			
			for(int count=0; count< cluster.size(); count++)
			{
				System.out.println( "Element Cluster EM: " + cluster.get(count) );
				//System.out.println( "Element: " + cluster.get(count) + cluster. );
			}
			*/
		}
	}
}
