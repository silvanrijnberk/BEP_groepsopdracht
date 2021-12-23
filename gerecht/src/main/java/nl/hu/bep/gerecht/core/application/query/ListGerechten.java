package nl.hu.bep.gerecht.core.application.query;

public class ListGerechten {
    private final String orderBy;
    private final String direction;

    public ListGerechten(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
