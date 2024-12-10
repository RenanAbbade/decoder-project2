package com.ead.authuser.repository;

import com.ead.authuser.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository  extends JpaRepository<UserModel, UUID> { //JpaRepository já possui @Repository não sendo necessário replicar a annotation

    //UserModel findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
/*

No Spring Data JPA, existem várias formas de buscar dados no banco de dados. Cada abordagem é adequada para cenários diferentes, dependendo da complexidade e flexibilidade necessária. Aqui estão as principais formas:

---

### 1. **Query Methods (Métodos de Convenção)**

Definem métodos no repositório seguindo a convenção de nomenclatura, como `findBy`, `readBy`, `getBy`, etc.

**Exemplo:**
```java
UserModel findByUsername(String username);
List<UserModel> findByStatusAndType(String status, String type);
```

- **Vantagens:** Simples, sem necessidade de escrever SQL/JPQL.
- **Limitações:** Menos flexível para consultas complexas.

---

### 2. **@Query (JPQL ou SQL Nativo)**

Permite definir consultas personalizadas usando JPQL (Java Persistence Query Language) ou SQL nativo.

**JPQL:**
```java
@Query("SELECT u FROM UserModel u WHERE u.username = :username")
UserModel findUserByUsername(@Param("username") String username);
```

**SQL Nativo:**
```java
@Query(value = "SELECT * FROM user_model WHERE username = :username", nativeQuery = true)
UserModel findUserByUsernameNative(@Param("username") String username);
```

- **Vantagens:** Flexível e permite consultas complexas.
- **Limitações:** Requer mais esforço para escrever e manter as consultas.

---

### 3. **Specification API (Critérios Dinâmicos)**

Usada para criar consultas dinâmicas baseadas em critérios, aproveitando a API Criteria do JPA.

**Exemplo:**
```java
public class UserSpecifications {
    public static Specification<UserModel> hasStatus(String status) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("status"), status);
    }
}
```
**Uso:**
```java
List<UserModel> users = userRepository.findAll(UserSpecifications.hasStatus("ACTIVE"));
```

- **Vantagens:** Ideal para filtros dinâmicos e consultas complexas.
- **Limitações:** Mais verboso.

---

### 4. **EntityManager (JPQL/SQL Programático)**

Permite executar consultas diretamente usando o `EntityManager`.

**JPQL:**
```java
TypedQuery<UserModel> query = entityManager.createQuery(
    "SELECT u FROM UserModel u WHERE u.username = :username", UserModel.class);
query.setParameter("username", "example");
UserModel user = query.getSingleResult();
```

**SQL Nativo:**
```java
Query query = entityManager.createNativeQuery(
    "SELECT * FROM user_model WHERE username = :username", UserModel.class);
query.setParameter("username", "example");
UserModel user = (UserModel) query.getSingleResult();
```

- **Vantagens:** Total controle sobre as consultas.
- **Limitações:** Requer mais esforço e código extra.

---

### 5. **Projections (Selecionando Campos Específicos)**

Permite buscar apenas campos específicos da entidade, retornando objetos DTO ou interfaces.

**Interface:**
```java
public interface UserProjection {
    String getUsername();
    String getEmail();
}
```
**Repositório:**
```java
@Query("SELECT u.username AS username, u.email AS email FROM UserModel u")
List<UserProjection> findAllUserSummaries();
```

- **Vantagens:** Reduz a carga de dados retornados.
- **Limitações:** Mais limitado para consultas dinâmicas.

---

### 6. **Named Queries**

Permite definir consultas em uma classe de entidade usando a anotação `@NamedQuery` ou `@NamedNativeQuery`.

**JPQL:**
```java
@NamedQuery(name = "UserModel.findByUsername", query = "SELECT u FROM UserModel u WHERE u.username = :username")
```

**Uso:**
```java
UserModel user = entityManager
    .createNamedQuery("UserModel.findByUsername", UserModel.class)
    .setParameter("username", "example")
    .getSingleResult();
```

- **Vantagens:** Consultas centralizadas nas entidades.
- **Limitações:** Menos flexível para consultas dinâmicas.

---

### 7. **QueryDSL**

Ferramenta adicional para criar consultas com um estilo fluente e fortemente tipado.

**Exemplo:**
```java
QUserModel user = QUserModel.userModel;
List<UserModel> users = new JPAQuery<>(entityManager)
    .select(user)
    .from(user)
    .where(user.status.eq("ACTIVE"))
    .fetch();
```

- **Vantagens:** Tipagem forte e consultas fluentes.
- **Limitações:** Requer dependência adicional.

---

### Comparação Rápida:

| **Método**              | **Facilidade de Uso** | **Flexibilidade**     | **Complexidade de Manutenção** |
|--------------------------|-----------------------|-----------------------|---------------------------------|
| Query Methods           | Alta                 | Baixa                | Baixa                          |
| @Query (JPQL/SQL)       | Média                | Alta                 | Média                          |
| Specification API       | Média                | Alta                 | Alta                           |
| EntityManager           | Baixa                | Muito Alta           | Alta                           |
| Projections             | Alta                 | Média                | Média                          |
| Named Queries           | Média                | Média                | Média                          |
| QueryDSL                | Média                | Alta                 | Alta                           |

Escolha a abordagem com base nas necessidades de flexibilidade, desempenho e facilidade de manutenção do seu projeto..*/