// --== CS400 File Header Information ==--
// Name: Yash Ramchandani
// Email: yramchandani@wisc.edu
// Team: FC
// TA: Abhay
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GraphConstructor {
    
    public GraphConstructor() {
    }

    /**
     * Method to build a graph from a text file
     * 
     * @return the graph object of the completed graph
     * @throws FileNotFoundException
     */
    public static CS400Graph<Location> buildGraph() throws FileNotFoundException {
        File file = new File("MapInput.txt");
        Scanner scan = new Scanner(file);
        String currentLine; //the current line of the file
        List<String> lineArray; //a comma separated array of the value from the current line
        ArrayList<Location> locations = new ArrayList<Location>(); //a list of all locations in the graph
        CS400Graph<Location> graph = new CS400Graph<Location>(); //the graph being added to
        Location source; //source for the edges to leave from
        Location temp; //temporary variable used to add edges
        Location end; //the destination for edges
        //the first line contains the names of the vertices so we need to iterate throught the first line to add all the vertices to the graph 
        currentLine = scan.nextLine();
        lineArray = Arrays.asList(currentLine.split("\\s*,\\s*"));
        for (int i = 0; i < lineArray.size(); i += 2) {
            graph.insertVertex(new Location(lineArray.get(i).trim(), Boolean.parseBoolean(lineArray.get(i + 1))));
            locations.add(new Location(lineArray.get(i).trim(), Boolean.parseBoolean(lineArray.get(i + 1))));
        }
        //iterates through each line in the file and adds all the edges
        while (scan.hasNextLine()) {
            currentLine = scan.nextLine(); //the current line being scanned
            lineArray = Arrays.asList(currentLine.split("\\s*,\\s*")); //splits the line into a comma separated array and removes whitespace
            temp = locations.get(locations.indexOf(new Location(lineArray.get(0).trim(), false))); //gets a location object using the name of the location
            source = graph.vertices.get(temp).data; //gets the location object from the vertices in the graph
            //inserts all the outgoing edges from the current vertex
            for (int i = 1; i < lineArray.size(); i += 2) {
                end = new Location(lineArray.get(i).trim(), false); //sets the end location
                graph.insertEdge(source, end, Integer.parseInt(lineArray.get(i + 1))); //inserts the edge
            }
        }
        scan.close();
        return graph;
    }

    public static void main(String[] args) {
        CS400Graph<Location> test = null;
        try {
            test = GraphConstructor.buildGraph();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(test);  
    }
}
