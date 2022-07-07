package com.steamy.backend.controllers.product

import com.steamy.backend.controllers.product.component.ProductsSummaryComponent
import com.steamy.backend.controllers.product.model.Products
import com.steamy.backend.controllers.product.model.ProductsSummary
import com.steamy.backend.controllers.product.repository.ProductsRepository
import com.steamy.backend.messages.NotFound
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
    val productsSummaryComponent: ProductsSummaryComponent ) {

    @GetMapping( value = ["/{id}"], produces = ["application/json"] )
    fun getProduct( @PathVariable id: Int ): ResponseEntity<Any> {
        val product: Products? = this.productsRepository.findByIdOrNull( id );

        if ( product === null )
            return ResponseEntity( NotFound( "No product has been found with an ID of $id." ), HttpStatus.NOT_FOUND )
        return ResponseEntity( product, HttpStatus.FOUND );
    }

    @GetMapping( value = ["/list"], produces = ["application/json"] )
    fun getProductList( @RequestParam( required = false ) name: String?,
                        @RequestParam( required = false ) category: String?,
                        @RequestParam( required = false ) price: Double?,
                        @RequestParam( required = false ) popular: Boolean?,
                        @RequestParam( required = false ) sort: String?,
                        @RequestParam( required = false, defaultValue = "0" ) pageNo: Int,
                        @RequestParam( required = false, defaultValue = "9" ) pageSize: Int ): ResponseEntity<Any> {

        val productList: Page<ProductsSummary> = this.productsSummaryComponent.findAllProducts( name, category, price, popular, PageRequest.of( pageNo, pageSize ) );

        if ( productList.isEmpty )
            return ResponseEntity( NotFound( "No products found with given condition(s)." ), HttpStatus.NOT_FOUND );
        return ResponseEntity( productList, HttpStatus.FOUND );
    }
}