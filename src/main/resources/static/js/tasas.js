const modifyBtn = document.querySelector("#modifyBtn");

modifyBtn.addEventListener("click", function (e){
	
	e.preventDefault();
	
	const updatedData = {
		idTasa: document.getElementById("editId").value,
		nombreCP: document.getElementById("editNombre").value,
		precioCP: document.getElementById("editPrecio").value,
		iva: document.getElementById("editIVA").value,
		tasaNacional: document.getElementById("editTAN").value,
		tasaInternacional: document.getElementById("editTAI").value,
		cotizacionUSD: document.getElementById("editCotizacion").value,
	}
    
    const jsonString = JSON.stringify(updatedData);
    
    let url = "http://localhost:8080/tasas/" + updatedData.idTasa;

	const options = {
	  method: 'PUT',
	  headers: {
	    'Content-Type': 'application/json'
	  },
	  body: jsonString
	};
    
    fetch(url, options)
	  .then(response => {
		  
	    if (!response.ok) 
	    {
			closeModal("editModal");
     		
	    	showFlashMessage("dataTable", "danger", response.status);
	    	
	    	throw Error();
	    }
	    
	    return response;
	  })
	  .then(() => {
		  
		  	closeModal("editModal");
     
	    	window.location = '/tasas';
	  })
	  .catch(error => {		  
   
	  });    
});

function editObject(element) {
	
	const idSeleccionado = element.parentNode.parentNode.id;

	let tasaSeleccionada = document.getElementById(idSeleccionado).getElementsByTagName("td");

	document.getElementById("editNombre").value = tasaSeleccionada[0].innerHTML;
	
	document.getElementById("editPrecio").value = tasaSeleccionada[1].innerHTML;
	
	document.getElementById("editIVA").value = tasaSeleccionada[2].innerHTML;	
	
	document.getElementById("editTAN").value = tasaSeleccionada[3].innerHTML;
	
	document.getElementById("editTAI").value = tasaSeleccionada[4].innerHTML;
	
	document.getElementById("editCotizacion").value = tasaSeleccionada[5].innerHTML;
	
	document.getElementById("editId").value = idSeleccionado;
};

function closeModal(modalId) {
	
	const createModal = document.getElementById(modalId);
	const body = document.body;
		 	
 	createModal.classList.remove('show');
 	
 	document.body.classList.remove('modal-open');
 	
	createModal.setAttribute('aria-hidden', 'true');
	
	createModal.setAttribute('style', 'display: none');
	
	const modalsBackdrops = document.getElementsByClassName('modal-backdrop');

    for(let i=0; i < modalsBackdrops.length; i++) 
	{
		document.body.removeChild(modalsBackdrops[i]);
	}
}