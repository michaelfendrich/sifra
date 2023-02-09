package sifrovani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sifrovani.form.Form;
import sifrovani.servis.CodeService;

@Controller
public class CipherController {

    @Autowired
    private CodeService servis;

    @GetMapping("/code")
    public String print(Model model) {
        model.addAttribute("code", new Form());
        return "index";
    }

    @PostMapping("/result")
    public String perform(@ModelAttribute Form form) {
        servis.perform(form);
        return "result";
    }
}
