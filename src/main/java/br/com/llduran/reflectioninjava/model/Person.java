package br.com.llduran.reflectioninjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person
{
    private long id;
    private String name;
    private String lastName;
    private int age;
}
