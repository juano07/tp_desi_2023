let idToDelete;

const modifyBtn = document.querySelector("#modifyBtn");

modifyBtn.addEventListener("click", function (e){
	
	e.preventDefault();
	
	const updatedData = {
		idCiudad: document.getElementById("editId").value,
		nombreCiudad: document.getElementById("editNombreCiudad").value,
		codigoPostal: document.getElementById("editCodigoPostal").value
	}
    
    const jsonString = JSON.stringify(updatedData);
    
    let url = "http://localhost:8080/ciudades/" + updatedData.idCiudad;

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
     
	    	window.location = '/ciudades';
	  })
	  .catch(error => {		  
		  
	    	console.log("Error al editar ciudad - " + error);    
	  });    
});

const deleteConfirmationBtn = document.getElementById("deleteConfirmationBtn");

deleteConfirmationBtn.addEventListener("click", function(e) {
	
	e.preventDefault();
	
    let url = "http://localhost:8080/ciudades/" + idToDelete;

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
     
	    	window.location = '/ciudades';
	  })
	  .catch(error => {
		  
			console.log("Error al borrar ciudad - " + error);    
	  });
});

function editObject(element) {
	
	const idSeleccionado = element.parentNode.parentNode.id;

	let ciudadSeleccionada = document.getElementById(idSeleccionado).getElementsByTagName("td");

	document.getElementById("editNombreCiudad").value = ciudadSeleccionada[0].innerHTML;
	
	document.getElementById("editCodigoPostal").value = ciudadSeleccionada[1].innerHTML;
	
	document.getElementById("editId").value = idSeleccionado;
};


function searchCiudad() {	
	
	let input, tr, td, i, txtValue;
	
	input = document.querySelector("#inputNombreCiudad");
	
	tr = document.getElementsByTagName("tr");
	
	for (i = 0; i < tr.length; i++) {
		
	  td = tr[i].getElementsByTagName("td")[0];
	  
		  if (td) {
			  
		    txtValue = td.textContent || td.innerText;
		    
		    if (txtValue.toLowerCase().indexOf(input.value.toLowerCase()) > -1) {
				
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
	
	idToDelete = element.parentNode.parentNode.id;
};