package com.handy.sayurbox.service.data.product;

import java.util.HashMap;
import java.util.Map;

public class GetProductStockListResponsePayload {
  private Map<String, Integer> productNameToStockMap = new HashMap<>();

  public Map<String, Integer> getProductNameToStockMap() {
    return productNameToStockMap;
  }

  public GetProductStockListResponsePayload setProductNameToStockMap(Map<String, Integer> productNameToStockMap) {
    this.productNameToStockMap = productNameToStockMap;
    return this;
  }
}
