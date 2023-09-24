package Assignment3_Q2;
import Assignment1_Q1.Stack;
import Assignment2_Q2.IndexMinPQ;

public class DijkstraSP {

	//-----------------------------------------------------------------
	// Title: Question 2
	// Author: Basme Zantout
	// ID: 99227947762
	// Section: 1
	// Assignment: 3
	// Description: This is an edited version of theh  Dijkstra Shortest
	//              Path algorithm taken from the "Algorithms", 4th 
	//              edition by Robert Sedgewick and Kevin Wayne" book 
	//              for reference
	//-----------------------------------------------------------------
	
	
	
//-------------------------------------------------------
// Attribute: an array which holds the vertices on the 
//            other side of an edge
//-------------------------------------------------------

	 private Edge[] edgeTo;
	 
		
//-------------------------------------------------------
// Attribute: an array that holds the distance from the 
//            source vertex to each vertex in the graph
//-------------------------------------------------------
		
	 public double[] distTo;
	 
	 
//-------------------------------------------------------
// Attribute: a minimum priority queue that holds the 
//            vertices based in their minimum distances
//-------------------------------------------------------
		 
	 private IndexMinPQ<Double> pq;
	 
	 
	 

	 public DijkstraSP(Digraph G, int s, int current_hat, int [][] ALL_Buses_Stations, int [] bus_stations_no, int timespan)
//-------------------------------------------------------
// Summary: finds the shortest path from the 1st vertex 
//          to every vertex in the graph
// Precondition: the standard Dijkstar method takes the 
//               Graph and the source vertex as parameters.
//               This edited version also takes the current
//               "hat" or bus that we are at when we are 
//               traversing over the graph, a 2-D array
//               that has all the bus stations of all buses,
//               an array which holds the number of stations
//               for each bus line, and a "timespan" int which
//               is the time taken to travel to vertex no. 1.
// Postcondition: calls a recursive "relax" method as a 
//                helper method to find the shortest paths
//                and passes to it the parameters.
//-------------------------------------------------------
	
	 {
		 edgeTo = new Edge[G.V()+1];
		 
		 distTo = new double[G.V()+1];
		 
		 pq = new IndexMinPQ<Double>(G.V()+1);
		 
		
		 
		 
		 for (int v = 0; v < G.V(); v++)
		 {
			 distTo[v] = Double.POSITIVE_INFINITY;
		 }
		 
		 distTo[s] = 0.0;
		 
		 pq.insert(s, 0.0);
		 
		 while (!pq.isEmpty())
		 {
			 int v = pq.delMin();
			 
			 for (Edge e : G.adj(v))
			 {
				 relax(e, ALL_Buses_Stations, bus_stations_no, timespan, current_hat);
			 }
		 }
	 } 
	 
	 
	 
	 
	 private void relax(Edge e, int [][] ALL_Buses_Stations, int [] bus_stations_no, int timespan, int current_hat)
//-------------------------------------------------------------
// Summary: finds the shortest path from the 1st vertex to 
//          the given edge considering different bus lines
// Precondition: takes the Graph's edge as parameter as well as 
//               the other parameters passed to the Dijkstra's 
//               method
// Postcondition: takes an edge and checks the distance to that
//                edge from the source vertex, does the needed
//                distance comparisons and updates the "distTo"
//                array and priority queue when needed considering
//                which "hat" or bus line we are using
//
// Note: the modified part in the relax method is commented below
//-------------------------------------------------------------
	
	 {
		 int v = e.either(), w = e.other(v);
		 
		 if (distTo[w] > distTo[v] + e.weight())
		 { 
			 
				
		//-------------------------------------------------------
		// IF the hat/bus number of the given edge is the same 
		// as the hat/bus line number that we used to reach vertex
		// 1, then perform the standard calculation to find the 
		// distance to the new given vertex*. In other words, if we are
		// using the same bus without changing stations then perform 
		// the standard calculation*. 
		// * Note: we just add the "timespan" to the distance which is 
		// the time we took to reach vertex 1
		//-------------------------------------------------------

			 
			 if (e.hat() == current_hat)
			 {
				 distTo[w] = distTo[v] + e.weight() + timespan;
				 edgeTo[w] = e;
			 }
			 
			 
				
		//-------------------------------------------------------
		// ELSE if we need to switch from one bus line to another
		// then we need to wait for the other bus to reach the vertex
		// we are waiting at
		//-------------------------------------------------------

			 else 
			 { 
					
			//-------------------------------------------------------
			// The time we wait for the other bus to come to our station 
			// is initially set to 0
			//-------------------------------------------------------

				 int time = 0;
				
			//-------------------------------------------------------
			// In a while loop we use the 2-D array that holds all 
			// the bus stations of the buses to check where the bus is
			// now. If the bus is not at the same station we are at, then
			// we iterate it so it goes to the next station and keep
			// checking until the bus reaches the station we are at.
			// After that we standardly  calculate the distance to 
			// vertex while adding the waiting time to it
			//-------------------------------------------------------

		    	 while (ALL_Buses_Stations[e.hat()][(((int) distTo[v] + time) % bus_stations_no[e.hat()])] != v)
				 {
					 
					 time++;					 
				 }
				 
				 distTo[w] = distTo[v] + time + e.weight();
				 edgeTo[w] = e;
			 }
			 
			 
			 
			 if (pq.contains(w)) 
			 {
				 pq.decreaseKey(w, distTo[w]);
			 }
			 else 
			 {
				 pq.insert (w, distTo[w]);
			 }
		 }
	 }
	 
	 
	 public double distTo(int v) 
	 { 
		 return distTo[v];
	 }
	 
}





