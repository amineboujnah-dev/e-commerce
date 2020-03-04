package org.sid.lightecom.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Product implements Serializable /* Serializable : les attrtibuts en format JSON */{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double currentPrice;
    private boolean promotion; /* pour vérifier si le produit est en promotion */
    private boolean selected; /* pour afficher le produit sélectionné */
    private boolean available;
    private String photoName;
    @Transient /* Transient : quantite n'est pas dans la base de données */
    private int quantity=1;
    @ManyToOne
    private Category category;


}
