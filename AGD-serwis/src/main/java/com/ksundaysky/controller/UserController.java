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
    public ModelAndView editUser(@PathVariable int id){
        User user = userService.findUserById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/users/edit");
        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("role_map", roleList);
        return modelAndView;
    }
    @RequestMapping(value="/users/update", method=RequestMethod.POST)
    public ModelAndView updateUser(@Valid User user, BindingResult bindingResult){

        User userExists = userService.findUserById(user.getId());
        if (userExists != null) {
            userExists.setEmail(user.getEmail());
            userExists.setRole_id(user.getRole_id());
            userExists.setName(user.getName());
            userExists.setLastName(user.getLastName());

        }
        if (bindingResult.hasFieldErrors("email") || bindingResult.hasFieldErrors("name") || bindingResult.hasFieldErrors("lastName")) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            List<Role> roleList = roleService.findAll();
            modelAndView.addObject("role_map", roleList);
            modelAndView.setViewName("/users/edit");
            return modelAndView;
        }
        userService.updateUser(userExists);
        return new ModelAndView("redirect:/users/list","udane","1");
    }

    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    public ModelAndView listUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.addObject("users",users);
        modelAndView.setViewName("/users/list");
        return  modelAndView;
    }

    @RequestMapping(value = {"/users/delete/{userId}"}, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int userId) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(userId);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/users/delete");

        return modelAndView;
    }

    @RequestMapping(value = {"/users/delete/accept/{userId}"}, method = RequestMethod.GET)
    public ModelAndView deleteAccept( @PathVariable int userId) {

        ModelAndView modelAndView = new ModelAndView();
        userService.deleteById(userId);
        modelAndView.setViewName("/home");
        modelAndView.addObject("successMessage", "Pracownik został usunięty");
        return modelAndView;

    }

}