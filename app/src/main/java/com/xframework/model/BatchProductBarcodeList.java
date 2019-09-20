package com.xframework.model;

import com.xframework.item.BatchProductItem;
import com.xframework.item.ProductItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BatchProductBarcodeList implements Serializable {

    private int batchCount = 0;
    private List<PWProductBarcode> barcodes = new ArrayList<>();
    private ArrayList<BatchProductItem> items = new ArrayList<>();

    public List<PWProductBarcode> getBarcodes() {
        return barcodes;
    }

    public ArrayList<BatchProductItem> getItems() {
        return items;
    }

    public int getBatchCount() {
        return batchCount;
    }

    public void initProductItem(ArrayList<BatchProductItem> items) {
        barcodes.clear();
        this.items = items;
        for(BatchProductItem item : items){
            batchCount += item.getBatchQuantity();
        }
    }

    public void addBarcodes(List<PWProductBarcode> barcodes) {
        for (PWProductBarcode barcode : barcodes) {
            addBarocde(barcode);
        }
    }

    /**
     * @param barcode 新增条码
     */
    public void addBarocde(PWProductBarcode barcode) {
        barcodes.add(barcode);
        int position = findItem(barcode.getProductCode());
        if (position < 0) {
            BatchProductItem item = new BatchProductItem();
            item.setProductCode(barcode.getProductCode());
            item.setProductName(barcode.getProductName());
            item.setQuantity(1);
            items.add(item);
        } else {
            BatchProductItem item = items.get(position);
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    /**
     * @param barcode 移除条码
     */
    public boolean removeBarcode(PWProductBarcode barcode) {
        if (!barcodes.remove(barcode)) {
            return false;
        } else {
            int position = findItem(barcode.getProductCode());
            if (position < 0) {
                return false;
            } else {
                BatchProductItem item = items.get(position);
                if (item.getQuantity() == 1 && item.isAdd()) {
                    items.remove(item);
                } else {
                    item.setQuantity(item.getQuantity() - 1);
                }
            }
        }
        return true;
    }

    /**
     * @param barcode 移除条码
     */
    public boolean removeBarcode(String barcode) {
        int position = findBarcode(barcode);
        if (position < 0) {
            return false;
        } else {
            PWProductBarcode product_barcode = barcodes.get(position);
            if (barcodes.remove(product_barcode)) {
                position = findItem(product_barcode.getProductCode());
                if (position >= 0) {
                    BatchProductItem item = items.get(position);
                    if (item.getQuantity() == 1 && item.isAdd()) {
                        items.remove(item);
                    } else {
                        item.setQuantity(item.getQuantity() - 1);
                    }
                }
            } else {
                return false;
            }
            return true;
        }
    }

    public int findItem(String product_code) {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getBodyProductCode() != null) {
                if (items.get(i).getBodyProductCode().equals(product_code)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int findBarcode(String barcode) {
        for (int i = 0; i < barcodes.size(); i++) {
            if (barcodes.get(i).getBarcode().equals(barcode)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        barcodes.clear();
        items.clear();
    }
}
