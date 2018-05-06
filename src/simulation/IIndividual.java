package simulation;

public interface IIndividual {
	
	public int move();
	public IIndividual reproduction();
	public double Comfort();
	public void kill();
	public void setDeath(boolean b);
	public boolean getDeath(); 
	//test
	public void GetPath();
}
