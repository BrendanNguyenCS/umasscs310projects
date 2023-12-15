package pa2;

import edu.princeton.cs.algs4.*;

import java.util.concurrent.atomic.AtomicInteger;

public class DijkstraTieSP {
    /**
     * Array which holds the distances of vertices to the source
     * distTo[v] = distance  of shortest s->v path
     */
    private final double[] distTo;
    /**
     * Array which holds the last edge of the shortest path between vertices and the source
     * edgeTo[v] = last edge on shortest s->v path
     */
    private final DirectedEdge[] edgeTo;
    /**
     * The priority queue of the vertices
     */
    private final IndexMinPQ<Double> pq;

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every other
     * vertex in the edge-weighted digraph {@code G}.
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     */
    public DijkstraTieSP(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException("edge " + e + " has negative weight");
            }
        }
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        validateVertex(s);
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        // relax vertices in order of distance from s
        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
        // check optimality conditions
        assert check(G, s);
    }

    /**
     * Relax an edge and update {@link #pq} if changed
     * @param e the edge to relax
     */
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
        // if they are equal
        else if (distTo[w] == distTo[v] + e.weight()) {
            // get size of paths
            AtomicInteger countPathW = new AtomicInteger();
            AtomicInteger countPathV = new AtomicInteger();
            pathTo(w).forEach(d -> countPathW.getAndIncrement());
            pathTo(v).forEach(d -> countPathV.getAndIncrement());
            if (countPathW.get() > countPathV.get()) {
                edgeTo[w] = e;
            }
        }
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    /**
     * Checks for the optimality conditions
     * <ol type="i">
     *     <li>For all edges {@code e}: distTo[e.to()] <= distTo[e.from()] + e.weight()
     *     <li>For all edges {@code e} on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()
     * </ol>
     * @param G the edge weighted digraph
     * @param s the source vertex
     * @return {@code true} if there is an optimal solution, {@code false} otherwise
     */
    private boolean check(EdgeWeightedDigraph G, int s) {
        // check that edge weights are non-negative
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }

        for (int v = 0; v < G.V(); v++) {
            if (v == s) {
                continue;
            }
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) {
                continue;
            }
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) {
                return false;
            }
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    /**
     * @param v the vertex
     * @throws IllegalArgumentException if the vertex doesn't satisfy the condition {@code 0 <= v < V}
     */
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    /**
     * Unit tests for the {@link DijkstraSP} and {@link DijkstraTieSP} data types.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        int s = Integer.parseInt(args[1]);

        // compute shortest paths
        DijkstraSP sp = new DijkstraSP(G, s);
        DijkstraTieSP sp2 = new DijkstraTieSP(G, s);

        // print shortest path for original DijkstraSP
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
        System.out.println();
        // print shortest path for new DijkstraTieSP
        for (int t = 0; t < G.V(); t++) {
            if (sp2.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp2.distTo(t));
                for (DirectedEdge e : sp2.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }
}
