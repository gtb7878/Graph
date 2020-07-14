
public class MSFImpl implements MSF
{   int[] pred;
    double[] dist;

    @Override
    public void compute(WeightedGraph g, int s)
    {
        if (g == null || s < 0 || s >= g.size()) return;

        BinHeap<Double, Integer> n = new BinHeap<>();
        BinHeap.Entry<Double, Integer>[] e = new BinHeap.Entry[g.size()];
        pred = new int[g.size()];


        for (int i = 0; i < g.size() ; i++) {
            if(i == s){
                continue;
            }
            e[i]= n.insert(Double.POSITIVE_INFINITY,i);
            pred[i] = NIL;

        }
        pred[s] = NIL;
        int u = s;
        while(!n.isEmpty()){
            for(int v = 0; v < g.deg(u); v++){
               int nachfolger = g.succ(u,v);
               if(n.contains(e[nachfolger]) && g.weight(u,v)<e[nachfolger].prio()){
                   n.changePrio(e[nachfolger],g.weight(u,v));
                   pred[nachfolger] = u;

               }

            }
            u = n.extractMin().data();
        }

    }

    @Override
    public int pred(int v)
    {
        return pred[v];
    }
}
