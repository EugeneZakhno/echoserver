package ru.intervale.courses.echoserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@RestController
// Documentation with Swagger by url: http://localhost:8080/swagger-ui/index.html#/
@Tag(name = "Главный контролер", description = "Здесь этот контроллер один, но можно сделать и другие контроллеры!")
public class MainController {

    //http://localhost:8080/hello
    @GetMapping("/hello")
    @Operation(summary = "здесь краткое описание! ", description = " болле подробное описание. Метод приветствия!")
    public String hello() {
        return "Hello!";
    }

    //http://localhost:8080/withParams?param=jesus
    @GetMapping(value = "withParams")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Operation(summary = "foo!!!", description = "Метод для передачи параметров в запросе.")
    public String withParams(@RequestParam("param") String param) {
        return param;
    }
    //http://localhost:8080/withPathVariable/12

    @GetMapping("/withPathVariable/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "передача id", description = "Метод просто возвращает передаваемый параметр ")
    public String withPathVariable(@PathVariable @RequestPart("point") @Parameter(description = "Идентификатор пользователя") Long id) {
        return String.valueOf(id);
    }


    @PostMapping(value = "/echo")
    @Operation(summary = "определение по хедеру", description = "Определить по хедеру что содержиться в теле запроса")
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

    @PutMapping(value = "/put")
    @Operation(summary = "попробовать put", description = "просто проверка отправки методом PUT")
    public String echoPut() {
        return "";
    }

    @GetMapping("/cookie")
    @Operation(summary = "Пробуем cookie", description = "При первом обращении к серверу - 200 OK и установить cookie с датой запроса. " +
            "При повторном обращении к серверу - вывести дату предыдущего обращения и обновить cookie с датой запроса")
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
