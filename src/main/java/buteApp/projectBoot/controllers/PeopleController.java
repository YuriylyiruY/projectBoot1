package buteApp.projectBoot.controllers;

import buteApp.projectBoot.models.User;
import buteApp.projectBoot.services.PeopleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") User person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid User person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User person, BindingResult bindingResult,
                         HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return "people/edit";
        int id = Integer.parseInt(request.getParameter("id"));
        peopleService.update(id, person);
        return "redirect:/";
    }

    @PostMapping("/del/{id}")
    public String delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        peopleService.delete(id);
        return "redirect:/";
    }

}
