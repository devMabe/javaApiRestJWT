package com.apijava.demoapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apijava.demoapi.dao.UsuarioDao;
import com.apijava.demoapi.model.Usuario;
import com.apijava.demoapi.utils.JWTUtil;

@RestController
public class AuthController {
    
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login" , method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
       
      Usuario userLogger  = usuarioDao.obtenerUserPasswordCredencial(usuario);

      if (usuario != null) {

        String tokenJWT = jwtUtil.create(String.valueOf(userLogger.getId()), usuario.getNombre());
        return tokenJWT;
      }

      return "FAIL";
    }
}
