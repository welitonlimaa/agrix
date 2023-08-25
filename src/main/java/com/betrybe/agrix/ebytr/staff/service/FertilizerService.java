package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.ObjectNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço responsável por operações relacionadas a fertilizantes.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Cria um novo fertilizante a partir dos dados fornecidos.
   *
   * @param fertilizerDto Os dados do fertilizante a serem criados.
   * @return O fertilizante criado.
   */
  public FertilizerDto createFertilizer(FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(fertilizerDto.getName());
    fertilizer.setBrand(fertilizerDto.getBrand());
    fertilizer.setComposition(fertilizerDto.getComposition());

    Fertilizer savedFertilizer = fertilizerRepository.save(fertilizer);

    return new FertilizerDto(savedFertilizer);
  }

  /**
   * Obtém uma lista de todos os fertilizantes cadastrados.
   *
   * @return Uma lista de FertilizerDto com os detalhes dos fertilizantes.
   */
  public List<FertilizerDto> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerRepository.findAll();
    return fertilizers.stream()
        .map(FertilizerDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Obtém as informações de um fertilizante pelo seu ID.
   *
   * @param id O ID do fertilizante a ser buscado.
   * @return As informações do fertilizante com o status HTTP 200
   *         ou lança uma exceção ObjectNotFoundException se o fertilizante não for encontrado.
   */
  public FertilizerDto getFertilizerById(Integer id) {
    Fertilizer fertilizer = fertilizerRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Fertilizante não encontrado!"));

    return new FertilizerDto(fertilizer);
  }

}
