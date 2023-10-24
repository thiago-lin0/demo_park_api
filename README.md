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

## Arquivo UsuarioService.java

A classe `UsuarioService` é um serviço que fornece operações relacionadas a `usuários` (ou Usuario no contexto do código). Vou explicar o código em detalhes:

Este trecho indica que a classe `UsuarioService` faz parte do pacote `com.tlino.demoparkapi.service`.
~~~java
package com.tlino.demoparkapi.service;
~~~

Aqui, você está importando várias classes e anotações necessárias para o funcionamento da classe. Importa a classe `Usuario` que é a entidade que representa um usuário, a classe `UsuarioRepository` que é o repositório para acessar dados de usuários no banco de dados, e várias anotações do Spring.
~~~java
import com.tlino.demoparkapi.entity.Usuario;
import com.tlino.demoparkapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
~~~

A anotação `@RequiredArgsConstructor` é uma anotação do projeto Lombok que gera um construtor que aceita todos os campos marcados com `final` como argumentos, tornando a injeção de dependência mais conveniente. A anotação `@Service` é usada para indicar que esta classe é um componente de serviço gerenciado pelo Spring, o que significa que pode ser injetada em outras partes da aplicação.
~~~java
@RequiredArgsConstructor
@Service
~~~

Aqui você define a classe `UsuarioService`.
~~~java
public class UsuarioService {
~~~

Este é um campo de classe que é marcado como `final` e é injetado com uma instância de `UsuarioRepository` no construtor da classe. Isso permite que a classe acesse os métodos do repositório para interagir com os dados do usuário no banco de dados.
~~~java
private final UsuarioRepository usuarioRepository;
~~~

Este método `salvar` é anotado com `@Transactional`, o que significa que é executado em uma transação do banco de dados. Ele recebe um objeto `Usuario` como argumento e o salva no banco de dados usando o método save do `usuarioRepository`. O método retorna o usuário salvo.
~~~java
@Transactional
public Usuario salvar(Usuario usuario) {
    return usuarioRepository.save(usuario);
}
~~~~

Este método `buscarPorId` é anotado com `@Transactional` com a opção `readOnly = true`, o que significa que é apenas uma operação de leitura, e não modificará os dados no banco de dados. Ele recebe o ID de um usuário como argumento, busca o usuário correspondente no banco de dados usando o método `findById` do `usuarioRepository`, e retorna o usuário encontrado. Se o usuário não for encontrado, lança uma exceção `RuntimeException`.
~~~java
@Transactional(readOnly = true)
public Usuario buscarPorId(Long id) {
    return usuarioRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Usuario não encontrado")
    );
}
~~~

Este método `editarSenha` também é anotado com `@Transactional` e recebe o ID de um usuário e uma nova senha como argumentos. Primeiro, ele chama o método `buscarPorId` para buscar o usuário pelo ID. Em seguida, atualiza a senha do usuário com a nova senha fornecida e retorna o usuário atualizado.
~~~java
@Transactional
public Usuario editarSenha(Long id, String password) {
    Usuario user = buscarPorId(id);
    user.setPassword(password);
    return user;
}
~~~

Este método `buscarTodos` é anotado com `@Transactional` com `readOnly = true`. Ele busca e retorna uma lista de todos os usuários no banco de dados usando o método `findAll do usuarioRepository`. Essa operação é apenas leitura e não modifica os dados no banco de dados.
~~~java
@Transactional(readOnly = true)
public List<Usuario> buscarTodos() {
    return usuarioRepository.findAll();
}
~~~

Em resumo, a classe `UsuarioService` fornece métodos para realizar operações relacionadas a usuários, como salvar, buscar por ID, editar senha e listar todos os usuários. Os métodos são anotados com `@Transactional` para garantir que sejam executados dentro de transações do banco de dados, e a injeção de dependência é usada para acessar o repositório de usuários (`UsuarioRepository`) para realizar operações no banco de dados.

## Arquivo UsuarioController.java

A classe UsuarioController é um controlador que mapeia requisições HTTP para as operações correspondentes do serviço de usuário (UsuarioService). Vou explicar o código em detalhes:

