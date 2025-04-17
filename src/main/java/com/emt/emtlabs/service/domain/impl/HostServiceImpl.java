package com.emt.emtlabs.service.domain.impl;

import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.repository.HostRepository;
import com.emt.emtlabs.service.domain.CountryService;
import com.emt.emtlabs.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }

    @Override
    public Host getHostById(Long id) {
        return hostRepository.findById(id).orElse(null);
    }

    @Override
    public Host addHost(Host author) {
        return hostRepository.save(author);
    }

    @Override
    public Host editHost(Long id, Host host) {
        Host hostOld = hostRepository.findById(id).orElse(null);
        if (hostOld == null) {
            return null;
        }
        hostOld.setName(host.getName());
        hostOld.setSurname(host.getSurname());
        hostOld.setCountry(host.getCountry());
        return hostRepository.save(hostOld);
    }

    @Override
    public void deleteHost(Long id) {
        hostRepository.deleteById(id);
    }
}
