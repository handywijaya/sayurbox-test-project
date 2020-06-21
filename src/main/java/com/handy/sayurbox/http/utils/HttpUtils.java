package com.handy.sayurbox.http.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HttpUtils {
  public static void failedMethodNotAllowed(HttpExchange exchange) throws IOException {
    String methodNotAllowedStr = "Method not allowed";

    exchange.sendResponseHeaders(405, methodNotAllowedStr.length());

    OutputStream outputStream = exchange.getResponseBody();
    outputStream.write(methodNotAllowedStr.getBytes());
    outputStream.flush();
    outputStream.close();
  }

  public static void successResponse(HttpExchange exchange, String responseStr) throws IOException {
    exchange.sendResponseHeaders(200, responseStr.length());

    OutputStream outputStream = exchange.getResponseBody();
    outputStream.write(responseStr.getBytes());
    outputStream.flush();
    outputStream.close();
  }

  public static void responseWithCode(HttpExchange exchange, Integer responseCode, String responseStr) throws IOException {
    exchange.sendResponseHeaders(responseCode, responseStr.length());

    OutputStream outputStream = exchange.getResponseBody();
    outputStream.write(responseStr.getBytes());
    outputStream.flush();
    outputStream.close();
  }
}
