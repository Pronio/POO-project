package pec;

import java.util.LinkedList;
import java.util.Random;

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
	
	public static void main(String[] args){
		
		PEC pec_teste = new PEC();
		
		Random r = new Random();
		
		for(int i=0; i<7;i++){
			double a = 10*r.nextDouble();
			System.out.println("Inserting Event with timestamp " + a);
			pec_teste.Add(new EventInst(a));
		}
		
		for(int i=0; i<pec_teste.eventList.size(); i++){
			System.out.print("["+pec_teste.eventList.get(i)+"], ");
		}
		System.out.println();
		
		pec_teste.Remove();
		pec_teste.Remove();
		pec_teste.Remove();
		
		for(int i=0; i<pec_teste.eventList.size(); i++){
			System.out.print("["+pec_teste.eventList.get(i)+"], ");
		}
		System.out.println();
		
		for(int i=0; i<2;i++){
			double a = 10*r.nextDouble();
			System.out.println("Inserting Event with timestamp " + a);
			pec_teste.Add(new EventInst(a));
		}
		
		pec_teste.Remove();
		
		for(int i=0; i<pec_teste.eventList.size(); i++){
			System.out.print("["+pec_teste.eventList.get(i)+"], ");
		}
	}

}
