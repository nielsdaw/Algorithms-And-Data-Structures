public class SpanningUSA {

	public static void main(String[] args) {
		String[] input = StdIn.readAllLines();

		ST verticies = new ST();
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(128);
		

		for (int i = 0; i < input.length; i++) {
			if(input[i].contains("--")) {
				String[] parts = input[i].split("--|\\s\\[|\\]");
				Edge e = new Edge((Integer) verticies.get(parts[0]+" "), (Integer) verticies.get(parts[1]+" "), Integer.parseInt(parts[2]));
				ewg.addEdge(e);
			}
			else {
				verticies.put(input[i], i);
			}
		}
		KruskalMST mst = new KruskalMST(ewg);
		StdOut.println(mst.weight());
	}
}