package com.test.eshopweb.controller;

import com.test.eshopweb.pojo.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class HelloController {

    // http://localhost:8080/hello
    @GetMapping("/hello")
    public Message message() {
        return new Message("Spring Boot Works! :-) ^_^");
    }

    // http://localhost:8080/dog
    @GetMapping(path = "/dog", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] dog() throws IOException {
        return Files.readAllBytes(Paths.get("files", "dog.jpeg"));
    }

    // http://localhost:8080/file?name=dog.jpeg
    // http://localhost:8080/file?name=pom.xml
    @GetMapping("/file")
    public ResponseEntity<Object> file(@RequestParam String name) throws IOException {
        Path path = Paths.get("files", name);
        if(!path.toFile().exists()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Message("File '" + name + "' does not exist!"));
        }
        MediaType mediaType = MediaType.TEXT_HTML;
        if(name.endsWith(".jpeg")) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if(name.endsWith(".xml")) {
            mediaType = MediaType.APPLICATION_XML;
        }
        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(Files.readAllBytes(path));
    }

}
