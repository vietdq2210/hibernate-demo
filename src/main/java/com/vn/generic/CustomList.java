package com.vn.generic;

import java.util.Arrays;

public class CustomList<E>  {
    public static final int CAPACITY_DEFAULT =10;
    Object[] values = new Object [CAPACITY_DEFAULT];

    int lengthActual = 0;
    int capacityActual = CAPACITY_DEFAULT;

    public void add(Object element){
        if (lengthActual < capacityActual){
            values[lengthActual] = element;
            lengthActual++;
        }else {
            //new
            values = refreshCapacity (values);
            values[lengthActual] = element;
            lengthActual++;
        }
    }

    private Object[] refreshCapacity(Object[] oldArray) {
        capacityActual = capacityActual * 2;
        return Arrays.copyOf(oldArray,capacityActual);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lengthActual; i++) {
            stringBuilder.append(values[i] + " ");
        }
        return stringBuilder.toString();
    }
}
