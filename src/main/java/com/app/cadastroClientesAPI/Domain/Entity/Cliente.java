package com.app.cadastroClientesAPI.Domain.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column
    private String criadoPor;

    @Column
    private String modificadoPor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataModificacao;

    @PrePersist
    public void onCreate() {
        this.dataCriacao = new Date();
        this.dataModificacao = new Date();
        this.criadoPor = getUsuarioAutenticado(); // Método para obter o usuário logado
        this.modificadoPor = getUsuarioAutenticado(); //Por default a primeira modificação é quem criou.
    }

    @PreUpdate
    public void onUpdate() {
        this.dataModificacao = new Date();
        this.modificadoPor = getUsuarioAutenticado(); // Método para obter o usuário logado
    }

    public String getUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : "Sistema";
    }


}
