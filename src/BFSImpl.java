import java.util.Queue;
import java.util.LinkedList;


public class BFSImpl implements BFS
{
    int[] dist;
    int[] pred;


    // siehe Folie 134.
    @Override
    public void search(Graph g, int s)
    {
        if (g == null) return;


        dist = new int[g.size()];
        pred = new int[g.size()];

        // 1
        for (int i = 0; i < g.size(); i++)
        {
            dist[i] = INF;
            pred[i] = NIL;
        }
        if (s < 0 || s >= g.size()) return;


        // 2
        dist[s] = 0;
        pred[s] = NIL;

        // 3
        Queue<Integer> waitingQueue = new LinkedList<>();
        waitingQueue.add(s);

        // 4
        int u, v;
        while (!waitingQueue.isEmpty())
        {
            // 4.1
            u = waitingQueue.poll();

            // 4.2
            for (int i = 0; i < g.deg(u); i++)
            {
                v = g.succ(u, i);
                if (dist[v] == INF)
                {
                    // 4.2.1
                    dist[v] = dist[u] + 1;
                    pred[v] = u;

                    // 4.2.2
                    waitingQueue.add(v);
                }
            }
        }
    }

    @Override
    public int dist(int v)
    {
        return dist[v];
    }

    @Override
    public int pred(int v)
    {
        return pred[v];
    }
}
