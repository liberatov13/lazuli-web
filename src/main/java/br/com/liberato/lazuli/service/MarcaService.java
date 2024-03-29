package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.Marca;
import br.com.liberato.lazuli.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Marca findById(Long idMarca) {
        return marcaRepository.findById(idMarca).orElse(null);
    }

    /**
     * @return Todas as marcas com status ativo
     */
    public List<Marca> findMarcasAtivas() {
        return marcaRepository.findByStatus(true);
    }

    public List<Marca> findMarcas() {
        return marcaRepository.findAll();
    }

    public Marca salvar(Marca marca) {
        marca.setNome(marca.getNome().toUpperCase());
        return marcaRepository.save(marca);
    }

    public Marca editar(Marca marca) {
        return this.salvar(marca);
    }
}
