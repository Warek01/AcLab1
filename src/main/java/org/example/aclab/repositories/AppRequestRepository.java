package org.example.aclab.repositories;

import org.example.aclab.entities.AppRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppRequestRepository extends JpaRepository<AppRequest, Long> {
}
