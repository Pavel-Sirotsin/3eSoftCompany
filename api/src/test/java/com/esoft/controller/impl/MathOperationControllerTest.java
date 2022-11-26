package com.esoft.controller.impl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.esoft.impl.MathOperationService;
import com.esoft.model.OperationValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MathOperationController.class)
class MathOperationControllerTest {

  private static String uri_add;
  private static String uri_div;
  private static Double first;
  private static Double second;
  private static Double zero;
  private static OperationValue value;
  private static OperationValue wrong_value;
  private static OperationValue empty;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private MathOperationService service;

  @BeforeAll
  static void setUp() {
    uri_add = "/operation/add";
    uri_div = "/operation/div";
    first = 10.0;
    second = 5.0;
    zero = 0.0;
    value = new OperationValue(first, second);
    empty = new OperationValue();
    wrong_value = new OperationValue(first, zero);
  }


  @AfterAll
  static void tearDown() {
    uri_add = null;
    uri_div = null;
    first = null;
    second = null;
    value = null;
    empty = null;
    wrong_value = null;
  }

  @Test
  @DisplayName("Verifying HTTP POST request matching for sum.")
  void whenPostThenMatchToAddAndReturn201() throws Exception {
    //When
    when(service.toAdd(value)).thenReturn(15.0);
    //Then
    mockMvc.perform(post(uri_add)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(value)))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Verifying HTTP GET request matching for division.")
  void whenGetThenMatchToDivideAndReturn200() throws Exception {
    //When
    when(service.toAdd(value)).thenReturn(2.0);
    //Then
    mockMvc.perform(get(uri_div)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(value)))
        .andExpect(status().isOk());

  }

  @Test
  @DisplayName("Should be 400_bad_request when empty fields.")
  void returnBadRequestWhenInvalidParamsForToAdd() throws Exception {
    //Then
    mockMvc.perform(post(uri_add)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(empty)))
        .andExpect(status().isBadRequest());

  }

  @Test
  @DisplayName("Should be 400_bad_request when zero param.")
  void returnBadRequestWhenZeroParamForToDivide() throws Exception {
    //Then
    mockMvc.perform(get(uri_div)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(wrong_value)))
        .andExpect(status().isBadRequest());

  }

  // and so on...
}