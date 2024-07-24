package site.brucewu.aha.constant;

import lombok.Data;
import lombok.Getter;

@Getter
public enum SortTypeEnum {

    /**
     * 最近时间
     */
    CREATE_TIME("createTime"),

    /**
     * 点赞数
     */
    LIKE_NUM("likeNum"),

    /**
     * 随机
     */
    RANDOM("random"),

    /**
     * 推荐
     */
    RECOMMEND("recommend");

    private final String type;

    SortTypeEnum(String type) {

        this.type = type;
    }

    public static SortTypeEnum getEnum(String sortType) {

        for (SortTypeEnum sortTypeEnum : SortTypeEnum.values()) {
            if(sortTypeEnum.getType().equals(sortType)) {
                return sortTypeEnum;
            }
        }

        return null;
    }
}
