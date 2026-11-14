package com.dario_mazzarella.gestionale_dipendenti.repository;

import com.dario_mazzarella.gestionale_dipendenti.model.Dipartimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDipartimentoRepository extends JpaRepository<Dipartimento, Long> {
}