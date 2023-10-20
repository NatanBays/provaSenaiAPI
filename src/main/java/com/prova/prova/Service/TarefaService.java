package com.prova.prova.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.prova.prova.Entity.Tarefa;

@Service
public class TarefaService {
    private Long proximoId = 1L;
    private Map<Long, Tarefa> tarefasPorCodigo = new HashMap<>();
    public List<Tarefa> buscarTarefa(){
        return new ArrayList<>(tarefasPorCodigo.values());
    }



    //Post(Inserir)
    public Tarefa criarTarefa(Tarefa tarefa) throws Exception{
        String status = tarefa.getStatus();
        String detalhes = tarefa.getDetalhes();

        Long id = proximoId++;
        tarefa.setId(id);

        if (tarefa == null){
            throw new Exception("Tarefa não pode estar vazia");
        }

        tarefasPorCodigo.put(id, tarefa);

        return tarefa;
    }

    //Delete(Auto explicativo)
    public void deletarTarefa(Long id) throws Exception {
        if(tarefasPorCodigo.containsKey(id)){
            tarefasPorCodigo.remove(id);
        } else {
            throw new Exception("Tarefa não encontrada");
        }
    }

    //Put(Atualizar)
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) throws Exception{
        if (tarefaAtualizada == null ) {
            throw new Exception("Tarefa não pode estar vazia");
        }

        String status = tarefaAtualizada.getStatus();
        String detalhes = tarefaAtualizada.getDetalhes();

        if (status == null || status.isEmpty()){
            throw new Exception("Campo de status não pode estar vazio");
        }
        if (detalhes == null || detalhes.isEmpty()){
            throw new Exception("Campo de detalhes não pode estar vazio");
        }

        Tarefa tarefaExistente = tarefasPorCodigo.get(id);

        tarefaExistente.setDetalhes(detalhes);
        tarefaExistente.setStatus(status);

        return tarefaExistente;
    }














}
