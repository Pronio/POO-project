package event;

import simulation.Individual;

import java.util.Random;

public abstract class Event_Individual extends Event_Simulation{
 
	Individual individual; 
	
	double expRandom(double mean){
		Random random = new Random(); 
		double next = random.nextDouble();
		return -mean*Math.log(1.0-next);
	}
	
}
