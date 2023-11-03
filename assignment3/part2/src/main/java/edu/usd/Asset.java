package edu.usd;

//TODO: create the Asset interface with the getValue and getName methods.
// Feel free to add more methods when sensible.

public interface Asset {
    double getValue(int numDays);
    String getName();
    double getStartingValue();
}