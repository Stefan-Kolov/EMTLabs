package com.emt.emtlabs.web;

import com.emt.emtlabs.dto.CreateHostDto;
import com.emt.emtlabs.dto.DisplayHostDto;
import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.projections.HostProjection;
import com.emt.emtlabs.model.views.AccomodationsPerHostView;
import com.emt.emtlabs.model.views.HostsPerCountryView;
import com.emt.emtlabs.service.application.HostApplicationService;
import com.emt.emtlabs.service.domain.HostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;
    private final HostService hostService;

    public HostController(HostApplicationService hostApplicationService, HostService hostService) {
        this.hostApplicationService = hostApplicationService;
        this.hostService = hostService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all available hosts")
    @GetMapping
    public List<DisplayHostDto> findAll(){
        return hostApplicationService.getAllHosts();
    }

    @Operation(summary = "Get host by ID", description = "Finds an host by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id){
        DisplayHostDto host = hostApplicationService.getHostById(id);
        return host != null ? ResponseEntity.ok(host) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new host", description = "Creates a new host.")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> addHost(@RequestBody CreateHostDto hostDto) {
       DisplayHostDto host = hostApplicationService.addHost(hostDto);
       return host != null ? ResponseEntity.ok(host) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update an existing host", description = "Updates an host by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto hostDto) {
        DisplayHostDto host = hostApplicationService.editHost(id, hostDto);
        return host != null ? ResponseEntity.ok(host) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Delete an host", description = "Deletes an host by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        DisplayHostDto host = hostApplicationService.getHostById(id);
        if(host == null){
            return ResponseEntity.notFound().build();
        }
        hostApplicationService.deleteHost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-country")
    public List<HostsPerCountryView> getAccommodationsByHost() {
        return hostService.getHostsPerCountry();
    }

    @GetMapping("/names")
    public List<HostProjection> getHostProjections() {
        return hostService.getHostProjections();
    }
}
