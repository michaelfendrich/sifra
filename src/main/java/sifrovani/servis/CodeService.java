package sifrovani.servis;

import org.springframework.stereotype.Service;
import sifrovani.form.Form;

import java.text.Normalizer;

@Service
public class CodeService {

    String text;
    String password;

    public Form perform(Form form) {
        text = removeSpecificSymbols(form.getText());
        password = removeSpecificSymbols(form.getPassword());
        if (text == "" || password == "") {
            throw new NullPointerException("You didn't entered a correct format of the wold or password.");
        }
        if (form.getOperation() == CodingType.ENCRYPTION) {
            form = encrypt(form);
        } else if (form.getOperation() == CodingType.DECRYPTION) {
            form = decrypt(form);
        } else {
            throw new NullPointerException("Operation is required.");
        }
        return form;
    }

    private String removeSpecificSymbols(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFKD)
                .replaceAll("\\p{M}", "")
                .replaceAll("\\p{Punct}", "")
                .replaceAll("[0-9]","")
                .toLowerCase().trim();
    }

    private Form encrypt(Form form) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result += " ";
            } else {
                int numberOfLetter = (int) text.charAt(i) + (int) password.charAt(i % password.length()) - 96;
                if (numberOfLetter > (int) 'z') {
                    numberOfLetter -= 26;
                }
                result += (char) numberOfLetter;
            }
        }
        form.setCode(result);
        return form;
    }

    private Form decrypt(Form form) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result += " ";
            } else {
                int numberOfLetter = (int) text.charAt(i) - (int) password.charAt(i % password.length()) + 96;
                if (numberOfLetter < (int) 'a') {
                    numberOfLetter += 26;
                }
                result += (char) numberOfLetter;
            }
        }
        form.setCode(result);
        return form;
    }
}
