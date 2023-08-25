package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.ObjectNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.FarmRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pelas operações relacionadas às fazendas.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Cria uma nova fazenda com base nos dados fornecidos.
   *
   * @param farmDto Os dados da fazenda a serem utilizados na criação.
   * @return Um objeto contendo os dados da fazenda criada.
   */
  public FarmDto createFarm(FarmDto farmDto) {
    Farm farm = new Farm();
    farm.setName(farmDto.getName());
    farm.setSize(farmDto.getSize());

    Farm savedFarm = farmRepository.save(farm);

    return new FarmDto(savedFarm);
  }

  /**
   * Obtém uma lista de todas as fazendas.
   *
   * @return Uma lista de objetos representando todas as fazendas.
   */
  public List<FarmDto> getAllFarms() {
    List<Farm> farms = farmRepository.findAll();
    return farms.stream()
        .map(FarmDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Obtém os detalhes de uma fazenda pelo seu ID.
   *
   * @param id O ID da fazenda a ser buscada.
   * @return Um objeto representando os detalhes da fazenda encontrada.
   * @throws ObjectNotFoundException Caso a fazenda com o ID fornecido não seja encontrada.
   */
  public FarmDto getFarmById(Integer id) {
    Farm farm = farmRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Fazenda não encontrada!"));

    return new FarmDto(farm);
  }


}
