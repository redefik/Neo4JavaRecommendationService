package it.viglietta.federico.Neo4JavaRecommendationService.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String externalId;
    private String description;
}
