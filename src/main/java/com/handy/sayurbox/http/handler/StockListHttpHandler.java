package com.handy.sayurbox.http.handler;

import com.google.gson.Gson;
import com.handy.sayurbox.http.utils.HttpUtils;
import com.handy.sayurbox.service.ProductService;
import com.handy.sayurbox.service.data.product.GetProductStockListResponsePayload;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class StockListHttpHandler implements HttpHandler {
  private final ProductService _productService;

  public StockListHttpHandler() {
    _productService = new ProductService();
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if (!"GET".equals(exchange.getRequestMethod())) {
      HttpUtils.failedMethodNotAllowed(exchange);
      return;
    }

    // get data from service layer
    GetProductStockListResponsePayload productStockList = _productService.getProductStockList();

    // build response string
    Gson gson = new Gson();
    String responseStr = gson.toJson(productStockList.getProductNameToStockMap());

    // return response
    HttpUtils.successResponse(exchange, responseStr);
  }
}
