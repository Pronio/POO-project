package pec;


/*	Classe criada s� para efeitos de teste
 * 
 * 
 * ****/
class EventInst extends Event{

	public EventInst(double t) {
		super(t);
	}
	
	public IEvent[] execute(){
		System.out.println("Executed Event...");
		return null;
	}

}
