package com.emt.emtlabs.listeners;

import com.emt.emtlabs.events.HostCreated;
import com.emt.emtlabs.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreated event) {
        this.hostService.refreshMaterializedView();
    }
}
