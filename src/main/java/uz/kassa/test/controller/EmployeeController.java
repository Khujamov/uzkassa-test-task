package uz.kassa.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.kassa.test.service.EmployeeService;

@RestController
@RequestMapping("/v1/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService service;
}
