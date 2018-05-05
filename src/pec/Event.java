package pec;

abstract class Event implements IEvent{
	
	protected double time;

	public Event() {}
	
	abstract public IEvent execute();

}
