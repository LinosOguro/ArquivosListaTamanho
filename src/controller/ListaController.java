package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import controller.IListaController;

public class ListaController implements IListaController{
	
	public ListaController() {
		super();
	}

	@Override
	public void readDir(String path) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					double MB = getDirSize(f);
						String linha = "DIR\t" + f.getName() + "  -  " + String.format("%.2f MB", MB);
						System.out.println(linha);
				} else if (files == null || files.length == 0) {
		            System.out.println("Nenhum arquivo encontrado neste diretório.");
				}
			}
		} else {
			throw new IOException("Diretório inválido");
		}
	}
	
	private double getDirSize(File dir) {
	    long sizeBytes = 0;
	    File[] files = dir.listFiles();
	    if (files != null) {
	        for (File f : files) {
	            if (f.isFile()) {
	                sizeBytes += f.length();
	            } else if (f.isDirectory()) {
	                sizeBytes += getDirSize(f) * 1024 * 1024; // converte MB de volta para bytes
	            }
	        }
	    }
	    return sizeBytes / (1024.0 * 1024.0);
	}
}
