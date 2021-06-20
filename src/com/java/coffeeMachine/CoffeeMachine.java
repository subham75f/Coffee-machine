package com.java.coffeeMachine;

import java.util.*;
/*
The solution implemented is to find the maximum number of beverages that could be served.
I have implemented here a O(2^N) (N=total count of beverages) approach for worst Case
First filtration of beverages are done , on the basis that either they can't be prepared
or the Amount is higher than total quantity of that item


Then updating the beverage map
Checking through recursion either that beverage could be taken or not,
So checking sufficient quantity is left or not , if yes then visit the next with subtracted quantity
once recursion completed add the amounts of the items back,
For not taken case there need not be any change in the amounts


When all the beverages are visited then check the count of beverage that could be taken
and store the beverages names in a List
return appropriate messages for all the beverages:- prepared, not sufficient quantity left, not available item
or all outlet occupied ( last option is on the assumption that totalNumber is not equal to beverages count)
 */
public class CoffeeMachine {
    private int totalNumber;
    private Map<String, Integer> totalQuantities;
    private Map<String, Map<StringBuilder, Integer>> beverages;
    private Map<String, String> finalBeveragesStatus;
    private List<String> maxBeverages;
    private Map<String, Integer> remQuantities;
    private List<String> beveragesPrepared;
    private List<String> filteredBeverages;
    private List<String> remBeverages;
    private int maxBeveragesCount;

    public CoffeeMachine(int totalNumber,
                         Map<String, Integer> totalQuantities,
                         Map<String, Map<StringBuilder, Integer>> beverages) {
        this.totalNumber = totalNumber;
        this.totalQuantities = totalQuantities;
        this.beverages = beverages;
        finalBeveragesStatus = new HashMap<>();
        maxBeverages = new ArrayList<>();
        remQuantities = new HashMap<>();
        beveragesPrepared = new ArrayList<>();
        filteredBeverages = new ArrayList<>();
        remBeverages = new ArrayList<>();
        maxBeveragesCount = 0;
    }

    public Map<String, String> getMaximumBeveragesServed() {
        if(beverages.size()==0)
            return finalBeveragesStatus;
        filterBeverages();

        if(beverages.size()==0)
            return finalBeveragesStatus;

        maxBeveragesThatCanBeServed(0);
        updateStatusesToAllBeverages();
        return finalBeveragesStatus;
    }

    private void updateStatusesToAllBeverages() {
        for( String canBeServed : maxBeverages) {
            if(totalNumber>0) {
                finalBeveragesStatus.put(canBeServed, canBeServed + " is prepared");
                totalNumber--;
            } else {
                finalBeveragesStatus.put(canBeServed, canBeServed + " cannot be prepared because all outlets are occupied");
            }
        }
        StringBuilder itemNotSufficient;
        for( Map.Entry<String, Map<StringBuilder, Integer>> beverage : beverages.entrySet()) {
            String beverageKey = beverage.getKey();
            if(finalBeveragesStatus.containsKey(beverageKey))
                continue;

            if(totalNumber<=0) {
                finalBeveragesStatus.put(beverageKey, beverageKey + " cannot be prepared because all outlets are occupied");
                continue;
            }

            Map<StringBuilder, Integer> ingredientList = beverage.getValue();
            itemNotSufficient = new StringBuilder();
            for( Map.Entry<StringBuilder, Integer> ingredient : ingredientList.entrySet()) {
                String ingredientKey = ingredient.getKey().toString();
                Integer amount = remQuantities.get(ingredientKey);
                if(amount<ingredient.getValue()) {
                    if(itemNotSufficient.length()>0) {
                        itemNotSufficient.append(", ");
                    }
                    itemNotSufficient.append(ingredient.getKey());
                }
            }
            if(itemNotSufficient.length()>0)
                finalBeveragesStatus.put(beverageKey,
                        beverageKey + " cannot be prepared because item " +  itemNotSufficient + " not sufficient");
        }
    }

