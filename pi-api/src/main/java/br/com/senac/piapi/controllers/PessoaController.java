package br.com.senac.piapi.controllers;

import br.com.senac.piapi.dto.PessoaDto;
import br.com.senac.piapi.services.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/pi/v1")
public class PessoaController {

    private PessoaService pessoaService;

    @GetMapping(value = "/pessoas")
    private ResponseEntity<List<PessoaDto>> buscaTodos(){
        return ResponseEntity.ok().body(pessoaService.findAll());
    }

    @GetMapping(value = "/pessoa/{id}")
    private ResponseEntity<PessoaDto> buscaPeloId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(pessoaService.findById(id));
    }

    @PostMapping(value = "/insert")
    private ResponseEntity<PessoaDto> inserePessoa(@RequestBody PessoaDto dto){
        URI uri = ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                        .path("/{id}")
                                            .buildAndExpand(dto.getId())
                                                .toUri();
        return  ResponseEntity.created(uri).body(pessoaService.insert(dto));
    }

    @PutMapping(value = "/update/{id}")
    private ResponseEntity<PessoaDto> atualizapessoa(@RequestBody PessoaDto dto, @PathVariable Long id){
        return ResponseEntity.ok().body(pessoaService.update(dto, id));

    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Void> deletePessoa(@PathVariable Long id){
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
