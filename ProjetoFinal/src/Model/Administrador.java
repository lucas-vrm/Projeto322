package Model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {

	public Administrador(String nome, String contato, String email, int id) {
		super(nome, contato, email, id);
	}
	
	

}
