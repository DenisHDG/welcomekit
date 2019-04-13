package br.com.neolog.welcomekit.cart;

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

import br.com.neolog.welcomekit.customer.Customer;

@Entity
@Table( name = "cart" )
public class Cart
{

    public enum Status
    {
        Active,
        Canceled,
        Finished

    }

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cart_id_seq" )
    @SequenceGenerator( name = "cart_id_seq", sequenceName = "cart_id_seq", allocationSize = 1 )
    @Column( name = "id", updatable = false, nullable = false, unique = true )
    private Integer id;

    @NotNull
    @Column( name = "status", nullable = false, unique = false )
    private Status status;

    @Column( name = "total_quantity", updatable = true, unique = false )
    private double totalQuantity;

    @OneToOne
    @JoinColumn( name = "customer", foreignKey = @ForeignKey( name = "CUSTOMER_ID_FK" ), nullable = false )
    private Customer customer;

    public Cart()
    {

    }

    public Cart(
        final Status status,
        final double totalQuantity,
        final Customer customer )
    {
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.customer = customer;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( customer == null ) ? 0 : customer.hashCode() );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
        long temp;
        temp = Double.doubleToLongBits( totalQuantity );
        result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
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
        Cart other = (Cart) obj;
        if( customer == null ) {
            if( other.customer != null )
                return false;
        } else if( ! customer.equals( other.customer ) )
            return false;
        if( id == null ) {
            if( other.id != null )
                return false;
        } else if( ! id.equals( other.id ) )
            return false;
        if( status != other.status )
            return false;
        if( Double.doubleToLongBits( totalQuantity ) != Double.doubleToLongBits( other.totalQuantity ) )
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

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(
        Status status )
    {
        this.status = status;
    }

    public double getTotalQuantity()
    {
        return totalQuantity;
    }

    public void setTotalQuantity(
        double totalQuantity )
    {
        this.totalQuantity = totalQuantity;
    }

    public Customer getCustomerId()
    {
        return customer;
    }

    public void setCustomerId(
        Customer customerId )
    {
        this.customer = customerId;
    }

}
