package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.model.DTO.ProductDTO;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.ProductService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ProductDTOController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductDTOResourceAssembler assembler;


    @GetMapping("/products/{id}")
    Resource<ProductDTO> findOne(@PathVariable Integer id) {
        ProductDTO productDTO = productService.findById(id);
        return assembler.toResource(productDTO);
    }


    @GetMapping("/products")
    Resources<Resource<ProductDTO>> findAll() {
        List<Resource<ProductDTO>> list = productService.getProducts().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(list,
                linkTo(methodOn(ProductDTOController.class).findAll()).withSelfRel());
    }

    @PostMapping("/products")
    ResponseEntity<?> save(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        Resource<ProductDTO> resource = assembler.toResource(productService.createProduct(productDTO));
        //return ResponseEntity.created(new URI(resource.));
        return new ResponseEntity<>(resource,null, HttpStatus.OK);


    }

    @PutMapping("/products/{id}")
    ResponseEntity<?> update(@RequestBody ProductDTO productDTO, @PathVariable Integer id) throws URISyntaxException {


        Resource<ProductDTO> resource = assembler.toResource(productService.updateProduct(id,productDTO));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }


    @DeleteMapping("/products/{id}")
    void delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }


}
