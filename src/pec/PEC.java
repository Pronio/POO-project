package pec;

import java.util.LinkedList;

class PEC implements IPEC{
	
	LinkedList<IEvent> eventList;
	
	public PEC() {
		this.eventList = new LinkedList<IEvent>();
	}
	
	public void Add(IEvent e){
		if(eventList.isEmpty()){
			eventList.add(e);
		}else{
			int i;
			for(i=0; i<eventList.size(); i++){
				if(e.compareTo(eventList.get(i)) < 0){
					break;
				}
			}
			if(i == 0){
				eventList.add(0, e);
			}else{
				eventList.add(i-1, e);
			}
		}
	}
	public IEvent Remove(){
		if(!eventList.isEmpty())
			return eventList.remove(0);
	}

}
