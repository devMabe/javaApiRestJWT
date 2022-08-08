package com.apijava.demoapi.dao;

import java.util.List;

import com.apijava.demoapi.model.Usuario;

public interface UsuarioDao {
    List<Usuario> getUsuarios();
    List<Usuario> getUsuario(Long id);
    void eliminar(int id);

    void registrar(Usuario usuario);

    Usuario obtenerUserPasswordCredencial(Usuario usuario);
}
