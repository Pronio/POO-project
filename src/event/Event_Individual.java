package event;

import simulation.IIndividual;
import simulation.ISimulation;

import java.util.Random;
/**
 * Event_Individual is the abstract class that extends the Event abstract class and has the purpose of distinguish events that act on an individual (Move,
 * Death and Reproduction) and events that act on the simulation it self (Observation).
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public abstract class Event_Individual extends Event_Simulation{
	
	IIndividual individual; 
	/**
	 *Class Event_Individual constructor to be called when the event at a given time.
	 * @param time Time in which the event will occur
	 * @param sim Simulation in which the event is taking place.
	 * @param individual Individual that will execute said event.
	 */
	public Event_Individual(double time, ISimulation sim, IIndividual individual) {
		super(time, sim);
		this.individual = individual;
	}
/**
 * The static method expRandom gives for a given mean value a random number according to a exponential distribution of probability.
 * @param mean Mean value of the exponential distribution.
 * @return double The random value generated.
 */
	static double expRandom(double mean){
		Random random = new Random(); 
		double next = random.nextDouble();
		return -mean*Math.log(1.0-next);
	}
}
