package org.example.aclab.services;

import lombok.extern.slf4j.Slf4j;
import org.example.aclab.entities.AppRequest;
import org.example.aclab.repositories.AppRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MainService {
   private final AppRequestRepository appRequestRepo;

   public MainService(AppRequestRepository appRequestRepo) {
      this.appRequestRepo = appRequestRepo;
   }

   public String registerRequest(Optional<Integer> delay) {
      log.info("Creating an app request");

      var appRequest = AppRequest.builder()
         .delay(delay.get())
         .build();
      appRequestRepo.save(appRequest);

      try {
         if (delay.isPresent()) {
            Thread.sleep(delay.get());
         }
      } catch (InterruptedException _) {
         return "Test (interrupted)";
      }

      return "Test";
   }

   public List<AppRequest> getRequests() {
      log.info("Sending all requests");
      return appRequestRepo.findAll();
   }
}
