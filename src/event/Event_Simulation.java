package event;
import pec.Event; 
import simulation.ISimulation;

public abstract class Event_Simulation extends Event{

	ISimulation sim;

	public Event_Simulation(double time, ISimulation sim) {
		super(time);
		this.sim = sim;
	}	
}
