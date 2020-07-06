public class WeightedGraphImpl implements WeightedGraph
{
    private int[][] amatrix;
    private double[][] weights;
    public WeightedGraphImpl(int[][] m, double[][] w)
    {
        amatrix = m;
        weights = w;
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
        int[][] helpmatrix = new int[size()][size()];
        int[] size = new int[size()];

        for (int v = 0; v < size(); v++) {
            int d = deg(v);
            for (int i = 0; i < d; i++) {
                int n = succ(v, i);
                helpmatrix[n][size[n]] = v;
                size[n]++;
            }
        }
        int[][] invertmatrix = new int[size()][];
        for (int v = 0; v < size(); v++) {
            invertmatrix[v] = new int[size[v]];
            for (int i = 0; i < size[v]; i++) {
                invertmatrix[v][i] = helpmatrix[v][i];
            }
        }

        return new GraphImpl(invertmatrix);

    }

    @Override
    public double weight(int v, int i)
    {
        return weights[v][i];
    }
}
