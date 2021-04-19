package uz.kassa.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.kassa.test.domain.entity.Company;
import uz.kassa.test.domain.entity.Employee;
import uz.kassa.test.domain.repository.CompanyRepository;
import uz.kassa.test.dto.ApiResponse;
import uz.kassa.test.dto.CompanyDTO;
import uz.kassa.test.dto.EmployeeDTO;
import uz.kassa.test.exception.EntityNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
  private final CompanyRepository repository;
  private final EmployeeService employeeService;
  private final PasswordEncoder passwordEncoder;

  public Company save(Company company) {
    return repository.save(company);
  }

  public Company findCompanyById(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Company not found with id:" + id));
  }

  public ApiResponse editCompany(Long id, CompanyDTO dto) {
    Company company = findCompanyById(id);
    company.setAddress(dto.getAddress());
    company.setName(dto.getName());
    company.setZipCode(dto.getZipCode());
    repository.save(company);
    return new ApiResponse(1, "Successfully Updated");
  }

  public ApiResponse blockCompany(Long id) {
    Company company = findCompanyById(id);
    company.setStatus(false);
    repository.save(company);
    return new ApiResponse(1, "Successfully Updated");
  }

  public List<Employee> getAllEmployee(Long companyId) {
    Company company = findCompanyById(companyId);
    return employeeService.getAllEmployeeByCompany(company);
  }

  public Employee getOneEmployee(Long companyId, Long employeeId) {
    return getAllEmployee(companyId).stream()
        .filter(employee -> employee.getId().equals(employeeId))
        .findFirst()
        .orElseThrow(() -> new EntityNotFoundException("Employee with id not found:" + employeeId));
  }

  public ApiResponse createEmployee(Long companyId, EmployeeDTO dto) {
    Company company = findCompanyById(companyId);
    Employee employee = new Employee(dto.getUsername(), passwordEncoder.encode(dto.getPassword()));
    employee.setCompany(company);
    employeeService.save(employee);
    return new ApiResponse(1, "Successfully created");
  }

  public ApiResponse editEmployee(Long companyId, Long employeeId, EmployeeDTO dto) {
    Employee one = getOneEmployee(companyId, employeeId);
    one.setUsername(dto.getUsername());
    one.setPassword(passwordEncoder.encode(dto.getPassword()));
    employeeService.save(one);
    return new ApiResponse(1, "Successfully updated");
  }

  public void deleteEmployee(Long companyId, Long employeeId) {
    employeeService.delete(employeeId);
  }
}
