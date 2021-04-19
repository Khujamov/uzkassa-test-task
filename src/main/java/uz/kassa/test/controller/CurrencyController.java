package uz.kassa.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.kassa.test.service.CurrencyService;

@RestController
@RequestMapping("/v1/api/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService service;


    @GetMapping
    public ResponseEntity<?> getCurrencyList(@RequestParam(name = "code") String code) {
        return ResponseEntity.ok(service.getCurrencyInfoByCode(code));
    }
}
