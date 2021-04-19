package uz.kassa.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kassa.test.component.CurrencyConnector;
import uz.kassa.test.dto.CurrencyInfo;
import uz.kassa.test.exception.CurrencyInfoNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyConnector connector;

    public List<CurrencyInfo> getCurrencyInfo() {
        return connector.getCurrencyInfo();
    }

    public CurrencyInfo getCurrencyInfoByCode(String code) {
        return getCurrencyInfo().stream()
                .filter(currencyInfo -> currencyInfo.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new CurrencyInfoNotFoundException("Currency not found by code: " + code));
    }
}
