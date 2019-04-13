package br.com.neolog.welcomekit.customer;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.google.common.base.MoreObjects;

/**
 * @author denis.goncalves
 */

@Entity
@Table( name = "customer" )
public class Customer
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "customer_id_seq" )
    @SequenceGenerator( name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1 )
    @Column( name = "id", updatable = false, nullable = false, unique = true )
    private Integer id;

    @NotNull
    @Column( name = "name", nullable = false )
    private String name;

    @NotNull
    @Column( name = "email", nullable = false, unique = true, updatable = false )
    private String email;

    @Positive
    @Column( name = "age", nullable = true )
    private int age;

    @NotNull
    @Column( name = "password", nullable = false )
    private String password;

    public Customer()
    {

    }

    public Customer(
        final String name,
        final String email,
        final int age,
        final String password )
    {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;

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

    public String getName()
    {
        return name;
    }

    public void setName(
        String name )
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(
        String email )
    {
        this.email = email;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(
        int age )
    {
        this.age = age;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(
        String password )
    {
        this.password = password;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( email, name, age, password );
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj )
            return true;
        if( obj == null )
            return false;
        if( getClass() != obj.getClass() )
            return false;
        final Customer other = (Customer) obj;
        return Objects.equals( email, other.email ) &&
            Objects.equals( name, other.name ) &&
            Objects.equals( age, other.age ) &&
            Objects.equals( password, other.password );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "name", name )
            .add( "email", email )
            .add( "age", age )
            .add( "password", password )
            .toString();
    }

}