package com.pennhacks.ecolens.controller;


import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class HomeController {

    @GetMapping("/index")
    public ResponseEntity<byte[]> getIndexPage() {
        try {
            // Specify the path to your HTML file
            Path path = Paths.get("src/main/resources/static/index.html");

            // Create a UrlResource from the path
            UrlResource resource = new UrlResource(path.toUri());

            // Check if the resource exists
            if (resource.exists()) {
                // Read the HTML file into a byte array
                byte[] bytes = Files.readAllBytes(path);

                // Serve the HTML file with the appropriate content type
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(bytes);
            } else {
                // Handle the case where the HTML file is not found
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            // Handle a malformed URL exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            // Handle an IO exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<byte[]> getIntroPage() {
        try {
            // Specify the path to your HTML file
            Path path = Paths.get("src/main/resources/static/intro.html");

            // Create a UrlResource from the path
            UrlResource resource = new UrlResource(path.toUri());

            // Check if the resource exists
            if (resource.exists()) {
                // Read the HTML file into a byte array
                byte[] bytes = Files.readAllBytes(path);

                // Serve the HTML file with the appropriate content type
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(bytes);
            } else {
                // Handle the case where the HTML file is not found
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            // Handle a malformed URL exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            // Handle an IO exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/dashboard")
    public ResponseEntity<byte[]> getDashboardPage() {
        try {
            // Specify the path to your HTML file
            Path path = Paths.get("src/main/resources/static/dashboard.html");

            // Create a UrlResource from the path
            UrlResource resource = new UrlResource(path.toUri());

            // Check if the resource exists
            if (resource.exists()) {
                // Read the HTML file into a byte array
                byte[] bytes = Files.readAllBytes(path);

                // Serve the HTML file with the appropriate content type
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(bytes);
            } else {
                // Handle the case where the HTML file is not found
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            // Handle a malformed URL exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            // Handle an IO exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}