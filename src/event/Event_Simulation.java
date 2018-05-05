package event;
import pec.Event; 
import simulation.Simulation;

public abstract class Event_Simulation extends Event{

	Simulation sim;

	public Event_Simulation(double time, Simulation sim) {
		super(time);
		this.sim = sim;
	}	
}
