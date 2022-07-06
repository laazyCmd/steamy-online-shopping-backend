package com.steamy.backend.controllers.product.model

import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class Products {

    @Id
    @Column
    val id: Int = 0;

    @Column
    val name: String = "";

    @Column
    val description: String = "";

    @Transient
    var image: String? = null;

    @Column
    val rating: Double = 0.0;

    @Column
    val price: Double = 0.0;

    @Column
    val sales: Int = 0;

    @Column
    val stocks: Int = 0;

    @Column
    val category: String = "";

    @Column
    val brand: String = "";

    @Column( name = "date_created" )
    val dateCreated: String = "";

    @Column
    val seller: String = "";

    @Column( name = "country_origin" )
    val countryOrigin: String = "";

    @Column( name = "shipping_origin" )
    val shippingOrigin: String = "";

    @Column( name = "shipping_fee" )
    val shippingFee: Double = 0.0;

    @Column( name = "is_popular" )
    val isPopular: Boolean = false;
}