package com.example.jpastreamer;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JpaStreamerInitializer implements CommandLineRunner {

    @Autowired
    private JPAStreamer jpaStreamer;

    public static void main(String[] args) {
      SpringApplication.run(JpaStreamerInitializer.class, args);
    }

    @Override
    public void run(String... args) {
        withFiler();
        withPagination();
        notNull();
        withProjection();
        withBeetwen();
    }

    private void withBeetwen() {
        jpaStreamer.stream(User.class)
                .filter(User$.id.between(1L, 2L))
                .map(User$.email)
                .forEach(System.out::println);
    }

    private void withProjection() {
        jpaStreamer.stream(User.class)
                .map(Projection.select(User$.name, User$.email))
                .forEach(System.out::println);
    }


    private void notNull() {
        jpaStreamer.stream(User.class)
                .filter(User$.name.isNotNull())
                .map(User$.email)
                .forEach(System.out::println);
    }

    private void withPagination() {
        jpaStreamer.stream(User.class)
                .map(User::getEmail)
                .skip(1)
                .limit(1)
                .forEach(System.out::println);
    }

    private void withFiler() {
        jpaStreamer.stream(User.class)
                .filter(User.NAME)
                .map(User::getEmail)
                .forEach(System.out::println);
    }
}
