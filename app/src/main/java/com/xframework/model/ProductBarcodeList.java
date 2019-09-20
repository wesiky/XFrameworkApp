package com.xframework.model;

import com.xframework.item.ProductItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductBarcodeList implements Serializable {
    private List<PWProductBarcode> barcodes = new ArrayList<>();
    private ArrayList<ProductItem> items = new ArrayList<>();

    public List<PWProductBarcode> getBarcodes() {
        return barcodes;
    }

    public ArrayList<ProductItem> getItems() {
        return items;
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
            ProductItem item = new ProductItem();
            item.setProductId(barcode.getProductId());
            item.setProductCode(barcode.getProductCode());
            item.setProductName(barcode.getProductName());
            item.setQuantity(1);
            items.add(item);
        } else {
            ProductItem item = items.get(position);
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
                ProductItem item = items.get(position);
                if (item.getQuantity() == 1) {
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
                    ProductItem item = items.get(position);
                    if (item.getQuantity() == 1) {
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
            if (items.get(i).getProductCode().equals(product_code)) {
                return i;
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
