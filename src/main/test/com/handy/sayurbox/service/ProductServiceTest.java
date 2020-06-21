package com.handy.sayurbox.service;

import com.handy.sayurbox.service.data.product.OrderProductRequestPayload;
import com.handy.sayurbox.service.data.product.OrderProductResponsePayload;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductServiceTest {
  private ProductService _productService;

  @BeforeMethod
  public void setUp() {
    _productService = new ProductService();
  }

  @Test
  public void getProductStockList_normal_normal() {
    Integer defaultAppleStock = 5;
    Integer defaultPapayaStock = 1;
    Integer defaultMangoStock = 4;

    Map<String, Integer> resultMap = _productService.getProductStockList().getProductNameToStockMap();
    Assert.assertEquals(resultMap.get("APPLE"), defaultAppleStock);
    Assert.assertEquals(resultMap.get("PAPAYA"), defaultPapayaStock);
    Assert.assertEquals(resultMap.get("MANGO"), defaultMangoStock);
  }

  @Test
  public void resetStock_normal_normal() {
    Integer defaultAppleStock = 5;
    Integer defaultPapayaStock = 1;
    Integer defaultMangoStock = 4;

    _productService.resetStock();

    Map<String, Integer> resultMap = _productService.getProductStockList().getProductNameToStockMap();
    Assert.assertEquals(resultMap.get("APPLE"), defaultAppleStock);
    Assert.assertEquals(resultMap.get("PAPAYA"), defaultPapayaStock);
    Assert.assertEquals(resultMap.get("MANGO"), defaultMangoStock);
  }

  @Test
  public void orderProduct_productNotFound_failed() {
    OrderProductResponsePayload result = _productService.orderProduct(
        new OrderProductRequestPayload()
            .setProductName("apel")
            .setQty(2)
            .setCustomerName("test-customer")
    );

    Assert.assertFalse(result.isSuccess());
    Assert.assertEquals(result.getMessage(), OrderProductResponsePayload.MESSAGE.PRODUCT_NOT_FOUND);
  }

  @Test
  public void orderProduct_stockNotEnough_failed() {
    OrderProductResponsePayload result = _productService.orderProduct(
        new OrderProductRequestPayload()
            .setProductName("APPLE")
            .setQty(20)
            .setCustomerName("test-customer")
    );

    Assert.assertFalse(result.isSuccess());
    Assert.assertEquals(result.getMessage(), OrderProductResponsePayload.MESSAGE.STOCK_NOT_ENOUGH);
  }

  @Test
  public void orderProduct_lowercaseProductName_success() {
    OrderProductResponsePayload result = _productService.orderProduct(
        new OrderProductRequestPayload()
            .setProductName("apple")
            .setQty(2)
            .setCustomerName("test-customer")
    );

    Assert.assertTrue(result.isSuccess());
    Assert.assertEquals(result.getMessage(), OrderProductResponsePayload.MESSAGE.SUCCESS);
  }

  @Test
  public void orderProduct_uppercaseProductName_success() {
    OrderProductResponsePayload result = _productService.orderProduct(
        new OrderProductRequestPayload()
            .setProductName("APPLE")
            .setQty(2)
            .setCustomerName("test-customer")
    );

    Assert.assertTrue(result.isSuccess());
    Assert.assertEquals(result.getMessage(), OrderProductResponsePayload.MESSAGE.SUCCESS);
  }

  @Test
  public void orderProduct_mixcaseProductName_success() {
    OrderProductResponsePayload result = _productService.orderProduct(
        new OrderProductRequestPayload()
            .setProductName("AppLE")
            .setQty(2)
            .setCustomerName("test-customer")
    );

    Assert.assertTrue(result.isSuccess());
    Assert.assertEquals(result.getMessage(), OrderProductResponsePayload.MESSAGE.SUCCESS);
  }
}
