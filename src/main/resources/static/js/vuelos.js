let idToDelete;

const modifyBtn = document.querySelector("#modifyBtn");

modifyBtn.addEventListener("click", function (e){
	
	e.preventDefault();
	
	const updatedData = {
		idVuelo: document.getElementById("editId").value,
		ciudadOrigen: document.getElementById("editCiudadOrigen").value,
		ciudadDestino: document.getElementById("editCiudadDestino").value,
		tipoVuelo: document.getElementById("editTipoVuelo").value,
		precioVuelo: document.getElementById("editPrecio").value,
		fechayhora: document.getElementById("editFechayhora").value,
		avion: document.getElementById("editAvion").value,
		estadoVuelo: document.getElementById("editEstadoVuelo").value
	}
    
    const jsonString = JSON.stringify(updatedData);
    
    let url = "http://localhost:8080/vuelos/" + updatedData.idVuelo;

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
     
	    	window.location = '/vuelos';
	  })
	  .catch(error => {		  
   
	  });    
});

const deleteConfirmationBtn = document.getElementById("deleteConfirmationBtn");

deleteConfirmationBtn.addEventListener("click", function(e) {
	
	e.preventDefault();
	
    let url = "http://localhost:8080/vuelos/" + idToDelete;

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
	      	closeModal("createModal");
     		
	    	showFlashMessage("dataTable", "danger", response.status);
	    	
	    	throw Error();
	    }
	    
	    return response;
	  })
	  .then(() => {
		  
		 	closeModal("deleteModal");
     
	    	window.location = '/vuelos';
	  })
	  .catch(error => {
	
	  });
});

function editObject(element) {
	
	const idSeleccionado = element.parentNode.parentNode.id;

	let vueloSeleccionado = document.getElementById(idSeleccionado).getElementsByTagName("td");

	selectElementByIndex("editCiudadOrigen", vueloSeleccionado[0].innerHTML);
	
	selectElementByIndex("editCiudadDestino", vueloSeleccionado[1].innerHTML);
	
	selectElementByValue("editTipoVuelo", vueloSeleccionado[2].innerHTML);	
	
	document.getElementById("editFechayhora").value = vueloSeleccionado[3].innerHTML;
	
	selectElementByIndex("editAvion", vueloSeleccionado[4].innerHTML);
	
	selectElementByValue("editEstadoVuelo", vueloSeleccionado[5].innerHTML);
	
	document.getElementById("editPrecio").value = vueloSeleccionado[6].innerHTML;
	
	document.getElementById("editId").value = idSeleccionado;
};


function searchVuelo() {	
	
	let input, tr, td, i, txtValue;
	
	input = document.querySelector("#inputDestino");
	
	tr = document.getElementsByTagName("tr");
	
	for (i = 0; i < tr.length; i++) {
		
	  td = tr[i].getElementsByTagName("td")[1];
	  
		  if (td) {
			  
		    txtValue = td.textContent || td.innerText;
		    
		    if (txtValue.toLowerCase().indexOf(input.value.toLowerCase()) > -1) 
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

function filterVueloByFecha() {	
	
	let input, tr, td, i, txtValue;
	
	input = document.querySelector("#inputFecha");
	
	tr = document.getElementsByTagName("tr");
	
	for (i = 0; i < tr.length; i++) {
		
	  td = tr[i].getElementsByTagName("td")[3];
	  
		  if (td) {
			  
		    txtValue = new Date(td.textContent || td.innerText).toISOString().split("T")[0];
		    
		    if (txtValue == input.value || input.value == "")
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

const saveBtn = document.querySelector("#saveBtn");

saveBtn.addEventListener("click", function (e){
	
	e.preventDefault();
	
	const createdData = {
		ciudadOrigen: document.getElementById("ciudadOrigen").value,
		ciudadDestino: document.getElementById("ciudadDestino").value,
		tipoVuelo: document.getElementById("tipoVuelo").value,
		precioVuelo: document.getElementById("precioVuelo").value,
		fechayhora: document.getElementById("fechayhora").value,
		avion: document.getElementById("avion").value,
		estadoVuelo: document.getElementById("estadoVuelo").value
	}
    
    const jsonString = JSON.stringify(createdData);
    
    let url = "http://localhost:8080/vuelos";

	const options = {
	  method: 'POST',
	  headers: {
	    'Content-Type': 'application/json'
	  },
	  body: jsonString
	};
    
    fetch(url, options)
	  .then(async response => {
		  
	    if (!response.ok) 
	    {
	    	closeModal("createModal");
	    	
	    	let text = await new Response(response.body).text();
     		
	    	showFlashMessage("dataTable", "danger", text);	 
	    	
	    	throw Error(text); 	
	    }
	    
	    return response;
	  })
	  .then(() => {
		  
		  	closeModal("createModal");
     
	    	window.location = '/vuelos';
	  })
	  .catch(e => {		  
  		    console.log(e);
	  });    
});

function identifyObject (element) {
	
	idToDelete = element.parentNode.parentNode.id;
};

function selectElementByValue(id, valueToSelect) {  
	  
    let element = document.getElementById(id);
    
    element.value = valueToSelect;
}

function selectElementByIndex(id, valueToSelect){
	
	let element = document.getElementById(id);
	
	let index = 0;
	
	for (let i = 0; i < element.options.length; i++){
		
	  if (element.options[i].label == valueToSelect){
		  
	    index = i;
	    
	    break;
	  }	  
	}
	
	element.selectedIndex = index;
}

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

function showFlashMessage(refElementId, messageType, flashMessage) {
	
	const refElement = document.getElementById(refElementId);
	
	refElement.insertAdjacentHTML('beforebegin', `<div class="alert alert-${messageType}" role="alert">
	        <span id="spanAlert">${flashMessage}</span>
	    </div>`);	
}