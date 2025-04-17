package com.emt.emtlabs.config;

import com.emt.emtlabs.model.domain.Country;
import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.model.domain.User;
import com.emt.emtlabs.model.enumerations.Category;
import com.emt.emtlabs.model.enumerations.Role;
import com.emt.emtlabs.repository.CountryRepository;
import com.emt.emtlabs.repository.HostRepository;
import com.emt.emtlabs.repository.ReservationRepository;
import com.emt.emtlabs.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, ReservationRepository reservationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        reservationRepository.save(new Reservation("Reservation 1", Category.HOTEL, hosts.get(0), 10));
        reservationRepository.save(new Reservation("Reservation 2", Category.APARTMENT, hosts.get(1), 3));
        reservationRepository.save(new Reservation("Reservation 3", Category.ROOM, hosts.get(2), 5));


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
