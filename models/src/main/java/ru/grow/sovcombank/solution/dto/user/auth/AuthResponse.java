package ru.grow.sovcombank.solution.dto.user.auth;

import lombok.Getter;
import lombok.Setter;
import ru.grow.sovcombank.solution.types.Role;

import java.util.Objects;

@Getter
@Setter
public class AuthResponse {
    private Long id;
    private Role role;
    private String jwt;

    public AuthResponse() {
    }

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AuthResponse jwt(String jwt) {
        setJwt(jwt);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AuthResponse)) {
            return false;
        }
        AuthResponse authenticationResponse = (AuthResponse) o;
        return Objects.equals(jwt, authenticationResponse.jwt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(jwt);
    }

    @Override
    public String toString() {
        return "{" + " jwt='" + getJwt() + "'" + "}";
    }
}
