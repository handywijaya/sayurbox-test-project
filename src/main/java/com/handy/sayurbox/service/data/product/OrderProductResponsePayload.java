package com.handy.sayurbox.service.data.product;

public class OrderProductResponsePayload {
  private boolean success;
  private MESSAGE message;

  public boolean isSuccess() {
    return success;
  }

  public OrderProductResponsePayload setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public MESSAGE getMessage() {
    return message;
  }

  public OrderProductResponsePayload setMessage(MESSAGE message) {
    this.message = message;
    return this;
  }

  public enum MESSAGE {
    SUCCESS,
    STOCK_NOT_ENOUGH,
    PRODUCT_NOT_FOUND
  }
}
