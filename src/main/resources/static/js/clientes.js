let dniToDelete;

const modifyBtn = document.querySelector("#modifyBtn");

modifyBtn.addEventListener("click", function (e) {
	
	e.preventDefault();
	
	const updatedData = {
		apellidoNombre: document.getElementById("editApellidoNombre").value,
		dni: document.getElementById("editDni").value,
		domicilio: document.getElementById("editDomicilio").value,
		email: document.getElementById("editEmail").value,
		fechaNacimiento: document.getElementById("editFechaNacimiento").value,
		numeroPasaporte: document.getElementById("editNumeroPasaporte").value
	}
    
    const jsonString = JSON.stringify(updatedData);
    
    let url = "http://localhost:8080/clientes/" + document.getElementById("editDni").value;

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
	      throw new Error(`Error HTTP - ${response.status}`);
	    }
	    
	    return response.json();
	  })
	  .then(() => {
		  
		 	const editModal = document.getElementById("editModal");
		 	
		 	document.body.classList.remove('modal-open');
		 	
		 	editModal.classList.remove('show');
		 	
      		editModal.setAttribute('aria-hidden', 'true');
      		
      		editModal.setAttribute('style', 'display: none');
			
			const modalsBackdrops = document.getElementsByClassName('modal-backdrop');

		     for(let i=0; i < modalsBackdrops.length; i++) 
		     {
		       document.body.removeChild(modalsBackdrops[i]);
		     }
     
	    	window.location = '/clientes';
	  })
	  .catch(error => {		  
		  
	    	console.log("Error al editar cliente - " + error);    
	  });    
});

const deleteConfirmationBtn = document.getElementById("deleteConfirmationBtn");

deleteConfirmationBtn.addEventListener("click", function(e) {
	
	e.preventDefault();
	
    let url = "http://localhost:8080/clientes/" + dniToDelete;

	const options = {
	  method: 'DELETE',
	  headers: {
	    'Content-Type': 'text/plain'
	  }
	};
    
    fetch(url, options)
	  .then(response => {
		  
	    if (!response.ok) 
	    {
	      throw new Error(`HTTP error ${response.status}`);
	    }
	    
	    return response;
	  })
	  .then(() => {
		  
		 	const deleteModal = document.getElementById("deleteModal");
		 	
		 	document.body.classList.remove('modal-open');
		 	
		 	deleteModal.classList.remove('show');
		 	
      		deleteModal.setAttribute('aria-hidden', 'true');
      		
      		deleteModal.setAttribute('style', 'display: none');
			
			const modalsBackdrops = document.getElementsByClassName('modal-backdrop');

		     for(let i=0; i < modalsBackdrops.length; i++) 
		     {
		       document.body.removeChild(modalsBackdrops[i]);
		     }
     
	    	window.location = '/clientes';
	  })
	  .catch(error => {
		  
			console.log("Error al borrar cliente - " + error);    
	  });
});

function editObject(element) {
	
	const dniSeleccionado = element.parentNode.parentNode.id;

	let clienteSeleccionado = document.getElementById(dniSeleccionado).getElementsByTagName("td");

	document.getElementById("editApellidoNombre").value = clienteSeleccionado[0].innerHTML;
	
	document.getElementById("editDni").value = clienteSeleccionado[1].innerHTML;
	
	document.getElementById("editDomicilio").value = clienteSeleccionado[2].innerHTML;
	
	document.getElementById("editEmail").value = clienteSeleccionado[3].innerHTML;
	
	document.getElementById("editFechaNacimiento").value = clienteSeleccionado[4].innerHTML;
	
	document.getElementById("editNumeroPasaporte").value = clienteSeleccionado[5].innerHTML;
};


function searchCliente() {	
	
	let input, tr, td, i, txtValue;
	
	input = document.querySelector("#inputDni");
	
	tr = document.getElementsByTagName("tr");
	
	for (i = 0; i < tr.length; i++) {
		
	  td = tr[i].getElementsByTagName("td")[1];
	  
		  if (td) {
			  
		    txtValue = td.textContent || td.innerText;
		    
		    if (txtValueindexOf(input.value) > -1) 
		    {				
		      tr[i].style.display = "";		      
		    } 
		    else 
		    {				
		      tr[i].style.display = "none";		      
		    }		    
		  }
	 }
};

function identifyObject (element) {
	
	dniToDelete = element.parentNode.parentNode.id;
};



