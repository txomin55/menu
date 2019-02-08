package com.txomin.menu.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.txomin.dto.EleccionDTO;
import com.txomin.dto.SeccionDTO;
import com.txomin.menu.service.MenuService;

@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping(value="/api/menu")
	public List<SeccionDTO> menu() throws FileNotFoundException {
		
		return menuService.recuperarMenu();
	}
	
	@PostMapping(value="/api/menu/eleccion")
	public void makeChoice(@RequestBody EleccionDTO eleccion) throws IOException {
		
		menuService.escribirEnFichero(eleccion);
	}
	
}
