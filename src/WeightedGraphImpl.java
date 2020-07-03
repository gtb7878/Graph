public class WeightedGraphImpl implements WeightedGraph
{
    int[][] amatrix;
    double[][] weights;
    public WeightedGraphImpl(int[][] m, double[][] w)
    {
        amatrix = m;
        weights = w;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public int deg(int v)
    {
        return 0;
    }

    @Override
    public int succ(int v, int i)
    {
        return 0;
    }

    @Override
    public Graph transpose()
    {
        return null;
    }

    @Override
    public double weight(int v, int i)
    {
        return 0;
    }
}
