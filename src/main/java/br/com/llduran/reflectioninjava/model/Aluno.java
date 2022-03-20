package br.com.llduran.reflectioninjava.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Data
public class Aluno
{
	private int codigo;
	private String nome;
	private String numMatricula;
	private LocalDate dataMatricula;
	private ArrayList<Double> notas;
	private double valor;

	public void addNota(double nota)
	{
		if (notas == null) {notas = new ArrayList<>();}
		notas.add(nota);
	}

	public double calcNotaFinal()
	{
		double notaFinal = 0;
		for(Double nota : notas)
		{
			notaFinal += nota;
		}

		return notaFinal;
	}

	public void imprimirDados()
	{
		String strDataMatricula = dataMatricula == null ? null : dataMatricula.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

		System.out.println("Dados do objeto: ");
		System.out.println("Objeto aluno(\n"+
				"codigo= " + codigo + "\n" +
				"nome= " + nome + "\n" +
				"numMatricula= " + numMatricula + "\n" +
				"dataMatricula= " + strDataMatricula + "\n" +
				"notas= " + notas + "\n" +
				"valor= " + valor + "\n" +
				")");
		System.out.println("Nota final: " + calcNotaFinal());
	}
}
