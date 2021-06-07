package lk.wixis360.website.advisor;

import lk.wixis360.website.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleException(RuntimeException ex){
        StandardResponse response = new StandardResponse(500, "error", ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
