package main;
import map.*; 

import simulation.*;
import pec.*; 
import event.*; 

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
/**
 * Main is the class that contains the main method.
 * <ul>
 * <li> The time of the event;
 * <li> The Individual associated with the event;
 * <li> The simulation in which the event was generated.
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public class Main {
	/**
	 * This method receives a string as input that is the 
	 * path to the XML file that will be simulated. This method uses the SAX parser in order to read said XML 
	 * file. This method contains:
	 * <ul> 
	 * <li>A Simulation object that is the simulation being executed; 
	 * <li>A Map object that is created based on the XML input file and with the SAX parser; 
	 * <li>A PEC (Pending Event Container) object that stores the events to be executed next; 
	 * <li>A initial population value in order to initialize the simulation and create said population (individuals); 
	 * <li>A auxiliary array of events to be added to the PEC in each iteration of the program.
	 * </ul>
	 * @param args Relative path of the XML input file.
	 * @throws Exception throws different types of exception according to the type of error found
	 */
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
	    
	    if(args.length<1) {
	    	System.err.println("XML file is not specified in the arguments");
	    	System.exit(1);
	    }
	    
	    try{
	    	SAXParser saxParser = fact.newSAXParser();
		    //Parse the XML document to this handler
		    SimHandler handler = new SimHandler(); 
		    saxParser.parse(new File(args[0]), handler);		    
		    map = new Map(handler.colsnb, handler.rowsnb, handler.getObs(), handler.getSpcost(),  handler.cmax, handler.xin, handler.yin);
		    sim = new Simulation(handler.finalinst, handler.maxpop,map,handler.xfinal,handler.yfinal, handler.comfortsens,handler.dparam, handler.mparam, handler.rparam);
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

		
		//Population initialization 
		for(int i = 0; i<initpop; i++) {
			IIndividual I = new Individual(map.getStart(), sim);
			pec.Add(new Death(sim, I)); 
			pec.Add(new Reproduction(sim, I)); 
			pec.Add(new Move(sim, I)); 
		}
		
		pec.Add(new Observation(sim));

		IEvent e = null; 
		IEvent [] e_next ; 

		for(e = pec.Remove(); e != null && e.getTime() <= sim.GetTmax(); e = pec.Remove()) {
			e_next = e.execute(); 
			for(int i = 0; i< e_next.length; i++) {
				if(e_next[i] != null && !Double.isNaN(e_next[i].getTime()))
					pec.Add(e_next[i]);
			}
		}
		System.exit(0);
	}
}
