package br.com.llduran.reflectioninjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product
{
    private long id;
    private String description;
    private Double price;
    private int quantity;
}
