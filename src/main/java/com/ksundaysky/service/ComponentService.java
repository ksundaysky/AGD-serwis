package com.ksundaysky.service;

import com.ksundaysky.model.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComponentService {

    public void saveComponent(Component component);
    public List<Component> findAll();
    public Component findById(int id);
    public void updateComponent(Component component);
    public void deleteById(int id);
}
