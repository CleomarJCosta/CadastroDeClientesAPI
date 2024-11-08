package com.app.cadastroClientesAPI.Service;

import com.app.cadastroClientesAPI.Domain.Entity.Usuario;
import com.app.cadastroClientesAPI.Domain.Repository.UsuarioRepository;
import com.app.cadastroClientesAPI.Exception.SenhaInvalidaException;
import com.app.cadastroClientesAPI.Exception.UserAlreadyExistsException;
import com.app.cadastroClientesAPI.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements  UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public Usuario registrar(Usuario usuario) throws UserAlreadyExistsException {
        Optional<Usuario> userOptional = Optional.ofNullable(usuarioRepository.findUserByEmail(usuario.getLogin())); // Buscar o usuário pelo email
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException( "Usuário já Existente : " + usuario.getLogin() );
        }else{
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        }

    }

    public UserDetails autenticar(Usuario usuario) throws SenhaInvalidaException {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
        if (senhasBatem) {
            return user;
        }
        throw new SenhaInvalidaException("Senha inválida.");
    }


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }


    public Optional<Usuario>  obterUsuarioPorId(Long id) throws UserNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return usuario;
        }
        throw new  UserNotFoundException("Usuario Inexistente.");
    }


    public void excluirUsuario(Long id) throws UserNotFoundException {
        // Obtem o login do usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginAutenticado = authentication.getName();

        // Obtenha o usuário a ser excluído pelo ID
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário Inexistente."));

        // Verifica se o login autenticado é o mesmo do usuário a ser excluído
        if (!usuario.getLogin().equals(loginAutenticado)) {
            throw new AccessDeniedException("Você só pode excluir seu próprio usuário.");
        }

        // Exclua o usuário se a verificação passar
        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findUserByEmail(email));
        if(usuario.isPresent()){
            String[] roles = new String[]{"USER"};

            return User
                    .builder()
                    .username(usuario.get().getLogin())
                    .password(usuario.get().getSenha())
                    .roles(roles)
                    .build();
        }else{
            throw new UsernameNotFoundException("Usuário não encontrado na base de dados.");
        }

    }

}
