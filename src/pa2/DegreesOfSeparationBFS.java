package pa2;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.algs4.BreadthFirstPaths;

/**
 *  The {@code DegreesOfSeparation} class provides a client for finding
 *  the degree of separation between one distinguished individual and
 *  every other individual in a social network.
 *  As an example, if the social network consists of actors in which
 *  two actors are connected by a link if they appeared in the same movie,
 *  and Kevin Bacon is the distinguished individual, then the client
 *  computes the Kevin Bacon number of every actor in the network.
 *  <p>
 *  The running time is proportional to the number of individuals and
 *  connections in the network. If the connections are given implicitly,
 *  as in the movie network example (where every two actors are connected
 *  if they appear in the same movie), the efficiency of the algorithm
 *  is improved by allowing both movie and actor vertices and connecting
 *  each movie to all of the actors that appear in that movie.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
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
        /*int i, no_args = args.length;
        // Get degrees of separation
        for(i=3;i<no_args;i++) {
            baconGraph.baconNumber(args[i]);
            Stack<Integer> path = baconGraph.graphPath(args[i]);
            baconGraph.printPath(path);
        }*/
    }
}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/



