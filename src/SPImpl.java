public class SPImpl implements SP
{
    int[] pred;
    double[] dist;

    @Override
    public boolean bellmanFord(WeightedGraph g, int s)
    {
        pred = new int[g.size()];
        dist = new double[g.size()];

        for (int i = 0; i < g.size() ; i++) {
            pred[i] = NIL;
            dist[i] = INF;
        }
        dist[s] = 0;

        for(int y = 0; y <= g.size()-1;y++){
            for(int z = 0;z <= g.size();z++){
                for(int i=0 ; i <g.deg(z); i++){
                    if(dist[g.succ(z,i)]> dist[z] + g.weight(z,i)){
                        dist[g.succ(z,i)]=dist[z]+g.weight(z,i);
                        pred[g.succ(z,i)]= z;
                    }
                }
            }

        }
        for(int x = 0; x <= g.size()-1; x++){
            for(int i=0 ; i <g.deg(x); i++)
            if(dist[g.succ(x,i)]> dist[x]+ g.weight(x,i)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void dijkstra(WeightedGraph g, int s)
    {
        BinHeap<Double, Integer> n = new BinHeap<>();
        BinHeap.Entry<Double, Integer>[] e = new BinHeap.Entry[g.size()];


        pred = new int[g.size()];
        dist = new double[g.size()];

        for (int i = 0; i < g.size() ; i++) {
            if(i == s){
                dist[s] = 0;
                pred[s] = NIL;
            }
            else {
                dist[i] = INF;
                pred[i] = NIL;
            }

            e[i] = n.insert(dist[i], i);

        }

        while(!(n.size() == 0) ) {
            int u = n.extractMin().data();
            for (int v = 0; v < g.deg(u) ; v++) {
                int j = g.succ(u, v);

                if (n.contains(e[j])){
                    if (dist[u] + g.weight(u, v) < dist[j]){
                        dist[j] = dist[u] + g.weight(u, v);
                        n.changePrio(e[j], dist[j]);
                        pred[j] = u;
                    }
                }
            }
        }

    }

    @Override
    public double dist(int v)
    {
        return dist[v];
    }

    @Override
    public int pred(int v)
    {
        return pred[v];
    }
}
