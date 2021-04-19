package uz.kassa.test.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.kassa.test.domain.entity.Employee;
import uz.kassa.test.domain.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Employee employee =
        employeeRepository
            .findByUsername(email)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + email + " is wrong"));
    return LoggedUser.create(employee);
  }

  public UserDetails loadUserById(Long id) {
    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User with id: " + id + " not found"));
    return LoggedUser.create(employee);
  }
}
