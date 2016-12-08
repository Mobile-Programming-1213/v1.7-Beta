package ssjk.cafein;

/**
 * Created by wqe13 on 2016-12-06.
 */

abstract class ArrayActivity{
    String[] stringarray;
    String[] alphaarray;
    double[] locationarray;
    int[] intarray;
    int count;

    public abstract String[] getCafeArray();
    public abstract double[] getLocationArray();
    public abstract int[] getIntArray();
    public abstract void remove();
}
