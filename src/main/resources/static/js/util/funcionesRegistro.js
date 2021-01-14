function esEstudiante(){
	var e = document.getElementById('estudiante1')
	if (e.checked == true){
		document.getElementById('carnet').value = document.getElementById('username').value;
		
	}else{
		document.getElementById('carnet').value = "XX99999";
	}
}

function myFunction2(n) {
	var x = document.getElementById(n);
	x.value = x.value.replace(/ /g,'');
	esEstudiante();
}

function myFunction(n) {
	var x = document.getElementById(n);
	  x.value = x.value.toUpperCase();
}

