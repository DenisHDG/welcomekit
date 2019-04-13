package br.com.neolog.welcomekit.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.neolog.welcomekit.product.Product;

@Entity
@Table( name = "stock" )
public class Stock
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "stock_id_seq" )
    @SequenceGenerator( name = "stock_id_seq", sequenceName = "stock_id_seq", allocationSize = 1 )
    @Column( name = "id", updatable = false, nullable = false, unique = true )
    private int id;

    @Column( name = "quantity", nullable = false )
    @NotNull
    private int quantity;
    @NotNull
    @OneToOne
    @JoinColumn( name = "product", foreignKey = @ForeignKey( name = "FK_PRODUCT_ID" ), nullable = false, unique = true )
    private Product product;

    public Stock(
        final Product product,
        final int quantity )
    {
        this.quantity = quantity;
        this.product = product;

    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
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
        Stock other = (Stock) obj;
        if( product == null ) {
            if( other.product != null )
                return false;
        } else if( ! product.equals( other.product ) )
            return false;
        if( quantity != other.quantity )
            return false;
        return true;
    }

    protected Stock()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(
        int id )
    {
        this.id = id;
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

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(
        Product product )
    {
        this.product = product;
    }

}
