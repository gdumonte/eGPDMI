package clusterizacao;


public class Host {
	
	private int origin;
	private int destination;
	private double time;
	private double downTime;
	private String classTime="";
	private String reencounter="";

	private double deltaTime;

	public Host()
	{
		
	}
	
	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	
	public String getReencounter() {
		return reencounter;
	}

	public void setReencounter(String reencounter) {
		this.reencounter = reencounter;
	}
	
	public double getDownTime() {
		return downTime;
	}

	public void setDownTime(double downTime) {
		this.downTime = downTime;
	}
	
	public double getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(double deltaTime) {
		this.deltaTime = deltaTime;
	}
	
	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}
	
	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
}
