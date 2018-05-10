package pec;

import java.util.LinkedList;

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
			int i;
			boolean found = false;
			//Change to ListIterator
			for(i=0; i<eventList.size(); i++){
				if(e.compareTo(eventList.get(i)) < 0){
					found = true;
					break;
				}
			}
			if(found == true){
				eventList.add(i, e);
			}
			else{
				eventList.add(e);
			}
		}
	}
	
	/**
	 * Calls remove method from LinkedList class with argument 0
	 * @return the IEvent at the first position of the LinkedList
	 */
	public IEvent Remove(){
		//System.out.println("Removing: "+eventList.getFirst().toString());
		if(!eventList.isEmpty())
			return eventList.remove(0);
		else return null;
	}
}
