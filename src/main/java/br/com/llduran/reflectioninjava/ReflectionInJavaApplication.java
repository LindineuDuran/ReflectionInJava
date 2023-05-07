package br.com.llduran.reflectioninjava;

import br.com.llduran.reflectioninjava.model.Aluno;
import br.com.llduran.reflectioninjava.model.Person;
import br.com.llduran.reflectioninjava.model.Product;
import br.com.llduran.reflectioninjava.model.Produto;
import br.com.llduran.reflectioninjava.service.ReflectionMapper;
import br.com.llduran.reflectioninjava.service.Reflexao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Map;

@SpringBootApplication
public class ReflectionInJavaApplication implements CommandLineRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(ReflectionInJavaApplication.class, args);
	}

	@Override
	public void run(String... args)
			throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException,
			InvocationTargetException, InstantiationException, NoSuchFieldException
	{
		System.out.println("=================================Testa ReflectionMapper de Atributos=================================");
		testaReflectionMapper();

		System.out.println("");

		System.out.println("=================================Testa Reflection=================================");
		testaReflection();

		System.out.println("");

		System.out.println("=======================Instancia Objeto em Tempo deExecucao=======================");
		instanciaObjetoEmTempoExecucao();
	}

	private void testaReflection() throws IllegalAccessException
	{
		Aluno aluno = new Aluno();
		aluno.setCodigo(1);
		aluno.setNome("Carlos");
		aluno.setNumMatricula("123456");
		aluno.setValor(500.55);
		aluno.addNota(10);
		aluno.addNota(5);

		//LocalDate dataMatricula = LocalDate.parse("24/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		/*
		Creating a LocalDate with Values in Java
		https://www.baeldung.com/java-creating-localdate-with-values
		*/
		LocalDate dataMatricula = LocalDate.of(2022, 01, 24);
		aluno.setDataMatricula(dataMatricula);

		aluno.imprimirDados();

		Reflexao.refletirObjeto(aluno, Aluno.class);
	}

	private void instanciaObjetoEmTempoExecucao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchFieldException
	{
		Class MinhaClasse = Class.forName("br.com.llduran.reflectioninjava.model.Aluno");
		Object meuObjeto = MinhaClasse.getConstructor().newInstance();

		Field f = meuObjeto.getClass().getDeclaredField("nome");
		f.setAccessible(true);
		f.set(meuObjeto, "Paulo");

		f = meuObjeto.getClass().getDeclaredField("codigo");
		f.setAccessible(true);
		f.set(meuObjeto, 9);

		f = meuObjeto.getClass().getDeclaredField("numMatricula");
		f.setAccessible(true);
		f.set(meuObjeto, "123456");

		f = meuObjeto.getClass().getDeclaredField("valor");
		f.setAccessible(true);
		f.set(meuObjeto, 500.55);

		/*
		Creating a LocalDate with Values in Java
		https://www.baeldung.com/java-creating-localdate-with-values
		*/
		LocalDate dataMatricula = LocalDate.of(2022, 01, 24);

		f = meuObjeto.getClass().getDeclaredField("dataMatricula");
		f.setAccessible(true);
		f.set(meuObjeto, dataMatricula);

		Class[] parametros = new Class[1];
		parametros[0] = double.class;
		Method m = meuObjeto.getClass().getDeclaredMethod("addNota", parametros[0]);
		m.invoke(meuObjeto, 99.9);
		m.invoke(meuObjeto, 80.5);
		m.invoke(meuObjeto, 70.0);

		m = meuObjeto.getClass().getDeclaredMethod("imprimirDados");
		m.invoke(meuObjeto);
	}

	private void testaReflectionMapper()
	{
		Person person = new Person();
		person.setId(1);
		person.setName("Gabriel");
		person.setLastName("Amorim");
		person.setAge(25);

		Map<String, Object> attributes = ReflectionMapper.getAttributesMap(person);

		for(String key : attributes.keySet()) {System.out.println(key + ": " + attributes.get(key));}

		System.out.println("");

		Product product = new Product();
		product.setId(1);
		product.setDescription("Oxford Dictionary");
		product.setPrice(11.90);
		product.setQuantity(1);

		attributes = ReflectionMapper.getAttributesMap(product);

		for(String key : attributes.keySet()) {System.out.println(key + ": " + attributes.get(key));}
	}
}
