package event;

import simulation.Individual;
import simulation.Simulation;

import java.util.Random;

public abstract class Event_Individual extends Event_Simulation{
	
	Individual individual; 
	
	public Event_Individual(double time, Simulation sim, Individual individual) {
		super(time, sim);
		this.individual = individual;
	}



	static double expRandom(double mean){
		Random random = new Random(); 
		double next = random.nextDouble();
		return -mean*Math.log(1.0-next);
	}
	
}
