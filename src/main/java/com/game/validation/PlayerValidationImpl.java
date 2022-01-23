package com.game.validation;

import com.game.entity.Player;
import com.game.exception.PlayerIncorrectDataException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PlayerValidationImpl implements PlayerValidation {

    Player player;

    public PlayerValidationImpl(Player player) {
        this.player = player;
    }

    public PlayerValidationImpl() {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void allParamNotNull() {
        if (isNameNull()) {
            throw new PlayerIncorrectDataException("Не задано имя персонажа");
        } else if (isTitleNull()) {
            throw new PlayerIncorrectDataException("Не задан титил персонажа");
        } else if (isRaceNull()) {
            throw new PlayerIncorrectDataException("Не задана раса персонажа");
        } else if (isProfessionNull()) {
            throw new PlayerIncorrectDataException("Не задана профессия персонажа");
        } else if (isBirthdayNull()) {
            throw new PlayerIncorrectDataException("Не задана дата рождения персонажа");
        } else if (isExperienceNull()) {
            throw new PlayerIncorrectDataException("Не задан опыт персонажа");
        }
    }

    @Override
    public boolean isNameNull() {
        return player.getName() == null;
    }

    @Override
    public boolean isTitleNull() {
        return player.getTitle() == null;
    }

    @Override
    public boolean isRaceNull() {
        return player.getRace() == null;
    }

    @Override
    public boolean isProfessionNull() {
        return player.getProfession() == null;
    }

    @Override
    public boolean isBirthdayNull() {
        return player.getBirthday() == null;
    }

    @Override
    public boolean isExperienceNull() {
        return player.getExperience() == null;
    }

    @Override
    public boolean isBannedNull() {
        return player.isBanned() == null;
    }

    @Override
    public void allParamValid() {
        isNameValid();
        isTitleValid();
        isBirthdayValid();
        isExperienceValid();
    }

    @Override
    public boolean isNameValid() {
        if (player.getName().length() > 12) {
            throw new PlayerIncorrectDataException("Имя персонажа слишком длинное, max 12 символов");
        }
        return true;
    }

    @Override
    public boolean isTitleValid() {
        if (player.getTitle().length() > 30) {
            throw new PlayerIncorrectDataException("Название титула персонажа слишком длинное");
        }
        return true;
    }

    @Override
    public boolean isBirthdayValid() {
        if (player.getBirthday().getTime() > new Date().getTime()) {
            throw new PlayerIncorrectDataException("Дата рождения персонажа позже даты регистрации");
        }
        if (player.getBirthday().getTime() < 0) {
            throw new PlayerIncorrectDataException("Дата рождения задана некоректно");
        }

        return true;
    }

    @Override
    public boolean isExperienceValid() {
        if (player.getExperience() < 0 || player.getExperience() > 10000000) {
            throw new PlayerIncorrectDataException("Не корректно задан опыт персонажа");
        }
        return true;
    }

}
