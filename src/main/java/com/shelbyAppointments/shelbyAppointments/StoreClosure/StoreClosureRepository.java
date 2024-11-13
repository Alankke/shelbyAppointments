package com.shelbyAppointments.shelbyAppointments.StoreClosure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StoreClosureRepository extends JpaRepository<StoreClosure, String> {
    List<StoreClosure> findByDateGreaterThanEqual(LocalDate date);
    List<StoreClosure> findByDate(LocalDate date);
    boolean existsByDate(LocalDate date);
}
