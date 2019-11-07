package com.lusostudios.asphaltcalc.calculations;

import java.util.ArrayList;

public class ConcreteBags {
    public ArrayList<Double> BagQuantity(double volume) {

        double bags1 = volume / 0.016666666666666666d;
        double bags2 = volume / 0.022222222222222223d;
        ArrayList<Double> bagList = new ArrayList();

        bagList.add(Double.valueOf(bags1));
        bagList.add(Double.valueOf(bags2));

        return bagList;
    }
}
