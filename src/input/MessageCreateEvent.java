/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package input;

import java.util.ArrayList;
import java.util.Random;

import clusterizacao.Clusterer;
import core.DTNHost;
import core.Message;
import core.World;
import routing.GPDMI;
import routing.InterestNodes;


/**
 * External event for creating a message.
 */
public class MessageCreateEvent extends MessageEvent {
	private int size;
	private int responseSize;
	
	
	//VARI�VEIS DE CONTROLE DO PROTOCOLO
		public static int contMensagem = 0;	
		public static int indiceMensagem = 1;
		Random gerador = new Random();
        Random geradorNosEnergia = new Random();
		
		public static int indice_classificacao = 0;
        public static int indice_nivel_energia = 0;
		public static int[] classificacao_msg;
        public static int[] nosEnergia;
		
		//public Integer[] ids_cluster = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		public ArrayList<Integer> cluster;
	    public ArrayList<Integer> cluster_energia;
		//

        // range de niveis dos nós
        int minOtimo = 75;
        int maxOtimo = 99;
        int minBom = 50;
        int maxBom = 76;
        int minRegular = 25;
        int maxRegular = 51;
        int minPessimo = 0;
        int maxPessimo = 26;
        //

	/**
	 * Creates a message creation event with a optional response request
	 * @param from The creator of the message
	 * @param to Where the message is destined to
	 * @param id ID of the message
	 * @param size Size of the message
	 * @param responseSize Size of the requested response message or 0 if
	 * no response is requested
	 * @param time Time, when the message is created
	 */
	public MessageCreateEvent(int from, int to, String id, int size,
			int responseSize, double time) {
		super(from,to, id, time);
		this.size = size;
		this.responseSize = responseSize;
	}


	/**
	 * Creates the message this event represents.
	 */
	@Override
	public void processEvent(World world) {		
		int node_origem = 0;

		
		//CONTROLA O N�MERO DE MENSAGENS QUE S�O SOLICITADAS
		//if(contMensagem == 10 || contMensagem == 20 || contMensagem == 30 || contMensagem == 40 || contMensagem == 50 || contMensagem == 60 || contMensagem == 70){
			//GERA A CLASSIFICA��O DA MENSAGEM 
			classificacao_msg = new int[36];	//GERA O ARRAY PARA ARMAZENAR A CLASSIFICA��O DA MENSAGEM
			indice_classificacao = gerador.nextInt(35); //GERA O iNDICE DE CLASSIFICAO QUE A MENSAGEM IR� TER
			//indice_classificacao = 0;
			System.out.println("indice Classificacao: " + indice_classificacao);
			//PREENCHE O ARRAY DE CLASSIFICA��O DA MENSAGEM
			for (int j = 0; j < classificacao_msg.length; j++) {
				if(j == indice_classificacao){
					classificacao_msg[j] = 1;
					
				}else{
					classificacao_msg[j] = 0;
				}
				
				System.out.print(classificacao_msg[j]+"|");
				
			}
			System.out.println();

			//CHAMA O GPDMI GERA GRUPO DE INTERESSES
		//cluster = GPDMI.geraGrupoDeInteressePrincipal(this.id, 30, classificacao_msg);
		//GPDMI.geraGrupoDeInteresseIntermediario(this.id, 30, classificacao_msg);
			cluster = GPDMI.geraGrupoDeInteressePrincipal(this.id, 30, classificacao_msg);

			GPDMI.geraGrupoDeInteresseIntermediario(this.id, 30, classificacao_msg);
            System.out.println();
			// CRIACAO DO GRUPO INTERMEDIARIO DE ENERGIA
            nosEnergia = new int[2];
		    //nosEnergia = new int[1];
           indice_nivel_energia = 0;
            for (int i = 0; i < nosEnergia.length; i++) {
                if (indice_nivel_energia == 0) {
                    if (i == indice_nivel_energia) {
                      //  nosEnergia[i] = geradorNosEnergia.nextInt((maxOtimo - minBom) + 1) + minBom;
						nosEnergia[i] = geradorNosEnergia.nextInt((maxOtimo - minBom) + 1) + minBom; //50-100
                    } else {
                       nosEnergia[i] = 0;
                    }
                }
                System.out.print(nosEnergia[i] + "|");
            }

		    GPDMI.geraGrupoDeNosIntermediarioEnergia(this.id, 3, nosEnergia);
						
			System.out.println();
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			//GERA O INDICE DO NO DE ORIGEM QUE NAO ESTA DENTRO DO CLUSTER 
			//idNodeOrigem = gerador.nextInt(cluster.size());
			node_origem = gerador.nextInt(cluster.size());
			
			//System.out.println("No de Origem " + node_origem + " esta no cluster? : " + cluster.contains(node_origem));
						
			while(cluster.contains(node_origem)){
				//System.out.println("No de origem esta no cluster");
				node_origem = gerador.nextInt(cluster.size());
			}
			//GERA OS NODES DE ORIGEM E DESTINO ALEATORIAMENTE
			int ind_destino = 0;
			
			//if(ind_destino == 0){
				ind_destino = gerador.nextInt(cluster.size());
			//}
					
			//System.out.println("Cluster que veio da classe:");
			GPDMI.imprimeClusterPrincipal(this.id);			
			System.out.println();
			GPDMI.imprimeClusterIntermediario(this.id);
            System.out.println();
			GPDMI.imprimeClusterIntermediarioEnergia(this.id);

			//System.out.println("Tamanho do Cluster Local: " + cluster.size());
			//System.out.print("Cluster Local: ");
			//for (int i = 0; i < cluster.size(); i++) {
			//	System.out.print("|" + cluster.get(i) + "|");
			//}
			
			System.out.println();

			
			//int ind_node_destino = cluster.indexOf(node_destino);

			DTNHost to = world.getNodeByAddress(cluster.get(ind_destino));
			DTNHost from = world.getNodeByAddress(node_origem);
			
			System.out.println("Origem: " + node_origem);
		    System.out.println("Destino: " + cluster.get(ind_destino));
		    System.out.println("Indice do no de destino: " + ind_destino);

			Message m = new Message(from, to, this.id, this.size);
			m.setIndice_calssificacao(indice_classificacao);
							
			m.setResponseSize(this.responseSize);
			from.createNewMessage(m);
					
			
			System.out.println();
			System.out.println("INCIA ROTEAMENTO:");
			System.out.println();
			
	//	}
		
		//contMensagem++;
		
		
	}

	@Override
	public String toString() {
		return super.toString() + " [" + fromAddr + "->" + toAddr + "] " +
		"size:" + size + " CREATE";
		
	}
}
