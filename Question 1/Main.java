package Assignment3_Q1;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		//-----------------------------------------------------------------
		// Title: Question 1
		// Author: Basme Zantout
		// ID: 99227947762
		// Section: 1
		// Assignment: 3
		// Description: This is the main class for Question 1 which calls 
		//              the methods that perform the needed operations 
	    //              which are intended to park given cars in the least
		//              costly parking slots.
		//-----------------------------------------------------------------
		

		
//-------------------------------------------------------
// Creating a Scanner object named "scanner"   
//-------------------------------------------------------
	
		Scanner scan = new Scanner (System.in);
		System.out.println("Enter:");
	
		
//-------------------------------------------------------
// Initiating a Graph object with the given number of 
// vertices and edges 
//-------------------------------------------------------
			
		Graph PARKING_Graph = new Graph(scan.nextInt() , scan.nextInt());

//-------------------------------------------------------
// Reading the parking fee of each parking slot
//-------------------------------------------------------
		
		int parking_fee = scan.nextInt();
		
		
//-------------------------------------------------------
// Initiating "total_capacity" which will hold the total
// capacities of all parking slots
//-------------------------------------------------------

		int total_capacity = 0;
		
		
//-------------------------------------------------------
// Initiating a capacity array that holds the capacity 
// of each vertex or "parking slot" in the graph
//-------------------------------------------------------
	
	    int[] capacity = new int[PARKING_Graph.V()];
	
	    
//-------------------------------------------------------
// Reading the capacity of each parking slot and storing
// it in the "capacity" array while also adding the 
// capacities to the "total_capacity" variable
//-------------------------------------------------------
	
		for (int i = 0 ; i < PARKING_Graph.V() ; i++)
		{
			capacity[i] = scan.nextInt();
			total_capacity += capacity[i];	
		}
		
		
//-------------------------------------------------------
// Reading edge vertices and their weights and adding 
// them to the Graph
//-------------------------------------------------------
	
		for (int i = 0 ; i < PARKING_Graph.E() ; i++)
		{
			Edge e = new Edge(scan.nextInt() - 1 , scan.nextInt() - 1 , scan.nextInt());
			PARKING_Graph.addEdge(e);
		}
		
		
//-------------------------------------------------------
// Reading the total number of cars to be parked
//-------------------------------------------------------
	
		int cars = scan.nextInt();
		
		
//-------------------------------------------------------
// Initializing a "DijkstraSP" array to find the shortest
// paths
//-------------------------------------------------------
	
		DijkstraSP sp = new DijkstraSP(PARKING_Graph, 0);
		
		
	
//-------------------------------------------------------
// In a for-loop, we park the car in the parking slot with 
// the least cost by finding the shortest path from the 
// first vertex to the parking slot
//-------------------------------------------------------
	
	for (int x = 0 ; x < cars ; x++)
		
		{
	
	//-------------------------------------------------------
    // IF there is no more capacity for cars, print out "-1"
    //-------------------------------------------------------
	
			if (total_capacity == 0)
			{
				System.out.print("-1 ");
			}
			
	//-------------------------------------------------------
    // ELSE find he parking slot with the minimum cost and 
	// available capacity
	//-------------------------------------------------------
			
			else
			{
				
			//-------------------------------------------------------
			// A counter for the vertices in the graph (not starting 
			// from the first vertex)
			//-------------------------------------------------------
					
				int i = 1;
				
			//-------------------------------------------------------
			// An int to store the index of the minimum distance
			//-------------------------------------------------------
	
				int index = 0;
				
			//-------------------------------------------------------
			// An int that holds the minimum distance
			//-------------------------------------------------------
	
				int min = (int) sp.distTo[0];
				
				
				
			//-------------------------------------------------------
			// In a while loop, for each vertex in the graph check
			// if it has available capacity (1), then check if its  
			// distance is less than the minimum distance or if the 
			// capacity of the minimum is 0 (2), then update the 
			// minimum distance variable and its index value (3)
			//-------------------------------------------------------
	
				while (i < PARKING_Graph.V())
				{
					// (1)
					
					if (capacity[i] != 0)
					{
						// (2)
						
						if ((capacity[index] == 0) || (sp.distTo[i] < min))
						{
							// (3)
							
							min = (int) sp.distTo[i];
							index = i;
						}
					}
					
					i++;
				}
				
				
			//-------------------------------------------------------
			// Print out the calculated minimum cost of the trip
			//-------------------------------------------------------
				
				System.out.print( (parking_fee + min) + " " );
				
				
			//-------------------------------------------------------
			// Decrease the capacity of the occupied parking slot 
			//-------------------------------------------------------
	
				capacity[index]--;
				
				
			//-------------------------------------------------------
			// Decrease the total capacity of the all parking slots by 1
			//-------------------------------------------------------
	
				total_capacity--;
			}
		}
        

		
	
//-------------------------------------------------------
// Closing the scanner
//-------------------------------------------------------
		
		scan.close();
	}

}














