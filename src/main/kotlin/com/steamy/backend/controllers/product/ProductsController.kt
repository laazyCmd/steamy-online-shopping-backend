package com.steamy.backend.controllers.product

import com.steamy.backend.controllers.product.model.Products
import com.steamy.backend.controllers.product.model.ProductsSummary
import com.steamy.backend.controllers.product.repository.ProductsRepository
import com.steamy.backend.controllers.product.repository.ProductsSummaryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO

@RestController
@RequestMapping( "/product" )
class ProductsController(
    val productsRepository: ProductsRepository,
    val productsSummaryRepository: ProductsSummaryRepository ) {

    @GetMapping( value = ["/{id}"], produces = ["application/json"] )
    fun getProduct( @PathVariable( "id" ) product_id: Int ): ResponseEntity<Any> {
        val specific_product: Products? = this.productsRepository.findByIdOrNull( product_id );
        specific_product?.prod_image = Base64.getEncoder().encodeToString( this.productsRepository.getProductImage( product_id ) );

        if ( specific_product === null ) return ResponseEntity( "No product has been found with an ID of $product_id", HttpStatus.NOT_FOUND );
        return ResponseEntity( specific_product, HttpStatus.FOUND );
    }

    @GetMapping( value = ["/list"], produces = ["application/json"] )
    fun getProductList( @RequestParam( "name", required = false ) name: String?,
                        @RequestParam( "category", required = false ) category: String?,
                        @RequestParam( "price", required = false ) price: Double?,
                        @RequestParam( "date", required = false ) date: String?,
                        @RequestParam( "popular_only", required = false ) popular_only: Boolean,
                        @RequestParam( "size" ) size: Int ): ResponseEntity<Any> {
        val product_list: MutableList<ProductsSummary> = this.productsSummaryRepository.findAll().subList( 0, size );
        if ( product_list.size == 0 ) return ResponseEntity( "You must specify the product's name, or its category and/or price range", HttpStatus.NOT_FOUND );

        product_list.forEach { it.prod_image = Base64.getEncoder().encodeToString( it.prod_image?.toByteArray() ) }


        if ( product_list.size > 0 ) return ResponseEntity( product_list, HttpStatus.OK );
        return ResponseEntity( "You must specify the product's name, or its category and/or price range", HttpStatus.NOT_FOUND );
    }
}