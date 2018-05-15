package com.ksundaysky.service;

import com.ksundaysky.model.Visit;

import java.util.List;

public interface VisitService {
    public void saveVisit(Visit visit);
    public Visit findVisitById(int id);
    public List<Visit> findAll();
    public void update(Visit visit);
    public List<Visit> findAllProductVisits(int id);

}
