package com.tlino.demoparkapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id //  Marca o campo id como a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que o valor da chave primária id é gerado automaticamente pelo banco de dados com estratégia de identidade.
    @Column(name = "id")
    private Long id;
    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Enumerated(EnumType.STRING) // para que minha fique no formato de string
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_CLIENTE;

    @Column(name = "data_craicao")
    private LocalDateTime dataCriacao;
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;
    @Column(name = "criado_por")
    private String criadoPor;
    @Column(name = "modificado_por")
    private String modificadoPor;



    public enum Role{
        ROLE_ADMIN, ROLE_CLIENTE

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                '}';
    }
}
