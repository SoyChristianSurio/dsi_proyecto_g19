
function confirmAction(id) {
	document.getElementById("departamentoIdHidden").value = id;
	}

function deleteDepartamento() {
    var id = document.getElementById("departamentoIdHidden").value;
    window.location = "/departamento/eliminar/"+ id;
    }