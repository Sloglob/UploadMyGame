package com.game.validation;

public interface PlayerValidation {

    void allParamNotNull();

    boolean isNameNull();

    boolean isTitleNull();

    boolean isRaceNull();

    boolean isProfessionNull();

    boolean isBirthdayNull();

    boolean isExperienceNull();

    boolean isBannedNull();

    void allParamValid();

    boolean isNameValid();

    boolean isTitleValid();

    boolean isBirthdayValid();

    boolean isExperienceValid();

}
