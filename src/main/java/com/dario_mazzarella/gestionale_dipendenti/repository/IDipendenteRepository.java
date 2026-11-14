package com.dario_mazzarella.gestionale_dipendenti.repository;

import com.dario_mazzarella.gestionale_dipendenti.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDipendenteRepository extends JpaRepository<Dipendente, Long> {
    List<Dipendente> findByRuoloIgnoreCase(String ruolo);
    List<Dipendente> findByDipartimentoId(Long dipartimentoId);
}