package simulation;

public class Simulation {
	int finalinst, initpop, maxpop, comfortsens, finalpop;
	public Simulation(){
		super();
	}
	
	public Simulation(int finalinst, int initpop, int maxpop, int comfortsens) {
		this.finalinst = finalinst;
		this.initpop = initpop;
		this.maxpop = maxpop;
		this.comfortsens = comfortsens;
	}

	@Override
	public String toString() {
		return "finalinst: "+finalinst+" inipop: "+initpop+" maxpop: "+maxpop+" comfortsens: "+comfortsens;
	} 
	
	
}
