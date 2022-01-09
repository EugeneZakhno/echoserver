package ru.intervale.courses.echoserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@RestController
public class MainController {

    //http://localhost:8080/hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    //http://localhost:8080/withParams?param=jesus
    @GetMapping(value = "withParams")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String withParams(@RequestParam("param") String param) {
        return param;
    }

    //http://localhost:8080/withPathVariable/12
    @GetMapping("/withPathVariable/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String withPathVariable(@PathVariable Long id) {
        return String.valueOf(id);
    }


    @PostMapping(value = "/echo")
    public ResponseEntity<String> echo(@RequestHeader Map<String, String> headers, @RequestBody String textBody) {
        if (headers.containsValue("application/json")) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(textBody);
        } else if (headers.containsValue("application/xml")) {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/xml")
                    .body(textBody);
        } else {
            return ResponseEntity.ok().body("Wrong Content-Type");
        }
    }

    @PostMapping(value = "/echoxml", produces = "application/xml")
    public String echoXml(@RequestBody String xmlText) {
        return xmlText;
    }

    @PutMapping(value = "/put")
    public String echoPut() {
        return "";
    }

    @GetMapping("/cookie")
    public ResponseEntity<?> cookie(@CookieValue(name = "Date", defaultValue = "firstRequest") String cookieName) {
        if (cookieName.equals("firstRequest")) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, String.valueOf(ResponseCookie.from("Date", "" +
                            new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(Calendar.getInstance().getTime()))
                            .build()))
                    .build();
        } else {
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookieName)
                    .header(HttpHeaders.SET_COOKIE, String.valueOf(ResponseCookie.from("Date", "" +
                            new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(Calendar.getInstance().getTime()))
                            .build()))
                    .build();
        }

    }
}
