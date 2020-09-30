//Código para Datables

//$('#example').DataTable(); //Para inicializar datatables de la manera más simple

$(document).ready(function() {    
    $('#tablaUsuarios').DataTable({
    //para cambiar el lenguaje a español
        "language": {
                "lengthMenu": "_MENU_ registros por pág",
                "zeroRecords": "No se encontraron resultados",
                "info": "registros del _START_ al _END_ de _TOTAL_ registros",
                "infoEmpty": " ",
                "infoFiltered": "( _MAX_ registros filtrados)",
                "sSearch": "Buscar:",
                "oPaginate": {
                    "sFirst": "<<",
                    "sLast":">>",
                    "sNext":">",
                    "sPrevious": "<"
			     },
		"sProcessing":"Procesando...",
            }
    });     
});