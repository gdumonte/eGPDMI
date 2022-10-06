package clusterizacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clusters {
	Map<Integer, Cluster> clusters = new HashMap<Integer, Cluster>(); 
	
	public Clusters()
	{
	}
	
	public void put(int key, int node, String area)
	{
		if(!clusters.containsKey(key))
		{
			Cluster cluster = new Cluster();
			cluster.add(node);
			cluster.setArea(area);
			clusters.put(key, cluster);
		}
		else
		{
			clusters.get(key).add(node);
		}
	}
	
	public void putEM(int key, int node, String area)
	{
		if(!clusters.containsKey(key))
		{
			Cluster cluster = new Cluster();
			cluster.add(node);
			cluster.addArea(area);
			clusters.put(key, cluster);
		}
		else
		{
			clusters.get(key).addArea(area);
			clusters.get(key).add(node);
		}
	}

	public Map<Integer, Cluster> getAllClusters()
	{
		return clusters;
	}
	
	public String getClusterAttribute(int index)
	{
		return clusters.get(index).getArea();
	}
	
	public ArrayList<Integer> getCluster(String area)
	{
		if (clusters == null)
		{
			return null;
		}
			
		int key=-1;
		for( int count=0; count<clusters.size(); count++)
		{
			if( clusters.containsKey(count) )
			{
				if( clusters.get(count).getArea().equals( area ))
				{
					key = count;
					break;
				}
			}
		}
		
		if(key==-1)
		{
			return new ArrayList<Integer>();
		}
		
		return clusters.get(key).getAll();
	}
	
	public ArrayList<Integer> getClusterEM(String area)
	{
		if (clusters == null)
		{
			return null;
		}
			
		int key=-1;
		for( int count=0; count<clusters.size(); count++)
		{
			
			List<String> areas = clusters.get(count).getAllAreas();
			for(int countAreas = 0; countAreas < areas.size(); countAreas++)
			{
				if( areas.get(countAreas).equals( area ))
				{
					key = count;
					break;
				}
			}
		}
		
		if(key==-1)
		{
			return new ArrayList<Integer>();
		}
		
		return clusters.get(key).getAll();
	}
	
	public ArrayList<String> getAreas(int clusterNumber)
	{
		if (clusters == null)
		{
			return null;
		}
		
		Cluster cluster = clusters.get(clusterNumber);
		
		return cluster.getAllAreas();
	}
}
