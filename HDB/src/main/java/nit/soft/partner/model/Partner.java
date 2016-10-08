package nit.soft.partner.model;

import java.util.Set;

public interface Partner {
    long getId();
    String getName();
    Set<Address> getAddresses();
    Set<Communication> getCommunications();
}