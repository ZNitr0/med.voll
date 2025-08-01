package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

// passando mapeamento api
@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar( @RequestBody @Valid DadosCadastroMedico dados){
            repository.save(new Medico(dados));
    }

    @GetMapping
    @Transactional
    public Page<DadosListagemMedico> listar( @PageableDefault(sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar( @RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.AtualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

    }

}
