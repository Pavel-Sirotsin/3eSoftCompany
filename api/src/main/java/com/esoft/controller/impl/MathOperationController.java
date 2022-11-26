package com.esoft.controller.impl;

import com.esoft.controller.BaseController;
import com.esoft.controller.ResponseBody;
import com.esoft.impl.MathOperationService;
import com.esoft.model.OperationValue;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/operation", produces = "application/json")
@Validated
public class MathOperationController implements BaseController {

  private final MathOperationService service;

  @Autowired
  public MathOperationController(MathOperationService service) {
    this.service = service;
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseBody toAdd(@NotNull @Valid @RequestBody OperationValue value) {
    return new ResponseBody(service.toAdd(value));
  }

  @GetMapping("/div")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBody toDivide(@NotNull @Valid @RequestBody OperationValue value) {
    return new ResponseBody(service.toDivide(value));
  }
}
