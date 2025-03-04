package org.sid.lightecom.dao;

import org.sid.lightecom.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource /* pour utiliser Spring Data Rest */
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
