package com.mmashyr.utils;

import com.mmashyr.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anton on 19.01.2017.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private Map<Product, Integer> salePositions = new HashMap<>();
    private double totalPrice;

    public ShoppingCart() {
    }

    public Map<Product, Integer> getSalePositions() {
        return salePositions;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void countPrice() {
        long price = 0;
        for (Product product : salePositions.keySet()) {
            price+= product.getPrice()
                    .multiply(new BigDecimal(salePositions.get(product))).doubleValue();
        }
       this.totalPrice = price;
    }

    public void addProduct(Product product, int numberToAdd) {
        if (salePositions.containsKey(product)) {
            int currentAmountOfProduct = salePositions.get(product);
            salePositions.put(product, currentAmountOfProduct + numberToAdd);
        } else {
            salePositions.put(product, numberToAdd);
        }
        this.countPrice();
    }

    public void removeProduct(Product product, int numberToRemove) {
        int currentAmountOfProduct = salePositions.get(product);
        if (currentAmountOfProduct <= numberToRemove) {
            salePositions.remove(product);
        } else {
            salePositions.put(product, currentAmountOfProduct - numberToRemove);
        }
        this.countPrice();
    }

    public void removeAll() {
        salePositions.clear();
        this.countPrice();
    }
}

