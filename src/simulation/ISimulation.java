package simulation;

/**
 * ISimulation interface is used for population development simulations and has the methods used to print reports based on the simulation actual state and to get simulation general parameters
 * that define the behavior of the simulation  
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author José Heraldo (82414)
 */
public interface ISimulation {

	/**
	 * Print to terminal the final report of the simulation
	 */
	public void finalStats();
	
	/**
	 * Print to terminal an intermediate report with a time stamp
	 * @param t 
	 * Time-stamp to be printed to the terminal 
	 */
	public void stats(double t);
	
	/**
	 * Get the value of the sensibility used to calculate new time stamps for move events 
	 * @return move sensibility constant 
	 */
	public int GetMove();
	
	/**
	 * Get the value of the sensibility used to calculate new time stamps for death events 
	 * @return death sensibility constant 
	 */
	public int GetDeath();
	
	/**
	 * Get the value of the sensibility used to calculate new time stamps for reproduction events 
	 * @return reproduction sensibility constant 
	 */
	public int GetReproduction();
	
	/**
	 * Get the value of the final instant of the simulation 
	 * @return final instant 
	 */
	public double GetTmax();    
}
