package com.steamy.backend.controllers.product.model

import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table( name = "products" )
class ProductsSummary {

    @Id
    @Column( name = "prod_id" )
    val prodId: Int = 0;

    @Column( name = "prod_name" )
    val prodName: String = "";

    @Column( name = "prod_image" )
    var prodImage: String? = null;

    @Column( name = "prod_price" )
    val prodPrice: Double = 0.0;

    @Column( name = "prod_sales" )
    val prodSales: Int = 0;

    @Column( name = "date_created" )
    val dateCreated: String = "";

    @Column( name = "is_popular" )
    val isPopular: Boolean = false;
}