package event;

import simulation.IIndividual;
import simulation.ISimulation;

import java.util.Random;

public abstract class Event_Individual extends Event_Simulation{
	
	IIndividual individual; 
	
	public Event_Individual(double time, ISimulation sim, IIndividual individual) {
		super(time, sim);
		this.individual = individual;
	}



	static double expRandom(double mean){
		Random random = new Random(); 
		double next = random.nextDouble();
		return -mean*Math.log(1.0-next);
	}
	
}
