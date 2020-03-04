package org.sid.lightecom.web;

import org.sid.lightecom.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.sid.lightecom.dao.ProduitRepository;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
@CrossOrigin("*")
@RestController
public class CatalogueRestController {
    private ProduitRepository produitRepository ;

    public CatalogueRestController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
    @GetMapping(path = "/photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        Product p = produitRepository.findById(id).get();
        return  Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));
    }

    @PostMapping(path = "uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception {
        Product p = produitRepository.findById(id).get();
        p.setPhotoName(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/ecom/products/" + p.getPhotoName()), file.getBytes());
        produitRepository.save(p); /* mettre à jour le produit dans la base de données */

    }
}
