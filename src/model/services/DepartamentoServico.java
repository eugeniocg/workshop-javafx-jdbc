package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Departamento;

public class DepartamentoServico {

	public List<Departamento> findAll(){
		//Mocando os Dados
		List<Departamento> lista = new ArrayList<>();
		lista.add(new Departamento(1, "Livros"));
		lista.add(new Departamento(2, "Computadores"));
		lista.add(new Departamento(3, "Eletronicos"));
		lista.add(new Departamento(4, "Acessórios"));
		return lista;
	}
}
