package pec;

abstract class Event implements IEvent{
	
	protected final double time;
	
	public Event(double t){
		time = t;
	}
	
	public int compareTo(IEvent e){
		if(time > ((Event)e).time) return 1;
		else if(time == ((Event)e).time) return 0;
		else return -1;
	}
	abstract public IEvent execute();

}
