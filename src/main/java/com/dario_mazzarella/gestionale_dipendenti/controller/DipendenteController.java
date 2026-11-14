package com.dario_mazzarella.gestionale_dipendenti.controller;

import com.dario_mazzarella.gestionale_dipendenti.model.Dipendente;
import com.dario_mazzarella.gestionale_dipendenti.service.IDipendenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    private final IDipendenteService dipendenteService;

    public DipendenteController(IDipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping
    public List<Dipendente> getAllDipendenti(@RequestParam(required = false) String ruolo) {
        if (ruolo != null && !ruolo.isBlank()) {
            return dipendenteService.findByRuolo(ruolo);
        }
        return dipendenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getDipendenteById(@PathVariable Long id) {
        return ResponseEntity.ok(dipendenteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(
            @Valid @RequestBody Dipendente dipendente,
            @RequestParam(required = false) Long dipartimentoId) {
        Dipendente creato = dipendenteService.save(dipendente, dipartimentoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(creato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateDipendente(
            @PathVariable Long id,
            @Valid @RequestBody Dipendente dipendente,
            @RequestParam(required = false) Long dipartimentoId) {
        return ResponseEntity.ok(dipendenteService.update(id, dipendente, dipartimentoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}