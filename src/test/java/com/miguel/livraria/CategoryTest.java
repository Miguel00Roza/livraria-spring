package com.miguel.livraria;

import com.miguel.livraria.domain.Category;
import com.miguel.livraria.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class CategoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    void saveCategoryWithSuccess() {

        Category cat = new Category();
        cat.setName("Terror");

        Category catSave = repository.save(cat);

        Assertions.assertNotNull(catSave.getId());
        Assertions.assertEquals("Terror", catSave.getName());
    }

}
