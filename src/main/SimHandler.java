package main;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import map.Obstacles;
import map.SpecialCostZones;
/**
 * SimHandler is the class that extends the DefaultHandler class and has the purpose of allow the parsing of the input XML file and set the attributes of
 * the corresponding simulation. 
 * A SimHandler object contains some attributes in order to store the information read from the XML file. This Attributes are: 
 * <ul>
 * <li> An optional array of special cost zones;
 * <li> An optional array of obstacles;
 * <li> The number of special cost zones and obstacles; 
 * <li> The event parameters (Death, move and reproduction);
 * <li> The simulation parameters (Maximum population size, comfort sensitivity, initial population and final instant of simulation);
 * <li> The grid parameters (Number of columns, number of rows); 
 * <li> The initial and final simulation points; 
 * <li> Some auxiliary variables like cost, scxin, scyin, scxfinal, scyfinal and fzone;
 * <li> The simulation in which the event was generated.
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author José Heraldo (82414)
 */
public class SimHandler extends DefaultHandler {
	
	//Attributes
	private SpecialCostZones[] spcost;
	private Obstacles[] obs; 
	//Number of Special Cost Zones and Obstacles
	int nsp = 0, nobs = 0; 
	//Events parameters 
	int dparam = 0, rparam = 0, mparam = 0, cost = Integer.MAX_VALUE; 
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
	//Flag
	boolean fzone = false;	
	
	/**
	 * Gets the array of special cost zones.
	 * @return SpecialCostZones[]
	 */
	public SpecialCostZones[] getSpcost() {
		return spcost; 
	}
	/**
	 * Gets the array of obstacles.
	 * @return Obstacles[]
	 */
	public Obstacles[] getObs() {
		return obs;
	}
	/**
	 * The method startElement is overridden from the class DefaultHandler in order to be able to store the information needed. 
	 * When the SAXParser founds an element this method is called and in this case the method checks what element was found and 
	 * sets some values from the element attributes in the XML to the variables in the class SimHandler.<br>
	 * This method also sets a flag to true when the element found is a Zone element because the input file follows a structure where 
	 * there is a value not set in the attributes of the element but in between tags of said element. And the method used to check this 
	 * information is {@linkplain #characters(char[], int, int) characters} method.
	 * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
	 * @param localName The local name (without prefix), or the empty string if Namespace processing is not being performed.
	 * @param qName The qualified name (with prefix), or the empty string if qualified names are not available.
	 * @param attr The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.
	 * @throws SAXException Any SAX exception, possibly wrapping another exception.
	 * @see {@linkplain DefaultHandler#startElement(String, String, String, Attributes) DefaultHandler startElement method}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException { 
		
	      if (qName.equalsIgnoreCase("simulation")) {
	    	  finalinst = Integer.parseInt(attr.getValue("finalinst")); 
	    	  initpop = Integer.parseInt(attr.getValue("initpop"));
	    	  maxpop = Integer.parseInt(attr.getValue("maxpop"));
	    	  comfortsens = Integer.parseInt(attr.getValue("comfortsens"));
    	  }
	      else if(qName.equalsIgnoreCase("grid")){
	    	  colsnb = Integer.parseInt(attr.getValue("colsnb")); 
	    	  rowsnb = Integer.parseInt(attr.getValue("rowsnb")); 
    	  }
	      else if (qName.equalsIgnoreCase("specialcostzones")) {
	    	  nsp = Integer.parseInt(attr.getValue("num"));  
	    	  spcost = new SpecialCostZones[nsp];
	    	  nsp--; 
	      }
	      else if (qName.equalsIgnoreCase("zone")){
	    	  scxin = Integer.parseInt(attr.getValue("xinitial")); 
	    	  scyin = Integer.parseInt(attr.getValue("yinitial"));
	    	  scxfinal = Integer.parseInt(attr.getValue("xfinal")); 
	    	  scyfinal = Integer.parseInt(attr.getValue("yfinal")); 
	    	  cost = Integer.MAX_VALUE; 
	    	  fzone = true;  
	      }
	      else if(qName.equals("obstacles")) {
	    	  nobs = Integer.parseInt(attr.getValue("num")); 
	    	  obs = new Obstacles[nobs];
	    	  nobs--;
	      }
	      else if(qName.equalsIgnoreCase("obstacle")){
	    	  obs[nobs] = new Obstacles(Integer.parseInt(attr.getValue("xpos")), Integer.parseInt(attr.getValue("ypos")));
	    	  nobs--; 
	      }
	      else if(qName.equalsIgnoreCase("death")) {
	    	  dparam = Integer.parseInt(attr.getValue("param")); 
	      }
	      else if(qName.equals("reproduction")) {
	    	  rparam = Integer.parseInt(attr.getValue("param")); 
	      }
	      else if(qName.equals("move")) {
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
	/**
	 * The method characters is overridden in order to set the cost of the special zone in analysis, since the structure of the input XML 
	 * file says that this cost is inside the element but not an attribute of said element and the fact that this method is called when character 
	 * data is found by SAXParser inside an element.
	 */
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
	/**
	 * The method endElement is overridden in order to assure that some values that are set in the element make sense in this application. 
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("zone")) {
			if(cost == Integer.MAX_VALUE){
				System.err.println("Special cost zone with a non specified cost.");
				System.exit(1);
			}
		}else if(qName.equals("specialcostzones")){
			if(nsp != -1){
				System.err.println("Wrong number of special cost zones."); 
				System.exit(1);
			}
		}else if(qName.equals("obstacles")) {
			if(nobs != -1){
				System.err.println("Wrong number of obstacles.");
				System.exit(1);
			}
		}
	}

	@Override
	public void error(SAXParseException e) throws SAXException{
		System.err.println("XML file is not correctly formed. "+e.getMessage());
		System.exit(1);
	}
	@Override
	public void fatalError(SAXParseException e) throws SAXException{
		System.err.println("XML file is not correctly formed. "+e.getMessage());
		System.exit(1);		
	}
}
