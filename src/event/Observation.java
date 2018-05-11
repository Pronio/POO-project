package event;

import pec.IEvent;
import simulation.ISimulation;
/**
 * Observation is the class that extends the Event_Simulation abstract class and has the purpose of distinguish different types of events. 
 * A Observation object encapsulates the information needed to process the corresponding event. This information includes: 
 * <ul>
 * <li> The time of the event;
 * <li> The simulation in which the event was generated.
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public class Observation extends Event_Simulation{
		 
	//Number of observation that we want to see in the simulation time
	public static final int NOBS = 20;
	/**
	 * Class Observation constructor to be called when the event is created in simulation time 0.
	 * @param sim Simulation in which the event was created.
	 */
	public Observation(ISimulation sim){
		super(sim.GetTmax()/NOBS, sim);  
	}	
	/**
	 * Class Observation constructor to be called at a given time in the simulation.
	 * @param time Time in which the event will occur.
	 * @param sim Simulation in which the event was created.
	 */
	public Observation(ISimulation sim, double time) {
		super(time, sim); 
	}
	/**
	 * Redefinition of the method execute() inherited from the abstract class Event. Creates a new Observation event to be added to the PEC and
	 * calls the corresponding simulation {@linkplain simulation.Simulation#stats(double) stats} method. During the simulation will always occur 
	 * 20 observations that call this method and when the time of simulation has been met the method called is the {@linkplain simulation.Simulation#finalStats() finalstats} method. 
	 * @return IEvent[] Returns a list of events to be executed next and null if the time of simulation has ended. 
	 */
	public IEvent[] execute(){
		//Calls the stats function that will print the state of the simulation to the console 
		this.sim.stats(time);
		IEvent[] e = new IEvent[1];		
		//Checks to see if all observations have been done (meaning checks if NOBS has been met)	
		if(this.time < this.sim.GetTmax()) {
			//Returns the new observation event to be added to the PEC
			e[0] = new Observation(this.sim, this.time + this.sim.GetTmax()/NOBS); 
			return e; 
		}else {
			//Calls the function finalstats because we have reached the end of the simulation
			this.sim.finalStats();
			//Doesn't return a new observation event to add to the PEC
			e[0] = null; 
			return e; 
		}
	}
}
