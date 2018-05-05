package event;
import pec.IPEC;
import simulation.Individual; 

public class Reproduction extends Event_Individual{

	public Reproduction(Individual individual, double time) {
		super(individual, time);
	}
	
	void execute(){
		IPEC pec; 
		pec.add(new Reproduction(this.individual, this.time())); 
		this.individual.reproduction(); 
	}

	double time(){
		double time = this.time;  
		time = time + expRandom((1-Math.log(1-this.individual.Comfort())*this.individual.rparam)); 
		return time; 
	}

	
}
