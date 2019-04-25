package ro.msg.learning.shop.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.Model.DTO.ProductDTO;
import ro.msg.learning.shop.Model.Product;
import ro.msg.learning.shop.Model.ProductCategory;
import ro.msg.learning.shop.Model.Supplier;
import ro.msg.learning.shop.Persistence.ProductRepository;
import ro.msg.learning.shop.Persistence.SupplierRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired(required = true)
    ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        Product product1 = new Product();
        product1.setId(product.getId());
        product1.setCategory(product.getCategory());
        product1.setDescription(product.getDescription());
        product1.setImgUrl(product.getImgUrl());
        product1.setName(product.getName());
        product1.setOrderDetails(product.getOrderDetails());
        product1.setPrice(product.getPrice());
        product1.setStocks(product.getStocks());
        product1.setSupplier(new Supplier(product.getSupplierId(),product.getSupplierName()));
        product1.setWeight(product.getWeight());
        productRepository.save(product1);
        log.info(product1.toString() + " added successfully");
        return product;
    }

    @Override
    public ProductDTO updateProduct(Integer id, ProductDTO product) {
        Optional<Product> aux = productRepository.findById(id);
        if (aux.isPresent()) {
            Product product1 = new Product();
            product1.setId(product.getId());
            product1.setCategory(product.getCategory());
            product1.setDescription(product.getDescription());
            product1.setImgUrl(product.getImgUrl());
            product1.setName(product.getName());
            product1.setOrderDetails(product.getOrderDetails());
            product1.setPrice(product.getPrice());
            product1.setStocks(product.getStocks());
            product1.setSupplier(new Supplier(product.getSupplierId(),product.getSupplierName()));
            product1.setWeight(product.getWeight());
            log.info(product.toString() + " updated successfully");
            return product;
        }
        log.info("Error while updating product " + product.toString());

        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        Optional<Product> aux = productRepository.findById(id);
        if (aux.isPresent()) {
            productRepository.delete(aux.get());
            log.info("Product with id "+id+" deleted successfully");
            return;
        }
        log.info("Error while deleting product "+id);
    }

    @Override
    public ProductDTO findById(Integer id) {
        Optional<Product> aux = productRepository.findById(id);
        if (aux.isPresent()) {
            Product product = aux.get();
            ProductDTO product1 = new ProductDTO();
            product1.setId(product.getId());
            product1.setCategory(product.getCategory());
            product1.setDescription(product.getDescription());
            product1.setImgUrl(product.getImgUrl());
            product1.setName(product.getName());
            product1.setOrderDetails(product.getOrderDetails());
            product1.setPrice(product.getPrice());
            product1.setStocks(product.getStocks());
            product1.setSupplierId(product.getSupplier().getId());
            product1.setSupplierName(product.getSupplier().getName());
            product1.setWeight(product.getWeight());
            log.info("Product with id "+id+" was found");
            return product1;
        }
        log.info("Product with id "+id+" was not found");
        return null;
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO product1 = new ProductDTO();
            product1.setId(product.getId());
            product1.setCategory(product.getCategory());
            product1.setDescription(product.getDescription());
            product1.setImgUrl(product.getImgUrl());
            product1.setName(product.getName());
            product1.setOrderDetails(product.getOrderDetails());
            product1.setPrice(product.getPrice());
            product1.setStocks(product.getStocks());
            product1.setSupplierId(product.getSupplier().getId());
            product1.setSupplierName(product.getSupplier().getName());
            product1.setWeight(product.getWeight());
            productDTOS.add(product1);
        }
        log.info("Product list is being returned");
        return productDTOS;
    }

}
