package com.example.demo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Data
@Getter
@Setter
public class Pizza {
    String type;
    String size;
    ArrayList<String > toppings;
}
