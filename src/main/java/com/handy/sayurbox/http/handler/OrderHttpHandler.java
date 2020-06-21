package com.handy.sayurbox.http.handler;

import com.handy.sayurbox.http.utils.HttpUtils;
import com.handy.sayurbox.service.ProductService;
import com.handy.sayurbox.service.data.product.OrderProductRequestPayload;
import com.handy.sayurbox.service.data.product.OrderProductResponsePayload;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

import static com.handy.sayurbox.service.data.product.OrderProductResponsePayload.MESSAGE.PRODUCT_NOT_FOUND;
import static com.handy.sayurbox.service.data.product.OrderProductResponsePayload.MESSAGE.STOCK_NOT_ENOUGH;

public class OrderHttpHandler implements HttpHandler {
  private final ProductService _productService;

  public OrderHttpHandler() {
    _productService = new ProductService();
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if (!"GET".equals(exchange.getRequestMethod())) {
      HttpUtils.failedMethodNotAllowed(exchange);
      return;
    }

    ParseUriResult parseUriResult = parseUri(exchange.getRequestURI());
    // validate data
    if (!valid(parseUriResult)) {
      HttpUtils.responseWithCode(exchange, 400, "Request not valid. Please read README.md for the instructions");
      return;
    }

    String productName = parseUriResult.getProductName();
    Integer qty = parseUriResult.getQty();
    String customerName = parseUriResult.getCustomerName();

    // order to service layer
    OrderProductResponsePayload orderProductResponsePayload = _productService.orderProduct(
        new OrderProductRequestPayload()
            .setProductName(productName)
            .setQty(qty)
            .setCustomerName(customerName));

    // return response
    if (orderProductResponsePayload.isSuccess()) {
      String successStr = customerName + " successfully ordered " + qty + " of " + productName;
      HttpUtils.successResponse(exchange, successStr);
    } else {
      if (PRODUCT_NOT_FOUND.equals(orderProductResponsePayload.getMessage())) {
        HttpUtils.responseWithCode(exchange, 404, "Product not found!");
      } else if (STOCK_NOT_ENOUGH.equals(orderProductResponsePayload.getMessage())) {
        HttpUtils.responseWithCode(exchange, 400, "Stock is not enough!");
      }
    }
  }

  private boolean valid(ParseUriResult parseUriResult) {
    if (parseUriResult == null) {
      return false;
    } else if (parseUriResult.getProductName() == null || parseUriResult.getQty() == null || parseUriResult.getCustomerName() == null) {
      return false;
    }
    return true;
  }

  private ParseUriResult parseUri(URI requestURI) {
    String[] split = requestURI.toString().split("/");

    if (split.length != 6) {
      return null;
    }

    String productName = split[3];
    String qtyStr = split[4];
    String customerName = split[5];

    Integer qty;
    try {
      qty = Integer.valueOf(qtyStr);
    } catch (Exception e) {
      return null;
    }

    return new ParseUriResult().setProductName(productName).setQty(qty).setCustomerName(customerName);
  }

  private static class ParseUriResult {
    private String productName;
    private Integer qty;
    private String customerName;

    public String getProductName() {
      return productName;
    }

    public ParseUriResult setProductName(String productName) {
      this.productName = productName;
      return this;
    }

    public Integer getQty() {
      return qty;
    }

    public ParseUriResult setQty(Integer qty) {
      this.qty = qty;
      return this;
    }

    public String getCustomerName() {
      return customerName;
    }

    public ParseUriResult setCustomerName(String customerName) {
      this.customerName = customerName;
      return this;
    }
  }
}
