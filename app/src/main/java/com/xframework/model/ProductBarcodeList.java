package com.xframework.model;

import com.xframework.item.ProductItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductBarcodeList implements Serializable {
    private List<PWProductBarcode> barcodes = new ArrayList<>();

    private ArrayList<ProductItem> items = new ArrayList<>();

    public void addBarcodes(List<PWProductBarcode> barcodes) {
        for (PWProductBarcode barcode:barcodes) {
            addBarocde(barcode);
        }
    }

    public void addBarocde(PWProductBarcode barcode) {
        barcodes.add(barcode);
        int i = findItem(barcode.getProductCode());
        if (i < 0) {
            ProductItem item = new ProductItem();
            item.setProductId(barcode.getProductId());
            item.setProductCode(barcode.getProductCode());
            item.setProductName(barcode.getProductName());
            item.setQuantity(1);
            items.add(item);
            return;
        }
        ProductItem productItem = items.get(i);
        productItem.setQuantity(productItem.getQuantity() + 1);
    }

    public void clear() {
        barcodes.clear();
        items.clear();
    }

    public int findBarcode(String barcode) {
        for (int i = 0; i < barcodes.size(); i++) {
            if (barcodes.get(i).getBarcode().equals(barcode))
                return i;
        }
        return -1;
    }

    public int findItem(String productName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductCode().equals(productName))
                return i;
        }
        return -1;
    }

    public List<PWProductBarcode> getBarcodes() {
        return barcodes;
    }

    public ArrayList<ProductItem> getItems() {
        return items;
    }

    public boolean removeBarcode(PWProductBarcode barcode) {
        if (!barcodes.remove(barcode))
            return false;
        int i = findItem(barcode.getProductCode());
        if (i < 0)
            return false;
        ProductItem item = items.get(i);
        if (item.getQuantity() == 1) {
            items.remove(item);
            return true;
        }
        item.setQuantity(item.getQuantity() - 1);
        return true;
    }

    public boolean removeBarcode(String barcode) {
        int i = findBarcode(barcode);
        if (i < 0)
            return false;
        PWProductBarcode productBarcode = barcodes.get(i);
        if (barcodes.remove(productBarcode)) {
            i = findItem(productBarcode.getProductCode());
            if (i >= 0) {
                ProductItem item = items.get(i);
                if (item.getQuantity() == 1) {
                    items.remove(item);
                    return true;
                }
                item.setQuantity(item.getQuantity() - 1);
            }
            return true;
        }
        return false;
    }
}
