package event;

import simulation.Individual;

public class Death extends Event_Individual{
	
	public Death(Individual individual, double time) {
		super(individual, time);
	}

	void execute(){		
		this.individual.death = true; 
		this.individual.kill(); 
	}
	
}
