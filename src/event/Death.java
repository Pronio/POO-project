package event;

import pec.IEvent;
import simulation.ISimulation;
import simulation.IIndividual;
/**
 * Death is the class that extends the Event_Individual abstract class and has the purpose of distinguish different types of events. 
 * A Death object encapsulates the information needed to process the corresponding event and a corresponding execute method. This information includes: 
 * <ul>
 * <li> The time of the event;
 * <li> The Individual associated with the event;
 * <li> The simulation in which the event was generated.
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jos� Heraldo ()
 */
public class Death extends Event_Individual{
	
	/**
	 * Class Death constructor to be called when the event is created in simulation time 0.
	 * @param sim 
	 * Simulation in which the event was created.
	 * @param individual Individual that is going to execute the event.
	 */
	public Death(ISimulation sim, IIndividual individual) {
		super(expRandom((1-Math.log(1-individual.Comfort()))*sim.GetDeath()), sim, individual);
	} 
	
	/**
	 * Class Death constructor to be called at a given time in the simulation.
	 * @param sim 
	 * Simulation in which the event was created.
	 * @param individual Individual that is going to execute the event.
	 */
	public Death(double time, ISimulation sim, IIndividual individual) {
		super(time, sim, individual); 
	}
	
	//Redefinition of the method execute() inherited from IEvent
	public IEvent[] execute(){

		//Call to the corresponding method of the individual associated with the event being executed 
		this.individual.kill();
		IEvent[] e = new IEvent[1]; 
		e[0] = null; 
		//Doens't return new events to be added to the PEC
		return e; 
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
