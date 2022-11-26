package pa2;

import edu.princeton.cs.algs4.*;

/**
 *  The {@code DegreesOfSeparationBFS} class is a modified implementation
 *  of Robert Sedgewick and Kevin Wayne's algs4 class {@code DegreesOfSeparation}.
 *  This class gets an actor's bacon number and the path between an actor
 *  and the source actor.
 *
 *  @author Brendan Nguyen
 */
public class DegreesOfSeparationBFS {
    private SymbolGraph sg;
    private BreadthFirstPaths bfs;

    // this class cannot be instantiated
    public DegreesOfSeparationBFS(String fname, String delimiter, String source) {
        sg = new SymbolGraph(fname, delimiter);
        int sourceIndex = sg.indexOf(source);
        bfs = new BreadthFirstPaths(sg.graph(), sourceIndex);
    }

    // Getters
    public SymbolGraph getSymbolGraph() { return sg; }
    public BreadthFirstPaths getBreadthFirstPaths() { return bfs; }

    // Read actor's name from standard input, print bacon relationship
    public int baconNumber(String sink)
    {
        // if the actor isn't listed
        if (!sg.contains(sink)) {
            return -1;
        }
        int sinkIndex = sg.indexOf(sink);
        // no connection between the source and sink
        if (!bfs.hasPathTo(sinkIndex)) {
            return -1;
        }
        return bfs.distTo(sinkIndex)/2;
    }

    // Calculate the path itself.
    public Stack<Integer> graphPath(String sink){
        Stack<Integer> path = new Stack<Integer>();
        // Get the sink index
        int sinkIndex = sg.indexOf(sink);
        // Add each part of the path between the actors to path stack
        for (int g : bfs.pathTo(sinkIndex)) {
            path.push(g);
        }
        return path;
    }

    public void printPath(Stack<Integer> path){
        // Now print. Every other vertex is an actor
        // Printing the current actor's bacon number
        String actor = sg.nameOf(path.pop());
        System.out.println(actor + " has a Bacon number of " + baconNumber(actor));
        // Print out the path that connects the actor to Bacon
        while (!path.isEmpty()) {
            // Get movie title
            String movie = sg.nameOf(path.pop());
            // Get common actor in movie
            String actor2 = sg.nameOf(path.pop());
            System.out.println(actor + " was in \"" + movie + "\" with " + actor2);
            // Update connecting actor in order to trace next connection
            actor = actor2;
        }
    }

    public void printHistogram() {
        // compute histogram of Kevin Bacon numbers - 100 for infinity
        int MAX_BACON = 100;
        int[] hist = new int[MAX_BACON + 1];
        Graph G = sg.graph();
        for (int v = 0; v < G.V(); v++) {
            int bacon = Math.min(MAX_BACON, bfs.distTo(v));
            hist[bacon]++;

            // to print actors and movies with large bacon numbers
            if (bacon/2 >= 7 && bacon < MAX_BACON)
                StdOut.printf("%d %s\n", bacon/2, sg.nameOf(v));
        }

        // print out histogram - even indices are actors
        for (int i = 0; i < MAX_BACON; i += 2) {
            if (hist[i] == 0) break;
            StdOut.printf("%3d %8d\n", i/2, hist[i]);
        }
        StdOut.printf("Inf %8d\n", hist[MAX_BACON]);
    }


    /*
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        String source    = args[2];

        DegreesOfSeparationBFS baconGraph = new DegreesOfSeparationBFS(filename, delimiter, source);

        baconGraph.printHistogram();

        // Print the Bacon diagram
        //baconGraph.printBaconDiagram();
        int i, no_args = args.length;
        // Get degrees of separation
        for(i=3;i<no_args;i++) {
            baconGraph.baconNumber(args[i]);
            Stack<Integer> path = baconGraph.graphPath(args[i]);
            baconGraph.printPath(path);
        }
    }
}


