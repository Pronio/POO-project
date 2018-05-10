package pec;

/**
 * Implements getTime method from IEvent interface 
 * & declares a double time variable,
 * representing the time-stamp.
 * The interface method execute() is declared as abstract
 * to be implemented by more specific events. 
 */
public abstract class Event implements IEvent{
	
	protected final double time;
	
	public Event(double t){
		time = t;
	}
	public double getTime() {
		return time; 
	}
	public int compareTo(IEvent e){
		if(time > ((Event)e).time) return 1;
		else if(time == ((Event)e).time) return 0;
		else return -1;
	}
	
	@Override
	public String toString() {
		return "Event [time=" + time + "]";
	}

	abstract public IEvent[] execute();

}
