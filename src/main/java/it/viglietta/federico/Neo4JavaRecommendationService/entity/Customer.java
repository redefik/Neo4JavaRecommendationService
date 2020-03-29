package it.viglietta.federico.Neo4JavaRecommendationService.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import java.util.ArrayList;
import java.util.List;

@Data
@NodeEntity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer born;

    @Relationship(type = "PURCHASED")
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }
}
