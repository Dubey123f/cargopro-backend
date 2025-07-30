
package com.cargopro.service;

import com.cargopro.dto.LoadRequestDTO;
import com.cargopro.entity.Load;
import com.cargopro.entity.LoadStatus;
import com.cargopro.repository.LoadRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoadService {
    private final LoadRepository loadRepository;

    public Load createLoad(LoadRequestDTO dto) {
        Load load = new Load();
        load.setShipperId(dto.getShipperId());
        load.setFacility(dto.getFacility());
        load.setProductType(dto.getProductType());
        load.setTruckType(dto.getTruckType());
        load.setNoOfTrucks(dto.getNoOfTrucks());
        load.setWeight(dto.getWeight());
        load.setComment(dto.getComment());
        load.setStatus(LoadStatus.POSTED);
        return loadRepository.save(load);
    }

    public Page<Load> getLoads(String shipperId, String truckType, LoadStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues();
        Load probe = new Load();
        probe.setShipperId(shipperId);
        probe.setTruckType(truckType);
        probe.setStatus(status);
        Example<Load> example = Example.of(probe, matcher);
        return loadRepository.findAll(example, pageable);
    }

    public Load getLoadById(UUID id) {
        return loadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Load not found with ID: " + id));
    }

    public Load updateLoad(UUID id, LoadRequestDTO dto) {
        Load load = getLoadById(id);
        load.setShipperId(dto.getShipperId());
        load.setFacility(dto.getFacility());
        load.setProductType(dto.getProductType());
        load.setTruckType(dto.getTruckType());
        load.setNoOfTrucks(dto.getNoOfTrucks());
        load.setWeight(dto.getWeight());
        load.setComment(dto.getComment());
        return loadRepository.save(load);
    }

    public void deleteLoad(UUID id) {
        loadRepository.deleteById(id);
    }

    public void updateLoadStatus(UUID id, LoadStatus status) {
        Load load = getLoadById(id);
        load.setStatus(status);
        loadRepository.save(load);
    }
}
