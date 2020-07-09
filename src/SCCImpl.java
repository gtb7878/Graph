public class SCCImpl implements SCC
{

    int[] speicher, sort;

    @Override
    public void compute(Graph g)
    {
        speicher = new int[g.size()];
        sort = new int[g.size()];
        DFS dfs1 = new DFSImpl();
        DFS dfs2 = new DFSImpl();

        dfs1.search(g);
        Graph gt = g.transpose();
        dfs2.search(gt, dfs1);

        int zaehler = 0;

        //Knoten sortiert nach Endzeit
        for (int i = 0; i < g.size(); i++)
        {
            sort[i] = dfs2.sequ(i);
        }

        for (int i = g.size()-1; i >= 0; i--)
        {
            if(speicher[sort[i]] != 0){
                continue;
            }

            int det1 = dfs2.det(sort[i]);
            zaehler++;
            speicher[sort[i]] = zaehler;

            for (int y = i - 1; y >= 0; y--)
            {
                int det2 = dfs2.det(sort[y]);

                if (det1 < det2) {
                    speicher[sort[y]] = zaehler;
                }
                else {
                    break;
                }
            }

        }
    }

    @Override
    public int component(int v)
    {
        return speicher[v];
    }
}
