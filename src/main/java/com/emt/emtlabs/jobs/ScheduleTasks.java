package com.emt.emtlabs.jobs;

import com.emt.emtlabs.service.domain.CommodationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasks {
    private final CommodationService accommodationCountService;

    public ScheduleTasks(CommodationService accommodationCountService) {
        this.accommodationCountService = accommodationCountService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
       this.accommodationCountService.refreshMaterializedView();
    }

}
