package com.domain.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wellcome")
public class WellcomControllers {

  @GetMapping // saat fungsi 
  public String wellcome(){
    return "Wellcome to Spring Boot Rest API";
  }
}
