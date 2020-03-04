package org.sid.lightecom.dao;

import org.sid.lightecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface ProduitRepository extends JpaRepository<Product,Long> { /*JpaRepository est une interface générique */
    @RestResource(path = "selectedProducts")
    public List<Product> findBySelectedIsTrue(); /* afficher les produits qui ont selected=true */
    @RestResource(path = "productsByKeyword")
    public List<Product> findByNameContains(@Param("mc") String mc);
    /* on peut utiliser @Query("select p from Product p where p.name like :x")
    public List<Product> chercher(@Param("x") String x); */
    @RestResource(path = "promoProducts")
    public List<Product> findByPromotionIsTrue();
    @RestResource(path = "dispoProducts")
    public List<Product> findByAvailableIsTrue();
}
