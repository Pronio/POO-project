package pec;

class EventInst extends Event{

	public EventInst(double t) {
		super(t);
	}
	
	public IEvent execute(){
		System.out.println("Executed Event...");
		return null;
	}

}
