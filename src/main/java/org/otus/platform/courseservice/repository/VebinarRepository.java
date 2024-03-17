package org.otus.platform.courseservice.repository;

import org.otus.platform.courseservice.model.vebinar.Vebinar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VebinarRepository extends JpaRepository<Vebinar, UUID> {
    List<Vebinar> findAllByCourseId(UUID courseId);
    Optional<Vebinar> findByIdAndDeletedHashIsNull(UUID id);
}
