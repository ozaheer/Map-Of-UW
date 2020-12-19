// --== CS400 File Header Information ==--
// Name: Oneeb Zaheer
// Email: ozaheer@wisc.edu
// Team: FC
// Role: Front End Developer 1
// TA: Abhay
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class Interface {
    
    // Lists all of the possible locations that are buildidngs, which that user can choose from to get directions to/from
    // returns a List of Location objects
    private static List<Location> listLocations(UWMap map) {
       List<Location> listOfBuildings = map.getBuildings();
    
       return listOfBuildings;
    }
    //Uses Djikstra's shortest path algorithm to find directions and prints them out to the user
    private static void getDirections(Scanner sc, UWMap map) {
        boolean invalidStartLocation = true;
        boolean invalidEndLocation = true;
        String start = "";
        String end = "";
        List<Location> shortestPath;
        // This loops until a valid start location has been inputted
        while (invalidStartLocation) {
            System.out.println("What's your starting point?");
            if (sc.hasNextLine()) {
            start = sc.nextLine();

            for (int i = 0; i < listLocations(map).size(); i++) {
                if (start.equals(listLocations(map).get(i).toString())) {
                    invalidStartLocation = false;
                    break;
                }
            }
                if (invalidStartLocation) {
                System.out.println("That starting point is invalid! Try again.");
                    }
                
            
            }
        }
        // loops until a valid end location has been inputted
        while (invalidEndLocation) {
            System.out.println("Where are you headed?");
            if (sc.hasNextLine()) {
                end = sc.nextLine();
                for (int i = 0; i < listLocations(map).size(); i++) {
                    if (end.equals(listLocations(map).get(i).toString())) {
                        invalidEndLocation = false;
                        break;
                    }
                }
                    if (invalidEndLocation) {
                    System.out.println("That destination is invalid! Try again.");
                        }
                    
            }
        }
        //If the location on the list of directions is a building, program tells user to walk through building,
        // If the location is not a building, program assumes it is a street and tells user to walk along it.
        try {
            shortestPath = map.getPath(start, end);
            System.out.println("\nDirections!");
            for (int i = 0; i < shortestPath.size(); i++) {
                Location nextLocation = shortestPath.get(i);
                if (nextLocation.getIsBuilding()) {
                    System.out.println("Walk through " + nextLocation.toString() + ".");
                } else {
                    System.out.println("Walk along " + nextLocation.toString() + ".");
                }
            }
        }
        // Because this method handles incorrect location inputs when it asks for the start/end points, this should
        // never trigger
        catch (IllegalArgumentException e) {
            System.out.println("This should never be triggered");
        }
        
    }
    // Built pretty much the same as the getDirections() method but uses the getPathCost() method to return the amount
    // of time in minutes that it would take to travel the shortest path between two locations
    private static void getDistance(Scanner sc, UWMap map) {
        boolean invalidStartLocation = true;
        boolean invalidEndLocation = true;
        String start = "";
        String end = "";
        // Functions the same as in the getDirections() method
        while (invalidStartLocation) {
            System.out.println("What's your starting point?");
            if (sc.hasNextLine()) {
            start = sc.nextLine();

            for (int i = 0; i < listLocations(map).size(); i++) {
                if (start.equals(listLocations(map).get(i).toString())) {
                    invalidStartLocation = false;
                    break;
                }
            }
                if (invalidStartLocation) {
                System.out.println("That starting point is invalid! Try again.");
                    }
                
            }
        }
        // Functions the same as in the getDirections() method
        while (invalidEndLocation) {
            System.out.println("Where are you headed?");
            if (sc.hasNextLine()) {
                end = sc.nextLine();
                for (int i = 0; i < listLocations(map).size(); i++) {
                    if (end.equals(listLocations(map).get(i).toString())) {
                        invalidEndLocation = false;
                        break;
                    }
                }
                    if (invalidEndLocation) {
                    System.out.println("That destination is invalid! Try again.");
                        }
                    
            }
        }

        try {
            int cost = map.getPathCost(start, end);
            System.out.println("Total travel time to get to " + end + " from " + start + " is " + cost + " minutes!");
        }
        catch (IllegalArgumentException e) {
            System.out.println("This should never be triggered.");
        }

    }
    //Called from main to run the front-end interface. The program terminates only when the user inputs "quit"
    private static void runFrontEnd(Scanner sc, UWMap map) {
        boolean userWantsToQuit = false;
        while (!userWantsToQuit) {
            System.out.println("What would you like to do?: List Locations || Get Directions || Get Distance || Quit");
            if (sc.hasNextLine()) {
                String userInput = sc.nextLine();
// If the user enters "List Locations", uses listLocations method and converts to a string which is printed out.
                if (userInput.equalsIgnoreCase("List Locations")) {
                    String listOfBuildingsString = "";
                    for (int i = 0; i < listLocations(map).size(); i++) {
                        listOfBuildingsString = listOfBuildingsString.concat(listLocations(map).get(i).toString() + "\n");
                    }
                    System.out.println("");
                    System.out.println("Here's where you can go to/from:");
                    System.out.println(listOfBuildingsString);
                }
                // Note that in the next two cases (user wanting to get directions or get distance), we pass a scanner
                // object and the UWMap to be used in the getDirections and getDistance methods
                if (userInput.equalsIgnoreCase("Get Directions")) {
                    getDirections(sc, map);
                }
                if (userInput.equalsIgnoreCase("Get Distance")) {
                    getDistance(sc, map);
                }
                // Program terminates when while loop condition is tested again
                if (userInput.equalsIgnoreCase("Quit")) {
                    userWantsToQuit = true;
                    System.out.println("Thanks for using the app!");
                }
            }
        }
    }

    //Exception occurs when the file used to construct the graph is not provided
    public static void main(String[] args) {
        try {
        UWMap map = new UWMap(GraphConstructor.buildGraph());
        Scanner sc = new Scanner(System.in);
        runFrontEnd(sc, map);
        } catch (FileNotFoundException e) {
            System.out.println("The file used to build the UWMap cannot be found.");
        }
        
    }
}
