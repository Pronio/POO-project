package simulation;

public interface ISimulation {

	public void finalStats();
	public void stats(double t);
	public int GetMove();
	public int GetDeath();
	public int GetReproduction();
	public double GetTmax();    
}
