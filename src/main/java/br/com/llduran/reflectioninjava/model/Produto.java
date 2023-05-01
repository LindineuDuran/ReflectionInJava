package br.com.llduran.reflectioninjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Classe para ser utilizada como base
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto
{
    private String nome;
    private String categoria;
    private Double preco;
    private String descricao;
}
