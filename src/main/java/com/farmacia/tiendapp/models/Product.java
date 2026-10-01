package com.farmacia.tiendapp.models;


import jakarta.persistence.*;


@Entity
@Table(name = "products")
public class Product {

    private Long id;

    private  String nombre;

    private String barCode;

    private int cantidad;

}
