import java.io.*;

// Testprogramm für Graphalgorithmen.
class GraphTest {
    // Testgraphen.
    // (Für eigene Tests können beliebige weitere Testgraphen
    // hinzugefügt werden.)
    private static Graph [] graphs = {
            new GraphImpl(new int[][]{
                    {0, 1},
                    {0, 2, 3},
                    {1, 3},
                    {1},
                    {3, 5},
                    {4, 5}
            }),

            new GraphImpl(new int[][]{
            }),

            // Beispiel eines ungewichteten Graphen.
            new GraphImpl(new int[][]{
                    {1, 2},
                    {},
                    {2}
            }),

            // Beispiel eines gewichteten Graphen.
            new WeightedGraphImpl(new int[][]{
                    {1, 2},
                    {},
                    {2}
            }, new double[][]{
                    {1.5, 0},
                    {},
                    {-3.7}
            }),

            // Beispiel eines gewichteten ungerichteten Graphen.
            new WeightedGraphImpl(new int[][]{
                    {1, 5, 4},
                    {0, 2, 3, 4, 5},
                    {1, 4, 3},
                    {2, 1, 4},
                    {3, 2, 1, 0, 5},
                    {0, 1, 4}
            }, new double[][]{
                    {3, 5, 1},
                    {3, 8, 7, 2, 5},
                    {8, 7, 5},
                    {5, 7, 8},
                    {8, 7, 2, 1, 4},
                    {5, 5, 4}
            }),

            new WeightedGraphImpl(new int[][]{
                    {3},
                    {0},
                    {0, 1},
                    {1, 2}
            }, new double[][]{
                    {4},
                    {3},
                    {6, 2},
                    {5, 1}
            }),

            // Eine ungewöhnliche Implementierung des Graphen 0 <-> 1
            // ohne Verwendung von Adjazenzlisten.
            new Graph() {
                public int size() {
                    return 2;
                }

                public int deg(int v) {
                    return 1;
                }

                public int succ(int v, int i) {
                    return 1 - v;
                }

                public Graph transpose() {
                    return this;
                }
            },

            new GraphImpl(new int[][]{
                    {1, 3, 5},
                    {},
                    {3, 6},
                    {4, 5},
                    {0, 5},
                    {1},
                    {2, 4, 6}
            })
    };
    // name und value ausgeben.
    private static <T> void print (String name, T value) {
        System.out.print(" " + name + "=" + value);
    }

    // v sowie name1, value1, name2 und value2 ausgeben
    // (name2 und value2 nur, wenn sie nicht null sind).
    private static <T1, T2>
    void print (int v, String name1, T1 value1, String name2, T2 value2) {
        System.out.print(v + ":");
        print(name1, value1);
        if (name2 != null) print(name2, value2);
        System.out.println();
    }

    // v sowie name und value ausgeben.
    private static <T> void print (int v, String name, T value) {
        print(v, name, value, null, null);
    }

    // Hauptprogramm.
    // Auswahl des Algorithmus durch das erste Kommandozeilenargument:
    // bfs -> breadth first search
    // dfs -> depth first search
    // sort -> topological sort
    // scc -> strongly connected components
    // msf -> minimum spanning forest
    // bell -> Bellman-Ford
    // dijk -> Dijkstra
    // Auswahl des Testgraphen durch das zweite Kommandozeilenargument.
    // (Bei den Algorithmen msf, bell und dijk muss ein gewichteter
    // Graph ausgewählt werden.)
    // Auswahl des Startknotens durch das optionale dritte
    // Kommandozeilenargument (Standardwert 0).
    public static void main (String [] args) {
        // Kommandozeilenargumente.
        String algo = args[0];
        Graph graph = graphs[Integer.parseInt(args[1])];
        int s = args.length >= 3 ? Integer.parseInt(args[2]) : 0;

        // Gewünschten Algorithmus ausführen
        // und die von ihm ermittelte Information ausgeben.
        int n = graph.size();
        switch (algo) {
            case "bfs":
                BFS bfs = new BFSImpl();
                bfs.search(graph, s);
                for (int v = 0; v < n; v++) {
                    print(v, "dist", bfs.dist(v), "pred", bfs.pred(v));
                }
                break;
            case "dfs":
                DFS dfs = new DFSImpl();
                dfs.search(graph);
                for (int v = 0; v < n; v++)
                {
                    print(v, "det", dfs.det(v), "fin", dfs.fin(v));
                }
                break;
            case "sort":
                DFS dfs2 = new DFSImpl();
                if (algo.equals("dfs")) {
                    dfs2.search(graph);
                }
                else {
                    if (!dfs2.sort(graph)) {
                        System.out.println("cycle");
                        return;
                    }
                }
                for (int i = 0; i < n; i++) {
                    int v = dfs2.sequ(i);
                    print(v, "det", dfs2.det(v), "fin", dfs2.fin(v));
                }
                break;
            case "scc":
                SCC scc = new SCCImpl();
                scc.compute(graph);
                for (int v = 0; v < n; v++) {
                    print(v, "component", scc.component(v));
                }
                break;
            case "msf":
                MSF msf = new MSFImpl();
                msf.compute((WeightedGraph)graph, s);
                for (int v = 0; v < n; v++) {
                    print(v, "pred", msf.pred(v));
                }
                break;
            case "bell":
            case "dijk":
                SP sp = new SPImpl();
                if (algo.equals("bell")) {
                    if (!sp.bellmanFord((WeightedGraph)graph, s)) {
                        System.out.println("negative cycle");
                        return;
                    }
                }
                else {
                    sp.dijkstra((WeightedGraph)graph, s);
                }
                for (int v = 0; v < n; v++) {
                    print(v, "dist", sp.dist(v), "pred", sp.pred(v));
                }
                break;
            default:
                System.out.println("unknown algorithm: " + algo);
                return;
        }
    }
}
