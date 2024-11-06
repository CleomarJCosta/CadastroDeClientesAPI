package com.app.cadastroClientesAPI.Domain.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String rua;

    @Column
    private String cidade;

    @Column
    private String estado;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL)
    private Cliente cliente;
}
