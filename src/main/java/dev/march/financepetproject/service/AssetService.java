package dev.march.financepetproject.service;

import dev.march.financepetproject.entity.Asset;
import dev.march.financepetproject.repository.AssetRepository;
import dev.march.financepetproject.websocket.PriceUpdateHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final PriceUpdateHandler priceUpdateHandler;
    private final PriceService priceService;

    public AssetService(AssetRepository assetRepository, PriceUpdateHandler priceUpdateHandler, PriceService priceService) {
        this.assetRepository = assetRepository;
        this.priceUpdateHandler = priceUpdateHandler;
        this.priceService = priceService;
    }

    public Asset findAssetBySymbol(String symbol) {
        return assetRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Asset not found!"));
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Scheduled(fixedRate = 10000)
    public void updatePrices() {
        List<Asset> assets = assetRepository.findAll();
        List<Asset> updatedAssets = new ArrayList<>();

        for (Asset asset : assets) {
            double newPrice = priceService.getCryptoPrice(asset.getSymbol());
            asset.setPrice(newPrice);
            assetRepository.save(asset);
            updatedAssets.add(asset);
        }

        try {
            priceUpdateHandler.sendPriceUpdates(updatedAssets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
