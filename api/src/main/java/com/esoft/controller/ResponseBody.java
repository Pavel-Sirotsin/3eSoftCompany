package com.esoft.controller;

public class ResponseBody {
  double value;

  public ResponseBody() {
  }

  public ResponseBody(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
