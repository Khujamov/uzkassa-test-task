package uz.kassa.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kassa.test.domain.entity.Company;
import uz.kassa.test.domain.entity.Employee;
import uz.kassa.test.domain.repository.EmployeeRepository;
import uz.kassa.test.exception.EntityNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
  private final EmployeeRepository repository;

  public boolean existsByEmail(String email) {
    return repository.existsByUsername(email);
  }

  public Employee findByConfirmToken(String token) {
    return repository
        .findByEmailConfirmToken(token)
        .orElseThrow(
            () -> new EntityNotFoundException("Employee nor found with confirm token:" + token));
  }

  public void save(Employee employee) {
    repository.save(employee);
  }

  public Employee getEmployeeByEmail(String email) {
    return repository
        .findByUsername(email)
        .orElseThrow(
            () -> new EntityNotFoundException("Employee nor found with username:" + email));
  }

  public Employee findById(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee with id not found:" + id));
  }

  public List<Employee> getAllEmployeeByCompany(Company company) {
    return repository.findAllByCompany(company);
  }

  public void delete(Long employeeId) {
    repository.deleteById(employeeId);
  }
}
