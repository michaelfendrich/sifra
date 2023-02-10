package sifrovani.servis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sifrovani.form.Form;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CodeServiceTest {

    private CodeService service;
    private Form form;

    @BeforeEach
    void setUp() {
        service = new CodeService();
        form = new Form();
    }

    @AfterEach
    void tearDown() {
        service = null;
        form = null;
    }

    @ParameterizedTest
    @MethodSource("prepareCsvForCoding")
    public void shouldReturnCorrectCode(String textToCode, String password, CodingType type, String result) {
        //given
        form.setText(textToCode);
        form.setPassword(password);
        form.setOperation(type);
        //when
        service.perform(form);
        //then
        assertEquals(result, form.getCode());
    }

    @Test
    public void shouldReturnNullPointerExceptionBecauseOperationIsNull() {
        Throwable exception = assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                form.setText("a");
                form.setPassword("a");
                form.setOperation(null);
                service.perform(form);
            }
        });
        assertEquals("Operation is required.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("prepareCsvToReturnExceptions")
    public void shouldReturnNullPointerExceptionBecauseTextFormatIsNotCorrect(String textToCode, String password, CodingType type, String result) {
        //given
        form.setText(textToCode);
        form.setPassword(password);
        form.setOperation(type);

        //when
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            service.perform(form);
        });
        //then
        assertEquals(result, exception.getMessage());
    }

    public static Stream<Arguments> prepareCsvForCoding() {
        return Stream.of(
                Arguments.of("Hradec je fajn", "krakov", CodingType.ENCRYPTION, "sjboty bf uwuf"),
                Arguments.of(" Cześć świate!", "Kraków", CodingType.ENCRYPTION, "nrfdr dojlib"),
                Arguments.of("Žiji v Krakově", "Česko .", CodingType.ENCRYPTION, "cnct y dcpntop"),
                Arguments.of("sjboty bf uwuf", "krakov", CodingType.DECRYPTION, "hradec je fajn")

        );
    }

    public static Stream<Arguments> prepareCsvToReturnExceptions() {
        return Stream.of(
                Arguments.of(".!@3?<>#$%'", "a", CodingType.ENCRYPTION, "You didn't entered a correct format of the wold or password."),
                Arguments.of("a", ".?", CodingType.ENCRYPTION, "You didn't entered a correct format of the wold or password.")
        );
    }
}