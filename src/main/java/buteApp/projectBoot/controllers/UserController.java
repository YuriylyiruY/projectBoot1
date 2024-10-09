package buteApp.projectBoot.controllers;

import buteApp.projectBoot.models.User;
import buteApp.projectBoot.services.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserServiceImp peopleService;

    @Autowired
    public UserController(UserServiceImp peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@RequestParam(name = "id", required = false) String name, Model model) {
        int id = Integer.parseInt(name);
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
    public String edit(Model model, @RequestParam(name = "id", required = false) String name) {
        System.out.println(name);
        int id = Integer.parseInt(name);
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User person, BindingResult bindingResult,
                         @RequestParam(name = "id", required = false) String name) {
        if (bindingResult.hasErrors())
            return "people/edit";
        int id = Integer.parseInt(name);
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
