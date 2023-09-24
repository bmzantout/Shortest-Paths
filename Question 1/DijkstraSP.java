package Assignment3_Q1;
import Assignment1_Q1.Stack;
import Assignment2_Q2.IndexMinPQ;

public class DijkstraSP {
	
	
	//-----------------------------------------------------------------
	// Title: Question 1
	// Author: Basme Zantout
	// ID: 99227947762
	// Section: 1
	// Assignment: 3
	// Description: This is a standard Dijkstra Shortest Path algorithm
    //              taken from the "Algorithms", 4th edition by Robert
	//              Sedgewick and Kevin Wayne" book for reference
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
	 
	 
	 
	 public DijkstraSP(Graph G, int s)
//-------------------------------------------------------
// Summary: finds the shortest path from the source 
//          vertex to every other vertex in the graph
// Precondition: takes the Graph and the source vertex 
//               as parameters
// Postcondition: calls a the "relax" method as a 
//                helper method to find the shortest paths
//-------------------------------------------------------
	
	 {
		 edgeTo = new Edge[G.V()];
		 
		 distTo = new double[G.V()];
		 
		 pq = new IndexMinPQ<Double>(G.V());
		 
		 
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
				 relax(e);
			 }
		 }
	 } 
	 
	 
	 
	 private void relax(Edge e)
//-------------------------------------------------------------
// Summary: finds the shortest path from the source 
//          vertex to the given edge
// Precondition: takes the Graph's edge as parameter
// Postcondition: takes an edge and checks the distance to that
//                edge from the source vertex, does the needed
//                distance comparisons and updates the "distTo"
//                array and priority queue when needed
//-------------------------------------------------------------
	
	 {
		 int v = e.either(), w = e.other(v);
		 
		 if (distTo[w] > distTo[v] + e.weight())
		 {
			 distTo[w] = distTo[v] + e.weight();
			 edgeTo[w] = e;
			 
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
	 

	 
}
