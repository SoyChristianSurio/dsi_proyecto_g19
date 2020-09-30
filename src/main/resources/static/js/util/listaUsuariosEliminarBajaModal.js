
function confirmAction(id,activo) {
                document.getElementById("userIdHidden").value = id;
                document.getElementById("userActivoHidden").value = activo;
                
                if (activo=="true") {
                	
                    document.getElementById("btnBaja").innerHTML="Dar de Baja";
                    document.getElementById("bajaModalTitle").innerHTML="Confirmar Baja";
                    document.getElementById("bajaModalBody").innerHTML="¿Desea dar de baja al usuario?";
                    document.getElementById("btnBaja").setAttribute("class", "btn btn-warning");
                } 
                else {
                	
                    document.getElementById("btnBaja").innerHTML="Activar";
                    document.getElementById("bajaModalTitle").innerHTML="Confirmar activación del usuario";
                    document.getElementById("bajaModalBody").innerHTML="¿Desea activar al usuario?";
                    document.getElementById("btnBaja").setAttribute("class", "btn btn-primary");
                }    
            }

            function deleteUser() {
                var id = document.getElementById("userIdHidden").value;
                window.location = "/usuario/eliminar/"+ id;
            }
            
            function lockUser() {
                var id = document.getElementById("userIdHidden").value;
                var activo = document.getElementById("userActivoHidden").value;
                   window.location = "/usuario/cambiarEstado/"+id+"/"+activo;
            } 