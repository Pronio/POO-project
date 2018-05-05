package simulation;

public class Individual implements IIndividual{
	private boolean death;
	private int cost, length; 


	public Individual(boolean death) {
		super();
		this.setDeath(death);
	}
	
	public double Comfort() {
		return 0.0; 
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
