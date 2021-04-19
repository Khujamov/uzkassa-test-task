package uz.kassa.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kassa.test.dto.LoginRequestDTO;
import uz.kassa.test.dto.RegisterDTO;
import uz.kassa.test.service.LoginOrRegisterService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginOrRegisterService service;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @GetMapping("/activate")
    public ResponseEntity<?> activate(@RequestParam(name = "token") String confirmToken) {
        return ResponseEntity.ok(service.activate(confirmToken));
    }


}
