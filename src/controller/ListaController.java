package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.IListaController;

public class ListaController implements IListaController {

	public ListaController() {
		super();
	}

	@Override
	public List<String> readDir(String path) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			List<String> lista = new ArrayList<>();

			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
//					double MB = getDirSize(f);
						double MB = files[i].length();
						MB /= (1024 * 1024);
						String linha = files[i].getName() + "  -  " + String.format("%.2f MB", MB);
						lista.add(linha);
					}
				}
			}

			for (int i = 0; i < lista.size(); i++) {
				for (int j = i + 1; j < lista.size(); j++) {

					String s1 = lista.get(i);
					String s2 = lista.get(j);

					String[] p1 = s1.split(" - ");
					String[] p2 = s2.split(" - ");

					// Verifica se tem o tamanho no texto
					if (p1.length == 2 && p2.length == 2) {

						String n1 = p1[1].replace(" MB", "").replace(",", ".");
						String n2 = p2[1].replace(" MB", "").replace(",", ".");

						double t1 = 0;
						double t2 = 0;

						try {
							t1 = Double.parseDouble(n1);
							t2 = Double.parseDouble(n2);
						} catch (Exception e) {
							
						}

						if (t1 > t2) {
							String aux = lista.get(i);
							lista.set(i, lista.get(j));
							lista.set(j, aux);
						}
					}
				}
			}

			return lista;

		} else {
			throw new IOException("Diretório inválido");
		}
	}

	/*
	 * private double getDirSize(File dir) { long sizeBytes = 0; File[] files =
	 * dir.listFiles(); if (files != null) { for (File f : files) { if (f.isFile())
	 * { sizeBytes += f.length(); } else if (f.isDirectory()) { sizeBytes +=
	 * getDirSize(f); } } } return sizeBytes / (1024.0 * 1024.0); }
	 */
}
