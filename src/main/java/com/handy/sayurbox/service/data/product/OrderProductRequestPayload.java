package com.handy.sayurbox.service.data.product;

public class OrderProductRequestPayload {
  private String productName;
  private Integer qty;
  private String customerName;

  public String getProductName() {
    return productName;
  }

  public OrderProductRequestPayload setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public Integer getQty() {
    return qty;
  }

  public OrderProductRequestPayload setQty(Integer qty) {
    this.qty = qty;
    return this;
  }

  public String getCustomerName() {
    return customerName;
  }

  public OrderProductRequestPayload setCustomerName(String customerName) {
    this.customerName = customerName;
    return this;
  }

  @Override
  public String toString() {
    return "OrderProductRequestPayload{" +
        "productName='" + productName + '\'' +
        ", qty=" + qty +
        ", customer='" + customerName + '\'' +
        '}';
  }
}
