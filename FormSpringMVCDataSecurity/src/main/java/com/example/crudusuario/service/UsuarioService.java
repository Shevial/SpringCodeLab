package com.example.crudusuario.service;

import com.example.crudusuario.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.crudusuario.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService{
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        System.out.println(usuario.toString());
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // Asegúrate de que esté encriptada
                .roles(usuario.getRole()) // Asegúrate de que los roles estén correctamente configurados
                .build();
    }

    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Encriptar la contraseña antes de guardarla
        return usuarioRepository.save(usuario);
    }
}

