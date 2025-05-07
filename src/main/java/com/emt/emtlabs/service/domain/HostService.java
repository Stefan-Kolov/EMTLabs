package com.emt.emtlabs.service.domain;

import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.projections.HostProjection;
import com.emt.emtlabs.model.views.AccomodationsPerHostView;
import com.emt.emtlabs.model.views.HostsPerCountryView;

import java.util.List;

public interface HostService {
    List<Host> getAllHosts();

    Host getHostById(Long id);

    Host addHost(Host author);

    Host editHost(Long id, Host host);

    void deleteHost(Long id);

    void refreshMaterializedView();

    List<HostsPerCountryView> getHostsPerCountry();
    List<HostProjection> getHostProjections();
}
