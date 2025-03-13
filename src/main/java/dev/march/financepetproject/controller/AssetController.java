package dev.march.financepetproject.controller;

import dev.march.financepetproject.entity.Asset;
import dev.march.financepetproject.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<Asset> findAssetBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(assetService.findAssetBySymbol(symbol));
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

}
