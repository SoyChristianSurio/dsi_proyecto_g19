
function confirmAction(id) {
	document.getElementById("materiaIdHidden").value = id;
	}

function deleteMateria() {
    var id = document.getElementById("materiaIdHidden").value;
    window.location = "/materia/eliminar/"+ id;
    }