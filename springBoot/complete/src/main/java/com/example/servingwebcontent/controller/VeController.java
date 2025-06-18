package com.example.servingwebcontent.controller;
import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
public class VeController {

    @Autowired
    private VeService veService;

    @GetMapping
    public List<Ve> getAll() {
        return veService.getAll();
    }

    @GetMapping("/{id}")
    public Ve getById(@PathVariable int id) {
        return veService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody Ve ve) {
        veService.create(ve);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Ve ve) {
        ve.setMaVe(id);
        veService.update(ve);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        veService.delete(id);
    }
}
