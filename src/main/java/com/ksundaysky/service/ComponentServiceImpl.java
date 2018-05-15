package com.ksundaysky.service;

import com.ksundaysky.model.Component;
import com.ksundaysky.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("componentService")
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    ComponentRepository componentRepository;

    @Override
    public void saveComponent(Component component) {
        componentRepository.save(component);
    }

    @Override
    public List<Component> findAll() {
        return componentRepository.findAll();
    }

    @Override
    public Component findById(int component_id) { return componentRepository.findById(component_id);}

    @Override
    public void updateComponent(Component component) { componentRepository.save(component); }

    @Override
    public void deleteById(int component_id){

        componentRepository.deleteById(component_id);
    }
}
