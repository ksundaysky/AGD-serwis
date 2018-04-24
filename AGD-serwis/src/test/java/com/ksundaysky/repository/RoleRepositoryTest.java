package com.ksundaysky.repository;

import com.ksundaysky.model.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {


    @Autowired
    RoleRepository roleRepository;
    @Test
    public void dummyTest(){
        Assert.assertTrue(true);
    }

    @Test
    public void findRole(){

        Role role = roleRepository.findByRole("ADMIN");
        Assert.assertEquals(1,role.getId());
    }

}

