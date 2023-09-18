package br.com.senac.piapi.dto;

import br.com.senac.piapi.entities.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

/**
 * DTO for {@link Pessoa}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDto implements Serializable {

   private Long id;
   private String nome;
   private String profissao;
   private Integer idade;
   private String descricao;
   private String telefone;
   private String email;

}