package uz.kassa.test.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.kassa.test.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company extends BaseEntity {

  private String name;

  private String address;

  private String zipCode;

  private Boolean status = true;

  public Company(String name, String address, String zipCode) {
    this.name = name;
    this.address = address;
    this.zipCode = zipCode;
  }
}
