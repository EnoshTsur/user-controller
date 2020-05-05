package com.enosh.users.bootstrap;

import com.enosh.users.entities.User;
import com.enosh.users.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository repository;

    public DataLoader(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        Stream.of(
                new User("Itsik", "Gabay", "its@ik.com"),
                new User("Efraim", "Shushan", "ef@shush.com"),
                new User("Liat", "Heirut", "li@at.co.il"),
                new User("Meitar", "Even", "mei@even.com")
        )
                .map(repository::save)
                .forEach(System.out::println);

    }
}
