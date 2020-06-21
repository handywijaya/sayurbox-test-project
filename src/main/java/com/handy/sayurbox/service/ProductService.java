package com.handy.sayurbox.service;

import com.handy.sayurbox.service.data.product.GetProductStockListResponsePayload;
import com.handy.sayurbox.service.data.product.OrderProductRequestPayload;
import com.handy.sayurbox.service.data.product.OrderProductResponsePayload;
import com.handy.sayurbox.service.data.product.ResetStockResponsePayload;

import java.util.HashMap;
import java.util.Map;

public class ProductService {
  private static Map<String, Integer> productNameToStockMap;

  public ProductService() {
    resetStocks();
  }

  private void resetStocks() {
    productNameToStockMap = new HashMap<>();

    productNameToStockMap.put("APPLE", 5);
    productNameToStockMap.put("PAPAYA", 1);
    productNameToStockMap.put("MANGO", 4);
  }

  public GetProductStockListResponsePayload getProductStockList() {
    return new GetProductStockListResponsePayload().setProductNameToStockMap(productNameToStockMap);
  }

  public ResetStockResponsePayload resetStock() {
    resetStocks();

    return new ResetStockResponsePayload().setSuccess(true);
  }

  public OrderProductResponsePayload orderProduct(OrderProductRequestPayload request) {
    boolean success = true;
    OrderProductResponsePayload.MESSAGE message = OrderProductResponsePayload.MESSAGE.SUCCESS;

    String productName = request.getProductName().toUpperCase();

    // since we only have 1 server, we can use synchronized
    // we can use locking mechanism with Redis or other 3rd party services if we have a distributed system
    synchronized (this) {
      Integer qty = productNameToStockMap.get(productName);

      // product not found
      if (qty == null) {
        success = false;
        message = OrderProductResponsePayload.MESSAGE.PRODUCT_NOT_FOUND;
      } else if (request.getQty() > qty) {
        success = false;
        message = OrderProductResponsePayload.MESSAGE.STOCK_NOT_ENOUGH;
      } else {
        productNameToStockMap.put(productName, qty - request.getQty());
      }
    }

    return new OrderProductResponsePayload().setSuccess(success).setMessage(message);
  }
}
