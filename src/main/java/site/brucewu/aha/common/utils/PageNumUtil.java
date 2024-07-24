package site.brucewu.aha.common.utils;

public class PageNumUtil {

    public static Integer getCurrentPageNumInput(Integer pageNum) {
        return (pageNum -= 1) >= 0 ? pageNum : 0;
    }

    public static Integer getCurrentPageNumOutput(Integer pageNum) {
        return pageNum += 1;
    }

}
