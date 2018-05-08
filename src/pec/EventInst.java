package pec;


/*	Classe criada só para efeitos de teste
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
