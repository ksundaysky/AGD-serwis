package com.ksundaysky.service;

import com.ksundaysky.model.Visit;
//import com.ksundaysky.repository.RoleRepository;
import com.ksundaysky.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("visitService")
public class VisitServiceImpl implements VisitService{

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public void saveVisit(Visit visit) {
        visitRepository.save(visit);
    }

    @Override
    public Visit findVisitById(int id) {
        return visitRepository.findById(id);
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }


    public List<Visit> findAllProductVisits(int id){
        return visitRepository.findAllByProductId(id);
    }



    @Override
    public void update(Visit visit) {

//        Role[] role = new Role[1];
////        user.getRoles().toArray(role);
////        Role userRole = roleRepository.findByRole(role[0].getRole());
////        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        visitRepository.save(visit);
    }
}
