package event;

import pec.IEvent;
import simulation.Simulation;

public class Observation extends Event_Simulation{
		 
	int num;  
	
	//Number of observation that we want to see in the simulation time
	public static final int NOBS = 20;
	
	//Constructor of the first observation event
	public Observation(Simulation sim){
		super(sim.getTmax()/NOBS, sim);  
		this.num = 1; 
	}
	//Constructor of a general observation event
	public Observation(Simulation sim, double time, int num) {
		super(time, sim);
		this.num = num; 
	}
	
	//Redefinition of the method execute() inherited from IEvent
	public IEvent execute(){
		//Checks to see if all observations have been done (meaning checks if NOBS has been met)
		if(this.num <= NOBS){
			System.out.println("Observation number: " + this.num);
			//Calls the stats function that will print the state of the simulation to the console 
			this.sim.stats();
			//Returns the new observation event to be added to the PEC
			return new Observation(this.sim, this.time + this.sim.getTmax()/NOBS, this.num+1); 
		}
		
		else {
			//Calls the function finalstats because we have reached the end of the simulation
			this.sim.finalstats();
			//Doesn't return a new observation event to add to the PEC
			return null; 
		}
	}

}
