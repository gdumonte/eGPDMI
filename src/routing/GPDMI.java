package routing;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import clusterizacao.Clusterer;

public class GPDMI {
	
	//Arquivvos de treinamento
	public static String dir = System.getProperty("user.dir") + "\\interestNodes\\interesses_reality\\";
	public static String direnergia = System.getProperty("user.dir") + "\\interestNodes\\energia_reality\\";
	//public static String dir = System.getProperty("user.dir") + "\\interestNodes\\interesses_infocom\\";
	public static String dir_salvar = System.getProperty("user.dir") + "\\reports\\reality_atual\\teste\\50-100\\0\\";
	//public static String dir_salvar = System.getProperty("user.dir") + "\\reports\\reality_atual\\gilmara\\75-100\\0\\";
	// public static String dir_salvar = System.getProperty("user.dir") + "\\reports\\infocom_on\\Prophet\\1\\";
	//public static String dir_salvar = System.getProperty("user.dir") + "\\reports\\reality_on\\Prophet\\1\\";
	public static Map<String, ArrayList<Integer>> cluster_intermediario = new HashMap<String, ArrayList<Integer>>();
	public static Map<String, ArrayList<Integer>> cluster_principal = new HashMap<String, ArrayList<Integer>>();
	public static Map<String, ArrayList<Integer>> cluster_energia = new HashMap<String, ArrayList<Integer>>();

	public static Map<String, ArrayList<Integer>> entregas_grupo = new HashMap<String, ArrayList<Integer>>();
	public static int num_nos_grupo_principal;
	
	
	public static void geraGrupoDeInteresseIntermediario(String id_mensagem, int num_interesse, int[] classificacao_msg){
		System.out.println("N de Interesses: " + num_interesse);
		String filePathTrainning = null;
		
		switch(num_interesse){
			case 1:
				filePathTrainning = dir + "interesses_reality1.arff";
				//filePathTrainning = dir + "interesses_infocom1.arff";
				break;
			case 5:
				filePathTrainning = dir + "interesses_reality5.arff";
				//filePathTrainning = dir + "interesses_infocom5.arff";
				break;
			case 10:
				filePathTrainning = dir + "interesses_reality10.arff";
				//filePathTrainning = dir + "interesses_infocom10.arff";
				break;
			case 15:
				filePathTrainning = dir + "interesses_reality15.arff";
				//filePathTrainning = dir + "interesses_infocom15.arff";
				break;
			case 20:
				filePathTrainning = dir + "interesses_reality20.arff";
				//filePathTrainning = dir + "interesses_infocom20.arff";
				break;
			case 25:
				filePathTrainning = dir + "interesses_reality25.arff";
				//filePathTrainning = dir + "interesses_infocom25.arff";
				break;
			case 30:
				//filePathTrainning = dir + "infocom6_interesses30.arff";
				filePathTrainning = dir + "interesses_reality30.arff";
				//filePathTrainning = dir + "interesses_infocom30.arff";		
				break;
			
		}
		
		//Instancia o clusterizador
		Clusterer clusterer = new Clusterer();
		//Cria o arraylist que recebe a classificacao da mensagem
		ArrayList<Integer> data = new ArrayList<Integer>();
		//Insere a classificacao no arraylist
		for (int i = 0; i < classificacao_msg.length; i++) {
			data.add(classificacao_msg[i]);
		}		
		
		System.out.println();
		
		//Envia para o clusterizaro o arquivo de treinamento e a classificacao da mensagem
		//Retorna o nome do grupo formado na clusterizacao
		
		String grupo_intermediario = clusterer.evaluateInstanceEM( filePathTrainning, data, 6, 38);
		System.out.println("Grupo Intermediario: " + grupo_intermediario);
		
				
		//Retorna o array list com os IDS dos nodos que fazem parte do grupo intermediario
		ArrayList clusterAtual = clusterer.getEMClusterElements( filePathTrainning, grupo_intermediario, 6, 38);
		
			
		//cluster_intermediario.put(id_mensagem, clusterer.getEMClusterElements( filePathTrainning, grupo_intermediario));
		cluster_intermediario.put(id_mensagem, clusterAtual);
		System.out.println("N de Nodes Intermediario: " + cluster_intermediario.get(id_mensagem).size());
		
		/*
		String grupo_intermediario = clusterer.evaluateInstanceEM( filePathTrainning, data);
		ArrayList clusterAtual = clusterer.getEMClusterElements( filePathTrainning, grupo_intermediario);
		System.out.println(" Test bug, Numero interesse: "+ num_interesse + " grupo intermediario " + grupo_intermediario);
		System.out.println(" Size of interests elements "+clusterAtual.size());
			*/
	}	
	
	
	public static ArrayList<Integer> geraGrupoDeInteressePrincipal(String id_mensagem, int num_interesse, int[] classificacao_msg){
		String filePathTrainning = null;
		
		switch(num_interesse){
		case 1:
			filePathTrainning = dir + "interesses_reality1.arff";
			//filePathTrainning = dir + "interesses_infocom1.arff";
			break;
		case 5:
			filePathTrainning = dir + "interesses_reality5.arff";
			//filePathTrainning = dir + "interesses_infocom5.arff";
			break;
		case 10:
			filePathTrainning = dir + "interesses_reality10.arff";
			//filePathTrainning = dir + "interesses_infocom10.arff";
			break;
		case 15:
			filePathTrainning = dir + "interesses_reality15.arff";
			//filePathTrainning = dir + "interesses_infocom15.arff";
			break;
		case 20:
			filePathTrainning = dir + "interesses_reality20.arff";
			//filePathTrainning = dir + "interesses_infocom20.arff";
			break;
		case 25:
			filePathTrainning = dir + "interesses_reality25.arff";
			//filePathTrainning = dir + "interesses_infocom25.arff";
			break;
		case 30:
			//filePathTrainning = dir + "infocom6_interesses30.arff";
			filePathTrainning = dir + "interesses_reality30.arff";
			//filePathTrainning = dir + "interesses_infocom30.arff";
			break;
		
	}
	
		
		//Instancia o clusterizaro
		Clusterer clusterer = new Clusterer();
		//Cria o array list que recebe a classificao da mensagem
		ArrayList<Integer> data = new ArrayList<Integer>();
		// Inseri a classificacao no arraylist
		for (int i = 0; i < classificacao_msg.length; i++) {
			data.add(classificacao_msg[i]);
		}		
		
		System.out.println();
		
		//Envia para o clusterizador o arquivo de treinamento e a classificacao da mensagem
		//Retorna o nome do gruipo formado na clusterizacao
					
		int num_cluster = num_interesse;
		num_cluster = 36; //numero grupos de interesse
		String grupo_principal = clusterer.evaluateInstanceKMeans( filePathTrainning, data, num_cluster );		
		System.out.println("Grupo Principal: " + grupo_principal);
		
				
		//Retorna o arraylist com os IDS dos nodos que fazem parte do grupo intermediario
		// cria o cluster energia
		cluster_principal.put(id_mensagem, clusterer.getKMeansClusterElements( filePathTrainning, grupo_principal, num_cluster ));
		System.out.println("N. de Nodos Principal: " + cluster_principal.get(id_mensagem).size());
		
		//Cria array de entregas
		ArrayList<Integer> lista_entrega = new ArrayList<>();
		entregas_grupo.put(id_mensagem, lista_entrega);			
		
		return cluster_principal.get(id_mensagem);		
	}

