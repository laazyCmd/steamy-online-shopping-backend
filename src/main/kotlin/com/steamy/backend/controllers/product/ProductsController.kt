package com.steamy.backend.controllers.product

import com.steamy.backend.controllers.product.model.Products
import com.steamy.backend.controllers.product.model.ProductsSummary
import com.steamy.backend.controllers.product.repository.ProductsRepository
import com.steamy.backend.controllers.product.repository.ProductsSummaryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping( "/product" )
class ProductsController(
    val productsRepository: ProductsRepository,
    val productsSummaryRepository: ProductsSummaryRepository ) {

    @GetMapping( value = ["/{id}"], produces = ["application/json"] )
    fun getProduct( @PathVariable( "id" ) product_id: Int ): ResponseEntity<Any> {
        val specific_product: Products? = this.productsRepository.findByIdOrNull( product_id )
        specific_product?.prodImage = Base64.getEncoder().encodeToString( this.productsRepository.getProductImage( product_id ) )

        if ( specific_product === null ) return ResponseEntity( "No product has been found with an ID of $product_id", HttpStatus.NOT_FOUND )
        return ResponseEntity( specific_product, HttpStatus.FOUND )
    }

    @GetMapping( value = ["/list"], produces = ["application/json"] )
    fun getProductList( @RequestParam( "name", required = false ) name: String?,
                        @RequestParam( "category", required = false ) category: String?,
                        @RequestParam( "sort", required = false ) sort: String?,
                        @RequestParam( "pageNo", required = false, defaultValue = "0" ) page_no: Int,
                        @RequestParam( "pageSize", required = false, defaultValue = "10" ) page_size: Int ): ResponseEntity<Any> {
        val product_list: Page<ProductsSummary> =
            if ( !name.isNullOrBlank() && !category.isNullOrBlank() )
                this.productsSummaryRepository.findAllByProdNameContainingAndProdCategory( name, category, PageRequest.of( page_no, page_size ) );
            else if ( !name.isNullOrBlank() )
                this.productsSummaryRepository.findAllByProdNameContaining( name, PageRequest.of( page_no, page_size ) );
            else if ( !category.isNullOrBlank() )
                this.productsSummaryRepository.findAllByProdCategory( category, PageRequest.of( page_no, page_size ) );
            else this.productsSummaryRepository.findAll( PageRequest.of( page_no, page_size ) );

        if ( product_list.isEmpty ) return ResponseEntity( "You must specify a product's name, or its category and/or price range", HttpStatus.NOT_FOUND )
        product_list.forEach { it?.prodImage = Base64.getEncoder().encodeToString( it.prodImage?.toByteArray() ) }

        return ResponseEntity( product_list, HttpStatus.FOUND )
    }
}