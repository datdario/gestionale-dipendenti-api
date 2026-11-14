package com.dario_mazzarella.gestionale_dipendenti.service;

import com.dario_mazzarella.gestionale_dipendenti.model.Dipendente;
import java.util.List;

public interface IDipendenteService {
    List<Dipendente> findAll();
    Dipendente findById(Long id);
    List<Dipendente> findByRuolo(String ruolo);
    Dipendente save(Dipendente dipendente, Long dipartimentoId);
    Dipendente update(Long id, Dipendente dettagliDipendente, Long dipartimentoId);
    void deleteById(Long id);
}