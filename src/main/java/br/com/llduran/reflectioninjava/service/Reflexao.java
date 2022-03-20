package br.com.llduran.reflectioninjava.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflexao
{
	public static void refletirObjeto(Object obj, Class classe) throws IllegalAccessException
	{
		System.out.println("===============================DADOS DA CLASSE===============================");
		exibeDadosClasse(classe);
		System.out.println("=============================ATRIBUTOS DA CLASSE=============================");
		exibeAtributosClasse(obj, classe, false);
		System.out.println("==============================METODOS DA CLASSE==============================");
		exibeMetodosClasse(classe);
	}

	private static void exibeDadosClasse(Class classe)
	{
		System.out.println("Nome da Classe: " + classe.getName());
		System.out.println("Nome (simples) da Classe: " + classe.getSimpleName());
		System.out.println("Nome Canonico da Classe: " + classe.getResource(classe.getName()));
		System.out.println("Tipo: " + classe.getTypeName());
	}

	private static void exibeAtributosClasse(Object obj, Class classe, boolean alteraAtributo)
	{
		Field[] atributos = classe.getDeclaredFields();
		Arrays.stream(atributos).forEach(a -> {
			try
			{
				a.setAccessible(true);

				if(alteraAtributo)
				{
					if(a.getType().isPrimitive()) { a.set(obj, 0); } else { a.set(obj, null);}
				}

				System.out.println(a.getName() + ": " + a.getType().getTypeName() +
						" ( primitivo: " + a.getType().isPrimitive() + ")" +
						" => valor: " + a.get(obj));
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		});
	}

	private static void exibeMetodosClasse(Class classe)
	{
		Method[] metodos = classe.getDeclaredMethods();
		Arrays.stream(metodos).forEach(m -> System.out.println(m.getName() + " - parametros: " + Arrays.toString(m.getParameterTypes()) +
				" - retorno: " + m.getReturnType().getSimpleName()));
	}
}
