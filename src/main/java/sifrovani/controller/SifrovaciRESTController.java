package sifrovani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sifrovani.form.Form;
import sifrovani.servis.CodeService;

@RestController
@CrossOrigin(origins = "*")
public class SifrovaciRESTController {

    @Autowired
    public CodeService service;

    @PostMapping("/sifra")
    public Form provedOperaci(@RequestBody Form form) {
        return service.provedOperaci(form);
    }
}
