package Assignment3_Q2;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//-----------------------------------------------------------------
		// Title: Question 2
		// Author: Basme Zantout
		// ID: 99227947762
		// Section: 1
		// Assignment: 3
		// Description: This is the main class for Question 1 which calls 
		//              the methods that perform the needed operations
	    //              which are intended to find the minimum amount of time
		//              needed to reach each station, or state if it is impossible
	    //              given a set of bus lines.
		//-----------------------------------------------------------------
		

		
//-------------------------------------------------------
// Creating a Scanner object named "scanner"   
//-------------------------------------------------------
	
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter: ");
	
		
//-------------------------------------------------------
// Reading the number of vertices (stations) in the graph
//-------------------------------------------------------

		int vertices = scan.nextInt();

			
//-------------------------------------------------------
// Reading the number of buses giving service
//-------------------------------------------------------

		int buses = scan.nextInt();
		
		
//-------------------------------------------------------
// Initiating an array to store the number of stations 
// in each bus line
//-------------------------------------------------------

		int [] bus_stations_no = new int [buses];
		
		
//-------------------------------------------------------
// Initiating a 2-D array to store all the stations of 
// all the buses
//-------------------------------------------------------

		int [][] ALL_Buses_Stations = new int [buses] [vertices];
		
		
//-------------------------------------------------------
// Initiating an array to know which bus lines include
// the starting vertex, station number 1
//-------------------------------------------------------

		int [] bus_station_START = new int [buses];
		
		for (int x = 0 ; x < bus_station_START.length ; x++)
		{
			bus_station_START[x] = -1;
		}
//-----------------------------------------------------------
// Initiating a Directed Graph with given number of vertices
//-----------------------------------------------------------
	
		Digraph Graph = new Digraph(vertices + 1);
		
		
		
		
//-----------------------------------------------------------
// In a for-loop, for each bus line:
//-----------------------------------------------------------
			
		for (int i = 0 ; i < buses ; i++)
		{
			
		//-----------------------------------------------------------
		// Read the number of stations in the bus line and store it
		// in the "bus_stations_no" array
		//-----------------------------------------------------------

			bus_stations_no[i] = scan.nextInt();
	
			
		//-----------------------------------------------------------
		// Create an auxiliary array with the number of stations as 
	    // its size. 
		//-----------------------------------------------------------

			int [] aux = new int[bus_stations_no[i]];
			
			
		//-----------------------------------------------------------
		// Assign the bus station numbers to the auxiliary array
		// and check if the stations include station number 1, if 
		// it is included then assign the position of the bus station 
		// to the "bus_station_START" array
		//-----------------------------------------------------------

			for (int j = 0 ; j < aux.length ; j++)
			{
				aux [j] = scan.nextInt();
				
				if (aux[j] == 1)
				{
					bus_station_START[i] = j;
				}
			}
				
			
		//-----------------------------------------------------------
		// Assign the bus stations in the auxiliary array to the 
		// 2-D "ALL_Buses_Stations" array that is intended to hold
		// all the bus stations of all buses
		//-----------------------------------------------------------
   
			for (int x = 0 ; x < aux.length ; x ++)
			{
				ALL_Buses_Stations [i][x] = aux[x];
			}
			
			
		//-----------------------------------------------------------
		// Establish the connecions between the bus stations stored
		// in the auxiliary array and create the Edges and Graph 
		// accordingly
		//-----------------------------------------------------------
   
			for (int j = 0 ; j < aux.length-1 ; j++)
			{
				
				Edge e = new Edge(aux[j], aux[j+1], 1 , i);
				Graph.addEdge(e);
				
			}
			
			
		//-------------------------------------------------------------
		// Establish the connection between the last and first stations
		//-------------------------------------------------------------
   
			Edge e = new Edge (aux[aux.length - 1], aux[0], 1 , i);
			Graph.addEdge(e);

		}
		
		
		
		
//-----------------------------------------------------------------------
// We may reach the first vertex using several bus lines. Particularly,
// all the bus lines that include the starting vertex. So we calculate the 
// path to the vertices starting from all the bus lines that include the 
// vertex 1 and compare them with each other to get the minimum among them
// The minimum value is initially set to the integer's maximum value
//-----------------------------------------------------------------------

		int min = Integer.MAX_VALUE;
		
		
//-----------------------------------------------------------------------
// The time needed to reach a vertex is also initially set to the 
// integer's maximum value
//-----------------------------------------------------------------------

		int time = Integer.MAX_VALUE;
	
		
//-------------------------------------------------------------
// Starting from the second vertex, for each vertex in the 
// graph (for each bus station excluding the 1st station)
// find the shortest path to it as follows:
//-------------------------------------------------------------

		for (int x = 2 ; x <= vertices ; x++)
		{
			
		//-------------------------------------------------------------
		// For each bus line:
		//-------------------------------------------------------------
   
			for (int i = 0 ; i < bus_station_START.length ; i++)
			{
				
			//-------------------------------------------------------------
			// If the bus line includes the starting vertex 1:
			//-------------------------------------------------------------
			
				if (bus_station_START[i] != -1)
				{
					
				//-----------------------------------------------------------------
				// Initialize a DijkstraSP object that has the Graph, 1 as a 
				// source vertex, the hat/bus line number we used to reach vertex 1, 
				// all the bus stations of all buses, the number of bus stations 
				// in each bus line, and time needed to reach vertex 1 as parameters
				//-----------------------------------------------------------------
			   
					DijkstraSP sp = new DijkstraSP(Graph, 1, i, ALL_Buses_Stations, bus_stations_no, bus_station_START[i]);
					
					
				//-------------------------------------------------------------
				// Get the time needed to reach the vertex
				//-------------------------------------------------------------
				
				    time = (int) sp.distTo(x);
				    
				    
				}
				
			//-------------------------------------------------------------
			// We keep comparing all the possible paths to reach a vertex 
			// and get the minimum/shortest one among them
			//-------------------------------------------------------------

				min = Math.min(min, time);
				
			}
			
			
		//-------------------------------------------------------------
		// IF there is no path to a station we print "-1"
		//-------------------------------------------------------------
			
			if (min == Integer.MAX_VALUE)
			{
				System.out.print("-1 ");
			}
			
		//-------------------------------------------------------------
		// OTHERWISE we print the shortest distance to the console
		//-------------------------------------------------------------
	
			else 
			{
				System.out.print(min + " ");
			}
			
		//-------------------------------------------------------------
		// We update the "min" and "time" values
		//-------------------------------------------------------------
	
			min = Integer.MAX_VALUE;
			time = Integer.MAX_VALUE;
		}
		
		
		
//-------------------------------------------------------
// Closing the scanner
//-------------------------------------------------------
		
		scan.close();
		
	}

}
