package main;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import map.Obstacles;
import map.SpecialCostZones;

public class SimHandler extends DefaultHandler {
	
	//Attributes
	private SpecialCostZones[] spcost;
	private Obstacles[] obs; 
	//Number of Special Cost Zones and Obstacles
	int nsp = 0, nobs = 0; 
	//Events parameters 
	int dparam = 0, rparam = 0, mparam = 0, cost = 0; 
	//Simulation parameters 
	int maxpop, comfortsens, initpop, finalinst; 
	//Grid parameters 
	int colsnb, rowsnb, cmax = 1;
	//Initial point parameters
	int xin, yin; 
	//Final point parameters
	int xfinal, yfinal; 
	//Special Cost Zone parameters 
	int scxin, scyin, scxfinal, scyfinal; 
	//Flags
	boolean fzone = false;
	
	//boolean fsim = false, fob = false, fzone = false, fmap = false, fspcost = false, fobs = false, fnsp = false, fnobs = false, fdparam = false, frparam = false, fmparam = false; 
	
	
	//Getters 
	public SpecialCostZones[] getSpcost() {
		return spcost; 
	}
	public Obstacles[] getObs() {
		return obs;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException { 
		
	      if (qName.equalsIgnoreCase("simulation")) {
	    	  finalinst = Integer.parseInt(attr.getValue("finalinst")); 
	    	  initpop = Integer.parseInt(attr.getValue("initpop"));
	    	  maxpop = Integer.parseInt(attr.getValue("maxpop"));
	    	  comfortsens = Integer.parseInt(attr.getValue("comfortsens"));
	    	  //fsim = true; 
    	  }
	      else if(qName.equalsIgnoreCase("grid")){
	    	  colsnb = Integer.parseInt(attr.getValue("colsnb")); 
	    	  rowsnb = Integer.parseInt(attr.getValue("rowsnb")); 
	    	  //fmap = true; 
    	  }
	      else if (qName.equalsIgnoreCase("specialcostzones")) {
	    	  //fspcost = true; 
	    	  nsp = Integer.parseInt(attr.getValue("num")); 
	    	  spcost = new SpecialCostZones[nsp]; 
	    	  nsp--; 
	      }
	      else if (qName.equalsIgnoreCase("zone")){
	    	  scxin = Integer.parseInt(attr.getValue("xinitial")); 
	    	  scyin = Integer.parseInt(attr.getValue("yinitial"));
	    	  scxfinal = Integer.parseInt(attr.getValue("xfinal")); 
	    	  scyfinal = Integer.parseInt(attr.getValue("yfinal")); 
	    	  fzone = true;  
	      }
	      else if(qName.equalsIgnoreCase("obstacles")) {
	    	  //fobs = true;
	    	  nobs = Integer.parseInt(attr.getValue("num")); 
	    	  obs = new Obstacles[nobs];
	    	  nobs--;
	      }
	      else if(qName.equalsIgnoreCase("obstacle")){
	    	  //fob = true; 
	    	  obs[nobs] = new Obstacles(Integer.parseInt(attr.getValue("xpos")), Integer.parseInt(attr.getValue("ypos")));
	    	  nobs--; 
	      }
	      else if(qName.equalsIgnoreCase("death")) {
	    	  //fdparam = true; 
	    	  dparam = Integer.parseInt(attr.getValue("param")); 
	      }
	      else if(qName.equals("reproduction")) {
	    	  //frparam = true; 
	    	  rparam = Integer.parseInt(attr.getValue("param")); 
	      }
	      else if(qName.equals("move")) {
	    	  //fmparam = true; 
	    	  mparam = Integer.parseInt(attr.getValue("param")); 
	      }
	      else if(qName.equals("initialpoint")){
	    	  xin = Integer.parseInt(attr.getValue("xinitial")); 
	    	  yin = Integer.parseInt(attr.getValue("yinitial")); 
	      }
	      else if(qName.equals("finalpoint")){
	    	  xfinal = Integer.parseInt(attr.getValue("xfinal")); 
	    	  yfinal = Integer.parseInt(attr.getValue("yfinal")); 
	      }
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException{
		if(fzone){
			cost = Integer.parseInt(new String(ch, start, length)); 
			if(cmax < cost)
				cmax = cost;
			spcost[nsp] = new SpecialCostZones(scxin, scyin, scxfinal, scyfinal, cost);
			nsp--; 
			fzone = false; 
		}
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		System.err.println("XML file is not correctly formed. " +e.getMessage());
		System.exit(1);
		
	}
	
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("XML file is not correctly formed. "+e.getMessage());
		System.exit(1);		
	}

}
