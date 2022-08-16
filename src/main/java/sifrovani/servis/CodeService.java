package sifrovani.servis;

import org.springframework.stereotype.Service;
import sifrovani.form.Form;

import java.text.Normalizer;

@Service
public class CodeService {

    String text;
    String heslo;

    public Form provedOperaci(Form form) {
        text = odstraneniZnaku(form.getText());
        heslo = odstraneniZnaku(form.getHeslo());
        if (text == "" || heslo == "") {
            throw new NullPointerException("Nezadal jsi spravny format textu nebo hesla.");
        }
        if (form.getOperace() == 1) {
            form = zasifruj(form);
        } else if (form.getOperace() == 2) {
            form = desifruj(form);
        } else {
            throw new NullPointerException("Nezadal jsi operaci.");
        }
        return form;
    }

    private String odstraneniZnaku(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFKD)
                .replaceAll("\\p{M}", "")
                .replaceAll("\\p{Punct}", "")
                .replaceAll("[0-9]","")
                .toLowerCase().trim();
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
