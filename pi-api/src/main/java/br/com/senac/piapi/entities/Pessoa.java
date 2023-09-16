package br.com.senac.piapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.apache.naming.factory.SendMailFactory;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "T_PESSOA", indexes = {
        @Index(name = "idx_pessoa_id_nome", columnList = "id, nome")
})
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nome;

    @Column(name = "profissao")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String profissao;

    @Column(name = "idade", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer idade;

    @Lob
    @Column(name = "descricao", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String descricao;


}