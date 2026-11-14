package com.dario_mazzarella.gestionale_dipendenti.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dipartimenti")
public class Dipartimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Il nome del dipartimento è obbligatorio")
    @Size(max = 60, message = "Il nome non può superare i 60 caratteri")
    @Column(nullable = false, unique = true, length = 60)
    private String nome;

    @NotBlank(message = "La sede è obbligatoria")
    @Column(nullable = false, length = 80)
    private String sede;

    @JsonIgnore
    @OneToMany(mappedBy = "dipartimento", cascade = CascadeType.ALL)
    private List<Dipendente> dipendenti = new ArrayList<>();

    public Dipartimento() {}

    public Dipartimento(String nome, String sede) {
        this.nome = nome;
        this.sede = sede;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }

    public List<Dipendente> getDipendenti() { return dipendenti; }
    public void setDipendenti(List<Dipendente> dipendenti) { this.dipendenti = dipendenti; }
}