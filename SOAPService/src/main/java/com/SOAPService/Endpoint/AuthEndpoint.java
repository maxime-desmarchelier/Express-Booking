package com.SOAPService.Endpoint;

import com.Database.AuthController;
import https.trainbooking_fr.train_booking_soap_service.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Endpoint
public class AuthEndpoint {

    private static final String NAMESPACE_URI = "https://trainbooking.fr/train-booking-soap-service";

    public static boolean checkTokenValidity(String token) {
        try {
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("XN}Ga@!E?4FQ(Em\"c4NzZ32a<|O<fMXN}Ga@!E?4FQ(Em\"c4NzZ32a<|O<fMXN}Ga@!E?4FQ(Em\"c4NzZ32a<|O<fM");
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();

            if (claims.getExpiration().before(new Date())) {
                return false;
            }

            // Get token from database and check if it's the same
            AuthController authController = new AuthController();
            String tokenFromDB = authController.getToken(claims.getSubject());
            return token.equals(tokenFromDB);

        } catch (Exception e) {
            return false;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthRequest")
    @ResponsePayload
    public AuthResponse authenticate(@RequestPayload AuthRequest request) {
        String user = request.getUsername();
        String pass = request.getPassword();

        AuthResponse response = new AuthResponse();

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("XN}Ga@!E?4FQ(Em\"c4NzZ32a<|O<fMXN}Ga@!E?4FQ(Em\"c4NzZ32a<|O<fMXN}Ga@!E?4FQ(Em\"c4NzZ32a<|O<fM");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        AuthController authController = new AuthController();
        if (authController.userExists(user, pass)) {
            long expiresIn = 2 * 60 * 60 * 1000; // 2 hours
            Date expiresAt = new Date(System.currentTimeMillis() + expiresIn);
            String jwtToken = Jwts.builder().setSubject(user).setExpiration(expiresAt).signWith(signingKey).compact();
            // save token in database
            authController.saveToken(user, jwtToken);
            response.setAuthToken(jwtToken);
        } else {
            response.setAuthToken("0");
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthCreateRequest")
    @ResponsePayload
    public AuthCreateResponse createUser(@RequestPayload AuthCreateRequest request) {
        String user = request.getUsername();
        String pass = request.getPassword();

        AuthCreateResponse response = new AuthCreateResponse();
        AuthController authController = new AuthController();

        response.setSucceed(authController.createUser(user, pass));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckTokenValidityRequest")
    @ResponsePayload
    public CheckTokenValidityResponse checkTokenValidity(@RequestPayload CheckTokenValidityRequest request) {
        String token = request.getToken();

        CheckTokenValidityResponse response = new CheckTokenValidityResponse();
        response.setSucceed(checkTokenValidity(token));

        return response;
    }
}