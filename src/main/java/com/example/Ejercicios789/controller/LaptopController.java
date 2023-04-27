package com.example.Ejercicios789.controller;

import com.example.Ejercicios789.model.Laptop;
import com.example.Ejercicios789.repository.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {


    private final LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
        Optional<Laptop> laptopOptional = laptopRepository.findById(id);

        if (laptopOptional.isPresent())
            return ResponseEntity.ok(laptopOptional.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));

        if (laptop.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (!laptopRepository.existsById(laptop.getId())) {
            return ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        if(!laptopRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll() {
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
