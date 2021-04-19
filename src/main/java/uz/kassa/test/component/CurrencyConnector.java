package uz.kassa.test.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import uz.kassa.test.dto.CurrencyInfo;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyConnector {

  private final RestTemplate restTemplate;

  @Value("${apis.currency-info:https://nbu.uz/uz/exchange-rates/json}")
  private String url;

  public List<CurrencyInfo> getCurrencyInfo() {
    try {
      CurrencyInfo[] currencyInfos = restTemplate.getForObject(url, CurrencyInfo[].class);
      return Arrays.asList(currencyInfos);
    } catch (RestClientResponseException e) {
      log.error("API ERROR {}", e.getMessage());
    } catch (Exception e) {
      log.error("ERROR {}", e.getMessage());
    }
    return null;
  }
}
