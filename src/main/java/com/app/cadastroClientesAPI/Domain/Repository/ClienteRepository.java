package com.app.cadastroClientesAPI.Domain.Repository;

import com.app.cadastroClientesAPI.Domain.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    @Query("SELECT c FROM Cliente c ORDER BY c.nome")
    List<Cliente> findAllOrderByNome();

    @Query("SELECT c FROM Cliente c WHERE c.endereco.estado = :estado")
    List<Cliente> findByEstado(@Param("estado") String estado);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    Cliente findClienteByEmail(@Param("email") String email);


}
