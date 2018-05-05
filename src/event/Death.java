package event;

import pec.IEvent;
import simulation.*;

public class Death extends Event_Individual{
	
	//Constructor 
	public Death(Simulation sim, Individual individual) {
		super(expRandom((1-Math.log(1-individual.Comfort()))*sim.getRparam()), sim, individual);
	} 
	
	//Redefinition of the method execute() inherited from IEvent
	public IEvent execute(){		
		//Updating the values of the Individual associated with the event being executed
		this.individual.setDeath(true);
		//Call to the corresponding method of the individual associated with the event being executed 
		this.individual.kill();
		//Don't return new events
		return null; 
	}

	//Redefinition of the method time() inherited from Event_Individual
	public double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(1-this.individual.Comfort()))*this.sim.getDparam()); 
		return time; 
	}
		
	@Override
	public String toString() {
		return "Death: [time=" + this.time + "]";
	}

	
}
