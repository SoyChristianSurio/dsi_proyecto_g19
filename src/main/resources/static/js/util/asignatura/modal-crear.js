

function activarCiclo() {
    var id = document.getElementById("cicloIdHidden").value;
    window.location = "/ciclo/activar/"+ id;
}
            
function desactivarCiclo() {
	var id = document.getElementById("cicloIdHidden").value;
    window.location = "/ciclo/desactivar/"+ id;
} 

function eliminarCiclo() {
	var id = document.getElementById("cicloIdHidden").value;
    window.location = "/ciclo/eliminar/"+ id;
} 



function preConfirmarAccion(id){
	document.getElementById("MateriaHidden").value = id;
}
function confirmarAction(accion) {
    var id = document.getElementById("MateriaHidden").value ;
    
    switch (accion) {
    	
    case 'activar':
    	window.location = "/asignatura/activar/"+ id;        
        break;
        
    case 'desactivar': 	
    	window.location = "/asignatura/desactivar/"+ id;
        break;
        
    case 'eliminar': 	
    	window.location = "/asignatura/eliminar/"+ id;
        break;
    }    
}