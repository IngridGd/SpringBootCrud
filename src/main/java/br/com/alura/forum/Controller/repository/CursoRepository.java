package br.com.alura.forum.Controller.repository;

import br.com.alura.forum.Modelo.Curso;
import br.com.alura.forum.Modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso, Long>  {
    Curso findByNome(String nomeCurso);
}
