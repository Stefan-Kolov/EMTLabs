package com.emt.emtlabs.service.domain.impl;

import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.projections.HostProjection;
import com.emt.emtlabs.model.views.HostsPerCountryView;
import com.emt.emtlabs.repository.HostRepository;
import com.emt.emtlabs.repository.HostsPerCountryViewRepository;
import com.emt.emtlabs.service.domain.CountryService;
import com.emt.emtlabs.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostsPerCountryViewRepository hostsPerCountryViewRepository) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
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
        hostRepository.save(author);
        this.refreshMaterializedView();
        return author;
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
        hostRepository.save(hostOld);
        this.refreshMaterializedView();
        return hostOld;
    }

    @Override
    public void deleteHost(Long id) {
        hostRepository.deleteById(id);
        this.refreshMaterializedView();
    }

    @Override
    public void refreshMaterializedView() {
        hostsPerCountryViewRepository.refreshMaterializedViews();
    }

    @Override
    public List<HostsPerCountryView> getHostsPerCountry() {
        return hostsPerCountryViewRepository.findAll();
    }

    @Override
    public List<HostProjection> getHostProjections() {
        return hostRepository.takeNameAndSurnameByProjection();
    }
}
