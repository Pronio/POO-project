package simulation;

public interface ISimulation {

	public void finalStats();
	public void stats();
	public int GetMove();
	public int GetDeath();
	public int GetReproduction();
	public int getCmax();
	public int getCparam();
	public double getTmax(); 
}
