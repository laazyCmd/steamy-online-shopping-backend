package com.steamy.backend.controllers.product.model

import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class Products {

    @Id
    @Column( name = "prod_id" )
    val prodId: Int = 0;

    @Column( name = "prod_name" )
    val prodName: String = "";

    @Column( name = "prod_description" )
    val prodDescription: String = "";

    @Transient
    var prodImage: String? = null;

    @Column( name = "prod_rating" )
    val prodRating: Double = 0.0;

    @Column( name = "prod_price" )
    val prodPrice: Double = 0.0;

    @Column( name = "prod_sales")
    val prodSales: Int = 0;

    @Column( name = "prod_stocks" )
    val prodStocks: Int = 0;

    @Column( name = "prod_category" )
    val prodCategory: String = "";

    @Column( name = "prod_brand" )
    val prodBrand: String = "";

    @Column( name = "date_created" )
    val dateCreated: String = "";

    @Column( name = "seller_name" )
    val sellerName: String = "";

    @Column( name = "country_origin" )
    val countryOrigin: String = "";

    @Column( name = "shipping_origin" )
    val shippingOrigin: String = "";

    @Column( name = "shipping_fee" )
    val shippingFee: Double = 0.0;

    @Column( name = "is_popular" )
    val isPopular: Boolean = false;
}