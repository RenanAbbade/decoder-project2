package com.ead.authuser.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorRecordResponse(int errorCode, String errorMessage, Map<String, String> errorDetails) {
}
/*
Os `records` em Java, introduzidos na versão 14 (como uma prévia) e oficializados na versão 16, trazem várias vantagens significativas:

1. **Sintaxe Concisa**: Com `records`, você pode definir classes de dados de forma muito mais concisa. Isso reduz a quantidade de código boilerplate que precisa ser escrito. Por exemplo, em vez de escrever uma classe com vários campos, construtores, métodos `getter`, `equals`, `hashCode` e `toString`, você pode definir tudo isso em uma única linha.
    ```java
    public record Person(String name, int age) {}
    ```

2. **Imutabilidade**: Os `records` são implicitamente finais e todos os seus campos são finais, o que significa que são imutáveis. Isso ajuda a criar objetos de valor que são seguros para uso em ambientes concorrentes.

3. **Desenvolvimento Rápido e Legível**: A redução de boilerplate melhora a legibilidade do código e acelera o desenvolvimento, permitindo que os desenvolvedores se concentrem na lógica de negócios em vez de na implementação de métodos de infraestrutura.

4. **Suporte Integrado para Métodos Comuns**: `records` automaticamente fornecem implementações de `equals`, `hashCode` e `toString` que são baseadas nos campos definidos, garantindo uma implementação correta e consistente desses métodos.

5. **Descontrução Facilita a Manipulação de Dados**: `records` permitem a descontrução, o que facilita a extração de valores dos campos em um modo mais direto.
    ```java
    public void printPersonInfo(Person person) {
        var (name, age) = person;
        System.out.println("Name: " + name + ", Age: " + age);
    }
    ```

Aqui está um exemplo simples de como um `record` se compara a uma classe tradicional:

### Classe Tradicional
```java
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

### Usando Records
```java
public record Person(String name, int age) {}
```

Como você pode ver, `records` tornam o código muito mais limpo e fácil de manter. Se precisar de mais alguma coisa, estou aqui para ajudar!

 */