    private void maxBeveragesThatCanBeServed(int index) {
        if(index<remBeverages.size()) {
            beveragesPrepared.add(remBeverages.get(index));
            Map<StringBuilder, Integer> ingredientList = beverages.get(remBeverages.get(index));
            boolean itemNotSufficient = isItemNotSufficient(ingredientList);

            if (!itemNotSufficient) {
                subtractQuantityAmounts(ingredientList);
                maxBeveragesThatCanBeServed(index+1);
                addBackQuantityAmounts(ingredientList);
            }

            int lastElement = beveragesPrepared.size()-1;
            beveragesPrepared.remove(lastElement);//removing last element ,so O(1) otherwise O(n) if Object used
            maxBeveragesThatCanBeServed(index+1);
        } else {
            if(maxBeveragesCount<beveragesPrepared.size()) {
                maxBeveragesCount = beveragesPrepared.size();
                maxBeverages.clear();
                maxBeverages.addAll(beveragesPrepared);
                remQuantities.clear();
                remQuantities.putAll(totalQuantities);
            }
        }
    }

    private boolean isItemNotSufficient(Map<StringBuilder, Integer> ingredientList) {
        for( Map.Entry<StringBuilder, Integer> ingredient : ingredientList.entrySet()) {
            String ingredientKey = ingredient.getKey().toString();
            if (totalQuantities.containsKey(ingredientKey)) {
                Integer amount = totalQuantities.get(ingredientKey);
                //Item is always available, Quantity might not be sufficient
                if (amount < ingredient.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void subtractQuantityAmounts(Map<StringBuilder, Integer> ingredientList) {
        for( Map.Entry<StringBuilder, Integer> ingredient : ingredientList.entrySet()) {
            String ingredientKey = ingredient.getKey().toString();
            if (totalQuantities.containsKey(ingredientKey)) {
                Integer amount = totalQuantities.get(ingredientKey);
                totalQuantities.put(ingredientKey, amount - ingredient.getValue());
            }
        }
    }

    private void addBackQuantityAmounts(Map<StringBuilder, Integer> ingredientList) {
        for( Map.Entry<StringBuilder, Integer> ingredient : ingredientList.entrySet()) {
            String ingredientKey = ingredient.getKey().toString();
            if (totalQuantities.containsKey(ingredientKey)) {
                Integer amount = totalQuantities.get(ingredientKey);
                totalQuantities.put(ingredientKey, amount + ingredient.getValue());
            }
        }
    }

    private void filterBeverages() {
        StringBuilder itemNotSufficient, itemNotAvailable;
        for( Map.Entry<String, Map<StringBuilder, Integer>> beverage : beverages.entrySet()) {
            Map<StringBuilder, Integer> ingredientList = beverage.getValue();
            itemNotSufficient = new StringBuilder();
            itemNotAvailable = new StringBuilder();
            for( Map.Entry<StringBuilder, Integer> ingredient : ingredientList.entrySet()) {
                String ingredientKey = ingredient.getKey().toString();
                if(totalQuantities.containsKey(ingredientKey)) {
                    Integer amount = totalQuantities.get(ingredientKey);
                    if(amount<ingredient.getValue()) {
                        if(itemNotSufficient.length()>0)
                            itemNotSufficient.append(", ");
                        itemNotSufficient.append(ingredient.getKey());
                    }
                } else {
                    if(itemNotAvailable.length()>0)
                        itemNotAvailable.append(", ");
                    itemNotAvailable.append(ingredient.getKey());
                }
            }
            prepareFilteredBeveragesMsg(itemNotSufficient, itemNotAvailable, beverage);
        }

        for (String filteredBeverage : filteredBeverages) {
            beverages.remove(filteredBeverage);
        }
        for (Map.Entry<String, Map<StringBuilder, Integer>> beverage : beverages.entrySet()) {
            remBeverages.add(beverage.getKey());
        }
    }

    private void prepareFilteredBeveragesMsg(StringBuilder itemNotSufficient, StringBuilder itemNotAvailable,
                                             Map.Entry<String, Map<StringBuilder, Integer>> beverage) {
        if(itemNotAvailable.length()>0|| itemNotSufficient.length()>0) {
            String beverageKey = beverage.getKey();
            if(itemNotAvailable.length()>0)
                finalBeveragesStatus.put(beverageKey,
                        beverageKey + " cannot be prepared because " + itemNotAvailable + " not available");
            else
                finalBeveragesStatus.put(beverageKey,
                        beverageKey + " cannot be prepared because item " +  itemNotSufficient + " not sufficient");

            filteredBeverages.add(beverageKey);
        }
    }
}
