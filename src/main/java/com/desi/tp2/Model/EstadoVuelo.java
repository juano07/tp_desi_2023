package com.desi.tp2.Model;

import java.util.ArrayList;
import java.util.List;

public enum EstadoVuelo {
	NORMAL, 
	REPROGRAMADO, 
	CANCELADO;
	
	public static List<String> getListaEstados()
    {
		List<String> listaEstados = new ArrayList<>();
		
        for(EstadoVuelo estado : EstadoVuelo.values())
        {
        	listaEstados.add(estado.toString());
        }
        
        return listaEstados;
    }
}
