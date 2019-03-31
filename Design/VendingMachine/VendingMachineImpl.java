package Amazon_Prepare.VendingMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {
    private Inventory<Coin> cashInventory;
    private Inventory<Item> itemInventory;
    private long totalSales;
    private Item currentItem;
    private long currentBalance;

    public VendingMachineImpl() {
        cashInventory = new Inventory<>();
        itemInventory = new Inventory<>();
        initialize();
    }

    private void initialize() {
        for (Coin c : Coin.values())
            cashInventory.put(c, 5);
        for (Item i : Item.values())
            itemInventory.put(i, 5);
    }

    @Override
    public long selectItemAndGetPrice(Item item) {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return currentItem.getPrice();
        }
        throw new SoldOutException("Sold out. Buy another item");
    }

    @Override
    public void insertCoin(Coin coin) {
        currentBalance += coin.getDenomination();
        cashInventory.add(coin);
    }

    private List<Coin> getChange(long amount) throws NotSufficientChangeException {
        List<Coin> changes = Collections.emptyList();

        if (amount > 0) {
            changes = new ArrayList<Coin>();
            long balance = amount;
            while (balance > 0) {
                if (balance >= Coin.QUARTER.getDenomination()
                        && cashInventory.hasItem(Coin.QUARTER)) {
                    changes.add(Coin.QUARTER);
                    balance = balance - Coin.QUARTER.getDenomination();
                }
                else if (balance >= Coin.DIME.getDenomination()
                        && cashInventory.hasItem(Coin.DIME)) {
                    changes.add(Coin.DIME);
                    balance = balance - Coin.DIME.getDenomination();
                }
                else if (balance >= Coin.NICKEL.getDenomination()
                        && cashInventory.hasItem(Coin.NICKEL)) {
                    changes.add(Coin.NICKEL);
                    balance = balance - Coin.NICKEL.getDenomination();
                }
                else if (balance >= Coin.PENNY.getDenomination()
                        && cashInventory.hasItem(Coin.PENNY)) {
                    changes.add(Coin.PENNY);
                    balance = balance - Coin.PENNY.getDenomination();
                }
                else
                    throw new NotSufficientChangeException("Not sufficient change");
            }
        }
        return changes;
    }

    private void UpdateCashInventory(List<Coin> change) {
        for (Coin c : change) {
            cashInventory.deduct(c);
        }
    }

    @Override
    public List<Coin> refund() {
        List<Coin> refund = getChange(currentBalance);
        UpdateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;
    }

    private boolean isFullPaid() {
        return currentBalance >= currentItem.getPrice();
    }

    private boolean hasSufficientChangeForAmount(long amount) {
        try {
            getChange(amount);
            return true;
        }
        catch (NotSufficientChangeException nsce) {
            return false;
        }
    }

    private boolean hasSufficientChange() {
        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }

    private Item collectItem() throws NotSufficientChangeException, NotFullPaidException {
        if (isFullPaid()) {
            if (hasSufficientChange()) {
                itemInventory.deduct(currentItem);
                return currentItem;
            }
            throw new NotSufficientChangeException("Not Sufficient Change");
        }
        long remainingChange = currentItem.getPrice() - currentBalance;
        throw new NotFullPaidException("Price not full paid, remaining: ", remainingChange);
    }

    private List<Coin> collectChange() {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        UpdateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    @Override
    public Bucket<Item, List<Coin>> collectItemAndChange() {
        Item item = collectItem();
        totalSales += currentItem.getPrice();

        List<Coin> change = collectChange();
        return new Bucket<Item, List<Coin>>(item, change);
    }

    @Override
    public void reset() {
        cashInventory.clear();
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }

    public long getTotalSales(){
        return totalSales;
    }

    public void printStats(){
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Item Inventory : " + itemInventory);
        System.out.println("Current Cash Inventory : " + cashInventory);
    }
}
