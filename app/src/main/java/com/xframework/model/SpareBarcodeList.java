package com.xframework.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpareBarcodeList implements Serializable {
    private List<SWSpareBarcode> barcodes = new ArrayList<SWSpareBarcode>();

    private boolean canAddItem = true;

    private boolean canDelEmpty = true;

    private ArrayList<SpareItem> items = new ArrayList<SpareItem>();

    public SpareBarcodeList() {}

    public SpareBarcodeList(boolean canDelEmpty, boolean canAddItem) {
        this.canDelEmpty = canDelEmpty;
        this.canAddItem = canAddItem;
    }

    public void RefreshItemsQuantity() {
        for (SpareItem item:items) {
            item.setQuantity(0);
        }
        for (SWSpareBarcode spareBarcode : barcodes) {
            int i = findItem(spareBarcode.getSpareCode());
            if (i >= 0){
                items.get(i).setQuantity(items.get(i).getQuantity() + spareBarcode.getChangQuantity());
            }
        }
    }

    public boolean addBarocde(SWSpareBarcode barcode) {
        barcodes.add(barcode);
        int i = findItem(barcode.getSpareCode());
        if (i < 0) {
            if (canAddItem) {
                SpareItem spareItem = new SpareItem();
                spareItem.setSpareId(barcode.getSpareId());
                spareItem.setSpareCode(barcode.getSpareCode());
                spareItem.setSpareName(barcode.getSpareName());
                spareItem.setSpareModel(barcode.getSpareModel());
                spareItem.setQuantity(barcode.getChangQuantity());
                items.add(spareItem);
            } else {
                return false;
            }
        } else {
            SpareItem spareItem = items.get(i);
            spareItem.setQuantity(spareItem.getQuantity() + barcode.getChangQuantity());
        }
        return true;
    }

    public boolean addItem(SpareItem item) {
        if (findItem(item.getSpareCode()) >= 0)
            return false;
        items.add(item);
        return true;
    }

    public void clear() {
        barcodes.clear();
        items.clear();
    }

    public int findBarcode(String barcode) {
        for (int i = 0; i < barcodes.size(); i++) {
            if ((barcodes.get(i)).getBarcode().equals(barcode))
                return i;
        }
        return -1;
    }

    public int findItem(String spareCode) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getSpareCode().equals(spareCode))
                return i;
        }
        return -1;
    }

    public List<SWSpareBarcode> getBarcodes() {
        return barcodes;
    }

    public ArrayList<SpareItem> getItems() {
        return items;
    }

    public boolean removeBarcode(SWSpareBarcode barcode) {
        if (!barcodes.remove(barcode))
            return false;
        int i = findItem(barcode.getSpareCode());
        if (i < 0)
            return false;
        SpareItem spareItem = items.get(i);
        if (spareItem.getQuantity() == barcode.getChangQuantity() && canDelEmpty) {
            items.remove(spareItem);
        } else {
            spareItem.setQuantity(spareItem.getQuantity() - barcode.getChangQuantity());
        }
        return true;
    }

    public boolean removeBarcode(String barcode) {
        int i = findBarcode(barcode);
        if (i < 0)
            return false;
        SWSpareBarcode spareBarcode = barcodes.get(i);
        if (barcodes.remove(spareBarcode)) {
            i = findItem(spareBarcode.getSpareCode());
            if (i >= 0) {
                SpareItem spareItem = items.get(i);
                if (spareItem.getQuantity() == spareBarcode.getChangQuantity() && canDelEmpty) {
                    items.remove(spareItem);
                } else {
                    spareItem.setQuantity(spareItem.getQuantity() - spareBarcode.getChangQuantity());
                }
            }
            return true;
        }
        return false;
    }
}
