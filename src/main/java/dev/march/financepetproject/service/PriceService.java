package dev.march.financepetproject.service;

import dev.march.financepetproject.repository.AssetRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import dev.march.financepetproject.entity.Asset;

import java.util.Map;

@Service
public class PriceService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final AssetRepository assetRepository;
    private static final String BINANCE_URL = "https://api.binance.com/api/v3/ticker/price?symbol=";
    private static final String YAHOO_FINANCE_URL = "https://finance.yahoo.com/quote/%s?p=%s";

    public PriceService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    // get data for crypto
    public double getCryptoPrice(String symbol) {
        String url = BINANCE_URL + symbol.toUpperCase() + "USDT";

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            return Double.parseDouble(response.get("price").toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch crypto price for " + symbol);
        }
    }

    private double getPreviousPrice(String symbol) {
        return assetRepository.findBySymbol(symbol)
                .map(Asset::getPrice)
                .orElseThrow(() -> new RuntimeException("No previous price found for " + symbol));
    }
}
