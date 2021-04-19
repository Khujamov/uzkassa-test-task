package uz.kassa.test.service.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.kassa.test.domain.entity.Employee;

import java.util.Collection;

/** Author: Khumoyun Khujamov Date: 11/13/20 Time: 2:34 PM */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUser implements UserDetails {

  private Long id;

  private String username;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public static LoggedUser create(Employee employee) {
    return new LoggedUser(employee.getId(), employee.getUsername(), employee.getPassword());
  }

  public LoggedUser(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
