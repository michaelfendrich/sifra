package sifrovani.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import sifrovani.form.Form;
import sifrovani.servis.CodingType;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CipherRESTControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void post() throws URISyntaxException {
        //given
        URI uri = new URI("http://localhost:" + port + "/code");
        Form form = new Form();
        form.setOperation(CodingType.ENCRYPTION);
        form.setPassword("Kraków");
        form.setText(" Cześć świate!");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Form> entity = new HttpEntity<>(form, httpHeaders);

        //when
        ResponseEntity<Form> response = restTemplate.postForEntity(uri, entity, Form.class);

        //then
        assertEquals("nrfdr dojlib", response.getBody().getCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}