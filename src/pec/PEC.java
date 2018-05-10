package pec;

import java.util.LinkedList;
import java.util.ListIterator;

import simulation.Individual;

/**
 * PEC class that holds the collection of events.
 * The collection is represented by a LinkedList.
 *
 */
public class PEC implements IPEC{
	
	LinkedList<IEvent> eventList;
	
	public PEC() {
		this.eventList = new LinkedList<IEvent>();
	}
	
	/**
	 * Ordered insertion in LinkedList.
	 * Uses add method from LinkedList to insert.
	 */
	public void Add(IEvent e){
		//System.out.println("Adding: "+e.toString()); 
		if(eventList.isEmpty()){
			eventList.add(e);
		}else{
			boolean found = false;
			//Change to ListIterator
			for(ListIterator<IEvent> i = eventList.listIterator(); i.hasNext();){
				if(e.compareTo(i.next()) < 0){
					i.previous();
					i.add(e);
					found = true;
					break;
				}
			}
			if(found == false){
				eventList.add(e);
			}
		}
	}
	
	/**
	 * Calls remove method from LinkedList class with argument 0
	 * @return the IEvent at the first position of the LinkedList,
	 * 			or null if the list is empty
	 */
	public IEvent Remove(){
		//System.out.println("Removing: "+eventList.getFirst().toString());
		if(!eventList.isEmpty())
			return eventList.remove(0);
		else return null;
	}
}
