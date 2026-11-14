package com.dario_mazzarella.gestionale_dipendenti.controller;

import com.dario_mazzarella.gestionale_dipendenti.exception.ResourceNotFoundException;
import com.dario_mazzarella.gestionale_dipendenti.model.Dipartimento;
import com.dario_mazzarella.gestionale_dipendenti.model.Dipendente;
import com.dario_mazzarella.gestionale_dipendenti.repository.IDipartimentoRepository;
import com.dario_mazzarella.gestionale_dipendenti.repository.IDipendenteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dipartimenti")
public class DipartimentoController {

    private final IDipartimentoRepository dipartimentoRepository;
    private final IDipendenteRepository dipendenteRepository;

    public DipartimentoController(IDipartimentoRepository dipartimentoRepository,
                                  IDipendenteRepository dipendenteRepository) {
        this.dipartimentoRepository = dipartimentoRepository;
        this.dipendenteRepository = dipendenteRepository;
    }

    @GetMapping
    public List<Dipartimento> getAllDipartimenti() {
        return dipartimentoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Dipartimento> createDipartimento(@Valid @RequestBody Dipartimento dipartimento) {
        Dipartimento salvato = dipartimentoRepository.save(dipartimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvato);
    }

    @GetMapping("/{id}/dipendenti")
    public List<Dipendente> getDipendentiByDipartimento(@PathVariable Long id) {
        if (!dipartimentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dipartimento non trovato con ID: " + id);
        }
        return dipendenteRepository.findByDipartimentoId(id);
    }
}