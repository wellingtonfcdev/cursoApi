package com.br.wellingtondev.api_.resources.exceptions;

import com.br.wellingtondev.api_.services.exceptions.DataIntegratyViolationException;
import com.br.wellingtondev.api_.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundThenReturnAResponseEntity() {
        ResponseEntity<StandartError> response = exceptionHandler
                .objectNotFound( new ObjectNotFoundException("Objeto não encontrado"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandartError.class, response.getBody().getClass());
        assertEquals("Objeto não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());

    }

    @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandartError> response = exceptionHandler
                .dataIntegrityViolationException(
                        new DataIntegratyViolationException(E_MAIL_JA_CADASTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandartError.class, response.getBody().getClass());
        assertEquals(E_MAIL_JA_CADASTRADO, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
        assertNotEquals("/user/2",response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(),response.getBody().getTimestamp());

    }
    }
