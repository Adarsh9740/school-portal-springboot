package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("public")
public class PublicController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/register", method= RequestMethod.GET)
    public String displayRegisterPage(Model model){
            model.addAttribute("person",new Person());
            return "register";
    }
@RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute(name = "person") Person person, Errors errors){
        if (errors.hasErrors()){
            return "register";
        }

    boolean isSaved = personService.createNewPerson(person);
    if(isSaved){
        return "redirect:/login?register=true";
    }else {
        return "register.html";
    }
    }
}
