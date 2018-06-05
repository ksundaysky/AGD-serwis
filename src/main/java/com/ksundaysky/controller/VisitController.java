package com.ksundaysky.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksundaysky.model.*;
import com.ksundaysky.model.Product;
import com.ksundaysky.repository.EventRepository;
import com.ksundaysky.repository.VisitRepository;
import com.ksundaysky.service.ClientService;
import com.ksundaysky.service.ProductService;
import com.ksundaysky.service.VisitService;
import com.ksundaysky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Controller
@RestController
public class VisitController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAnyAuthority('ADMIN','REJESTRUJACY')")
    @RequestMapping(value = {"/clients/{client_id}/products/{id}/visits/create"}, method = RequestMethod.GET)
    public ModelAndView createNewVisit(@PathVariable("id") int id, @PathVariable("client_id") int client_id) {
        ModelAndView modelAndView = new ModelAndView();
        Visit visit = new Visit();

        List<User> serwisantList = userService.findAll().stream()
                .filter(user -> user.getRole_id() == 2)
                .map(user -> new User(user.getId(),user.getEmail(), user.getPassword(), user.getName(), user.getLastName(), user.getActive(), user.getRole_id()))
                .collect(Collectors.toList());
        modelAndView.addObject("visit", visit);
        modelAndView.addObject("productId", id);
        modelAndView.addObject("client_id", client_id);
        modelAndView.addObject("serwisantList",serwisantList);
        modelAndView.setViewName("/clients/products/visits/create");
        return modelAndView;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','REJESTRUJACY')")
    @RequestMapping(value = "/clients/{client_id}/products/{id}/visits/create", method = RequestMethod.POST)
    public ModelAndView createNewVisit( @Valid Visit visit,  BindingResult bindingResult, @PathVariable int id, @PathVariable("client_id") int client_id) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {

            List<User> serwisantList = userService.findAll().stream()
                    .filter(user -> user.getRole_id() == 2)
                    .map(user -> new User(user.getId(),user.getEmail(), user.getPassword(), user.getName(), user.getLastName(), user.getActive(), user.getRole_id()))
                    .collect(Collectors.toList());
            modelAndView.addObject("visit", visit);
            modelAndView.addObject("productId", id);
            modelAndView.addObject("client_id",client_id);
            modelAndView.addObject("serwisantList",serwisantList);
            modelAndView.setViewName("/clients/products/visits/create");
        }
        else {
            visit.setClient_id(client_id);
            visit.setProduct_id(id);
           // visit.setReceipt_date(visit.getReceipt_date().replace("T"," "));
            //visit.setReceipt_date(visit.getReceipt_date()+" "+visit.time_start);
           // visit.receipt_date = visit.receipt_date+" "+visit.time_start;
            //System.out.println("*****************************"+visit.getReceipt_date()+"****************************");
            visitService.saveVisit(visit);
            modelAndView.addObject("successMessage", "Wizyta została dodana");
            //modelAndView.addObject("visit", new Visit());
            modelAndView.setViewName("/home");
        }


        return modelAndView;
    }

    @RequestMapping(value = "/visits")
    public ModelAndView index(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Visit> visits = visitService.findAll();
        for(Visit visit : visits){
            setVisitData(visit);
        }
        modelAndView.addObject("visits",visits);
        modelAndView.setViewName("/clients/products/visits/index");
        String successMessage = (String)request.getSession().getAttribute("successMessage");
        if( successMessage != null) {
            modelAndView.addObject("successMessage", successMessage);
            request.getSession().removeAttribute("successMessage");
        }

        return modelAndView;
    }

    @RequestMapping(value="/eventss", method=RequestMethod.GET)
    public String getEventsInRange() {
//        List<Visit> visits = visitService.findAll();
//
//        List<Event> events = new ArrayList<Event>();
//
        String jsonMsg = null;
//        for(Visit visit : visits)
//        {
//            Event event = new Event();
//            event.setStart(visit.getReceipt_date_start());
//            event.setEnd(visit.getReceipt_date_end());
//            event.setTitle("dziaaaaala");
//            events.add(event);
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
        return jsonMsg;
    }

    @RequestMapping(value="/clients/{client_id}/products/{product_id}/visits/{id}")
    public ModelAndView show(@PathVariable int id){
        Visit visit = visitService.findVisitById(id);

        ModelAndView modelAndView = new ModelAndView();
        if(visit == null){
            modelAndView.addObject("errorMessage","Wizyta o danym id nie istnieje");
        }
        modelAndView.addObject("visit", visit);
        modelAndView.setViewName("/clients/products/visits/show");

        return modelAndView;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SERWISANT')")
    @RequestMapping(value = "/clients/{client_id}/products/{product_id}/visits/repair/{id}")
    public ModelAndView repair(@PathVariable("product_id") int product_id, @PathVariable("client_id") int client_id, @PathVariable("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView();
        Visit visit = visitService.findVisitById(id);

        if(visit == null){
            modelAndView.addObject("errorMessage", "Wizyta o danym id nie istnieje");
        }

        modelAndView.addObject("visit", visit);
        modelAndView.setViewName("/clients/products/visits/repair");
        return modelAndView;

    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SERWISANT')")
    @RequestMapping(value = "/clients/{client_id}/products/{product_id}/visits/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid Visit visit, BindingResult bindingResult, @PathVariable int product_id, @PathVariable("client_id") int client_id) {

        ModelAndView modelAndView = new ModelAndView();
        Visit visitExists = visitService.findVisitById(visit.getId());
        if (visitExists != null) {
            visitExists.setActual_description(visit.getActual_description());
            visitExists.setRepair_date(visit.getRepair_date());
            visitExists.setPick_up_date(visit.getPick_up_date());
            visitExists.setCosts(visit.getCosts());
            visitExists.setNote(visit.getNote());
            visitService.update(visitExists);
        }
        else{
            modelAndView.addObject("visit", visit);
            modelAndView.setViewName("/clients/products/visits/repair");
            return modelAndView;
        }
        modelAndView.addObject("successMessage", "Wizyta została edytowana");
        modelAndView.setViewName("/home");

        return modelAndView;
    }

    private void setVisitData(Visit visit) {
        Client client = clientService.findById(visit.getClient_id());
        if(client!= null) {
            String clientNameSurname = client.getClient_name() + ' ' + client.getClient_surname();
            visit.setClientNameSurname(clientNameSurname);
        }
        User serviceman = userService.findUserById(visit.getEmployerId());
        if(serviceman != null)
            visit.setServisantSurname(serviceman.getLastName());
        Product product = productService.findById(visit.getProduct_id());
        if(product != null)
            visit.setProductName(product.getProduct_name());
    }

}