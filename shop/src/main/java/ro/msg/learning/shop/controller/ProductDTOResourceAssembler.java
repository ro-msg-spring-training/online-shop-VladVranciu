package ro.msg.learning.shop.controller;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.model.DTO.ProductDTO;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ProductDTOResourceAssembler implements ResourceAssembler<ProductDTO, Resource<ProductDTO>> {
    @Override
    public Resource<ProductDTO> toResource(ProductDTO productDTO) {
        return new Resource<>(productDTO,
                linkTo(methodOn(ProductDTOController.class).findOne(productDTO.getId())).withSelfRel(),
                linkTo(methodOn(ProductDTOController.class).findAll()).withRel("products"));

    }
}
