package event;
import pec.IEvent;
import simulation.IIndividual;
import simulation.ISimulation; 

public class Reproduction extends Event_Individual{

	//Constructor 
	public Reproduction(ISimulation sim, IIndividual individual){
		super(expRandom((1-Math.log(individual.Comfort()))*sim.GetReproduction()), sim, individual);
	}
	public Reproduction(double time, ISimulation sim, IIndividual individual){
		super(time, sim, individual);
	}
	
	//Redefinition of the method execute() inherited from IEvent
	public IEvent execute(){
		
		IIndividual child;
		
		//Verifies if the individual has died and call to the corresponding method of the individual associated with the event being executed
		if((child = this.individual.reproduction())!=null) {
					
		
			//Returning the new move event to be added to the PEC
			return new Reproduction(this.time(), this.sim, child); 			
		}
		//Doesn't return a new event to be added to the PEC
		else
			return null; 
	}
	

	//Redefinition of the method time() inherited from Event_Individual
	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.sim.GetReproduction()); 
		return time; 
	}
	@Override
	public String toString() {
		return "Reproduction: [time=" + this.time + "]";
	}


	
}
