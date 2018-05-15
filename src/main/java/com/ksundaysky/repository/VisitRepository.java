package com.ksundaysky.repository;

import com.ksundaysky.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("visitRepository")
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Visit findById(int id);
    @Transactional
    Long deleteById(int id);
}
