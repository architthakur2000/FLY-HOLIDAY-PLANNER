import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// This is the frontend class used to operate the user side of the project. 

public class Frontend {

	private static Scanner scnr;
	private static CS400Graph<String> graph;
	private static AirportDataReader airports;

	public static void main(String[] args) {
		scnr = new Scanner(System.in);
		graph = new CS400Graph<>();
		airports = new AirportDataReader();
		
		boolean check = false;
		String input = "";

		while (!check) {
			
			printMainMenu();
			System.out.print("Enter a valid command");
			input = scnr.nextLine();
			switch (input) {
		
			// This case is used to access all the cities in the data map. 
			case "a":
				Scanner airportList;
				try {
					airportList = new Scanner(new File("AirportData.csv"));
					while (airportList.hasNextLine()) {
						System.out.println(airportList.nextLine());
					}
					break;
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}
				
				
			//This case is used to search all the cities in the data map. 
			case "f":
				System.out.print("Enter a city with aiport");
				String city = scnr.nextLine();

				if (graph.containsVertex(city)) {
					System.out.println(city + " is in the data map");
				} else {
					System.out.println(city + " is not in the data map");
				}
				break;
				
			// This Case helps find the shortest Path between cities
			case "s":
				System.out.println("Enter your starting point");
				String cityA = scnr.nextLine();

				if (!graph.containsVertex(cityA)) {
					System.out.println(cityA + " is not the map.");
					break;
				}

				System.out.print("Enter your ending point");
				String cityB = scnr.nextLine();

				if (!graph.containsVertex(cityB)) {
					System.out.println(cityB + " is not the map.");
					break;
				}
				try {
					String path = graph.shortestPath(cityA, cityB).toString();
					System.out.print("The shortest route between these two cities is: ");
					System.out.println(path);
				} catch (NoSuchElementException e) {
					System.out.println("There is not a path between those cities");
				}
				break;
			
			
				//Returns the shortest distance between the two cities. 
			case "c":
				System.out.print("Enter a starting city: ");
				String cityC = scnr.nextLine();

				if (!graph.containsVertex(cityC)) {
					System.out.println(cityC + " is not the data map.");
					break;
				}

				System.out.print("Enter the ending point");
				String cityD = scnr.nextLine();

				if (!graph.containsVertex(cityD)) {
					System.out.println(cityD + " is not the map.");
					break;
				}
				// Print a message for the user if there is no path.
				try {
					graph.getPathCost(cityC, cityD);
					System.out.print(
							"Shortest and cheapest flight for the chosen destinations: ");
					System.out.println(graph.getPathCost(cityC, cityD));
				} catch (NoSuchElementException e) {
					System.out.println("No path exists between " + cityC + " & " + cityD + ".");
				}
				break;
				// This case exits the application.  
			case "x":
				check = true;
				break;

			}
		}
		System.out.println("Thank You! for Using Flight Planner");
		scnr.close();
	}

	private static void printMainMenu() {
		System.out.println("Welcome to Flight Planner!");
		System.out.println("a. Access all cities");
		System.out.println("f. Search a city in the data map");
		System.out.println("s. Find the shortest path between cities.");
		System.out.println("c. Return the shortest distance between cities.");
		System.out.println("x. Exit");
	}
}
// Author: ARCHIT THAKUR