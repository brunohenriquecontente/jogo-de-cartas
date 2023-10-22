package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.AbstractBaseRepositoryImpl;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class PlayerServiceImpl extends AbstractBaseRepositoryImpl<PlayerEntity, Long> implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;



    public PlayerServiceImpl(PlayerRepository playerRepository) {
        super(playerRepository);
    }


    public PlayerDTO insert(PlayerDTO playerDTO) {
        PlayerEntity playerEntity = new PlayerEntity();
        BeanUtils.copyProperties(playerDTO, playerEntity);
        playerEntity = playerRepository.save(playerEntity);
        return new PlayerDTO(playerEntity.getId(),playerEntity.getName());
    }

    public PlayerDTO drawCards(PlayerDTO playerDTO){


        return  null;
    }

}