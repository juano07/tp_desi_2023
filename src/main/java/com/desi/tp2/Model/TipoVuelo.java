package com.desi.tp2.Model;

import java.util.ArrayList;
import java.util.List;

public enum TipoVuelo {
	NACIONAL, 
	INTERNACIONAL;
	
	public static List<String> getListaTiposVuelo()
    {
		List<String> listaTiposVuelo = new ArrayList<>();
		
        for(TipoVuelo tipoVuelo : TipoVuelo.values())
        {
        	listaTiposVuelo.add(tipoVuelo.toString());
        }
        
        return listaTiposVuelo;
    }
}
