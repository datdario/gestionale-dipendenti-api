package com.dario_mazzarella.gestionale_dipendenti.service;

import com.dario_mazzarella.gestionale_dipendenti.exception.ResourceNotFoundException;
import com.dario_mazzarella.gestionale_dipendenti.model.Dipartimento;
import com.dario_mazzarella.gestionale_dipendenti.model.Dipendente;
import com.dario_mazzarella.gestionale_dipendenti.repository.IDipartimentoRepository;
import com.dario_mazzarella.gestionale_dipendenti.repository.IDipendenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DipendenteService implements IDipendenteService {

    private final IDipendenteRepository dipendenteRepository;
    private final IDipartimentoRepository dipartimentoRepository;

    public DipendenteService(IDipendenteRepository dipendenteRepository,
                             IDipartimentoRepository dipartimentoRepository) {
        this.dipendenteRepository = dipendenteRepository;
        this.dipartimentoRepository = dipartimentoRepository;
    }

    @Override
    public List<Dipendente> findAll() {
        return dipendenteRepository.findAll();
    }

    @Override
    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato con ID: " + id));
    }

    @Override
    public List<Dipendente> findByRuolo(String ruolo) {
        return dipendenteRepository.findByRuoloIgnoreCase(ruolo);
    }

    @Override
    public Dipendente save(Dipendente dipendente, Long dipartimentoId) {
        if (dipartimentoId != null) {
            Dipartimento dip = dipartimentoRepository.findById(dipartimentoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Dipartimento non trovato con ID: " + dipartimentoId));
            dipendente.setDipartimento(dip);
        }
        return dipendenteRepository.save(dipendente);
    }

    @Override
    public Dipendente update(Long id, Dipendente dettagli, Long dipartimentoId) {
        Dipendente esistente = findById(id);
        esistente.setNome(dettagli.getNome());
        esistente.setCognome(dettagli.getCognome());
        esistente.setEmail(dettagli.getEmail());
        esistente.setRuolo(dettagli.getRuolo());

        if (dipartimentoId != null) {
            Dipartimento dip = dipartimentoRepository.findById(dipartimentoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Dipartimento non trovato con ID: " + dipartimentoId));
            esistente.setDipartimento(dip);
        }
        return dipendenteRepository.save(esistente);
    }

    @Override
    public void deleteById(Long id) {
        Dipendente esistente = findById(id);
        dipendenteRepository.delete(esistente);
    }
}