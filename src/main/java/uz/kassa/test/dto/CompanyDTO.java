package uz.kassa.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CompanyDTO {
    @NotBlank(message = "Company Name should not be empty")
    private String name;

    @NotBlank(message = "Company Address should not be empty")
    private String address;

    @NotBlank(message = "Company ZipCode should not be empty")
    private String zipCode;
}
