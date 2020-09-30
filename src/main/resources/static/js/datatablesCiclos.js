//Código para Datables

//$('#example').DataTable(); //Para inicializar datatables de la manera más simple

$(document).ready(function() {    
    $('#cilosDataTables').DataTable({
    	ordering: false,
    	paging: false,
    //para cambiar el lenguaje a español
        "language": {
                "lengthMenu": "ver _MENU_",
                "zeroRecords": "No encontrado",
                "info": "",//"_START_ al _END_ de _TOTAL_ pág",
                "infoEmpty": "-",
                "infoFiltered": "(filtrado _MAX_ regs)",
                "sSearch": "",
                searchPlaceholder: "buscar",
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