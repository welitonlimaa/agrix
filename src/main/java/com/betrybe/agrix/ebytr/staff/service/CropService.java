package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.ObjectNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.repository.FarmRepository;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço responsável por operações relacionadas a plantações.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmRepository farmRepository;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Construtor da classe de serviço para operações relacionadas a plantações.
   *
   * @param cropRepository       O repositório de plantações.
   * @param farmRepository       O repositório de fazendas.
   * @param fertilizerRepository O repositório de fertilizantes.
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmRepository farmRepository,
      FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.farmRepository = farmRepository;
    this.fertilizerRepository = fertilizerRepository;
  }


  /**
   * Cria uma nova plantação associada a uma fazenda existente.
   *
   * @param farmId  O ID da fazenda à qual a plantação será associada.
   * @param cropDto Os dados da plantação a serem criados.
   * @return Um CropDto representando a plantação recém-criada.
   * @throws ObjectNotFoundException Se a fazenda não for encontrada no repositório.
   */
  public CropDto createCrop(Integer farmId, CropDto cropDto) {
    Farm farm = farmRepository.findById(farmId)
        .orElseThrow(() -> new ObjectNotFoundException("Fazenda não encontrada!"));

    Crop crop = new Crop();
    crop.setName(cropDto.getName());
    crop.setPlantedArea(cropDto.getPlantedArea());
    crop.setPlantedDate(cropDto.getPlantedDate());
    crop.setHarvestDate(cropDto.getHarvestDate());
    crop.setFarm(farm);

    Crop savedCrop = cropRepository.save(crop);

    return new CropDto(savedCrop);
  }

  /**
   * Obtém uma lista de plantações associadas a uma fazenda.
   *
   * @param farmId O ID da fazenda para a qual se deseja obter as plantações.
   * @return Uma lista de objetos que representam as plantações associadas à fazenda.
   * @throws ObjectNotFoundException Se nenhuma fazenda for encontrada com o ID fornecido.
   */
  public List<CropDto> getFarmsCrops(Integer farmId) {
    Farm farm = farmRepository.findById(farmId)
        .orElseThrow(() -> new ObjectNotFoundException("Fazenda não encontrada!"));

    List<Crop> crops = cropRepository.getCrops(farmId);
    return crops.stream()
        .map(CropDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Obtém uma lista de todas as plantações cadastradas.
   *
   * @return Uma lista plantações contendo o id, nome, área plantada e id da fazenda
   */
  public List<CropDto> getAllCrops() {
    List<Crop> crops = cropRepository.findAll();
    return crops.stream()
        .map(CropDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Obtém os detalhes de uma plantação pelo seu ID.
   *
   * @param id O ID da plantação a ser buscada.
   * @return Os detalhes da plantação com o status HTTP 200, ou lança uma exceção
   *         ObjectNotFoundException se a plantação não for encontrada.
   */
  public CropDto getCropById(Integer id) {
    Crop crop = cropRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Plantação não encontrada!"));

    return new CropDto(crop);
  }

  /**
   * Busca plantações a partir da data de colheita.
   *
   * @param start Data de início da busca.
   * @param end   Data de fim da busca.
   * @return Uma lista de plantações.
   */
  public List<CropDto> searchCropsByHarvestDate(LocalDate start, LocalDate end) {
    List<Crop> crops = cropRepository.findByHarvestDateBetween(start, end);
    return crops.stream()
        .map(CropDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Associa um fertilizante a uma plantação.
   *
   * @param cropId       O ID da plantação.
   * @param fertilizerId O ID do fertilizante.
   * @throws ObjectNotFoundException Se a plantação ou o fertilizante não forem encontrados.
   */
  @Transactional
  public void associateCropWithFertilizer(Integer cropId, Integer fertilizerId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(() -> new ObjectNotFoundException("Plantação não encontrada!"));

    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(() -> new ObjectNotFoundException("Fertilizante não encontrado!"));

    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  /**
   * Obtém a lista de fertilizantes associados a uma plantação pelo seu ID.
   *
   * @param cropId O ID da plantação.
   * @return Uma lista contendo os detalhes dos fertilizantes associados à plantação.
   * @throws ObjectNotFoundException Se a plantação não for encontrada no repositório.
   */
  public List<FertilizerDto> getFertilizersByCropId(Integer cropId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(() -> new ObjectNotFoundException("Plantação não encontrada!"));

    List<FertilizerDto> fertilizerDtos = crop.getFertilizers().stream()
        .map(FertilizerDto::new)
        .collect(Collectors.toList());

    return fertilizerDtos;
  }


}
