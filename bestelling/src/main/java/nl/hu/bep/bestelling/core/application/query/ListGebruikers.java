package nl.hu.bep.bestelling.core.application.query;

public class ListBestellings {
    private final String orderBy;
    private final String direction;

    public ListBestellings(String orderBy, String direction) {
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
