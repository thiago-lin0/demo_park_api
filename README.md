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



