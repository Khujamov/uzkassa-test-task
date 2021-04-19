package uz.kassa.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kassa.test.dto.CompanyDTO;
import uz.kassa.test.dto.EmployeeDTO;
import uz.kassa.test.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/companies")
@RequiredArgsConstructor
public class CompanyController {
  private final CompanyService service;

  @PutMapping("/{id}")
  public ResponseEntity<?> editCompany(@PathVariable(name = "id") Long id, @Valid @RequestBody CompanyDTO dto) {
    return ResponseEntity.ok(service.editCompany(id, dto));
  }

  @PutMapping("/block/{id}")
  public ResponseEntity<?> blockCompany(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(service.blockCompany(id));
  }


  @GetMapping("/{id}/employees")
  public ResponseEntity<?> getAllEmployeeByCompanyId(@PathVariable(name = "id") Long companyId) {
    return ResponseEntity.ok(service.getAllEmployee(companyId));
  }

  @GetMapping("/{id}/employees/{employeeId}")
  public ResponseEntity<?> getEmployeeByCompanyId(
          @PathVariable(name = "id") Long companyId,
          @PathVariable(name = "employeeId") Long employeeId) {
    return ResponseEntity.ok(service.getOneEmployee(companyId, employeeId));
  }

  @PostMapping("/{id}/employees")
  public ResponseEntity<?> createEmployee(
          @PathVariable(name = "id") Long companyId, @Valid @RequestBody EmployeeDTO dto) {
    return ResponseEntity.ok(service.createEmployee(companyId, dto));
  }

  @PutMapping("/{id}/employees/{employeeId}")
  public ResponseEntity<?> editEmployee(
          @PathVariable(name = "id") Long companyId,
          @PathVariable(name = "employeeId") Long employeeId,
          @Valid @RequestBody EmployeeDTO dto) {
    return ResponseEntity.ok(service.editEmployee(companyId, employeeId, dto));
  }

  @DeleteMapping("/{id}/employees/{employeeId}")
  public ResponseEntity<?> deleteEmployee(
          @PathVariable(name = "id") Long companyId,
          @PathVariable(name = "employeeId") Long employeeId) {
    service.deleteEmployee(companyId, employeeId);
    return ResponseEntity.noContent().build();
  }
}
