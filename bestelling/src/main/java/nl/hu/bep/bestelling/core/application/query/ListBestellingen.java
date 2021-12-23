package nl.hu.bep.bestelling.core.application.query;

public class ListBestellingen {
    private final String orderBy;
    private final String direction;

    public ListBestellingen(String orderBy, String direction) {
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