Este trecho indica que a classe UsuarioController faz parte do pacote com.tlino.demoparkapi.web.controller.
~~~java
package com.tlino.demoparkapi.web.controller;
~~~

Aqui, você está importando classes e anotações necessárias para o funcionamento do controlador. A classe Usuario é a entidade que representa um usuário, a classe UsuarioService é usada para executar operações relacionadas a usuários, e várias anotações do Spring Framework são usadas para configurar o controlador.
~~~java
import com.tlino.demoparkapi.entity.Usuario;
import com.tlino.demoparkapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
~~~

A anotação @RequiredArgsConstructor é do projeto Lombok e gera um construtor que aceita todos os campos marcados com final como argumentos, tornando a injeção de dependência mais conveniente. A anotação @RestController indica que esta classe é um controlador que lida com solicitações HTTP e a anotação @RequestMapping define o caminho base para todas as solicitações mapeadas por este controlador, que é "api/v1/usuarios".
~~~java
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
~~~

Aqui você define a classe UsuarioController.
~~~java
public class UsuarioController {
~~~

Este é um campo de classe que é injetado com uma instância de UsuarioService no construtor da classe. Isso permite que o controlador acesse os métodos do serviço de usuário para realizar operações.
~~~java
private final UsuarioService usuarioService;
~~~

Este método create é mapeado para solicitações HTTP POST na rota definida pelo @RequestMapping. Ele recebe um objeto Usuario no corpo da solicitação (@RequestBody) que representa o usuário a ser criado. O método chama o serviço usuarioService.salvar(usuario) para salvar o usuário no banco de dados, e retorna uma resposta HTTP com o status "201 Created" e o usuário criado no corpo da resposta.
~~~java
@PostMapping
public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
    Usuario user = usuarioService.salvar(usuario);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
}
~~~

Este método getById é mapeado para solicitações HTTP GET com um parâmetro de caminho ({id}). O método @PathVariable extrai o valor do parâmetro da URL e o usa como o ID do usuário a ser buscado. O método chama o serviço usuarioService.buscarPorId(id) para recuperar o usuário com o ID especificado e retorna uma resposta HTTP com o usuário no corpo da respost
~~~java
@GetMapping("/{id}")
public ResponseEntity<Usuario> getById(@PathVariable Long id){
    Usuario user = usuarioService.buscarPorId(id);
    return ResponseEntity.ok(user);
}
~~~

Este método updatePassword é mapeado para solicitações HTTP PATCH com um parâmetro de caminho ({id}). O método @PathVariable extrai o valor do parâmetro da URL e o usa como o ID do usuário a ser atualizado. Ele recebe um objeto Usuario no corpo da solicitação que contém a nova senha a ser atribuída ao usuário. O método chama o serviço usuarioService.editarSenha(id, usuario.getPassword()) para atualizar a senha do usuário com o ID especificado e retorna uma resposta HTTP com o usuário atualizado no corpo da resposta.
~~~java
@PatchMapping("/{id}")
public ResponseEntity<Usuario> updatePassword(@PathVariable Long id,@RequestBody Usuario usuario){
    Usuario user = usuarioService.editarSenha(id, usuario.getPassword());
    return ResponseEntity.ok(user);
}
~~~

Este método getAll é mapeado para solicitações HTTP GET sem parâmetros adicionais. Ele chama o serviço usuarioService.buscarTodos() para recuperar uma lista de todos os usuários e retorna uma resposta HTTP com a lista de usuários no corpo da resposta.
~~~java
@GetMapping
public ResponseEntity<List<Usuario>> getAll(){
    List<Usuario> users = usuarioService.buscarTodos();
    return ResponseEntity.ok(users);
}
~~~

Em resumo, a classe UsuarioController é responsável por expor endpoints de API REST para criar, recuperar e atualizar registros de usuários. Ela mapeia solicitações HTTP para as operações correspondentes do serviço de usuário (UsuarioService) e retorna respostas HTTP apropriadas, incluindo os dados dos usuários envolvidos. Isso permite que a aplicação interaja com os dados dos usuários por meio de uma API RESTful.

