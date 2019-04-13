package br.com.neolog.welcomekit.cartItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.neolog.welcomekit.cart.Cart;
import br.com.neolog.welcomekit.product.Product;

@Entity
@Table( name = "cart_item" )
public class CartItem
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cart_item_id_seq" )
    @SequenceGenerator( name = "cart_item_id_seq", sequenceName = "cart_item_id_seq", allocationSize = 1 )
    @Column( name = "id", updatable = false, nullable = false, unique = true )
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "product", foreignKey = @ForeignKey( name = "PRODUCT_ID_FK" ), nullable = false )
    private Product product;

    @ManyToOne
    @JoinColumn( name = "cart", foreignKey = @ForeignKey( name = "CART_ID_FK" ), nullable = false )
    private Cart cartId;

    @NotNull
    @Positive
    @Column( name = "quantity", nullable = false, unique = false )
    private int quantity;

    public CartItem()
    {

    }

    public CartItem(
        final Product product,
        final Cart cart,
        final int quantity )
    {
        this.product = product;
        this.cartId = cart;
        this.quantity = quantity;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( cartId == null ) ? 0 : cartId.hashCode() );
        result = prime * result + ( ( product == null ) ? 0 : product.hashCode() );
        result = prime * result + quantity;
        return result;
    }

    @Override
    public boolean equals(
        Object obj )
    {
        if( this == obj )
            return true;
        if( obj == null )
            return false;
        if( getClass() != obj.getClass() )
            return false;
        CartItem other = (CartItem) obj;
        if( cartId == null ) {
            if( other.cartId != null )
                return false;
        } else if( ! cartId.equals( other.cartId ) )
            return false;
        if( product == null ) {
            if( other.product != null )
                return false;
        } else if( ! product.equals( other.product ) )
            return false;
        if( quantity != other.quantity )
            return false;
        return true;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(
        Integer id )
    {
        this.id = id;
    }

    public Product getProductId()
    {
        return product;
    }

    public void setProductId(
        Product productId )
    {
        this.product = productId;
    }

    public Cart getCartId()
    {
        return cartId;
    }

    public void setCartId(
        Cart cartId )
    {
        this.cartId = cartId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(
        int quantity )
    {
        this.quantity = quantity;
    }

}
