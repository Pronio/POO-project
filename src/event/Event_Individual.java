package event;

import simluation.IIndividual; 

import java.util.Random;

public abstract class Event_Individual {
 
	Individual individual; 
	double time;
	
	double expRandom(double mean){
		Random random = new Random(); 
		double next = random.nextDouble();
		return -mean*Math.log(1.0-next);
	}

	public Event_Individual(Individual individual, double time) {
		super();
		this.individual = individual;
		this.time = time;
	}
	
}
