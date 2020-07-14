
public class MSFImpl implements MSF
{   int[] pred;
    double[] dist;


    // siehe Folie 173
    @Override
    public void compute(WeightedGraph g, int s)
    {
        if (g == null || s < 0 || s >= g.size()) return;

        BinHeap<Double, Integer> n = new BinHeap<>();
        BinHeap.Entry<Double, Integer>[] e = new BinHeap.Entry[g.size()];
        pred = new int[g.size()];

        // 1
        for (int i = 0; i < g.size() ; i++) {
            if(i == s){
                continue;
            }
            // 1.1
            e[i]= n.insert(Double.POSITIVE_INFINITY,i);
            // 1.2
            pred[i] = NIL;

        }
        // 2
        pred[s] = NIL;
        // 3
        int u = s;
        // 4
        while(!n.isEmpty()){
            // 4.1
            for(int v = 0; v < g.deg(u); v++){
               int nachfolger = g.succ(u,v);
               if(n.contains(e[nachfolger]) && g.weight(u,v)<e[nachfolger].prio()){
                   // 4.1.1
                   n.changePrio(e[nachfolger],g.weight(u,v));
                   // 4.1.2
                   pred[nachfolger] = u;

               }

            }
            // 4.2
            u = n.extractMin().data();
        }

    }

    @Override
    public int pred(int v)
    {
        return pred[v];
    }
}
