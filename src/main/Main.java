package main;
import map.*; 
import simulation.*;
import pec.*; 
import event.*; 

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws Exception{
		
		//Builds a SAX parser factory
		SAXParserFactory fact = SAXParserFactory.newInstance();
		//Checks the validity of the document against the DTD 
	    fact.setValidating(true); 
	    //Objects to store the XML file info
	    Simulation sim = null ;
	    Map map = null;  	    
	    PEC pec = new PEC(); 
	    double tnow = 0.0; 
	    
	    try{
	    	SAXParser saxParser = fact.newSAXParser();
		    //Parse the XML document to this handler
		    SimHandler handler = new SimHandler(); 
		    saxParser.parse(new File("src/data1.xml"), handler);
		    //Getting the objects stored in the parser handler 
		    System.out.println("---------------------------");
		    System.out.println("----------- MAP -----------"); 
		    System.out.println("---------------------------");	
		    sim = new Simulation(handler.finalinst, handler.maxpop, handler.initpop,handler.xfinal,handler.yfinal, handler.colsnb, handler.rowsnb, handler.cmax,handler.comfortsens,handler.dparam, handler.mparam, handler.rparam);
		    map = new Map(handler.colsnb, handler.rowsnb, handler.getObs(), handler.getSpcost(), handler.xin, handler.yin);
		    //Control prints
		    System.out.println("---------------------------");
		    System.out.println("------- PARSER DATA -------"); 
		    System.out.println("---------------------------");
		    System.out.println("Simulation: "+sim.toString()); 
		    System.out.println("Spetial Cost Zones: "+ Arrays.deepToString(handler.getSpcost()));
		    System.out.println("Obstacles: "+ Arrays.deepToString(handler.getObs()));
		    System.out.println("Death param = "+sim.GetDeath());
		    System.out.println("Reproduction param = "+sim.GetReproduction());
		    System.out.println("Move param = "+sim.GetMove());
		    
	    }catch(IOException e) {
	    	System.err.println("IO error");
	    	System.exit(1);
	    }catch(SAXException e) {
	    	System.err.println("Parser error");
	    	System.exit(1);
	    }catch(ParserConfigurationException e) {
	    	System.err.println("Parser configuration error");
	    	System.exit(1);
	    }catch(IndexOutOfBoundsException e) {
	    	System.err.println("Dimensions error");
	    	System.exit(1); 
	    }catch (NullPointerException e) {
	    	System.err.println("Null pointer error"); 
	    	System.exit(1);
	    }catch(NumberFormatException e ) {
	    	System.err.println("Number format error");
	    	System.exit(1);
	    }catch(NegativeArraySizeException e) {
	    	System.err.println("Negative array size error");
	    	System.exit(1);
	    }
	
		System.out.println("---------------------------");
		System.out.println("----------- PEC -----------");
		System.out.println("---------------------------");
		
		//Population initialization 
		for(int i = 0; i<sim.getInitpop(); i++) {
			IIndividual I = new Individual(map.start, sim);
			System.out.println("Created individual number: "+(i+1)); 
			sim.addIndividual(I);
			pec.Add(new Death(sim, I)); 
			pec.Add(new Reproduction(sim, I)); 
			pec.Add(new Move(sim, I)); 
			
		}
		
		pec.Add(new Observation(sim));
		pec.Add(new Observation(sim, sim.getTmax()));

		IEvent e = null, e_next = null; 

		for(e = pec.Remove(); e != null && e.getTime() <= sim.getTmax(); e = pec.Remove()) {
			tnow = e.getTime(); 
			sim.setTnow(tnow);
			sim.setNev(sim.getNev()+1); 
			e_next = e.execute();
			if(e_next != null && !Double.isNaN(e_next.getTime()))
				pec.Add(e_next);
		}
		System.exit(-1);
	}
}
