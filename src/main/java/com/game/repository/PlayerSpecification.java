package com.game.repository;


import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class PlayerSpecification {
    public static Specification<Player> hasName(String name) {
        return (root, criteriaQuery, criteriaBuilder)
                -> {
            if (name == null || name.isEmpty()) return null;
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Player> hasTitle(String title) {
        return (root, criteriaQuery, criteriaBuilder)
                -> {
            if (title == null || title.isEmpty()) return null;
            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Player> hasRace(Race race) {
        return (root, criteriaQuery, criteriaBuilder)
                -> {
            if (race == null) return null;
            return criteriaBuilder.equal(root.get("race"), race);
        };
    }

    public static Specification<Player> hasProfession(Profession profession) {
        return (root, criteriaQuery, criteriaBuilder)
                -> {
            if (profession == null) return null;
            return criteriaBuilder.equal(root.get("profession"), profession);
        };
    }

    public static Specification<Player> hasBirthday(Long after, Long before) {
        return (root, criteriaQuery, criteriaBuilder)
                -> {
            if (after == null && before == null) return null;

            if (after != null && before == null)
                return criteriaBuilder.between(root.get("birthday"), new Date(after), new Date(1100, 1, 1));
            if (after == null && before != null)
                return criteriaBuilder.between(root.get("birthday"), new Date(100, 1, 1), new Date(before));
            return criteriaBuilder.between(root.get("birthday"), new Date(after), new Date(before));
        };
    }

    public static Specification<Player> hasBanned(Boolean banned) {
        return (root, criteriaQuery, criteriaBuilder)
                -> {
            if (banned == null) return null;
            return criteriaBuilder.equal(root.get("banned"), banned);
        };
    }

    public static Specification<Player> hasExperience(Integer minExperience, Integer maxExperience) {
        return (root, criteriaQuery, criteriaBuilder)
                -> {
            if (minExperience == null && maxExperience == null) return null;
            if (minExperience != null && maxExperience == null)
                return criteriaBuilder.between(root.get("experience"), minExperience, Integer.MAX_VALUE);
            if (minExperience == null && maxExperience != null)
                return criteriaBuilder.between(root.get("experience"), 0, maxExperience);
            return criteriaBuilder.between(root.get("experience"), minExperience, maxExperience);
        };
    }

    public static Specification<Player> hasLevel(Integer minLevel, Integer maxLevel) {
        return (root, criteriaQuery, criteriaBuilder)
                -> {
            if (minLevel == null && maxLevel == null) return null;
            if (minLevel != null && maxLevel == null)
                return criteriaBuilder.between(root.get("level"), minLevel, Integer.MAX_VALUE);
            if (minLevel == null && maxLevel != null)
                return criteriaBuilder.between(root.get("level"), 0, maxLevel);
            return criteriaBuilder.between(root.get("level"), minLevel, maxLevel);
        };
    }

}
