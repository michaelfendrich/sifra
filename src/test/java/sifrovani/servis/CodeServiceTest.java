package sifrovani.servis;

import org.junit.jupiter.api.Test;
import sifrovani.form.Form;

import static org.junit.jupiter.api.Assertions.*;

class CodeServiceTest {

    private CodeService servis;
    private Form form;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        servis = new CodeService();
        form = new Form();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        servis = null;
        form = null;
    }

    @Test
    public void provedZasifrovani() {
        form.setText("Hradec je fajn");
        form.setHeslo("krakov");
        form.setOperace(1);
        servis.provedOperaci(form);
        assertEquals("sjboty bf uwuf", form.getSifra());
    }

    @Test
    public  void provedDesifrovani() {
        form.setText("sjboty bf uwuf");
        form.setHeslo("krakov");
        form.setOperace(2);
        servis.provedOperaci(form);
        assertEquals("hradec je fajn", form.getSifra());
    }
}