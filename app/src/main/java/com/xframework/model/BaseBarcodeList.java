package com.xframework.model;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseBarcodeList implements Serializable {
    private ArrayList<SWSpareBarcode> barcodes = new ArrayList<>();

    public boolean addBarocde(SWSpareBarcode barcode) {
        barcodes.add(barcode);
        return true;
    }

    public void clear() {
        barcodes.clear();
    }

    public int findBarcode(String barcode) {
        for (int i = 0; i < barcodes.size(); i++) {
            if (barcodes.get(i).getBarcode().equals(barcode))
                return i;
        }
        return -1;
    }

    public ArrayList<SWSpareBarcode> getBarcodes() {
        return barcodes;
    }

    public boolean removeBarcode(SWSpareBarcode barcode) {
        return barcodes.remove(barcode);
    }

    public boolean removeBarcode(String barcode) {
        int i = findBarcode(barcode);
        if (i < 0)
            return false;
        SWSpareBarcode spareBarcode = barcodes.get(i);
        return barcodes.remove(spareBarcode);
    }
}
