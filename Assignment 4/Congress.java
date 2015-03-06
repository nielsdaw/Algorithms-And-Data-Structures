import java.util.PriorityQueue;

public class Congress {

	public static void main(String[] args) {
		int numberOfStates = Integer.parseInt(StdIn.readLine()); 
		int numberOfSeats = Integer.parseInt(StdIn.readLine());
		PriorityQueue<State> states = new PriorityQueue<State>(numberOfSeats);
		
		while(StdIn.hasNextLine()) {			// Give alle states 1 seat
			String state = StdIn.readLine();	// read State 
			int population = Integer.parseInt(StdIn.readLine()); // read population
			states.add(new State(state, population));	// add new state to queue
			numberOfSeats--;					// decrement total number of seats
		}
		
		while(numberOfSeats > 0){ 				// Distribute remaining seats
			State state = states.remove();		// Get State with highest priority
			state.addSeat();					// add seat to that state
			numberOfSeats--;					// decrement total number of seats
			states.add(state);					// add the state back to the queue (with new quotient)
		}

		while(!states.isEmpty()){ 				// Display results
			State tmpState = states.remove();
			StdOut.println(tmpState.getName() + " " + tmpState.getSeats());
		}
	}
}