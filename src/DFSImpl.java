public class DFSImpl implements DFS
{
    int[] det, fin, pred, node, sfin;;// snode;
    //int[] sdet, sfin, spred, snode;
    int time;
    String[] colour;
    private boolean sequed, cycle;

    @Override
    public void search(Graph g)
    {
        sequed = false;
        if (g == null) return;

        colour = new String[g.size()];
        det = new int[g.size()];
        fin = new int[g.size()];
        pred = new int[g.size()];
        node = new int[g.size()];


        for (int i = 0; i < g.size(); i++)
        {
            colour[i] = "white";
            node[i] = i;
        }
        time = 0;
        // 0
        for (int i = 0; i < g.size(); i++)
        {
            if (colour[i].equals("white"))
            {
                // 1
                pred[i] = -1;

                // 2
                searchthrough(g, i);
            }

        }





    }

    @Override
    public void search(Graph g, DFS d)
    {
        sequed = false;
        if (g == null || d == null) return;

        colour = new String[g.size()];
        det = new int[g.size()];
        fin = new int[g.size()];
        pred = new int[g.size()];
        node = new int[g.size()];


        for (int i = 0; i < g.size(); i++)
        {
            colour[i] = "white";
            node[i] = i;
        }
        time = 0;
        // 0
        for (int i = g.size()-1; i >= 0; i--)
        {
            int node = d.sequ(i);
            if (colour[node].equals("white"))
            {
                // 1
                pred[node] = -1;

                // 2
                searchthrough(g, node);
            }

        }
    }

    @Override
    public boolean sort(Graph g)
    {
        sequed = false;
        cycle = false;
        if (g == null) return false;

        colour = new String[g.size()];
        det = new int[g.size()];
        fin = new int[g.size()];
        pred = new int[g.size()];
        node = new int[g.size()];


        for (int i = 0; i < g.size(); i++)
        {
            colour[i] = "white";
            node[i] = i;
        }
        time = 0;
        // 0
        for (int i = 0; i < g.size(); i++)
        {
            if (colour[i].equals("white"))
            {
                // 1
                pred[i] = -1;

                // 2
                searchthrough(g, i);
            }

        }
        return !cycle;
    }

    @Override
    public int det(int v)
    {
        return det[v];
    }

    @Override
    public int fin(int v)
    {
        return fin[v];
    }

    @Override
    public int sequ(int i)
    {
        // sortieren

        //int[] ret = sort();
        //return ret[i];

        if (!sequed)
        {
            sfin = fin.clone();
            return sort()[i];
        }
        return node[i];






        //return i;
    }


    // Hilfsfunktionen


    private int[] sort()
    {
        int tempfin, tempnode, tempdet, temppred;

        for (int i = 0; i < node.length - 1; i++)
        {
            if (sfin[i] < sfin[i+1])
            {
                continue;
            }
            tempfin = sfin[i];
            tempnode = node[i];
            //tempdet = det[i];
            //temppred = pred[i];
            sfin[i] = sfin[i+1];
            node[i] = node[i+1];
            //det[i] = det[i+1];
            //pred[i] = pred[i+1];
            sfin[i+1] = tempfin;
            node[i+1] = tempnode;
            //det[i+1] = tempdet;
            //pred[i+1] = temppred;
            sort();



        }
        sequed = true;
        return node;
    }

    private void searchthrough(Graph g, int u)
    {

        time++;
        // 2.1
        if (time <= 2*g.size())
        {
            det[u] = time;
            colour[u] = "gray";

            // 2.2
            for (int i = 0; i < g.deg(u); i++)
            {
                // check sort: cycle?
                if (colour[g.succ(u, i)].equals("gray")) cycle = true;

                if (colour[g.succ(u, i)].equals("white"))
                {
                    // 2.2.1
                    pred[g.succ(u, i)] = u;

                    // 2.2.2
                    searchthrough(g, g.succ(u, i));

                }


            }
            time++;
            if (time <= 2*g.size())
            {
                fin[u] = time;
                colour[u] = "black";
            }

        }
    }


}
