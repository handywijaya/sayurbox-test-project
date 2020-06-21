package com.handy.sayurbox.http.handler;

import com.handy.sayurbox.http.utils.HttpUtils;
import com.handy.sayurbox.service.ProductService;
import com.handy.sayurbox.service.data.product.ResetStockResponsePayload;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ResetStockHttpHandler implements HttpHandler {
  private final ProductService _productService;

  public ResetStockHttpHandler() {
    _productService = new ProductService();
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if (!"GET".equals(exchange.getRequestMethod())) {
      HttpUtils.failedMethodNotAllowed(exchange);
      return;
    }

    ResetStockResponsePayload resetStockResponsePayload = _productService.resetStock();

    if (resetStockResponsePayload.isSuccess()) {
      String responseStr = "Product stock restored!";
      HttpUtils.successResponse(exchange, responseStr);
    } else {
      String responseStr = "Unknown error occurred!";
      HttpUtils.responseWithCode(exchange, 500, responseStr);
    }
  }
}
