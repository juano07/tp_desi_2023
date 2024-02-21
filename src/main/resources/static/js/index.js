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