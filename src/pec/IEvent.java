package pec;

public interface IEvent extends Comparable<IEvent>{
	public IEvent execute();
	public double getTime(); 
}
