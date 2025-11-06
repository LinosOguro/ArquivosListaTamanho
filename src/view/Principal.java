package view;

import java.util.List;

import controller.IListaController;
import controller.ListaController;

public class Principal {

	public static void main(String[] args) {
		
		IListaController lisCont = new ListaController();
		String dir = "C:\\Windows";
		
		try {
			List<String> result = lisCont.readDir(dir);
			for (String linha : result) {
				System.out.println(linha);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
