package br.com.brunohenrique.desafiocartas.service.impl;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.exceptions.BadRequestException;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;
import br.com.brunohenrique.desafiocartas.service.PlayerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;

  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public PlayerDTO insert(PlayerDTO playerDTO) {
    PlayerEntity playerEntity = new PlayerEntity();
    BeanUtils.copyProperties(playerDTO, playerEntity);
    playerEntity.setCards(null);
    playerEntity.setMatch(null);
    playerEntity = playerRepository.save(playerEntity);

    return new PlayerDTO(playerEntity.getId(), playerEntity.getName(), null, null);
  }

  public PlayerDTO getById(Long playerId) {

    PlayerEntity playerEntity =
        playerRepository
            .findById(playerId)
            .orElseThrow(() -> BadRequestException.notFoundException("Player not found."));
    return playerEntity.toDTO();
  }

  public void deleteById(Long playerId) {
    playerRepository.deleteById(playerId);
  }

  public PlayerDTO updateById(Long playerId, PlayerDTO playerDTO) {
    PlayerDTO playerDTOResponse = null;
    if (playerRepository.existsById(playerId)) {
      PlayerEntity playerEntity =
          playerRepository
              .findById(playerId)
              .orElseThrow(() -> BadRequestException.notFoundException("Player not found."));
      playerEntity.setName(playerDTO.name());
      playerEntity.setCards(null);
      playerEntity.setMatch(null);
      playerDTOResponse = new PlayerDTO(playerEntity.getId(), playerEntity.getName(), null, null);
      playerRepository.save(playerEntity);
    } else {
      throw BadRequestException.notFoundException("Player not found.");
    }
    return playerDTOResponse;
  }
}
