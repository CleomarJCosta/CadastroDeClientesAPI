package com.app.cadastroClientesAPI.Domain.Repository;

import com.app.cadastroClientesAPI.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUserByEmail(String email);
}
