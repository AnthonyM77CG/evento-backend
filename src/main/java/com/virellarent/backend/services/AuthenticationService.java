package com.virellarent.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Rol;
import com.virellarent.backend.entities.Usuario;
import com.virellarent.backend.repositories.RolRepository;
import com.virellarent.backend.repositories.UsuarioRepository;
import com.virellarent.backend.util.AuthenticationRequest;
import com.virellarent.backend.util.AuthenticationResponse;
import com.virellarent.backend.util.RefreshTokenRequest;
import com.virellarent.backend.util.RegisterRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final UsuarioRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final RolRepository rolRepository;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                Rol rolUser = rolRepository.findByNombre("USER")
                                .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
                var user = Usuario.builder()
                                .usuario(request.username())
                                .correo(request.email())
                                .contraseña(passwordEncoder.encode(request.password()))
                                .rol(rolUser)
                                .creadoEn(request.createIn())
                                .build();
                userRepository.save(user);
                var jwtToken = jwtService.generateToken(user);
                // jwtService.printTokenDates(jwtToken);
                var refreshToken = jwtService.generateRefreshToken(user);
                // jwtService.printTokenDates(refreshToken);
                return new AuthenticationResponse(jwtToken, refreshToken);
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
                var user = userRepository.findByCorreo(request.email()).orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                // jwtService.printTokenDates(jwtToken);
                var refreshToken = jwtService.generateRefreshToken(user);
                // jwtService.printTokenDates(refreshToken);
                return new AuthenticationResponse(jwtToken, refreshToken);
        }

        public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
                String userEmail = jwtService.extractUsername(request.refreshToken());
                if (userEmail != null) {
                        var user = userRepository.findByCorreo(userEmail).orElseThrow();
                        if (jwtService.isTokenValid(request.refreshToken(), user)) {
                                var accessToken = jwtService.generateToken(user);
                                // jwtService.printTokenDates(accessToken);
                                return new AuthenticationResponse(accessToken, request.refreshToken());
                        }
                }
                throw new RuntimeException("Token de refreso INVALIDO");
        }
}