	//Metodo gera a clusterizacao dos grupos de energia conforme o arquivos de treinamento usando o algoritmo EM
	public static void geraGrupoDeNosIntermediarioEnergia(String id_mensagem, int nivel_energia, int[] nosEnergia) {
		String filePathTrainning = null;

		switch (nivel_energia) {
			case 3:
				filePathTrainning = direnergia + "teste50.arff";   //energy reality
				//filePathTrainning = direnergia + "energy5.arff";   //energy reality
				//filePathTrainning = direnergia + "energyInfocom.arff";
				break;
		}
		Clusterer clusterer = new Clusterer();
		ArrayList<Integer> data = new ArrayList<Integer>();

		for (int i = 0; i < nosEnergia.length; i++) {
			data.add(nosEnergia[i]);
		}
		System.out.println();
		String grupo_intermediario = clusterer.evaluateInstanceEM(filePathTrainning, data, 4, 4);
		//String grupo_intermediario = clusterer.evaluateInstanceEM(filePathTrainning, data, 3, 3);

		System.out.println("Grupo de Agrupamento EM (Intermediario de Energia ): " + grupo_intermediario);
		ArrayList clusterAtualEnergia = clusterer.getEMClusterElements(filePathTrainning, grupo_intermediario, 4,4);
		//ArrayList clusterAtualEnergia = clusterer.getEMClusterElements(filePathTrainning, grupo_intermediario, 3,3);

		cluster_energia.put(id_mensagem, clusterAtualEnergia);
		System.out.println("N. de Nodes Intermediarios EM Energia: " + cluster_energia.get(id_mensagem).size());

	}


