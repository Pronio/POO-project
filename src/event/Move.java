package event;

import simulation.Individual;

public class Move extends Event_Individual{

	public Move(Individual individual, double time) {
		super(individual, time);
	}
	
	void execute(){
		IPEC pec; 
		pec.add(new Move(this.individual, this.time())); 
		this.individual.move(); 
	}

	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(this.individual.Comfort()))*this.individual.mparam); 
		return time; 
	}

}
