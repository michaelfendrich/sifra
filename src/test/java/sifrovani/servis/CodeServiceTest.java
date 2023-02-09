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
        //given
        form.setText("Hradec je fajn");
        form.setPassword("krakov");
        form.setOperation(1);
        //when
        servis.perform(form);
        //then
        assertEquals("sjboty bf uwuf", form.getCode());
    }

    @Test
    public void provedZasifrovaniSDiakritikou1() {
        //given
        form.setText(" Cześć świate!");
        form.setPassword("Kraków.");
        form.setOperation(1);
        //when
        servis.perform(form);
        //then
        assertEquals("nrfdr dojlib", form.getCode());
    }

    @Test
    public void provedZasifrovaniSDiakritikou2() {
        //give
        form.setText("Žiji v Krakově");
        form.setPassword("Česko .");
        form.setOperation(1);
        //when
        servis.perform(form);
        //then
        assertEquals("cnct y dcpntop", form.getCode());
    }

    @Test
    public void provedDesifrovani() {
        //given
        form.setText("sjboty bf uwuf");
        form.setPassword("krakov");
        form.setOperation(2);
        //when
        servis.perform(form);
        //then
        assertEquals("hradec je fajn", form.getCode());
    }

    @Test
    public void provedChybneSifrovani() {
        Throwable exception = assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                form.setText("a");
                form.setPassword("a");
                form.setOperation(0);
                servis.perform(form);
            }
        });
        assertEquals("Operation is required.", exception.getMessage());
    }

    @Test
    public void provedChybneDesifrovani1() {
        //give
        form.setText("a");
        form.setPassword("a");
        form.setOperation(3);

        //when
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            servis.perform(form);
        });
        //then
        assertEquals("Operation is required.", exception.getMessage());
    }

    @Test
    public void provedChybneSifrovaniSPrazdnymTextem() {
        //given
        form.setText(".!@3?<>#$%'");
        form.setPassword("a");
        form.setOperation(1);

        //when
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            servis.perform(form);
        });
        //then
        assertEquals("You didn't entered a correct format of the wold or password.", exception.getMessage());
    }

    @Test
    public void provedChybneSifrovaniSPrazdnymHeslem() {
        //give
        form.setText("a");
        form.setPassword(".?");
        form.setOperation(1);
        //when
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            servis.perform(form);
        });
        //then
        assertEquals("You didn't entered a correct format of the wold or password.", exception.getMessage());
    }
}