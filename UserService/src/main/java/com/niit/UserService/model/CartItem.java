package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CartItem {

    @Id
    private String email;
    List<Dishes> dishesList;

    public void addDishes(Dishes dishes1) {

        if (Objects.isNull(dishesList)) {
            dishesList = new ArrayList<>();
        }
        dishesList.add(dishes1);
    }
}