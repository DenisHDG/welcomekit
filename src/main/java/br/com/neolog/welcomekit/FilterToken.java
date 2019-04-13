package br.com.neolog.welcomekit;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.neolog.welcomekit.customer.Customer;
import br.com.neolog.welcomekit.session.Session;
import br.com.neolog.welcomekit.session.SessionRepository;

@WebFilter( urlPatterns = {
    "/product/*",
    "/category/*",
    "/stock/*",
    "/cart/*",
    "/cart_item/*"
} )
public class FilterToken
    implements
        Filter
{

    private static final Logger logger = LogManager.getLogger( FilterToken.class );

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public void init(
        final FilterConfig filterConfig )
        throws ServletException
    {
        logger.info( "Filter create with sucess!" );
    }

    @Override
    public void doFilter(
        final ServletRequest servletRequest,
        final ServletResponse servletResponse,
        final FilterChain chain )
        throws IOException,
            ServletException
    {

        logger.info( "Filter request" );

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String token = request.getHeader( "token" );

        final Session session = sessionRepository.findSessionByTokenAndExpirationDateGreaterThan( token, LocalDateTime.now() );
        if( session != null ) {
            final Customer customer = session.getCustomer();
            ThreadCustomer.setCustomer( customer );
            try {
                chain.doFilter( request, response );
            } finally {
                ThreadCustomer.removeCustomer();
            }
        } else {
            response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token" );
        }
    }

    @Override
    public void destroy()
    {
        logger.error( "filter destroyed" );
    }

}