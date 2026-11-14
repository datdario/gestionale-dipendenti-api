package com.dario_mazzarella.gestionale_dipendenti.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "dipendenti")
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String cognome;

    @NotBlank(message = "L'email aziendale è obbligatoria")
    @Email(message = "Formato email non valido")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Il ruolo è obbligatorio")
    @Size(max = 40)
    @Column(nullable = false, length = 40)
    private String ruolo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dipartimento_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Dipartimento dipartimento;

    public Dipendente() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }

    public Dipartimento getDipartimento() { return dipartimento; }
    public void setDipartimento(Dipartimento dipartimento) { this.dipartimento = dipartimento; }
}