package org.sid.lightecom;

import net.bytebuddy.utility.RandomString;
import org.sid.lightecom.dao.CategoryRepository;
import org.sid.lightecom.dao.ProduitRepository;
import org.sid.lightecom.entities.Category;
import org.sid.lightecom.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import java.util.Random;

@SpringBootApplication
public class LightEcomApplication implements CommandLineRunner {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;


    public static void main(String[] args) {
        SpringApplication.run(LightEcomApplication.class, args);
    }

    @Override
    public void run(String[] args) {
        repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);
        categoryRepository.save(new Category(null,"Computers",null,null,null));
        categoryRepository.save(new Category(null,"Printers",null,null,null));
        categoryRepository.save(new Category(null,"Smartphone",null,null,null));
        Random rnd = new Random();
        categoryRepository.findAll().forEach(category -> {
            for (int i = 0; i<10; i++) {
            Product p = new Product();
            p.setName(RandomString.make(18));
            p.setCurrentPrice(100+rnd.nextInt(10000));
            p.setAvailable(rnd.nextBoolean());
            p.setPromotion(rnd.nextBoolean());
            p.setSelected(rnd.nextBoolean());
            p.setCategory(category);
            p.setPhotoName("unknown.png");
            produitRepository.save(p); }
        });



    }

}
