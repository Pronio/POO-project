package main;
import map.*; 
import simulation.*;
import pec.*; 
import event.*; 

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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
	    int initpop = 0;
	    
	    try{
	    	SAXParser saxParser = fact.newSAXParser();
		    //Parse the XML document to this handler
		    SimHandler handler = new SimHandler(); 
		    saxParser.parse(new File("src/data1.xml"), handler);
		    //Getting the objects stored in the parser handler 
		    System.out.println("---------------------------");
		    System.out.println("----------- MAP -----------"); 
		    System.out.println("---------------------------");	
		    map = new Map(handler.colsnb, handler.rowsnb, handler.getObs(), handler.getSpcost(),  handler.cmax, handler.xin, handler.yin);
		    sim = new Simulation(handler.finalinst, handler.maxpop,map,handler.xfinal,handler.yfinal, handler.comfortsens,handler.dparam, handler.mparam, handler.rparam);
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
		    initpop = handler.initpop;
		    
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
		for(int i = 0; i<initpop; i++) {
			IIndividual I = new Individual(map.getStart(), sim);
			System.out.println("Created individual number: "+(i+1)); 
			pec.Add(new Death(sim, I)); 
			pec.Add(new Reproduction(sim, I)); 
			pec.Add(new Move(sim, I)); 
			
		}
		
		pec.Add(new Observation(sim));

		IEvent e = null; 
		IEvent [] e_next ; 

		for(e = pec.Remove(); e != null && e.getTime() <= sim.GetTmax(); e = pec.Remove()) {
			//System.out.println("Current Event: " +e); 
			e_next = e.execute(); 
			for(int i = 0; i< e_next.length; i++) {
				if(e_next[i] != null && !Double.isNaN(e_next[i].getTime()))
					pec.Add(e_next[i]);
			}
		}
		System.exit(-1);
	}
}
