package br.com.senac.piapi.services;

import br.com.senac.piapi.dto.PessoaDto;
import br.com.senac.piapi.entities.Pessoa;
import br.com.senac.piapi.repositories.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor @Slf4j
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private ModelMapper mapper;

    public List<PessoaDto> findAll(){
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        log.info("listando Pessoas");
        return pessoaList.stream().map((element) -> mapper.map(element, PessoaDto.class)).toList();
    }

    public PessoaDto findById(Long id){
        Pessoa pessoa = pessoaRepository.findById(id)
                                            .orElseThrow(() -> new RuntimeException("id " + id + " não encontrado!"));
        return mapper.map(pessoa, PessoaDto.class);
    }

    public PessoaDto insert(PessoaDto dto){
        Pessoa pessoa = pessoaRepository.save(mapper.map(dto, Pessoa.class));
        return mapper.map(pessoa, PessoaDto.class);
    }

    public PessoaDto update(PessoaDto dto, Long id){
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado"));
        pessoa.setDescricao(dto.getDescricao());
        pessoa.setNome(dto.getNome());
        pessoa.setIdade(dto.getIdade());
        pessoa.setProfissao(dto.getProfissao());
        pessoa = pessoaRepository.save(pessoa);
        return mapper.map(pessoa, PessoaDto.class);
    }

    public void delete(Long id){
        try {
            pessoaRepository.deleteById(id);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
