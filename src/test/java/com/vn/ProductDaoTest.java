package com.vn;

import com.vn.dao.ProductDao;
import com.vn.dao.impl.AbtractGenericDao;
import com.vn.dao.impl.ProductDaoImpl;
import com.vn.entities.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductDaoTest {

    @Test
    public void ProductDao_paging_success(){
        ProductDao productDao = new ProductDaoImpl();
        List<Product> products =  productDao.findByPaging(1,3);
        for (Product p : products){
            System.out.println(p.toString());
        }
    }


    @Test
    public void ProductDao_create_success(){
        ProductDao productDao = new ProductDaoImpl();
        Product products = new Product();
        products.setName("san pham 100");
        products.setImage("dadas");
        products.setPrice(new BigDecimal(3000.0));


        productDao.create(products);
    }

    @Test
    public void ProductDao_findById_success(){
        ProductDao productDao = new ProductDaoImpl();
        Product product = (Product) productDao.readById(3);
        System.out.println(product);
    }

    @Test
    public void ProductDao_findAll_success(){
        ProductDao productDao = new ProductDaoImpl();
        List<Product>  products =  productDao.readAll();
        for (Product p : products){
            System.out.println(p);
        }

    }

    @Test
    public void ProductDao_update_success(){
        ProductDao productDao = new ProductDaoImpl();
        Product products = new Product();
        products.setProductId(4);
        products.setName("san pham 20000");
        products.setImage("asdasdada");

        Product productDB = (Product) productDao.readById(products.getProductId());
        productDB.setName(products.getName());
        productDB.setImage(products.getImage());
        productDao.update(productDB);
        System.out.println(products);
    }


    @Test
    public void ProductDao_delete_success(){
        ProductDao productDao = new ProductDaoImpl();
        productDao.deleteById(5);
    }
}
