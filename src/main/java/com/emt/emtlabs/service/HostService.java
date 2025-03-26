package com.emt.emtlabs.service;

import com.emt.emtlabs.model.Host;
import com.emt.emtlabs.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> save(HostDto host);

    Optional<Host> update(HostDto host,Long id);

    void deleteById(Long id);
}
