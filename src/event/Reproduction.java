package event;
import pec.IEvent;
import simulation.Individual;
import simulation.Simulation; 

public class Reproduction extends Event_Individual{

	//Constructor 
	public Reproduction(Simulation sim, Individual individual) {
		super(expRandom((1-Math.log(individual.Comfort()))*sim.getRparam()), sim, individual);
	}
	public Reproduction(double time, Simulation sim, Individual individual){
		super(time, sim, individual);
	}
	
	//Redefinition of the method execute() inherited from IEvent
	public IEvent execute(){
		//Call to the corresponding method of the individual associated with the event being executed
		this.individual.reproduction(); 
		//Returning the new move event to be added to the PEC
		return new Reproduction(this.time(), this.sim, this.individual); 
	}
	


	//Redefinition of the method time() inherited from Event_Individual
	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.sim.getRparam()); 
		return time; 
	}
	@Override
	public String toString() {
		return "Reproduction: [time=" + this.time + "]";
	}


	
}
