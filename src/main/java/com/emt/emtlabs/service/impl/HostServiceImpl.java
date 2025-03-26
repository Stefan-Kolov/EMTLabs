package com.emt.emtlabs.service.impl;

import com.emt.emtlabs.model.Host;
import com.emt.emtlabs.model.dto.HostDto;
import com.emt.emtlabs.repository.HostRepository;
import com.emt.emtlabs.service.CountryService;
import com.emt.emtlabs.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(HostDto host) {
        if(host.getName() != null && host.getSurname() != null && countryService.findById(host.getCountry()).isPresent()) {
            return Optional.of(hostRepository.save(new Host(host.getName(),host.getSurname(),countryService.findById(host.getCountry()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(HostDto host, Long id) {
        return hostRepository.findById(id).map(existingHost -> {
            if(host.getName() != null){
                existingHost.setName(host.getName());
            }
            if(host.getSurname() != null){
                existingHost.setSurname(host.getSurname());
            }
            if(host.getCountry() != null && countryService.findById(host.getCountry()).isPresent()){
                existingHost.setCountry(countryService.findById(host.getCountry()).get());
            }

            return hostRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
