
package com.cargopro.controller;

import com.cargopro.dto.LoadRequestDTO;
import com.cargopro.entity.Load;
import com.cargopro.entity.LoadStatus;
import com.cargopro.service.LoadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
public class LoadController {
    private final LoadService loadService;

    @PostMapping
    public Load create(@Valid @RequestBody LoadRequestDTO dto) {
        return loadService.createLoad(dto);
    }

    @GetMapping
    public Page<Load> getLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType,
            @RequestParam(required = false) LoadStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return loadService.getLoads(shipperId, truckType, status, page, size);
    }

    @GetMapping("/{id}")
    public Load getById(@PathVariable UUID id) {
        return loadService.getLoadById(id);
    }

    @PutMapping("/{id}")
    public Load update(@PathVariable UUID id, @Valid @RequestBody LoadRequestDTO dto) {
        return loadService.updateLoad(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        loadService.deleteLoad(id);
    }
}
