package event;
import pec.IEvent;
import simulation.IIndividual;
import simulation.ISimulation; 
/**
 * Reproduction is the class that extends the Event_Individual abstract class and has the purpose of distinguish different types of events. 
 * A Reproduction object encapsulates the information needed to process the corresponding event and a corresponding execute method. This information includes: 
 * <ul>
 * <li> The time of the event;
 * <li> The Individual associated with the event;
 * <li> The simulation in which the event was generated.
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public class Reproduction extends Event_Individual{

	/**
	 * Class Reproduction constructor to be called when the event is created in simulation time 0.
	 * @param sim Simulation in which the event was created.
	 * @param individual Individual that is going to execute the event.
	 */
	public Reproduction(ISimulation sim, IIndividual individual){
		super(expRandom((1-Math.log(individual.Comfort()))*sim.GetReproduction()), sim, individual);
	}
	/**
	 * Class Reproduction constructor to be called at a given time in the simulation.
	 * @param time Time in which the event will occur.
	 * @param sim 
	 * Simulation in which the event was created.
	 * @param individual Individual that is going to execute the event.
	 */
	public Reproduction(double time, ISimulation sim, IIndividual individual){
		super(time, sim, individual);
	}
	
	/**
	 * Redefinition of the method execute() inherited from the abstract class Event. Creates 4 new events to be added to the PEC.
	 * A new reproduction for the parent individual and the 3 events to initialize the child individual. The method also calls the 
	 * corresponding individual {@linkplain simulation.Individual#reproduction() method}.
	 * @return IEvent[] Returns a list of events to be executed next and null if the individual executing the Move event has already died. 
	 */	
	public IEvent[] execute(){
		IIndividual child;
		IEvent[] e; 
		//Verifies if the individual has died and call to the corresponding method of the individual associated with the event being executed
		if((child = this.individual.reproduction())!=null) {
			e = new IEvent[4]; 
			//Parent next reproduction
			e[0] = new Reproduction(this.time(), sim, individual);
			//Child initialization
			e[1] = new Reproduction(this.time(), sim, child); 
			e[2] = new Move(this.timeM(), sim, child); 
			e[3] = new Death(this.timeD(), sim, child); 	

		}
		else {
			e = new IEvent[1]; 
			e[0] = null; 
		}
		return e;		 
	}
	/**
	 * Redefinition of the method time() inherited from Event_Individual. Adds to the attribute time of the individual calling this method 
	 * a new time defined by an exponential distribution of mean value (1 - log(1-comfort))*reproduction_parameter.
	 * @return double Returns the corresponding time of the next reproduction event.
	 */
	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.sim.GetReproduction()); 
		return time; 
	}
	/**
	 * Redefinition of the method time() inherited from Event_Individual. Adds to the attribute time of the individual calling this method 
	 * a new time defined by an exponential distribution of mean value (1 - log(comfort))*move_parameter.
	 * @return double Returns the corresponding time of the next move event.
	 */
	double timeM(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.sim.GetMove()); 
		return time;
	}
	/**
	 * Redefinition of the method time() inherited from Event_Individual. Adds to the attribute time of the individual calling this method 
	 * a new time defined by an exponential distribution of mean value (1 - log(1-comfort))*death_parameter.
	 * @return double Returns the corresponding time of the next death event.
	 */
	double timeD(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(1-this.individual.Comfort()))*this.sim.GetDeath()); 
		return time;		
	}	
}
