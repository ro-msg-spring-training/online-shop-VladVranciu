package ro.msg.learning.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.DTO.ProductDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    SupplierRepository supplierRepository;



    @Override
    public ProductDTO createProduct(ProductDTO product) {
        Product product1 = new Product();
        ProductCategory productCategory = productCategoryRepository.findById(product.getProductCategory()).get();
        product1.setProductCategory(productCategory);
        product1.setDescription(product.getDescription());
        product1.setImgUrl(product.getImgUrl());
        product1.setName(product.getName());
        product1.setOrderDetails(product.getOrderDetails());
        product1.setPrice(product.getPrice());
        product1.setStocks(product.getStocks());
        Supplier supplier = supplierRepository.findById(product.getSupplierId()).get();
        product1.setSupplier(supplier);
        product1.setWeight(product.getWeight());
        Product product2 = productRepository.save(product1);
        log.info("Add product " + product2);
        //log.info(product2.toString() + " added successfully");
        return product;
    }

    @Override
    public ProductDTO updateProduct(Integer id, ProductDTO product) {
        Optional<Product> aux = productRepository.findById(id).map(
                product1 -> {
                    ProductCategory productCategory = productCategoryRepository.findById(product.getProductCategory()).get();
                    product1.setProductCategory(productCategory);
                    product1.setDescription(product.getDescription());
                    product1.setImgUrl(product.getImgUrl());
                    product1.setName(product.getName());
                    //product1.setOrderDetails(product.getOrderDetails());
                    product1.setPrice(product.getPrice());
                    //product1.setStocks(product.getStocks());
                    product1.setSupplier(supplierRepository.findById(product.getSupplierId()).get());
                    product1.setWeight(product.getWeight());
                    return productRepository.save(product1);

                });
        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
        //productRepository.delete(productRepository.findById(id).get());
        log.info("Product with id " + id + " deleted successfully");
    }

    @Override
    public ProductDTO findById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        ProductDTO product1 = new ProductDTO();
        product1.setId(product.getId());
        product1.setProductCategory(product.getProductCategory().getId());
        product1.setDescription(product.getDescription());
        product1.setImgUrl(product.getImgUrl());
        product1.setName(product.getName());
        product1.setOrderDetails(product.getOrderDetails());
        product1.setPrice(product.getPrice());
        product1.setStocks(product.getStocks());
        product1.setSupplierId(product.getSupplier().getId());
        product1.setSupplierName(product.getSupplier().getName());
        product1.setWeight(product.getWeight());
        log.info("Product with id " + id + " was found");
        return product1;

        //log.info("Product with id "+id+" was not found");
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO product1 = new ProductDTO();
            product1.setId(product.getId());
            product1.setProductCategory(product.getProductCategory().getId());
            product1.setDescription(product.getDescription());
            product1.setImgUrl(product.getImgUrl());
            product1.setName(product.getName());
            product1.setOrderDetails(product.getOrderDetails());
            product1.setPrice(product.getPrice());
            product1.setStocks(product.getStocks());
            product1.setSupplierId(product.getSupplier().getId());
            product1.setSupplierName(product.getSupplier().getName());
            product1.setWeight(product.getWeight());
            product1.setOrderDetails(product.getOrderDetails());
            productDTOS.add(product1);
        }
        log.info("Product list is being returned");
        return productDTOS;
    }

}
