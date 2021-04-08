package model;

import java.util.Comparator;

public class SortClienti implements Comparator<Client> {
    @Override
    public int compare(Client o1, Client o2) {
        return o1.getArrivalTime()-o2.getArrivalTime();
    }
}
