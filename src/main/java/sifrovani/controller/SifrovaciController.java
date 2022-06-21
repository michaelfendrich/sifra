package sifrovani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sifrovani.form.Form;
import sifrovani.servis.CodeService;

@Controller
public class SifrovaciController {

    @Autowired
    private CodeService servis;

    @GetMapping("/sifra")
    public String print(Model model) {
        model.addAttribute("sifra", new Form());
        return "index";
    }

    @PostMapping("/vysledek")
    public String provedOperaci(@ModelAttribute Form form) {
        servis.provedOperaci(form);
        return "vysledek";
    }
}
