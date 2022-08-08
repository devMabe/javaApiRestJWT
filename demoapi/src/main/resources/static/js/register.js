// Call the dataTables jQuery plugin
$(document).ready(function() {
  
    //onready
  });
  
  
  async function agregarUsuarios() {
    
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.user = document.getElementById('txtUser').value;
    datos.password = document.getElementById('InputPassword').value;
    
    let repetirpassword = document.getElementById('RepeatPassword').value;

    if(!(datos.nombre !== '' && datos.user !== '' && datos.password!==''  && repetirpassword !==''))
    {
        alert("No se pueden dejar  campos vacios");
        return;
    }

    if (repetirpassword !== datos.password) {
        alert("La contraseña que escribiste no coinciden");
        return;
    }
    
      const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
        
      });
      
      alert('Cuenta creada con exito');
      window.location.href = 'login.html';
    
  
  }
  
  