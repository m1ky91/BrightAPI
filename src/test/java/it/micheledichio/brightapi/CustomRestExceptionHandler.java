package it.micheledichio.brightapi;

import it.micheledichio.brightapi.repository.RealmRepository;
import it.micheledichio.brightapi.service.RealmMapper;
import it.micheledichio.brightapi.service.RealmService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@SpringBootTest
@ActiveProfiles("test")
public class CustomRestExceptionHandler {

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private HttpHeaders httpHeaders;

    @Mock
    private HttpStatus httpStatus;

    @Mock
    private WebRequest webRequest;

    @Mock
    private MethodArgumentTypeMismatchException methodArgumentTypeMismatchException;

    @InjectMocks
    private CustomRestExceptionHandler customRestExceptionHandler;

}
