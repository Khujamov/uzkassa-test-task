package uz.kassa.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.kassa.test.domain.entity.Company;
import uz.kassa.test.domain.entity.Employee;
import uz.kassa.test.dto.ApiResponse;
import uz.kassa.test.dto.LoginRequestDTO;
import uz.kassa.test.dto.RegisterDTO;
import uz.kassa.test.exception.LoginException;
import uz.kassa.test.service.security.auth.AuthService;
import uz.kassa.test.util.Utils;

@Service
@RequiredArgsConstructor
public class LoginOrRegisterService {
    private final AuthService authService;
    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse register(RegisterDTO dto) {
        ApiResponse apiResponse;
        String email = dto.getUsername();
        String password = dto.getPassword();
        String companyName = dto.getCompanyName();
        String companyAddress = dto.getCompanyAddress();
        String companyZipCode = dto.getCompanyZipCode();
        if (!employeeService.existsByEmail(email)) {
            String confirmToken = Utils.nextAlphaNumeric();
            String encodedPassword = passwordEncoder.encode(password);
            Company company = companyService.save(new Company(companyName, companyAddress, companyZipCode));
            Employee employee = new Employee(email, encodedPassword, confirmToken);
            employee.setCompany(company);
            employeeService.save(employee);
            mailService.sendEmail(email, confirmToken);
            apiResponse = new ApiResponse(1, "Successfully Sent");
        } else {
            apiResponse = new ApiResponse(0, "Username: " + email + " already registered!");
        }
        return apiResponse;
    }

    public ApiResponse login(LoginRequestDTO dto) {
        String email = dto.getUsername();
        String password = dto.getPassword();
        Employee employee = employeeService.getEmployeeByEmail(email);
        if (passwordEncoder.matches(password, employee.getPassword())) {
            if (employee.isConfirmed()) {
                String token = authService.authUser(email, password);
                return new ApiResponse(1, authService.prepareJwtResponse(token));
            } else throw new LoginException("Employee not activated");
        } else throw new LoginException("Password is wrong");
    }

    public ApiResponse activate(String confirmToken) {
        Employee employee = employeeService.findByConfirmToken(confirmToken);
        employee.setConfirmed(true);
        employeeService.save(employee);
        return new ApiResponse(1, "Successfully activated");
    }
}
