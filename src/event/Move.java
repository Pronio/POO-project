package event;

import pec.IEvent;
import simulation.IIndividual;
import simulation.ISimulation;
/**
 * Move is the class that extends the Event_Individual abstract class and has the purpose of distinguish different types of events. 
 * A Move object encapsulates the information needed to process the corresponding event. This information includes: 
 * <ul>
 * <li> The time of the event;
 * <li> The Individual associated with the event;
 * <li> The simulation in which the event was generated.
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author José Heraldo ()
 */
public class Move extends Event_Individual{

	/**
	 * Class Move constructor to be called when the event is created in simulation time 0.
	 * @param sim 
	 * Simulation in which the event was created.
	 * @param individual Individual that is going to execute the event.
	 */
	public Move(ISimulation sim, IIndividual individual) {
		super(expRandom((1-Math.log(individual.Comfort()))*sim.GetMove()), sim, individual);
	}
	/**
	 * Class Move constructor to be called at a given time in the simulation.
	 * @param sim 
	 * Simulation in which the event was created.
	 * @param individual Individual that is going to execute the event.
	 */
	public Move(double time, ISimulation sim, IIndividual individual) {
		super(time, sim, individual);
	}

	/**
	 * Redefinition of the method execute() inherited from the abstract class Event.
	 * @return IEvent[] Returns a list of events to be executed next and null if the individual executing the Move event has already died. 
	 */
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
	
	/**
	 * Redefinition of the method time() inherited from Event_Individual.
	 * @return
	 */
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
