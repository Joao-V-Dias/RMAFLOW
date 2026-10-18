package com.joao.RMAFlow.service;

import com.joao.RMAFlow.exception.NotFoundEntityException;
import com.joao.RMAFlow.model.Modelo;
import com.joao.RMAFlow.repository.ModeloRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    private final ModeloRepository repository;

    public ModeloService(ModeloRepository repository) {
        this.repository = repository;
    }

    public List<Modelo> listar(){
        return repository.findAll();
    }

    public Modelo buscarPorId(Long id) throws NotFoundEntityException {
        return repository.findById(id).orElseThrow(() -> new NotFoundEntityException("modelo: " + id));
    }

    @Transactional
    public Modelo salvar(Modelo modelo){
        return repository.save(modelo);
    }

    @Transactional
    public void excluir(Long id){
        repository.deleteById(id);
    }
}
