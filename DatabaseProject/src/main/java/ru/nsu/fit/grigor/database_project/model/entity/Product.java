package ru.nsu.fit.grigor.database_project.model.entity;

import org.jetbrains.annotations.NotNull;

public class Product {
  private String productName;//todo: need setter???
  private int price;

  public Product(@NotNull String productName, int price) {
    this.productName = productName;
    this.price = price;
  }

  public String getProductName() {
    return productName;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
