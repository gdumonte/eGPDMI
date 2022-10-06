package clusterizacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cluster {
	private ArrayList<Integer> nodes = new ArrayList<>();
	private String area;
	private ArrayList<String> areas = new ArrayList<>();
	
	public Cluster()
	{
		
	}
	
	public void add(int value)
	{
		if( !nodes.contains(value))
		{
			nodes.add(value);	
		}
	}
	
	public void addArea(String areaValue)
	{
		if( !areas.contains(areaValue))
		{
			areas.add(areaValue);	
		}
	}
	
	public ArrayList<Integer> getAll()
	{
		return nodes;
	}
	
	public ArrayList<String> getAllAreas()
	{
		return areas;
	}
	
	public void setArea(String value)
	{
		area = value;
	}
	
	public String  getArea()
	{
		return area;
	}
}
