package pec;

/**
 * Implements getTime method from IEvent interface 
 * &amp; declares a double time variable,
 * representing the time-stamp.
 * The interface method execute() is declared as abstract
 * to be implemented by more specific events. 
 */
public abstract class Event implements IEvent{
	
	/**
	 * Time stamp variable for events
	 */
	protected final double time;
	
	/**
	 * Constructor for event class 
	 * @param t time stamp value to be held by Event
	 */
	public Event(double t){
		time = t;
	}
	
	/**
	 * Get time variable (time stamp)
	 */
	public double getTime() {
		return time; 
	}
	
	/**
	 * Implementation of compareTo method from Comparable interface.
	 * @param e IEvent object to be compared to this method instance
	 * @return 1 if calling object has time stamp value greater than parameter 
	 * 			object time stamp; 0 if equal or -1 if calling object time stamp is less
	 * 			than the parameter object's. 
	 */
	public int compareTo(IEvent e){
		if(time > ((Event)e).time) return 1;
		else if(time == ((Event)e).time) return 0;
		else return -1;
	}
	
	/**
	 * Print Event object information
	 */
	@Override
	public String toString() {
		return "Event [time=" + time + "]";
	}
	
	/**
	 * Abstract method declared in interface, to be implemented in subclasses
	 */
	abstract public IEvent[] execute();

}
