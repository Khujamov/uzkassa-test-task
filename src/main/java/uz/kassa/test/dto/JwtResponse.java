package uz.kassa.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Author: Khumoyun Khujamov Date: 11/13/20 Time: 4:08 PM */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
  private String type;
  private String token;

  public JwtResponse(String token) {
    this.type = "Bearer ";
    this.token = token;
  }
}
