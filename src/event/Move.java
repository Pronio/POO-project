package event;

import pec.IEvent;
import simulation.IIndividual;
import simulation.ISimulation;

public class Move extends Event_Individual{

	//Constructor 
	public Move(ISimulation sim, IIndividual individual) {
		super(expRandom((1-Math.log(individual.Comfort()))*sim.GetMove()), sim, individual);
	}
	public Move(double time, ISimulation sim, IIndividual individual) {
		super(time, sim, individual);
	}


	//Redefinition of the method execute() inherited from IEvent
	public IEvent[] execute(){
		//Call to the corresponding method of the individual associated with the event being executed and verifies if the individual has died 
		IEvent[] e = new IEvent[1];
		if(this.individual.move()) { 
			//Returning the new move event to be added to the PEC 
			e[0] = new Move(this.time(), this.sim, this.individual); 
		//Doesn't return a new event to be added to the PEC
		}else { 
			e[0] = null; 
		}
		return e; 
	}

	//Redefinition of the method time() inherited from Event_Individual
	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.sim.GetMove()); 
		return time; 
	}
	@Override
	public String toString() {
		return "Move: [time=" + this.time + "] Individual: "+this.individual;
	}

}
