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
	
	
	@Override
	public String toString() {
		return "Event [time=" + time + "]";
	}

	abstract public IEvent execute();

}
