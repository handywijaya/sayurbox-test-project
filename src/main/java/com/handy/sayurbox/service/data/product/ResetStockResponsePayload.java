package com.handy.sayurbox.service.data.product;

public class ResetStockResponsePayload {
  private boolean success;

  public boolean isSuccess() {
    return success;
  }

  public ResetStockResponsePayload setSuccess(boolean success) {
    this.success = success;
    return this;
  }
}
