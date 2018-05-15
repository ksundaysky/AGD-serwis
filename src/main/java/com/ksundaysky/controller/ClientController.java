package com.ksundaysky.controller;

import com.ksundaysky.model.Role;
import com.ksundaysky.model.Product;
import com.ksundaysky.service.ProductService;
import org.springframework.stereotype.Controller;

import com.ksundaysky.service.ClientService;
import com.ksundaysky.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/clients/create"}, method = RequestMethod.GET)
    public ModelAndView createNewClient() {
        ModelAndView modelAndView = new ModelAndView();
        Client client = new Client();
        modelAndView.addObject("client", client);
        modelAndView.setViewName("/clients/create");
        return modelAndView;
    }
    @RequestMapping(value = "/clients/create", method = RequestMethod.POST)
    public ModelAndView createNewClient(@Valid Client client, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/clients/create");
        }
        else {
            clientService.saveClient(client);
            modelAndView.addObject("successMessage", "Klient został dodany");
           // modelAndView.addObject("client", new Client());
            modelAndView.setViewName("/home");
        }


        return modelAndView;
    }

    @RequestMapping(value = "/clients")
    public ModelAndView index(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Client> clients = clientService.findAll();
        modelAndView.addObject("clients",clients);
        modelAndView.setViewName("/clients/index");
        String successMessage = (String)request.getSession().getAttribute("successMessage");
        if( successMessage != null) {
            modelAndView.addObject("successMessage", successMessage);
            request.getSession().removeAttribute("successMessage");
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/clients/delete/{clientId}"}, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int clientId) {

        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.findById(clientId);
        if(client == null){
            modelAndView.addObject("errorMessage", "Klient o danym id nie istnieje");
        }
        modelAndView.addObject("client", client);
        modelAndView.setViewName("/clients/delete");

        return modelAndView;
    }

    @RequestMapping(value = "/clients/edit/{id}")
    public ModelAndView editClient(@PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.findById(id);

        if(client == null){
            modelAndView.addObject("errorMessage", "Klient o danym id nie istnieje");
        }
        modelAndView.addObject("client", client);
        modelAndView.setViewName("/clients/edit");
        //List<Client> clientList = clientService.findAll();
        //Map<Integer, String> roleMap = clientList.stream().collect(Collectors.toMap(Client::getId, Role::getRole));
        //modelAndView.addObject("role_map", roleList);
        return modelAndView;
    }

    @RequestMapping(value = "/clients/edit", method = RequestMethod.POST)
    public ModelAndView updateClient(@Valid Client client, BindingResult bindingResult, HttpServletRequest request) {

        Client clientExists = clientService.findById(client.getId());
        if (clientExists != null) {
            clientExists.setClient_surname(client.getClient_surname());
            clientExists.setClient_name(client.getClient_name());
            clientExists.setPhone_number(client.getPhone_number());
            clientExists.setCity(client.getCity());
            clientExists.setPostcode(client.getPostcode());
            clientExists.setStreet(client.getStreet());
            clientExists.setStreet_local_number(client.getStreet_local_number());

        }

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("client", client);
            modelAndView.setViewName("/clients/edit");

            return modelAndView;
        }


        clientService.updateClient(clientExists);
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("successMessage", "Klient został edytowany");
        //modelAndView.addObject("client", new Client());
        //modelAndView.setViewName("/home");

        request.getSession().setAttribute("successMessage", "Dane klienta zostały zaktualizowane");
        return new ModelAndView("redirect:/clients");

    }


    @RequestMapping(value = {"/clients/delete/accept/{userId}"}, method = RequestMethod.GET)
    public ModelAndView deleteAccept(@PathVariable int userId) {

        ModelAndView modelAndView = new ModelAndView();
        clientService.deleteById(userId);
        modelAndView.setViewName("/home");
        modelAndView.addObject("successMessage", "Klient został usunięty");
        return modelAndView;

    }


    @RequestMapping(value = "/clients/{id}")
    public ModelAndView showClient(@PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.findById(id);

        if(client == null){
            modelAndView.addObject("errorMessage", "Klient o danym id nie istnieje");
        }
        List<Product> clientProductList = productService.findAll().stream()
                .filter(product -> product.getClient_id() == id)
                .map(product -> new Product(product.getId(),product.getProduct_name(),product.getBrand(),product.getModel(), product.getNote(),product.getSerial(),product.getClient_id()))
                .collect(Collectors.toList());
        modelAndView.addObject("products",clientProductList);
        modelAndView.addObject("client", client);
        modelAndView.setViewName("/clients/show");
        return modelAndView;


    }

}
