package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exception.PlayerIncorrectDataException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@RequestMapping("/rest/players")
@RestController
public class PlayerController {

    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Player> showAllPlayers(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "title", required = false) String title,
                                       @RequestParam(value = "race", required = false) Race race,
                                       @RequestParam(value = "profession", required = false) Profession profession,
                                       @RequestParam(value = "after", required = false) Long after,
                                       @RequestParam(value = "before", required = false) Long before,
                                       @RequestParam(value = "banned", required = false) Boolean banned,
                                       @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                       @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                       @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                       @RequestParam(value = "maxLevel", required = false) Integer maxLevel,
                                       @RequestParam(value = "order", required = false, defaultValue = "ID") PlayerOrder order,
                                       @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {


        return service.getAllPlayers(name, title, race, profession, after, before,
                banned, minExperience, maxExperience, minLevel, maxLevel,
                order, pageNumber, pageSize);
    }

    @GetMapping("/count")
    public Integer showAllPlayers(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "title", required = false) String title,
                                  @RequestParam(value = "race", required = false) Race race,
                                  @RequestParam(value = "profession", required = false) Profession profession,
                                  @RequestParam(value = "after", required = false) Long after,
                                  @RequestParam(value = "before", required = false) Long before,
                                  @RequestParam(value = "banned", required = false) Boolean banned,
                                  @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                  @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                  @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                  @RequestParam(value = "maxLevel", required = false) Integer maxLevel) {


        return service.getAllPlayers(name, title, race, profession, after, before,
                banned, minExperience, maxExperience, minLevel, maxLevel,
                PlayerOrder.ID, 0, Integer.MAX_VALUE).size();
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable long id) {
        validID(id);
        return service.getPlayer(id);
    }

    @PostMapping("")
    public Player createPlayer(@RequestBody Player player) {
        if (player == null) {
            throw new PlayerIncorrectDataException("Не заданы значения для игрока");
        }

        return service.createPlayer(player);
    }

    @PostMapping("/{id}")
    public Player updatePlayer(@PathVariable("id") long id, @RequestBody Player player) {
        validID(id);
        player.setId(id);
        return service.updatePlayer(id, player);
    }


    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") long id) {
        validID(id);
        service.deletePlayer(id);
    }

    private void validID(long id) {
        if (id <= 0 || (id != (int) id)) {
            throw new PlayerIncorrectDataException("Введен некорректный id");
        }
    }

}
