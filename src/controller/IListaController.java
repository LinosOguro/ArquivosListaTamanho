package controller;

import java.io.IOException;
import java.util.List;

public interface IListaController {

	public List<String> readDir(String path) throws IOException;
}
