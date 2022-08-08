package com.apijava.demoapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.apijava.demoapi.model.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return  entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(int id) {
       Usuario usuario = entityManager.find(Usuario.class, id);
       entityManager.remove(usuario); 
    }

    @Override
    public void registrar(Usuario usuario) {
       entityManager.merge(usuario); 
    }

    @Override
    public Usuario obtenerUserPasswordCredencial(Usuario usuario) {
        String query = "FROM Usuario WHERE user=:user";
        List<Usuario> lista =  entityManager.createQuery(query).setParameter("user", usuario.getUser()).getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();    

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if ( argon2.verify(passwordHashed, usuario.getPassword()) ){
            return lista.get(0);
        }
        return null;
    }

    @Override
    public List<Usuario> getUsuario(Long id) {
        
        String query = "FROM Usuario WHERE Id = :id";
        return  entityManager.createQuery(query).setParameter("id", id).getResultList();
    }
    
}
