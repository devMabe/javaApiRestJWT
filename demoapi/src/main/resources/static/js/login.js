// Call the dataTables jQuery plugin
$(document).ready(function() {
  
    //onready
  });
  
  
  async function iniciarSesion() {
    
    let datos = {};
    
    datos.user = document.getElementById('txtUserLogin').value;
    datos.password = document.getElementById('txtPassLogin').value;
    
   

    if(!( datos.user !== '' && datos.password!=='' ))
    {
        alert("No se pueden dejar  campos vacios");
        return;
    }

 
    
      const request = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
        
      });
  
      const respuesta = await request.text();

      if(respuesta !== 'FAIL'){
        localStorage.token = respuesta;
        localStorage.user = datos.user;
        window.location.href = 'usuarios.html';
      }else{
        alert('Las credenciales son incorrectas. Intentelo nuevamente');
      }

  
  }


  
  