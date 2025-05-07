package com.emt.emtlabs.config.init;

import com.emt.emtlabs.dto.authLogsDto;
import com.emt.emtlabs.model.domain.Commodation;
import com.emt.emtlabs.model.domain.Country;
import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.domain.User;
import com.emt.emtlabs.model.enumerations.Category;
import com.emt.emtlabs.model.enumerations.Role;
import com.emt.emtlabs.repository.CommodationRepository;
import com.emt.emtlabs.repository.CountryRepository;
import com.emt.emtlabs.repository.HostRepository;
import com.emt.emtlabs.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final CommodationRepository commodationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, CommodationRepository commodationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.commodationRepository = commodationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final List<authLogsDto> logs = new ArrayList<>();

    public void addLog(authLogsDto log) {
        logs.add(log);
    }

    @PostConstruct
    public void init() {

        countryRepository.save(new Country("USA", "North America"));
        countryRepository.save(new Country("Germany", "Europe"));
        countryRepository.save(new Country("Japan", "Asia"));
        countryRepository.flush();
        List<Country> countries = countryRepository.findAll();

        hostRepository.save(new Host("Stefan", "Kolov", countries.get(0)));
        hostRepository.save(new Host("Jane", "Smith", countries.get(1)));
        hostRepository.save(new Host("Alice", "Johnson", countries.get(2)));
        hostRepository.flush();
        List<Host> hosts = hostRepository.findAll();

        commodationRepository.save(new Commodation("Reservation 1", Category.HOTEL, hosts.get(0), 10));
        commodationRepository.save(new Commodation("Reservation 2", Category.APARTMENT, hosts.get(1), 3));
        commodationRepository.save(new Commodation("Reservation 3", Category.ROOM, hosts.get(2), 5));


        userRepository.save(new User(
                "host",
                passwordEncoder.encode("host"),
                "host",
                "host",
                Role.ROLE_HOST
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

    }
}
