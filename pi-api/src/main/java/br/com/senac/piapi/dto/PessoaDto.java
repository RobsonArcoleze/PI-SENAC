package br.com.senac.piapi.dto;

import br.com.senac.piapi.entities.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Pessoa}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDto implements Serializable {

    Long id;

    @NotEmpty
    @NotBlank(message = "Nome deve ser informado")
    String nome;

    @NotBlank(message = "Profissão deve ser informado")
    String profissao;

    @NotNull(message = "Idade deve ser informada")
    Integer idade;

    private String descricao;
}