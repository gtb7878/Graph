public class DFSImpl implements DFS
{

    int[] det, fin, pred;
    int time;
    String[] colour;

    @Override
    public void search(Graph g)
    {
        if (g == null) return;

        colour = new String[g.size()];
        det = new int[g.size()];
        fin = new int[g.size()];
        pred = new int[g.size()];


        for (int i = 0; i < g.size(); i++)
        {
            colour[i] = "white";
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

    }

    @Override
    public boolean sort(Graph g)
    {
        return false;
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
        return 0;
    }


    // Hilfsfunktionen

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
