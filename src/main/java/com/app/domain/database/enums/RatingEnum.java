package com.app.domain.database.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RatingEnum {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private Integer rating;

    public static RatingEnum fromInteger(Integer rating) {
        switch (rating) {
            case 1:
                return RatingEnum.ONE;

            case 2:
                return RatingEnum.TWO;

            case 3:
                return RatingEnum.THREE;

            case 4:
                return RatingEnum.FOUR;

            case 5:
                return RatingEnum.FIVE;
        }
        
        return null;
    }
}
