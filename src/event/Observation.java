package event;

import simluation.IIndividual; 

import simulation.Simulation;

public class Observation{
	Observation(Simulation sim){
		System.out.println(sim.toString());
	}
	Observation(Simulation sim, double time){
		System.out.println(sim.singleobs());
	}
}
