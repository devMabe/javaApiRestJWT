package com.apijava.demoapi.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apijava.demoapi.dao.UsuarioDao;
import com.apijava.demoapi.model.Usuario;
import com.apijava.demoapi.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader (value = "Autorization") String token){

        if (!validarToken(token)) {return null;}

        return  usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId!=null;
    }


    @RequestMapping(value = "api/usuarios" , method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuario/{id}")
    public List<Usuario> getUsuario(@RequestHeader (value = "Autorization") String token,@PathVariable Long id){
      
        if (!validarToken(token)) {return null;}

        return  usuarioDao.getUsuario(id);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@RequestHeader (value = "Autorization") String token,@PathVariable int id){
      
        usuarioDao.eliminar(id);
        
    }

    @RequestMapping( value = "api/update/usuario")
    public List<String> updateUsuario(@RequestHeader (value = "Autorization") String token){
        return List.of("Mango", "Tomate", "Cebolla", "Lenteja");
    }
}
