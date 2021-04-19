package uz.kassa.test.domain.repository;

import org.springframework.stereotype.Repository;
import uz.kassa.test.domain.entity.Company;
import uz.kassa.test.domain.entity.Employee;
import uz.kassa.test.domain.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {
  Optional<Employee> findByCompany_Id(Long id);
  Optional<Employee> findByUsername(String username);
  boolean existsByUsername(String email);
  Optional<Employee> findByEmailConfirmToken(String token);
  List<Employee> findAllByCompany(Company company);
}
