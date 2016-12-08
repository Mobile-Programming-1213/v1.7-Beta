package ssjk.cafein;

/**
 * Created by wqe13 on 2016-12-06.
 */

public class sortMain extends ArrayActivity implements ArrayCalculation {

    public sortMain(int num){
        stringarray = new String[num];
        locationarray = new double[num];
        alphaarray = new String[num];
        intarray = new int[num];
        count = num;
    }

    @Override
    public String[] getCafeArray() {
        return stringarray;
    }

    @Override
    public double[] getLocationArray() {
        return locationarray;
    }

    @Override
    public int[] getIntArray() {
        return intarray;
    }

    @Override
    public void remove() {
        stringarray = null;
        locationarray = null;
        alphaarray = null;
        intarray = null;
    }


    @Override
    public void sort_array_Alpha() {
        String stmp, stmp2;
        double dtmp;
        for (int i = 0; i < count - 1; i++) {
            int compare = alphaarray[i].compareTo(alphaarray[i + 1]);
            if (compare > 0) {
                stmp = stringarray[i];
                stringarray[i] = stringarray[i + 1];
                stringarray[i + 1] = stmp;
                stmp2 = alphaarray[i];
                alphaarray[i] = alphaarray[i + 1];
                alphaarray[i + 1] = stmp2;
                dtmp = locationarray[i];
                locationarray[i] = locationarray[i+1];
                locationarray[i+1] = dtmp;
                i = -1;
            }
        }
    }

    @Override
    public void sort_array_Loc() {
            String stmp;
            double dtmp;
            for (int i = 0; i < count - 1; i++) {
                if (locationarray[i] > locationarray[i+1]) {
                    stmp = stringarray[i];
                    dtmp = locationarray[i];
                    stringarray[i] = stringarray[i+1];
                    locationarray[i] = locationarray[i+1];
                    stringarray[i+1] = stmp;
                    locationarray[i+1] = dtmp;
                    i = -1;
                }
            }
    }

    @Override
    public void sort_array_Price() {
        int tmp;
        String stmp;
        for (int i = 0; i < count - 1; i ++){
            if(intarray[i] > intarray[i+1]){
                tmp = intarray[i];
                stmp = stringarray[i];
                intarray[i] = intarray[i+1];
                stringarray[i] = stringarray[i+1];
                intarray[i+1] = tmp;
                stringarray[i+1] = stmp;
                i = -1;
            }
        }
    }
}
