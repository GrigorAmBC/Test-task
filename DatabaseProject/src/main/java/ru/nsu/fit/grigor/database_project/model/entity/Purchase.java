package ru.nsu.fit.grigor.database_project.model.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Purchase {
  private Customer customer;
  private Product product;
  private Date date;// todo: how do we format??

  public Purchase(@NotNull Customer customer, @NotNull Product product, @NotNull Date date) {
    this.customer = customer;
    this.product = product;
    this.date = date;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Product getProduct() {
    return product;
  }

  public Date getDate() {
    return date;
  }
}
