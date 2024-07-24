package site.brucewu.aha.constant;

import lombok.Getter;

@Getter
public enum SortRuleEnum {

    ASC("asc"),

    DESC("desc");

    private final String role;

    private SortRuleEnum(String role) {
        this.role = role;
    }

    public static SortRuleEnum getEnum(String sortRule) {

        for (SortRuleEnum sortRuleEnum : SortRuleEnum.values()) {
            if(sortRuleEnum.getRole().equals(sortRule)) {
                return sortRuleEnum;
            }
        }

        return null;
    }
}
