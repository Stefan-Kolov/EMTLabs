package com.emt.emtlabs.events;

import com.emt.emtlabs.model.domain.Host;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class HostCreated extends ApplicationEvent {
    public HostCreated(Host source) {
        super(source);
    }


}
