package pec;

/**
 * Contract for the general methods of a PEC class
 *
 */
public interface IPEC {
	/**
	 * Do ordered insertion according to the 
	 * time-stamp representing variable.
	 * Lower time-stamps are put on the first positions .
	 * @param e event to be added.
	 */
	public void Add(IEvent e);
	/**
	 * Removes the event from the first position
	 * from a PEC. This event has the lowest time-stamp value.
	 * @return event removed
	 */
	public IEvent Remove();
}
