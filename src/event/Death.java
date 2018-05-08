package event;

import pec.IEvent;
import simulation.ISimulation;
import simulation.IIndividual;

public class Death extends Event_Individual{
	
	//Constructor 
	public Death(ISimulation sim, IIndividual individual) {
		super(expRandom((1-Math.log(1-individual.Comfort()))*sim.GetDeath()), sim, individual);
	} 
	
	//Redefinition of the method execute() inherited from IEvent
	public IEvent execute(){

		//Call to the corresponding method of the individual associated with the event being executed 
		this.individual.kill();

		//Doens't return new events to be added to the PEC
		return null; 
	}

	//Redefinition of the method time() inherited from Event_Individual
	public double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(1-this.individual.Comfort()))*this.sim.GetDeath()); 
		return time; 
	}
		
	@Override
	public String toString() {
		return "Death: [time=" + this.time + "]";
	}

	
}
