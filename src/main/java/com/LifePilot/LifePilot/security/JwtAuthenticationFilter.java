package com.LifePilot.LifePilot.security;

import com.LifePilot.LifePilot.repository.UserRepository;
import com.LifePilot.LifePilot.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // No Authorization header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract JWT
        String token = authHeader.substring(7);

        try {

            // Validate token
            if (!jwtService.isTokenValid(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Extract email from JWT
            String email = jwtService.extractEmail(token);

            // Don't authenticate if already authenticated
            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                userRepository.findByEmail(email).ifPresent(user -> {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    user.getAuthorities()
                            );

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);
                });
            }

        } catch (Exception e) {
            // Invalid token — continue without authentication
        }

        filterChain.doFilter(request, response);
    }
}