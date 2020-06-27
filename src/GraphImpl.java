public class GraphImpl implements  Graph
{
    private int[][] amatrix;
    public GraphImpl(int[][] m)
    {
        amatrix = m;
    }

    @Override
    public int size()
    {
        return amatrix.length;
    }

    @Override
    public int deg(int v)
    {
        if (v < 0 || v >= size()) return -1;
        return amatrix[v].length;
    }

    @Override
    public int succ(int v, int i)
    {
        if (v < 0 || v >= size()) return -1;
        else
        {
            if (i < 0 || i >= deg(v)) return -1;
            return amatrix[v][i];
        }
    }

    @Override
    public Graph transpose()
    {
        return null;  // TODO
    }
}
