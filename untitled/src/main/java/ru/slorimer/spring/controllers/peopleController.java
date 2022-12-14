package ru.slorimer.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.slorimer.spring.DAO.userDAO;
import ru.slorimer.spring.model.User;
import ru.slorimer.spring.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class peopleController {
    private userDAO userdAO;
    private PersonValidator personValidator;


    public peopleController(userDAO userdAO, PersonValidator personValidator) {
        this.userdAO = userdAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", userdAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", userdAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") User user){
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid User user, BindingResult bindingResult){
        personValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return "people/new";
        userdAO.save(user);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", userdAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id){
        personValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        userdAO.edit(id, user);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userdAO.delete(id);
        return "redirect:/people";
    }
}
