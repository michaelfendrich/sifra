package sifrovani.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Form {
    private String text;
    private int operace;
    private String heslo;
    private String sifra;
}
