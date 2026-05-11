package org.example.aclab.controllers;

import org.example.aclab.entities.AppRequest;
import org.example.aclab.services.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController("/")
public class MainController {
   private final MainService mainService;

   public MainController(MainService mainService) {
      this.mainService = mainService;
   }

   @GetMapping("/")
   public String get(@RequestParam(required = false) Optional<Integer> delay) {
      return mainService.registerRequest(delay);
   }

   @GetMapping("/requests")
   public List<AppRequest> getRequests() {
      return mainService.getRequests();
   }
}
