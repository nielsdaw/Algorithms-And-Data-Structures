public class State implements Comparable<State>{
	private String name;
	private int population;
	private int seats;
	private Double pquotient;

	public State(String name, int population) {
		this.name = name;
		this.population = population;
		seats = 1;
		pquotient = pquotientDivider();
	}

	public String getName() {return name;}

	public int getPopulation() {return population;}

	public int getSeats() {return seats;}

	public double getPquotient(){return pquotient;}

	public void setPopulation(int population) {this.population = population;}

	public void addSeat() {seats++; pquotient = pquotientDivider();}

	/**
	*	divides population with the squareroot of the states seats, multiplied by the states
	*	seats +1. Used to prioritize each state in the priority queue.
	*
	*	@return Double quotient
	**/
	public double pquotientDivider(){return this.population / (Math.sqrt((seats * (seats + 1))));} 

	/**
	*	Override the compareTo method in the Comparable interface. Evaluate two state objects quotients	
	*	Used to prioritize each state in the priority queue.
	*
	*	@Return int value
	**/
	public int compareTo(State o){
		return this.pquotient < o.getPquotient() ? 1 : (this.pquotient > o.getPquotient() ? -1 : 0);
	}
}	