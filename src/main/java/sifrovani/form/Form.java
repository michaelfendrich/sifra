package sifrovani.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import sifrovani.servis.CodingType;

@Data
@NoArgsConstructor
public class Form {
    private String text;
    private CodingType operation;
    private String password;
    private String code;
}
