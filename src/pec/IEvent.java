package pec;

/**
 * Interface representing the contract to be used
 * by classes of other packages to interact
 * with a general event. Assumes that a variable
 * representing the time-stamp is added
 * in inheriting classes
 *
 */
public interface IEvent extends Comparable<IEvent>{
	/**
	 * Run an event main logic 
	 * @return a new event of the same type
	 * with new parameters
	 */
	public IEvent[] execute();
	
	/**
	 * Get the time-stamp variable
	 * @return the variable representing the time-stamp  
	 */
	public double getTime(); 
}
