package event;
import pec.IEvent;
import simulation.Individual; 

public class Reproduction extends Event_Individual{

	//Constructor 
	public Reproduction(Individual individual, double time) {
		this.individual = individual; 
		this.time = time; 
	}
	
	//Redefinition of the method execute() inherited from IEvent
	public IEvent execute(){
		//Call to the corresponding method of the individual associated with the event being executed
		this.individual.reproduction(); 
		//Returning the new move event to be added to the PEC
		return new Reproduction(this.individual, this.time()); 
	}
	
	//Redefinition of the method time() inherited from Event_Individual
	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort())*this.sim.getRparam())); 
		return time; 
	}

	
}
