package com.ksundaysky.repository;

import com.ksundaysky.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;

@Repository("componentRepository")
public interface ComponentRepository extends JpaRepository<Component,Integer> {
    Component findById(int component_id);
    @Transactional
    Long deleteById(int component_id);
}
