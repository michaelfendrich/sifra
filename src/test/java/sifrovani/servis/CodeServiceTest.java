package sifrovani.servis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
    public void provedZasifrovaniSDiakritikou1() {
        form.setText(" Cześć świate!");
        form.setHeslo("Kraków.");
        form.setOperace(1);
        servis.provedOperaci(form);
        assertEquals("nrfdr dojlib", form.getSifra());
    }

    @Test
    public void provedZasifrovaniSDiakritikou2() {
        form.setText("Žiji v Krakově");
        form.setHeslo("Česko .");
        form.setOperace(1);
        servis.provedOperaci(form);
        assertEquals("cnct y dcpntop", form.getSifra());
    }

    @Test
    public void provedDesifrovani() {
        form.setText("sjboty bf uwuf");
        form.setHeslo("krakov");
        form.setOperace(2);
        servis.provedOperaci(form);
        assertEquals("hradec je fajn", form.getSifra());
    }

    @Test
    public void provedChybneSifrovani() {
        Throwable exception = assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                form.setText("a");
                form.setHeslo("a");
                form.setOperace(0);
                servis.provedOperaci(form);
            }
        });
        assertEquals("Nezadal jsi operaci.", exception.getMessage());
    }

    @Test
    public void provedChybneDesifrovani1() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            form.setText("a");
            form.setHeslo("a");
            form.setOperace(3);
            servis.provedOperaci(form);
        });
        assertEquals("Nezadal jsi operaci.", exception.getMessage());
    }

    @Test
    public void provedChybneSifrovaniSPrazdnymTextem() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            form.setText(".!@3?<>#$%'");
            form.setHeslo("a");
            form.setOperace(1);
            servis.provedOperaci(form);
        });
        assertEquals("Nezadal jsi spravny format textu nebo hesla.", exception.getMessage());
    }

    @Test
    public void provedChybneSifrovaniSPrazdnymHeslem() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            form.setText("a");
            form.setHeslo(".?");
            form.setOperace(1);
            servis.provedOperaci(form);
        });
        assertEquals("Nezadal jsi spravny format textu nebo hesla.", exception.getMessage());
    }
}