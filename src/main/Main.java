package main;
import map.IMap;
import map.Map;
import map.Obstacles;
import map.SpecialCostZones;
import simulation.Simulation;

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
	    Simulation sim ;
	    Map map = null;  
	    SpecialCostZones[] spcost = null;
	    Obstacles[] obs = null; 
	    
	    try{
	    	SAXParser saxParser = fact.newSAXParser();
		    //Parse the XML document to this handler
		    SimHandler handler = new SimHandler(); 
		    saxParser.parse(new File("src/data.xml"), handler);
		    //Getting the objects stored in the parser handler 
		    System.out.println("---------------------------");
		    System.out.println("----------- MAP -----------"); 
		    System.out.println("---------------------------");	
		    sim = new Simulation(handler.finalinst, handler.initpop, handler.maxpop, handler.comfortsens);
		    map = new Map(handler.colsnb, handler.rowsnb, handler.getObs(), handler.getSpcost(), handler.xin, handler.yin);
		    
		    //Control prints
		    System.out.println("---------------------------");
		    System.out.println("------- PARSER DATA -------"); 
		    System.out.println("---------------------------");
		    System.out.println("Simulation: "+sim.toString()); 
		    System.out.println("Spetial Cost Zones: "+ Arrays.deepToString(spcost));
		    System.out.println("Obstacles: "+ Arrays.deepToString(obs));
		    System.out.println("Death param = "+handler.dparam);
		    System.out.println("Reproduction param = "+handler.rparam);
		    System.out.println("Move param = "+handler.mparam);
		    
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
	   
	    
	    IMap node;
	    
	    System.out.println("---------------------------");
	    System.out.println("---------- MOVES ----------"); 
	    System.out.println("---------------------------");		
		node = map.start.nextNodeRandom();
		
		System.out.println(node);
		
		for (int i = 0; i<100; i++) {
			node = node.nextNodeRandom(); 
			System.out.println(node);
		}		
		System.out.println("---------------------------");
		System.out.println("---------------------------");
		
	}
	
}
