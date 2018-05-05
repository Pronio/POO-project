package event;

import pec.IEvent;
import simulation.Individual;
import simulation.Simulation;

public class Move extends Event_Individual{

	//Constructor 
	public Move(Simulation sim, Individual individual) {
		super(expRandom((1-Math.log(individual.Comfort()))*sim.getMparam()), sim, individual);
	}
	public Move(double time, Simulation sim, Individual individual) {
		super(time, sim, individual);
	}


	//Redefinition of the method execute() inherited from IEvent
	public IEvent execute(){
		//Call to the corresponding method of the individual associated with the event being executed
		this.individual.move(); 
		//Returning the new move event to be added to the PEC
		return new Move(this.time(), this.sim, this.individual); 
	}

	//Redefinition of the method time() inherited from Event_Individual
	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.sim.getMparam()); 
		return time; 
	}
	@Override
	public String toString() {
		return "Move: [time=" + this.time + "]";
	}

}
