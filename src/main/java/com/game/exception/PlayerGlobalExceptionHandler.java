package com.game.exception;

import com.game.entity.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlayerGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PlayerIncorrectData> handleException(PlayerIncorrectDataException exception) {
        PlayerIncorrectData data = new PlayerIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<PlayerIncorrectData> handleException(PlayerNoFoundException exception) {
        PlayerIncorrectData data = new PlayerIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
