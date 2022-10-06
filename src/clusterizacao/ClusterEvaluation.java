package clusterizacao;


import java.io.BufferedReader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class ClusterEvaluation {

    HashMap<String, HashSet<Integer>> classes;
    HashMap<String, HashSet<Integer>> clusters;
    Instances adataset; // annotated dataset
    Integer n; // number of instances

    public ClusterEvaluation(String dataset) throws Exception {
    	
    	
        this.classes = new HashMap<String, HashSet<Integer>>();
        this.clusters = new HashMap<String, HashSet<Integer>>();
        
        
        BufferedReader reader = new BufferedReader(new FileReader(dataset));
        
        
        this.adataset = new Instances(reader);
        
        System.out.println( "test class:" + this.adataset.getRevision() );
        System.out.println( "numAttributes:" + this.adataset.numAttributes() );
        //System.out.println( "numAttributes:" + this.adataset. );
        
        reader.close();
        
        
        //Attribute aClass = adataset.attribute("@@class@@");
        //Attribute aCluster = adataset.attribute("cluster");
        
        Attribute aClass = adataset.attribute( "class" );
        Attribute aCluster = adataset.attribute( "cluster" );
        //Attribute aCluster = adataset.attribute("origin");
        
        
        n = 0;
        for (Iterator<Instance> itr = adataset.iterator(); itr.hasNext();) {
            Instance i = itr.next();
            
            
            n++;
            //String iclass = i.stringValue(aClass); 		//original code
            //String icluster = i.stringValue(aCluster);	//original code
            
            String iclass = i.stringValue(aClass);
            String icluster = i.stringValue(aCluster);
            
            if(!iclass.equals("?"))
            {
                HashSet<Integer> curr_classes = classes.get(iclass);
                if (curr_classes == null)
                    curr_classes = new HashSet<Integer>();
                curr_classes.add(n);
                classes.put(iclass, curr_classes);
                HashSet<Integer> curr_clusters = clusters.get(icluster);
                if (curr_clusters == null)
                    curr_clusters = new HashSet<Integer>();
                curr_clusters.add(n);
                clusters.put(icluster, curr_clusters);	
            }
        }
        
        System.out.println("--------Clusters--------");
        for (Entry<String, HashSet<Integer>> cluster : clusters.entrySet())
            System.out.println("\t" + cluster.getKey() + ": "
                    + cluster.getValue().size());
        System.out.println("--------Classes--------");
        for (Entry<String, HashSet<Integer>> cl : classes.entrySet())
            System.out
                    .println("\t" + cl.getKey() + ": " + cl.getValue().size());
                    
    }

    private int getIntersect(HashSet<Integer> a, HashSet<Integer> b) {
        HashSet<Integer> clone = new HashSet<Integer>(a);
        clone.retainAll(b);
        return clone.size();
    }

    public double getPurity() {
        double p = 0.0;
        for (HashSet<Integer> icluster : clusters.values()) {
            double max = 0.0;
            for (HashSet<Integer> iclass : classes.values()) {
                double intersect = getIntersect(icluster, iclass);
                if (intersect > max)
                    max = intersect;
            }
            p += max;
        }
        return p / n;
    }

    public double getPartitionCoefficient() {
        double pc = 0.0;
        for (HashSet<Integer> icluster : clusters.values()) {
            for (HashSet<Integer> iclass : classes.values()) {
                double intersect = getIntersect(icluster, iclass);
                intersect /= icluster.size();
                pc += Math.pow(intersect, 2);
            }
        }
        return pc / (clusters.size() * classes.size());
    }

    public double getEntropy() {
        double e = 0.0;
        for (HashSet<Integer> icluster : clusters.values()) {
            double r = (double) icluster.size() / n;
            r *= (-1.0) / Math.log(classes.size());
            for (HashSet<Integer> iclass : classes.values()) {
                double intersect = getIntersect(icluster, iclass);
                intersect /= icluster.size();
                if (intersect > 0)
                    intersect *= Math.log(intersect);
                e += r * intersect;
            }
        }
        return e;
    }

    public double getFmeasure() {
        double f = 0.0;
        for (HashSet<Integer> icluster : clusters.values()) {
            double max = 0.0;
            for (HashSet<Integer> iclass : classes.values()) {
                double intersect = getIntersect(icluster, iclass);
                double p = intersect / icluster.size();
                double r = intersect / iclass.size();
                double fm = 2 * p * r / (p + r);
                if (fm > max)
                    max = fm;
            }
            f += icluster.size() * max / n;
        }
        return f;
    }

    public static void main(String[] args) throws Exception {
    	
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interest_EM_Simple.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\one_reality_simpleTemp_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo_Clusterizacao\\fromItalyTraining_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo_Clusterizacao\\realityDataTraining_Measured.arff";
    	
    	String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom5_EM_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality20_Measured.arff";
    	
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality20_EM_Measured.arff";
    	
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom10_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom15_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_infocom20_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality5_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality10_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality15_Measured.arff";
    	//String WekaFile = "C:\\Ronaldo\\Doctorate_Materias\\Redes_Intermitentes\\artigo\\interesses_reality20_Measured.arff";
    	
    	
    	System.out.println("Cluster Evaluation File: " + WekaFile);
    	ClusterEvaluation ceval = new ClusterEvaluation(WekaFile);
        System.out.println(" Fmeasure: " + ceval.getFmeasure());
        System.out.println(" Purity:  " + ceval.getPurity());
        System.out.println(" PartitionCoefficient: " + ceval.getPartitionCoefficient());
        System.out.println(" Entropy: " + ceval.getEntropy());
       
    }

}
