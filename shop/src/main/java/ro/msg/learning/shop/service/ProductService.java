package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.DTO.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO product);
    ProductDTO updateProduct(Integer id, ProductDTO product);
    void deleteProduct(Integer id);
    ProductDTO findById(Integer id);
    List<ProductDTO> getProducts();
}
