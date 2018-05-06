package pec;

import java.util.LinkedList;

public class PEC implements IPEC{
	
	LinkedList<IEvent> eventList;
	
	public PEC() {
		this.eventList = new LinkedList<IEvent>();
	}
	
	public void Add(IEvent e){
		if(eventList.isEmpty()){
			eventList.add(e);
			System.out.println("PEC empty adding "+e.toString()); 
		}else{
			int i;
			boolean found = false;
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
			System.out.println("Adding "+e.toString()); 
		}
	}
	public IEvent Remove(){
		if(!eventList.isEmpty())
			return eventList.remove(0);
		else return null;
	}
}
