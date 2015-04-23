public class SpanningUSA {

	public static void main(String[] args) {
		String[] input = StdIn.readAllLines(); // Read input. The format is 'String ' (with a space in the end as it is in USA-highway-miles.txt) and 'String--String [digits]'
		ST verticies = new ST();
		
		// Reads all verticies and adds them to a symbol table with Key: String, Value: i. Breaks when first edge is seen.
		for (int i = 0; i < input.length; i++) {
			if(input[i].contains("--")) {break;}
			else {verticies.put(input[i], i);}
		}
		
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(verticies.size()); // Creates an EdgeWeightedGraph with size equal to number of verticies.
		
		// Connects the verticies with edges
		for (int j = 0+ewg.V(); j < input.length; j++) {
			String[] parts = input[j].split("--|\\s\\[|\\]"); // Regex which splits '"San Diego"--Utica [2802]' into '"San Diego"', 'Utica', '2802'.
			Edge e = new Edge((Integer) verticies.get(parts[0]+" "), (Integer) verticies.get(parts[1]+" "), Integer.parseInt(parts[2])); // Create a new edge with above information.
			ewg.addEdge(e); // Add edge to the EdgeWeightedGraph
		}
		KruskalMST mst = new KruskalMST(ewg);
		StdOut.println(mst.weight());
		//StdOut.println(ewg.toString()); // Prints a representation of the graph. Comment/Uncomment as necessary.
	}
}