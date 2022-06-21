package sifrovani.servis;

import org.springframework.stereotype.Service;
import sifrovani.form.Form;

import java.util.Locale;

@Service
public class CodeService {

    String text;
    String heslo;

    public void provedOperaci(Form form) {
        text = form.getText().toLowerCase();
        heslo = form.getHeslo().toLowerCase();
        if (form.getOperace() == 1) {
            zasifruj(form);
        } else if (form.getOperace() == 2) {
            desifruj(form);
        } else {
            throw new NullPointerException("Nezadal jsi operaci");
        }
    }

    private Form zasifruj(Form form) {
        String vysledek = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                vysledek += " ";
            } else {
                int cisloZnaku = (int) text.charAt(i) + (int) heslo.charAt(i % heslo.length()) - 96;
                if (cisloZnaku > (int) 'z') {
                    cisloZnaku -= 26;
                }
                vysledek += (char) cisloZnaku;
            }
        }
        form.setSifra(vysledek);
        return form;
    }

    private Form desifruj(Form form) {
        String vysledek = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                vysledek += " ";
            } else {
                int cisloZnaku = (int) text.charAt(i) - (int) heslo.charAt(i % heslo.length()) + 96;
                if (cisloZnaku < (int) 'a') {
                    cisloZnaku += 26;
                }
                vysledek += (char) cisloZnaku;
            }
        }
        form.setSifra(vysledek);
        return form;
    }
}
