package br.com.alura.forum.Controller;

import br.com.alura.forum.Controller.Form.TopicoForm;
import br.com.alura.forum.Controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.Controller.dto.TopicoDto;
import br.com.alura.forum.Controller.repository.CursoRepository;

import br.com.alura.forum.Modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }


    }
    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){

       Topico topico = form.converter(cursoRepository);
       topicoRepository.save(topico);


        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public DetalhesDoTopicoDto detalhar (@PathVariable Long id) {
        Topico topico = topicoRepository.getOne(id);

        return new TopicoDto(topico);
    }
}