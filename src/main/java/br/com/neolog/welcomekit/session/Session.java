package br.com.neolog.welcomekit.session;

import java.time.LocalDateTime;

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
import javax.validation.constraints.Size;

import br.com.neolog.welcomekit.customer.Customer;

@Entity
@Table( name = "session" )
public class Session
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "customer_session_id_seq" )
    @SequenceGenerator( name = "customer_session_id_seq", sequenceName = "customer_session_id_seq", allocationSize = 1 )
    @Column( name = "id", updatable = false, nullable = false, unique = true )
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn( name = "customer", foreignKey = @ForeignKey( name = "CUSTOMER_SESSION_ID_FK" ), nullable = false, unique = true )
    private Customer customer;
    @NotNull
    @Size( min = 1, max = 255 )
    @Column( updatable = true, unique = false, name = "token", nullable = false )
    private String token;

    @NotNull
    @Column( updatable = true, unique = false, name = "expiration_date", nullable = false )
    private LocalDateTime expirationDate;

    public Session()
    {

    }

    public Session(
        final Customer customer,
        final String token,
        final LocalDateTime expirationDate )
    {
        this.customer = customer;
        this.token = token;
        this.expirationDate = expirationDate;

    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( customer == null ) ? 0 : customer.hashCode() );
        result = prime * result + ( ( expirationDate == null ) ? 0 : expirationDate.hashCode() );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( token == null ) ? 0 : token.hashCode() );
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
        Session other = (Session) obj;
        if( customer == null ) {
            if( other.customer != null )
                return false;
        } else if( ! customer.equals( other.customer ) )
            return false;
        if( expirationDate == null ) {
            if( other.expirationDate != null )
                return false;
        } else if( ! expirationDate.equals( other.expirationDate ) )
            return false;
        if( id == null ) {
            if( other.id != null )
                return false;
        } else if( ! id.equals( other.id ) )
            return false;
        if( token == null ) {
            if( other.token != null )
                return false;
        } else if( ! token.equals( other.token ) )
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

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(
        Customer customer )
    {
        this.customer = customer;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(
        String token )
    {
        this.token = token;
    }

    public LocalDateTime getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(
        LocalDateTime expirationDate )
    {
        this.expirationDate = expirationDate;
    }

}
