package com.ead.authuser.model;


import com.ead.authuser.enumerator.UserStatus;
import com.ead.authuser.enumerator.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Apenas valores não nulos durante o processo de serialization (Envio de body json)
@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable {

    /*
    Quando uma classe implementa Serializable, o Java permite que a JVM converta os atributos do objeto em uma sequência de bytes (serialização). Esse processo inclui:
Salvar os dados dos atributos do objeto, incluindo objetos compostos (desde que eles também implementem Serializable).
Guardar as informações do tipo da classe para que o objeto possa ser reconstruído (desserializado) corretamente.
    */

    @Serial  //Esta anotação foi introduzida no Java 14 e é usada para indicar explicitamente que um campo ou método está relacionado à serialização. Ela é opcional, mas ajuda a tornar o código mais claro, especialmente para indicar que serialVersionUID é de fato um identificador de serialização.
    private static final long serialVersionUID = 1L; // Este campo é utilizado pelo Java para garantir a compatibilidade de versões de uma classe serializável durante o processo de serialização e desserialização.
    //Quando um objeto serializado é desserializado, a JVM compara o serialVersionUID do objeto serializado com o serialVersionUID da classe que está tentando carregar. Se os valores forem diferentes, isso indica que a classe foi modificada desde a última serialização, e uma exceção InvalidClassException será lançada. Isso ajuda a evitar problemas ao tentar carregar objetos de versões diferentes da classe.


    @Id //chave primária
    @GeneratedValue(strategy = GenerationType.AUTO) //criação automatica sempre que inserido um novo usuário
    private UUID userId; //UUID: Util para arquiteturas distribuidas, no que se toca a replicação, evitando conflitos em arquiteturas distribuidas, sendo um identificador universal.

    @Column(nullable = false, unique = true, length = 50) //unique = true, define atributo unico, como um username precisa
    private String username;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @JsonIgnore //Sempre que for serializado, esse campo será ignorado (não será passado em integrações, por questões de segurança)
    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) //trabalho como se o enum fosse uma String
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 255)//255 é o valor default do campo
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;
}
