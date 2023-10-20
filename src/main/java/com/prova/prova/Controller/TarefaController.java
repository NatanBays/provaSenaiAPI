package com.prova.prova.Controller;

import com.prova.prova.Service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prova.prova.Entity.Tarefa;
import com.prova.prova.Service.TarefaService;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {

    final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @PostMapping("add")
    public ResponseEntity<?> criarTarefa(@RequestBody Tarefa tarefa) {
        try {
            tarefa = tarefaService.criarTarefa(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("find")
    public ResponseEntity<?> buscarTarefa(){
        try{
            List<Tarefa> lista = tarefaService.buscarTarefa();

            if (lista.isEmpty()) {
                return new ResponseEntity<>("A Lista de tarefas está vazia!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(lista, HttpStatus.OK);
            }
        } catch (Exception ex){
            return new ResponseEntity<>("Erro na requisição", HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarTarefa(@PathVariable("id") Long id) {
        try {
            tarefaService.deletarTarefa(id);
            return new ResponseEntity<>("Tarefa excluido com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarTarefa(
            @PathVariable("id") Long id,
            @RequestBody Tarefa tarefaAtualizada) {
        try {
            Tarefa tarefa = tarefaService.atualizarTarefa(id, tarefaAtualizada);
            return new ResponseEntity<>(tarefa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


















}
