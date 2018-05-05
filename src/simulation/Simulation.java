package simulation;

public class Simulation {
	int finalinst, initpop, maxpop, comfortsens, finalpop;
	int rparam, mparam, dparam; 
	double tmax; 
	
	public Simulation(){
		super();
	}
	
	public double getTmax(){
		return tmax; 
	}
	
	public int getRparam() {
		return rparam;
	}

	public void setRparam(int rparam) {
		this.rparam = rparam;
	}

	public int getMparam() {
		return mparam;
	}

	public void setMparam(int mparam) {
		this.mparam = mparam;
	}

	public int getDparam() {
		return dparam;
	}

	public void setDparam(int dparam) {
		this.dparam = dparam;
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

	public void stats() {
		// TODO Auto-generated method stub
		
	}

	public void finalstats() {
		// TODO Auto-generated method stub
		
	} 
	
	
}
