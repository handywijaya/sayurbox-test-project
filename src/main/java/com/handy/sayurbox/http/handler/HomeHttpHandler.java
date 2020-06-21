package com.handy.sayurbox.http.handler;

import com.handy.sayurbox.http.utils.HttpUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class HomeHttpHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if (!"GET".equals(exchange.getRequestMethod())) {
      HttpUtils.failedMethodNotAllowed(exchange);
      return;
    }

    String responseStr = "Please read README.md for the instructions";
    HttpUtils.successResponse(exchange, responseStr);
  }
}
