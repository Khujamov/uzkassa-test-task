package uz.kassa.test.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import uz.kassa.test.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseEntity {

  private String username;

  private String password;

  private String emailConfirmToken;

  private Boolean confirmed;

  @ManyToOne(fetch = FetchType.LAZY)
  public Company company;

  public Employee(String username, String password, String emailConfirmToken) {
    this(username, password);
    this.emailConfirmToken = emailConfirmToken;
    this.confirmed = false;
  }

  public Employee(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Boolean isConfirmed() {
    return this.confirmed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Employee employee = (Employee) o;
    return Objects.equals(username, employee.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), username);
  }
}
