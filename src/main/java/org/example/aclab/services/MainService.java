package org.example.aclab.services;

import lombok.extern.slf4j.Slf4j;
import org.example.aclab.entities.AppRequest;
import org.example.aclab.repositories.AppRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MainService {
   private final AppRequestRepository appRequestRepo;

   public MainService(AppRequestRepository appRequestRepo) {
      this.appRequestRepo = appRequestRepo;
   }

   public String registerRequest(Optional<Integer> delay) {
      log.info("Creating an app request with {} delay", delay.isEmpty() ? "null" : delay.get());

      final var appRequest = AppRequest.builder()
         .delay(delay.orElse(null))
         .build();
      appRequestRepo.save(appRequest);

      if (delay.isEmpty()) {
         return "No Delay";
      }

      final long end = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(delay.get());
      while (System.nanoTime() < end) {
      }

      return "Delay %d millis".formatted(delay.get());
   }

   public List<AppRequest> getRequests() {
      log.info("Sending all requests");
      return appRequestRepo.findAll();
   }
}
