package Assignment3_Q2;

public class Edge {
	

	//-----------------------------------------------------------------
	// Title: Question 2
	// Author: Basme Zantout
	// ID: 99227947762
	// Section: 1
	// Assignment: 3
	// Description: This is an Edge class, which has the attributes and
    //              methods required for the Edge object used in the Graph
	//-----------------------------------------------------------------
	

	
//-------------------------------------------------------
// Attribute: the vertices at both sides of the edge
//-------------------------------------------------------
	
	 private final int v, w;
	 
		
//-------------------------------------------------------
// Attribute: the number of times we visit an edge
//-------------------------------------------------------

	 private final int weight;
	 
		
//-------------------------------------------------------
// Attribute: each edge has a hat number or a bus line 
//            number
//-------------------------------------------------------

	 private final int hat;
	 
	 
	 
	 public Edge(int v, int w, int weight, int hat)
//------------------------------------------------------------------
// Summary: a Constructor method
// Precondition: this constructor takes the vertices and
//               the edge weight as parameters
// Postcondition: the method initializes the attributes
//------------------------------------------------------------------
	
	 {
		 this.v = v;
		 this.w = w;
		 this.weight = weight;
		 this.hat = hat;
	 }
	 
	 
	 
	 
	 public int either()
//-------------------------------------------------------
// Summary: returns the Vertex at one side of the edge
// Precondition: a getter method
// Postcondition: returns the "v" vertex in an edge
//-------------------------------------------------------

	 { 
		 return v;
	 }
	 
	 
	 
	 
	 public int other(int vertex)
//-------------------------------------------------------
// Summary: returns the vertex at the other side of the edge
// Precondition: a getter method
// Postcondition: returns the vertex facing the given vertex
//-------------------------------------------------------
	
	 {
		 if (vertex == v) return w;
		 else return v;
	 }
	 
	 
	 
	 
	 public int hat()
//-------------------------------------------------------
// Summary: returns the hat/bus number of the edge
// Precondition: a getter method
// Postcondition: returns the hat/bus number
//-------------------------------------------------------
	
	 {
		 return hat;
	 }
	 
	 
	 
	 
	 public int weight()
//-------------------------------------------------------
// Summary: returns the weight integer of an edge
// Precondition: a getter method
// Postcondition: returns the "weight" of an edge
//-------------------------------------------------------
	 {
		 return this.weight;
	 }
	 
	 
	 
	 
	 
	 public int compareTo(Edge that)
//-------------------------------------------------------
// Summary: compares two edges
// Precondition: takes the edge to be compared to as parameter
// Postcondition: the method compares two edges by their 
//                weight values
//-------------------------------------------------------
	
	 {
		 if        (this.weight < that.weight)   return -1;
		 else if   (this.weight > that.weight)   return +1;
		 else                                    return 0;
	 } 
	 
	 

}
