# demo_park_api

## Recurso Usuario
Nessa parte criei e persistir dos dados no banco da classe Usuario

## arquivo Usuario.java
**Este trecho indica que a classe ´Usuario´ faz parte do pacote ´com.tlino.demoparkapi.entity´.**
~~~java
package com.tlino.demoparkapi.entity;
~~~

Aqui, estou importando várias bibliotecas e anotações. jakarta.persistence.* e lombok.* contêm anotações e utilitários usados para mapear objetos Java para tabelas de banco de dados.
~~~java
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util Objects;
~~~

Essas são anotações do Lombok. @Getter e @Setter são usadas para gerar automaticamente métodos getters e setters para as propriedades da classe. @NoArgsConstructor gera um construtor sem argumentos.
~~~java
@Getter @Setter @NoArgsConstructor
~~~

Aqui, você está usando as anotações @Entity e @Table. @Entity indica que esta classe é uma entidade JPA (Java Persistence API), que pode ser mapeada para uma tabela no banco de dados. @Table é usada para especificar o nome da tabela no banco de dados (no caso, "usuarios").
~~~java
@Entity
@Table(name = "usuarios")
~~~

A classe Usuario é definida como pública e implementa a interface Serializable, que é usada para sinalizar que objetos dessa classe podem ser serializados.
~~~java
public class Usuario implements Serializable {
~~~

Aqui, estão definidos os atributos da classe Usuario. O atributo id é marcado com @Id, indicando que é a chave primária da tabela no banco de dados. @GeneratedValue especifica que o valor do id é gerado automaticamente pelo banco de dados com uma estratégia de identidade (geralmente autoincremento). Os outros atributos são mapeados para colunas no banco de dados usando a anotação @Column, que também especifica algumas propriedades, como se o campo pode ser nulo e seu comprimento máximo. O atributo role é do tipo enumerado e é mapeado como uma string no banco de dados.
~~~java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;
@Column(name = "username", nullable = false, unique = true, length = 100)
private String username;
@Column(name = "password", nullable = false, length = 200)
private String password;
@Enumerated(EnumType.STRING)
@Column(name = "role", nullable = false, length = 25)
private Role role;
@Column(name = "data_craicao")
private LocalDateTime dataCriacao;
@Column(name = "data_modificacao")
private LocalDateTime dataModificacao;
@Column(name = "criado_por")
private String criadoPor;
@Column(name = "modificado_por")
private String modificadoPor;
~~~

Aqui é definido um enum Role, que especifica os possíveis valores para o atributo role.
~~~java
public enum Role {
    ROLE_ADMIN, ROLE_CLIENTE
}
~~~

Os últimos três métodos (equals, hashCode e toString) são métodos sobrescritos da classe Object e fornecem implementações personalizadas para esses métodos, que são comumente usados ao lidar com objetos em Java.

No geral, esta classe Usuario é usada para mapear objetos Java para registros em uma tabela de banco de dados, e as anotações fornecem informações sobre como essa mapeação deve ser feita. O Lombok é usado para reduzir a quantidade de código "boilerplate" necessário.

## Arquivo UsuarioRepository.java

Esse código é uma interface Java que faz parte de um projeto que utiliza o framework Spring Data JPA para facilitar a persistência de dados em um banco de dados. Vou explicar o código em detalhes:

Este trecho indica que a interface UsuarioRepository faz parte do pacote com.tlino.demoparkapi.repository.
~~~java
package com.tlino.demoparkapi.repository;
~~~

Aqui, você está importando duas classes. A classe Usuario é a entidade que você deseja persistir no banco de dados. A classe JpaRepository é parte do Spring Data JPA e fornece métodos padrão para realizar operações de CRUD (Create, Read, Update, Delete) no banco de dados relacionado à entidade.
~~~java
import com.tlino.demoparkapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
~~~

Neste trecho, você declara a interface `UsuarioRepository` que estende a interface `JpaRepository`. O `JpaRepository` é parametrizado com dois tipos:

1.`Usuario` (ou seja, a entidade com a qual você deseja trabalhar): Isso indica que você está criando um repositório para a entidade `Usuario`. O Spring Data JPA gera automaticamente as consultas SQL necessárias para operações de CRUD com base nesta entidade.

2.`Long` (ou seja, o tipo da chave primária da entidade): Isso indica o tipo de dado da chave primária da entidade `Usuario`. No caso, a chave primária é do tipo `Long`.

A interface `UsuarioRepository` herda todos os métodos definidos na interface `JpaRepository`, que incluem métodos como `save`, `findById`, `findAll`, `delete`, entre outros. Isso permite que você realize operações de banco de dados sem escrever consultas SQL manualmente. Por exemplo, você pode salvar um novo `Usuario` chamando `usuarioRepository.save(usuario)`, buscar um `Usuario` pelo ID chamando `usuarioRepository.findById(id)`, ou listar todos os Usuario chamando `usuarioRepository.findAll()`, entre outras operações.

Essa interface é uma parte fundamental de como o Spring Data JPA simplifica o acesso e a manipulação de dados em bancos de dados relacionais, permitindo que você se concentre na lógica de negócios em vez de lidar com a camada de persistência de dados.
~~~java
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
~~~



