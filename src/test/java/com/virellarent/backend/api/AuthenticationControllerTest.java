package com.virellarent.backend.api;

import com.virellarent.backend.services.AuthenticationService;
import com.virellarent.backend.util.AuthenticationRequest;
import com.virellarent.backend.util.AuthenticationResponse;
import com.virellarent.backend.util.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthenticationControllerTest {
    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    private AuthenticationController authenticationController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    void testRegister() throws Exception {
        AuthenticationResponse response = new AuthenticationResponse("token", "refreshToken");
        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(response);
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"username\":\"test\",\"email\":\"test@test.com\",\"password\":\"password\",\"createIn\":\"2023-01-01T00:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").value("token"))
                .andExpect(jsonPath("$.refresh_token").value("refreshToken"));
    }

    @Test
    void testAuthenticate() throws Exception {
        AuthenticationResponse response = new AuthenticationResponse("token", "refreshToken");
        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(response);
        mockMvc.perform(post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").value("token"))
                .andExpect(jsonPath("$.refresh_token").value("refreshToken"));
    }
}
