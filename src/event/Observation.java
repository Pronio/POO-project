package event;

import pec.IEvent;
import simulation.ISimulation;

public class Observation extends Event_Simulation{
		 
	//Number of observation that we want to see in the simulation time
	public static final int NOBS = 20;
	
	//Constructor of the first observation event
	public Observation(ISimulation sim){
		super(sim.GetTmax()/NOBS, sim);  
	}
	//Constructor of a general observation event
	public Observation(ISimulation sim, double time) {
		super(time, sim); 
	}
	
	//Redefinition of the method execute() inherited from IEvent
	public IEvent[] execute(){
		
		//Calls the stats function that will print the state of the simulation to the console 
		this.sim.stats(time);
		
		//Checks to see if all observations have been done (meaning checks if NOBS has been met)	
		if(this.time + this.sim.GetTmax()/NOBS<this.sim.GetTmax()) {
			//Returns the new observation event to be added to the PEC
			IEvent[] e = new IEvent[1]; 
			e[0] = new Observation(this.sim, this.time + this.sim.GetTmax()/NOBS); 
			return e; 
		}else {
			//Calls the function finalstats because we have reached the end of the simulation
			this.sim.finalStats();
			//Doesn't return a new observation event to add to the PEC
			return null; 
		}
	}
	@Override
	public String toString() {
		return "Observation: [time=" + time + "]"; 
	}
	
	

}
