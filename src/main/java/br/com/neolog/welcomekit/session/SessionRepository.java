package br.com.neolog.welcomekit.session;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neolog.welcomekit.customer.Customer;

@Repository
public interface SessionRepository
    extends
        JpaRepository<Session,Integer>
{

    boolean existsSessionByCustomer(
        final Customer customer );

    Session findSessionByCustomer(
        final Customer costomer );

    Session findSessionByTokenAndExpirationDateGreaterThan(
        final String token,
        final LocalDateTime expirationDate );

}
