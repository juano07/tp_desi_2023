let idToDelete;

const modifyBtn = document.querySelector("#modifyBtn");

modifyBtn.addEventListener("click", function (e){
	
	e.preventDefault();
	
	const updatedData = {
		idAvion: document.getElementById("editId").value,
		modelo: document.getElementById("editModelo").value,
		aerolinea: document.getElementById("editAerolinea").value,
		filas: document.getElementById("editFilas").value,
		asientosXFila: document.getElementById("editAsientosXFila").value
	}
    
    const jsonString = JSON.stringify(updatedData);
    
    let url = "http://localhost:8080/aviones/" + updatedData.idAvion;

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
     
	    	window.location = '/aviones';
	  })
	  .catch(error => {		  
		  
	    	console.log("Error al editar avión - " + error);    
	  });    
});

const deleteConfirmationBtn = document.getElementById("deleteConfirmationBtn");

deleteConfirmationBtn.addEventListener("click", function(e) {
	
	e.preventDefault();
	
    let url = "http://localhost:8080/aviones/" + idToDelete;

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
     
	    	window.location = '/aviones';
	  })
	  .catch(error => {
		  
			console.log("Error al borrar avión - " + error);    
	  });
});

function editObject(element) {
	
	const idSeleccionado = element.parentNode.parentNode.id;

	let avionSeleccionado = document.getElementById(idSeleccionado).getElementsByTagName("td");

	document.getElementById("editModelo").value = avionSeleccionado[0].innerHTML;
	
	document.getElementById("editAerolinea").value = avionSeleccionado[1].innerHTML;
	
	document.getElementById("editFilas").value = avionSeleccionado[2].innerHTML;
	
	document.getElementById("editAsientosXFila").value = avionSeleccionado[3].innerHTML;
	
	document.getElementById("editId").value = idSeleccionado;
};


function searchAvion() {	
	
	let input, tr, td, i, txtValue;
	
	input = document.querySelector("#inputModelo");
	
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
