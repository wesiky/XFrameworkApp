package com.xframework.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComponentBatchBarcodeList  implements Serializable {
    private ArrayList<LTDelivery> barcodes = new ArrayList<>();

    private boolean canAddItem = true;

    private boolean canDelEmpty = true;

    public ComponentBatchBarcodeList() {}

    public ComponentBatchBarcodeList(boolean canDelEmpty, boolean canAddItem) {
        this.canDelEmpty = canDelEmpty;
        this.canAddItem = canAddItem;
    }

    public boolean addBarocde(LTDelivery barcode) {
        if (findBarcode(barcode.getBarcode()) < 0) {
            barcodes.add(barcode);
            return true;
        }
        return false;
    }

    public void clear() {
        barcodes.clear();
    }

    public int findBarcode(String barcode) {
        for (int i = 0; i < barcodes.size(); i++) {
            if ((barcodes.get(i)).getBarcode().equals(barcode))
                return i;
        }
        return -1;
    }

    public ArrayList<LTDelivery> getBarcodes() {
        return barcodes;
    }

    public boolean removeBarcode(LTDelivery barcode) {
        return barcodes.remove(barcode);
    }

    public boolean removeBarcode(String barcode) {
        int i = findBarcode(barcode);
        if (i < 0)
            return false;
        LTDelivery ltBarcode = barcodes.get(i);
        return barcodes.remove(ltBarcode);
    }
}
