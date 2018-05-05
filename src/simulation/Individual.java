package simulation;

public class Individual implements IIndividual{
	private boolean death;
	private double comfort; 
	private int cost, length; 

	
	public double getComfort() {
		return comfort;
	}

	public void setComfort(double comfort) {
		this.comfort = comfort;
	}

	public Individual(boolean death) {
		super();
		this.setDeath(death);
	}
	
	public double Comfort() {
		return this.comfort; 
	}
	public void kill(){
	}
	
	public void move() { 
	}
	
	public void reproduction() {
	}

	public boolean getDeath() {
		return death;
	}

	public void setDeath(boolean death) {
		this.death = death;
	}
	
	
}
