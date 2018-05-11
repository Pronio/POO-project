package event;
import pec.Event; 
import simulation.ISimulation;
/**
 * Event_Simulation is the abstract class that extends the Event abstract class and has the purpose of distinguish events that act on an individual (Move,
 * Death and Reproduction) and events that act on the simulation it self (Observation).
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public abstract class Event_Simulation extends Event{

	ISimulation sim;
/**
 *Class Event_Simulation constructor to be called when the event at a given time.
 * @param time Time in which the event will occur
 * @param sim Simulation in which the event is taking place.
 */
	public Event_Simulation(double time, ISimulation sim) {
		super(time);
		this.sim = sim;
	}	
}
