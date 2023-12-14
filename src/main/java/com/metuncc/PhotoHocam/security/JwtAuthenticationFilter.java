package com.metuncc.PhotoHocam.security;

import com.metuncc.PhotoHocam.domain.User;
import com.metuncc.PhotoHocam.repository.UserRepository;
import com.metuncc.PhotoHocam.service.PhotoHocamService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * This class filtering authentication based on JWT that already in header of the request.
 * Actually we take request (HttpServletRequest) and take "Authorization" header of this object.
 * If this is not null, we split token part of the header, then validate this token. If it is not valid or it is not
 * available on the request, we deny request.
 *
 * This filtering operation is explained on Spring Security documentation.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    @Value("${res.security.secret}")
    private String SECRET;

    @Value("${res.security.exp}")
    private long EXP;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwtToken = null;
            String bearer = request.getHeader("Authorization");
            if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")){
                jwtToken =  bearer.substring("Bearer".length() + 1);
            }
            Boolean isValidToken = false;
            try {
                Jwts
                        .parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(jwtToken);
                isValidToken = true;
            } catch (Exception e) {
                isValidToken = false;
            }
            if (StringUtils.hasText(jwtToken) && isValidToken) {
                Long id = Long.parseLong(
                        Jwts
                                .parser()
                                .setSigningKey(SECRET)
                                .parseClaimsJws(jwtToken)
                                .getBody().getSubject()
                );
                User user = userRepository.getById(id);
                UserDetails userDetails = new JwtUserDetails(user.getId(),user.getUsername(),user.getPassword());
                if (Objects.nonNull(userDetails)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            return;
        }
        filterChain.doFilter(request, response);
    }
}