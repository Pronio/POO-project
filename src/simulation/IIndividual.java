package simulation;

/**
 * IIndividual interface complements this package with methods to act over individuals of a population development simulation, 
 * all the action of the simulation is centered in these methods, these are the behaviors that define the development of the simulation and ultimately
 * the result
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public interface IIndividual {
	
	/**
	 * Moves the individual to another point on the map
	 * @return true if the operation was successful, false if the individual is already dead 
	 */
	public boolean move();
	
	/**
	 * Create a new individual that share some characteristics with the parent individual
	 * @return The new Individual or null if the parent individual (this) is already death
	 */
	public IIndividual reproduction();
	
	/**
	 * Calculate the comfort of an individual, this parameter is based on several characteristics of the ongoing simulation
	 * including the past actions of this individual
	 * @return Comfort value
	 */
	public double Comfort();
	
	/**
	 * Kill the individual and remove it from the simulation, after this method execution over an individual, no other action can be made by that individual
	 */
	public void kill();

}
