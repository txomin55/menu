package com.txomin.menu.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.txomin.dto.EleccionDTO;
import com.txomin.dto.SeccionDTO;

@Service
public interface MenuService {

	void escribirEnFichero(EleccionDTO eleccion) throws IOException;

	List<SeccionDTO> recuperarMenu() throws FileNotFoundException;
}
