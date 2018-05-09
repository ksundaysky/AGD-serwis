package com.ksundaysky.controller;

import com.ksundaysky.model.Product;
import com.ksundaysky.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
@Controller
public class ProductController {


    @Autowired
    ProductService productService;

    @RequestMapping(value = {"/products/create"}, method = RequestMethod.GET)
    public ModelAndView createNewProdct() {
        ModelAndView modelAndView = new ModelAndView();
        Product product = new Product();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("/products/create");
        return modelAndView;
    }

    @RequestMapping(value = "/products/create", method = RequestMethod.POST)
    public ModelAndView createNewProdct(@Valid Product product, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        Product productExist = productService.findBySerialNumber(product.getSerial());
        if(productExist != null)
        {
            bindingResult
                    .rejectValue("serial","error.product","Ten numer jest już przypisany");
        }
          if (bindingResult.hasErrors()) {	
            modelAndView.setViewName("/products/create");	
        }	
        else {	
             productService.saveProduct(product);	             
             modelAndView.addObject("successMessage", "Produkt został dodany");	            
             modelAndView.addObject("product", new Product());	             
             modelAndView.setViewName("/home");	             
        }


        return modelAndView;
    }

    @RequestMapping(value = "/products")

    public ModelAndView index(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        modelAndView.setViewName("/products/index");
        String successMessage = (String)request.getSession().getAttribute("successMessage");
        if( successMessage != null) {
            modelAndView.addObject("successMessage", successMessage);
            request.getSession().removeAttribute("successMessage");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/products/edit/{id}")
    public ModelAndView edit(@PathVariable int id){
        Product product = productService.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        if(product == null){	
             modelAndView.addObject("errorMessage", "Produkt o danym id nie istnieje");	
        }
        modelAndView.addObject("product", product);
        modelAndView.setViewName("/products/edit");

        return modelAndView;
    }

    @RequestMapping(value = "/products/delete/{productId}")
    public ModelAndView delete(@PathVariable int productId){
        Product product = productService.findById(productId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("/products/delete");

        return modelAndView;
    }


    @RequestMapping(value = {"/products/delete/accept/{productId}"}, method = RequestMethod.GET)
    public ModelAndView deleteAccept( @PathVariable int productId) {

        ModelAndView modelAndView = new ModelAndView();
        productService.deleteById(productId);
        modelAndView.setViewName("/home");
        modelAndView.addObject("successMessage", "Produkt został usunięty");
        return modelAndView;

    }

    @RequestMapping(value="/products/edit", method=RequestMethod.POST)
    public ModelAndView update(@Valid Product product, BindingResult bindingResult, HttpServletRequest request){

        Product productExists = productService.findById(product.getId());
        if (productExists != null) {
            productExists.setName(product.getName());
            productExists.setBrand(product.getBrand());
            productExists.setLastName(product.getLastName());
            productExists.setModel(product.getModel());
            productExists.setProduct_name(product.getProduct_name());
            productExists.setNote(product.getNote());

        }

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("product", product);
            modelAndView.setViewName("/products/edit");

            return modelAndView;
        }

        request.getSession().setAttribute("successMessage", "Produkt został poprawnie zmodyfikowany!!");
        productService.updateProduct(productExists);


        return new ModelAndView("redirect:/products");
    }

    @RequestMapping(value="/products/{id}")
    public ModelAndView show(@PathVariable int id){
        Product product = productService.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        if(product == null){	
            modelAndView.addObject("errorMessage","Produkt o danym id nie istnieje");	
        }
        modelAndView.addObject("product", product);
        modelAndView.setViewName("/products/show");

        return modelAndView;
    }

}
