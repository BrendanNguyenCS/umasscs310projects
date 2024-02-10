package pa2;

import edu.princeton.cs.algs4.*;

/**
 *  The {@code DegreesOfSeparationBFS} class is a modified implementation
 *  of Robert Sedgewick and Kevin Wayne's algs4 class {@link DegreesOfSeparation}.
 *  This class gets an actor's Bacon number and the path between an actor
 *  and the source actor.
 *  @author Brendan Nguyen
 */
public class DegreesOfSeparationBFS {
    /**
     * The symbol graph which models the Bacon graph
     */
    private final SymbolGraph sg;
    /**
     * The object to find the BFS paths from a source
     */
    private final BreadthFirstPaths bfs;

    /**
     * Constructor
     * @param fname the file name
     * @param delimiter the delimiter for {@link SymbolGraph}
     * @param source the source of the Bacon graph
     */
    public DegreesOfSeparationBFS(String fname, String delimiter, String source) {
        sg = new SymbolGraph(fname, delimiter);
        int sourceIndex = sg.indexOf(source);
        bfs = new BreadthFirstPaths(sg.graph(), sourceIndex);
    }

    /**
     * Getter for {@link #sg}
     * @return the symbol graph used as a reference for the Bacon graph
     */
    public SymbolGraph getSymbolGraph() {
        return sg;
    }

    /**
     * Getter for {@link #bfs}
     * @return the object used to find the breadth-first search paths from the source
     */
    public BreadthFirstPaths getBreadthFirstPaths() {
        return bfs;
    }

    /**
     * Returns the actor's Bacon relationship number
     * @param sink the actor
     * @return the bacon number
     */
    public int baconNumber(String sink) {
        if (!sg.contains(sink)) {                   // if the actor isn't listed
            return -1;
        }
        int sinkIndex = sg.indexOf(sink);
        if (!bfs.hasPathTo(sinkIndex)) {            // no connection between the source and sink
            return -1;
        }
        return bfs.distTo(sinkIndex) / 2;
    }

    /**
     * Calculates the path between Bacon and an actor
     * @param sink the actor
     * @return the stack which defines the path between Bacon and the actor
     */
    public Stack<Integer> graphPath(String sink){
        Stack<Integer> path = new Stack<>();
        int sinkIndex = sg.indexOf(sink);
        for (int g : bfs.pathTo(sinkIndex)) {      // Add each part of the path between the actors to path stack
            path.push(g);
        }
        return path;
    }

    /**
     * Prints the string representation of the path between two actors.
     * <p>
     * Note that every other vertex in the stack is an actor with the vertex between them is a movie.
     * @param path the path
     */
    public void printPath(Stack<Integer> path){
        String actor = sg.nameOf(path.pop());
        System.out.println(actor + " has a Bacon number of " + baconNumber(actor));
        while (!path.isEmpty()) {                   // Print out the path that connects the actor to Bacon
            String movie = sg.nameOf(path.pop());   // Get movie title
            String actor2 = sg.nameOf(path.pop());  // Get common actor in movie
            System.out.println(actor + " was in \""
                    + movie + "\" with " + actor2);
            actor = actor2;                         // Update connecting actor in order to trace next connection
        }
    }

    /**
     * Prints the Bacon histogram for this Bacon graph
     */
    public void printHistogram() {
        // compute histogram of Kevin Bacon numbers - 100 for infinity
        int MAX_BACON = 100;
        int[] hist = new int[MAX_BACON + 1];
        Graph G = sg.graph();
        for (int v = 0; v < G.V(); v++) {
            int bacon = Math.min(MAX_BACON, bfs.distTo(v));
            hist[bacon]++;
            // to print actors and movies with large bacon numbers
            if (bacon/2 >= 7 && bacon < MAX_BACON) {
                StdOut.printf("%d %s\n", bacon / 2, sg.nameOf(v));
            }
        }
        // print out histogram - even indices are actors
        for (int i = 0; i < MAX_BACON; i += 2) {
            if (hist[i] == 0) {
                break;
            }
            StdOut.printf("%3d %8d\n", i/2, hist[i]);
        }
        StdOut.printf("Inf %8d\n", hist[MAX_BACON]);
    }

    public static void main(String[] args) {
        // Get command arguments
        String filename  = args[0];
        String delimiter = args[1];
        String source    = args[2];
        DegreesOfSeparationBFS baconGraph = new DegreesOfSeparationBFS(filename, delimiter, source);
        baconGraph.printHistogram();
        // Get degrees of separation for actors
        for(int i = 3; i < args.length; i++) {
            baconGraph.baconNumber(args[i]);
            Stack<Integer> path = baconGraph.graphPath(args[i]);
            baconGraph.printPath(path);
        }
    }
}


