package org.example.aclab.services;

import org.example.aclab.entities.AppRequest;
import org.example.aclab.repositories.AppRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainServiceTest {
   @Mock
   private AppRequestRepository appRequestRepo;

   @InjectMocks
   private MainService mainService;

   @Test
   void registerRequest_shouldSaveAppRequestWithCorrectDelay() {
      var captor = ArgumentCaptor.forClass(AppRequest.class);
      when(appRequestRepo.save(any(AppRequest.class)))
         .thenAnswer(invocation -> invocation.getArgument(0));

      mainService.registerRequest(Optional.of(0));

      verify(appRequestRepo).save(captor.capture());
      assertThat(captor.getValue().getDelay()).isEqualTo(0);
   }

   @Test
   void registerRequest_shouldReturnTest_whenDelayPresent() {
      String result = mainService.registerRequest(Optional.of(0));
      assertThat(result).isEqualTo("Delay 0 millis");
   }

   @Test
   void registerRequest_shouldReturnTest_whenDelayIsEmpty() {
      String result = mainService.registerRequest(Optional.empty());
      assertThat(result).isEqualTo("No Delay");
   }

   @Test
   void registerRequest_shouldSaveOnce() {
      mainService.registerRequest(Optional.of(0));
      verify(appRequestRepo, times(1)).save(any(AppRequest.class));
   }

   @Test
   void getRequests_shouldReturnAllRequestsFromRepository() {
      var requests = List.of(
         AppRequest.builder().delay(100).build(),
         AppRequest.builder().delay(200).build()
      );
      when(appRequestRepo.findAll()).thenReturn(requests);

      List<AppRequest> result = mainService.getRequests();

      assertThat(result).hasSize(2).isEqualTo(requests);
   }

   @Test
   void getRequests_shouldReturnEmptyList_whenNoRequestsExist() {
      when(appRequestRepo.findAll()).thenReturn(List.of());

      List<AppRequest> result = mainService.getRequests();

      assertThat(result).isEmpty();
   }

   @Test
   void getRequests_shouldDelegateToRepository() {
      mainService.getRequests();

      verify(appRequestRepo, times(1)).findAll();
   }
}