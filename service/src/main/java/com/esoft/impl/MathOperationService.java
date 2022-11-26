package com.esoft.impl;

import com.esoft.BaseService;
import com.esoft.model.OperationValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MathOperationService implements BaseService<Double, OperationValue> {

  @Override
  public Double toAdd(OperationValue value) {
    log.info("method toAdd, value params: {}", value.toString());
    return Double.sum(value.getFirst(), value.getSecond());
  }

  @Override
  public Double toDivide(OperationValue value) {
    log.info("method toDivide, value params: {}", value.toString());
    return value.getFirst() / value.getSecond();
  }
}
