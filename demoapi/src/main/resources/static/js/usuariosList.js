// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuariosList').DataTable();
});


async function cargarUsuarios() {
  
    const request = await fetch('api/usuarios', {
      method: 'GET',
      headers: getHeaders()
      
    });

    let listHTML = '';
    const usuarios = await request.json();
    
    usuarios.forEach(usuario => {
      let botonEliminar =  `<a href='#' onclick="eliminarUsuarios(${usuario.id})" class='btn btn-danger btn-circle btn-sm'><i class='fas fa-trash'></i></a>`;
      let usuarioHTML = `<tr><td>${usuario.id}</td> <td>${usuario.nombre}</td><td>${usuario.user}</td><td> ${botonEliminar} </td> </tr>`;

      listHTML += usuarioHTML;
    });
   

    document.querySelector('#usuariosList tbody').outerHTML = listHTML;


    console.log(usuarios);


}

function getHeaders (){
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Autorization': localStorage.token
  }
}

async function eliminarUsuarios(id) {

  if(!confirm('¿Desea eliminar este usuario?')){
    return;
  }
  
  const request = await fetch('api/usuarios/' + id, {
    method: 'DELETE',
    headers: getHeaders()
    
  });

  document.location.reload();
}