	// Metodo verifica se o identificador do idNode esta dentro do grupo cluster intermediario.
	public static boolean isGrupoIntermediario(String id_mensagem, int idNode, int idNodeDestination){
		for (int i = 0; i < cluster_intermediario.get(id_mensagem).size(); i++) {
			if(cluster_intermediario.get(id_mensagem).get(i) == idNode){
		    if (idNode == idNodeDestination) {
		    	return true;
			} else {
				for (int j = 0; j < cluster_energia.get(id_mensagem).size(); j++){
					if (cluster_energia.get(id_mensagem).get(j) == idNode){
						return true;
					}
				}
			}
				//return true;
			}
		}
		return false;
	}
	// Metodo verifica se o identificador do Node esta dentro do grupo cluster principal.
	public static boolean isGrupoPrincipal(String id_mensagem, int idNode){
		for (int i = 0; i < cluster_principal.get(id_mensagem).size(); i++) {
			if(cluster_principal.get(id_mensagem).get(i) == idNode){
				boolean existe = false;
				for (int j = 0; j < entregas_grupo.get(id_mensagem).size(); j++) {
					if(entregas_grupo.get(id_mensagem).get(j) == idNode){
						existe = true;
					}
				}
				if(!existe){
					entregas_grupo.get(id_mensagem).add(idNode);
				}
							
				return true;
			}
		}
		
		return false;
			
		
	}
	//Metodo imprime no arquivo as mensagens entregues por grupo
	public static void imprimeEntregas(){
		
		FileWriter arq;
		try {
			LocalDateTime agora = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			String agoraFormatado = agora.format(formatter);
			String name = "info"+agoraFormatado+".txt";
			arq = new FileWriter(dir_salvar + name);
		//	arq = new FileWriter(dir_salvar + "info.txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			
			Iterator<Map.Entry<String, ArrayList<Integer>>> entries = entregas_grupo.entrySet().iterator();
			while (entries.hasNext()) {
			  Map.Entry<String, ArrayList<Integer>> entry = entries.next();
			  String id_mensagem = entry.getKey();
			  ArrayList<Integer> lista_de_entregas = entry.getValue();
			  
			  for (int i = 0; i < lista_de_entregas.size(); i++) {			
					System.out.print("|" + lista_de_entregas.get(i) + "|");
			  }
			  
			  System.out.println();
			  double var1 = entregas_grupo.get(id_mensagem).size();			
			  double var2 = cluster_principal.get(id_mensagem).size();						
			  double porc_entregas_grupo = var1/var2;
			  
			  System.out.println();
			  System.out.println("N. de nodos: " + cluster_principal.get(id_mensagem).size());
			  System.out.println("N. entregas no grupo: " + entregas_grupo.get(id_mensagem).size());
			  System.out.println("Entregas no grupo: " + porc_entregas_grupo);
			  
			  
			  gravarArq.printf("Total Nodos Grupo	|	Total Entregas Grupo	|	Porc Entregas Grupo %n");
			  gravarArq.printf(cluster_principal.get(id_mensagem).size() + "	|	" + entregas_grupo.get(id_mensagem).size() + "	|	" + porc_entregas_grupo + "%n");

			  
			}
			
			arq.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
	//Metodos que imprimem no debug o tamanho de cada cluster formado
	public static void imprimeClusterPrincipal(String id_mensagem){
		System.out.println("Tamanho do Cluster Principal: " + cluster_principal.get(id_mensagem).size());
		System.out.print("Cluster Principal: ");
		for (int i = 0; i < cluster_principal.get(id_mensagem).size(); i++) {
			System.out.print("|" + cluster_principal.get(id_mensagem).get(i) + "|");
		}
		
		System.out.println();
	}
	
	public static void imprimeClusterIntermediario(String id_mensagem){
		System.out.println("Tamanho do Cluster Intermediario: " + cluster_intermediario.get(id_mensagem).size());
		System.out.print("Cluster Intermediario: ");
		for (int i = 0; i < cluster_intermediario.get(id_mensagem).size(); i++) {
			System.out.print("|" + cluster_intermediario.get(id_mensagem).get(i) + "|");
		}
		
		System.out.println();
	}

	public static void imprimeClusterIntermediarioEnergia(String id_mensagem){
		System.out.println("Tamanho do Cluster Intermediario Energia: " + cluster_energia.get(id_mensagem).size());
		System.out.print("Cluster Intermediario Energia: ");
		for (int i = 0; i < cluster_energia.get(id_mensagem).size(); i++) {
			System.out.print("|" + cluster_energia.get(id_mensagem).get(i) + "|");
		}

		System.out.println();
	}


	
		
}
