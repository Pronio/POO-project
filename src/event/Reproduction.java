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
	public IEvent[] execute(){
		
		IIndividual child;
		IEvent[] e; 
		//Verifies if the individual has died and call to the corresponding method of the individual associated with the event being executed
		if((child = this.individual.reproduction())!=null) {
			e = new IEvent[4]; 
			//Parent next reproduction
			e[0] = new Reproduction(this.time(), sim, individual);
			//Child initialization
			e[1] = new Reproduction(this.time(), sim, child); 
			e[2] = new Move(this.timeM(), sim, child); 
			e[3] = new Death(this.timeD(), sim, child); 	

		}
		else {
			e = new IEvent[1]; 
			e[0] = null; 
		}
		return e;		 
	}
	

	//Redefinition of the method time() inherited from Event_Individual
	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.sim.GetReproduction()); 
		return time; 
	}
	double timeM(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.sim.GetMove()); 
		return time;
	}
	double timeD(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(1-this.individual.Comfort()))*this.sim.GetDeath()); 
		return time;		
	}
	@Override
	public String toString() {
		return "Reproduction: [time=" + this.time + "]";
	}


	
}
