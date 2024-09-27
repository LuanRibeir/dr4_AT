package com.luanribeiro.dr4_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanribeiro.dr4_1.model.Veiculo;
import com.luanribeiro.dr4_1.repository.VeiculoRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository vRepository;

    public List<Veiculo> getAll() {
        log.info("Service fetching all vehicles.");

        return vRepository.findAll();
    }

    public Veiculo getById(Long id) throws Exception {
        log.info("Service fetching vehicle with ID: {}", id);

        return vRepository.findById(id).orElseThrow(() -> new Exception("Vehicle does not exist."));
    }

    public Veiculo save(Veiculo veiculo) {
        log.info("Service saving new vehicle: {}", veiculo);

        return vRepository.save(veiculo);
    }

    public Veiculo deleteById(Long id) throws Exception {
        log.info("Service deleting vehicle with ID: {}", id);

        Veiculo deletedV = vRepository.findById(id).orElseThrow(() -> new Exception("Vehicle does not exist."));
        vRepository.deleteById(id);

        return deletedV;
    }

    public Veiculo updateById(Long id, Veiculo veiculo) throws Exception {
        log.info("Service updating vehicle with ID: {}", id);

        Veiculo toUpdate = vRepository.findById(id).orElseThrow(() -> new Exception("Vehicle does not exist."));
        toUpdate.update(veiculo.getMarca(), veiculo.getPlaca(), veiculo.getPreco());

        return toUpdate;
    }
}
