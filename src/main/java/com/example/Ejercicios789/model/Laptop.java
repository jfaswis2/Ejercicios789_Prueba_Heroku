package com.example.Ejercicios789.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(name = "Entidad Laptop", description = "Entidad laptop para representar algo")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Clave de autoincremento tipo Long")
    private Long id;
    @Schema(description = "Cantidad de RAM de la laptop")
    private String ram;
    private String cpu;
    private String storage;

    public Laptop(Long id, String ram, String cpu, String storage) {
        this.id = id;
        this.ram = ram;
        this.cpu = cpu;
        this.storage = storage;
    }

    public Laptop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
