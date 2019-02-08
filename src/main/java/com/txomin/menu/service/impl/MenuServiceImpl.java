package com.txomin.menu.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.txomin.dto.EleccionDTO;
import com.txomin.dto.PlatoDTO;
import com.txomin.dto.SeccionDTO;
import com.txomin.menu.service.MenuService;

@Component
public class MenuServiceImpl implements MenuService{

	@Override
	public void escribirEnFichero(EleccionDTO eleccion) throws IOException {
		String homePath = System.getProperty("user.home");
		String title = homePath + "//menu//elecciones//" + eleccion.getIdentificador() + ".txt";
	 
	    BufferedWriter writer = new BufferedWriter(new FileWriter(title));
	    writer.write(eleccion.getPlatos());
	     
	    writer.close();
	}

	@Override
	public List<SeccionDTO> recuperarMenu() throws FileNotFoundException {
List<SeccionDTO> secciones = new ArrayList<SeccionDTO>();
		
		String homePath = System.getProperty("user.home");
		
		Scanner scanner = new Scanner(new File(homePath + "//menu//fichero.txt"));  
		while (scanner.hasNextLine()) {  
			String seccionString = scanner.nextLine();
			
			String[] detallesSeccion = seccionString.split(">");
			SeccionDTO seccionDTO = new SeccionDTO();
			seccionDTO.setNombre(detallesSeccion[0]);
			
			String[] platosString = detallesSeccion[1].split("\\*");
			Stream<String> platosStream = Arrays.stream(platosString);

			List<PlatoDTO> platos = new ArrayList<PlatoDTO>();
			platosStream.forEach(p -> {
				if(!p.equals("")) {
					
					String[] detallesPlato = p.split(":");
					
					List<Integer> alergenos = Arrays.stream(detallesPlato[3].split(","))
							.filter(v -> !v.equals(""))
							.map(a -> Integer.valueOf(a))
							.collect(Collectors.toList());
					
					platos.add(new PlatoDTO(Integer.valueOf(detallesPlato[0]), detallesPlato[1], Integer.valueOf(detallesPlato[2]), alergenos, detallesPlato[4]));
				}
			});
			
			seccionDTO.setPlatos(platos);
			secciones.add(seccionDTO);
		}
		scanner.close();
		
		return secciones;
	}

}
