package com.xframework.model;

import com.xframework.item.BatchProductItem;
import com.xframework.util.ChintProductUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BatchProductBarcodeList implements Serializable {
    private List<Integer> barcodeToItems = new ArrayList<Integer>();

    private List<PWProductBarcode> barcodes = new ArrayList<PWProductBarcode>();

    private int batchCount = 0;

    List<BaseProductBody> itemBodys = new ArrayList<BaseProductBody>();

    private ArrayList<BatchProductItem> items = new ArrayList<BatchProductItem>();

    private void addBarocde(List<PWProductBarcode> barcodes, int index) {
        BatchProductItem item = items.get(index);
        int i = 0;
        for (PWProductBarcode productBarcode : barcodes) {
            int j = i;
            if (findBarcode(productBarcode.getBarcode()) < 0) {
                barcodes.add(productBarcode);
                barcodeToItems.add(Integer.valueOf(index));
                j = i + 1;
            }
            i = j;
        }
        item.setQuantity(item.getQuantity() + i);
    }

    public int findItem(BaseProductBody productBody) {
        boolean bool = false;
        for (int i = 0; i < items.size(); i++) {
            if (ChintProductUtil.isDeliveryBody(itemBodys.get(i), productBody)) {
                if (items.get(i).getBatchQuantity() > items.get(i).getQuantity())
                    return i;
                bool = true;
            }
        }
        return bool ? -1 : -2;
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

    public int addBarcode(List<PWProductBarcode> barcodes) {
        if (barcodes.size() > 0) {
            int i = findItem(ChintProductUtil.checkProduct(barcodes.get(0).getProductName()));
            if (i < 0)
                return i;
            addBarocde(barcodes, i);
            return 0;
        }
        return -2;
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

    public List<Integer> getBarcodeToItems() {
        return barcodeToItems;
    }

    public List<PWProductBarcode> getBarcodes() {
        return barcodes;
    }

    public int getBatchCount() {
        return batchCount;
    }

    public ArrayList<BatchProductItem> getItems() {
        return items;
    }

    public void initProductItem(ArrayList<BatchProductItem> items) {
        barcodes.clear();
        items = items;
        for (int i = 0; i < items.size(); i++) {
            batchCount += items.get(i).getBatchQuantity();
            itemBodys.add(ChintProductUtil.checkProduct(items.get(i).getBodyProductName()));
        }
    }

    public boolean removeBarcode(String barcode) {
        int i = findBarcode(barcode);
        if (i < 0)
            return false;
        int j = barcodeToItems.get(i).intValue();
        try {
            barcodes.remove(i);
            barcodeToItems.remove(i);
            BatchProductItem batchProductItem = items.get(j);
            if (batchProductItem.getQuantity() == 1 && batchProductItem.isAdd()) {
                items.remove(batchProductItem);
                return true;
            }
            batchProductItem.setQuantity(batchProductItem.getQuantity() - 1);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
