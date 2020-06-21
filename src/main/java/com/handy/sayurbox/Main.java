package com.handy.sayurbox;

import com.handy.sayurbox.http.handler.HomeHttpHandler;
import com.handy.sayurbox.http.handler.OrderHttpHandler;
import com.handy.sayurbox.http.handler.ResetStockHttpHandler;
import com.handy.sayurbox.http.handler.StockListHttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
  public static int SERVER_THREAD_SIZE = 10;

  public static void main(String[] args) throws IOException {
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(SERVER_THREAD_SIZE);

    HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8000), 1);

    server.createContext("/", new HomeHttpHandler());
    server.createContext("/product/stock/list", new StockListHttpHandler());
    server.createContext("/product/stock/reset", new ResetStockHttpHandler());
    server.createContext("/product/order", new OrderHttpHandler());

    server.setExecutor(threadPoolExecutor);
    server.start();

    System.out.println("Server started on port 8000");
  }
}
