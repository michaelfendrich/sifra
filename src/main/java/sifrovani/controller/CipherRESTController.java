package sifrovani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sifrovani.form.Form;
import sifrovani.servis.CodeService;

@RestController
@CrossOrigin(origins = "*")
public class CipherRESTController {

    @Autowired
    public CodeService service;

    @PostMapping("/code")
    public ResponseEntity<Form> perform(@RequestBody Form form) {
        return ResponseEntity.ok(service.perform(form));
    }
}
