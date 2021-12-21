package nl.hu.bep.beoordeling.core.application.query;

public class FindBeoordelingByKeyword {
    private final String keyword;
    private final String orderBy;
    private final String direction;

    public FindBeoordelingByKeyword(String keyword, String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.keyword = keyword;
        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
