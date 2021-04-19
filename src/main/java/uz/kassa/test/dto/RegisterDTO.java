package uz.kassa.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegisterDTO {

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String username;

    @NotBlank(message = "password should not be empty")
    private String password;

    @NotBlank(message = "Company Name should not be empty")
    private String companyName;

    @NotBlank(message = "Company Address should not be empty")
    private String companyAddress;

    @NotBlank(message = "Company ZipCode should not be empty")
    private String companyZipCode;
}
