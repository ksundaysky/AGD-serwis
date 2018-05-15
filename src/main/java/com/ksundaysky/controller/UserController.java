package com.ksundaysky.controller;

import com.ksundaysky.model.Role;
import com.ksundaysky.model.User;
import com.ksundaysky.service.RoleService;
import com.ksundaysky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = {"/users/create"}, method = RequestMethod.GET)
    public ModelAndView createNewUser() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        int roleId = 0;
        modelAndView.addObject("user", user);
        modelAndView.addObject("user.role_id_int", roleId);
        modelAndView.setViewName("/users/create");
        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("role_map", roleList);

        return modelAndView;
    }


    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Ten email został już przypisany do pracownika");
        }
        if (bindingResult.hasErrors()) {
            List<Role> roleList = roleService.findAll();
            modelAndView.addObject("role_map", roleList);
            modelAndView.setViewName("/users/create");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Pracownik został dodany");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/home");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/users/edit/{id}")
    public ModelAndView editUser(@PathVariable int id) {
        User user = userService.findUserById(id);

        ModelAndView modelAndView = new ModelAndView();
        if(user == null){
            modelAndView.addObject("errorMessage", "Użytkownik o danym id nie istnieje");
        }
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/users/edit");
        List<Role> roleList = roleService.findAll();
        Map<Integer, String> roleMap = roleList.stream().collect(Collectors.toMap(Role::getId, Role::getRole));
        modelAndView.addObject("role_map", roleList);
        //return new ModelAndView("/users/edit","user",user);
        return modelAndView;
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserById(user.getId());
        if (userExists != null) {
            userExists.setEmail(user.getEmail());
//            userExists.setRoles(user.getRoles());
            userExists.setRole_id(user.getRole_id());
            userExists.setName(user.getName());
            userExists.setLastName(user.getLastName());
            userExists.setExperience(user.getExperience());
            userExists.setJobStartingDate(user.getJobStartingDate());
            userService.updateUser(userExists);
        }
        modelAndView.addObject("successMessage", "Pracownik został edytowany");
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("/home");

        return modelAndView;
    }

    @RequestMapping(value = {"/users/delete/{userId}"}, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int userId) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(userId);
        if(user == null){
            modelAndView.addObject("errorMessage", "Użytkownik o danym id nie istnieje");
        }
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/users/delete");

        return modelAndView;
    }


    @RequestMapping(value = {"/users/list"}, method = RequestMethod.GET)
    public ModelAndView test(@RequestParam Integer roleId) {

        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = roleService.findAll();
        modelAndView.addObject("roles", roles);
        List<User> users;
        if (roleId == null || roleId == 0) {
            users = userService.findAll().stream()
                    .map(user -> new User(user.getId(),user.getEmail(), user.getPassword(), user.getName(), user.getLastName(), user.getActive(),
                            user.getRole_id(), user.getExperience(),user.getJobStartingDate()))
                    .collect(Collectors.toList());
        } else {
            String roleName = roleService.getRoleById(roleId).getRole();

            users = userService.findAll().stream()
                    .filter(user -> user.getRole_id() == roleId)
                    //.map(user -> new User(user.getId(),user.getEmail(), user.getPassword(), user.getName(), user.getLastName(), user.getActive(), user.getRole_id(), user.getExperience(),user.getJobStartingDate()))
                    .collect(Collectors.toList());
        }

        modelAndView.addObject("users", users);
        modelAndView.setViewName("/users/list");
        return modelAndView;
    }



    @RequestMapping(value = {"/users/delete/accept/{userId}"}, method = RequestMethod.GET)
    public ModelAndView deleteAccept(@PathVariable int userId) {

        ModelAndView modelAndView = new ModelAndView();
        userService.deleteById(userId);
        modelAndView.setViewName("/home");
        modelAndView.addObject("successMessage", "Pracownik został usunięty");
        return modelAndView;

    }


    @RequestMapping(value = "/users/view/{id}")
    public ModelAndView viewUser(@PathVariable int id) {
        User user = userService.findUserById(id);

        ModelAndView modelAndView = new ModelAndView();
        if(user == null){
            modelAndView.addObject("errorMessage", "Użytkownik o danym id nie istnieje");
        }
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/users/view");
        return modelAndView;


    }

}