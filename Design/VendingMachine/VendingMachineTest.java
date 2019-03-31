package Amazon_Prepare.VendingMachine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class VendingMachineTest {
    private static VendingMachine vm;

    public static void setUp(){
        vm = VendingMachineFactory.createVendingMachine();
    }

    public static void tearDown(){
        vm = null;
    }

    public void testBuyItemWithExactPrice() {
        //select item, price in cents
        long price = vm.selectItemAndGetPrice(Item.COKE);
        //price should be Coke's price
        assertEquals(Item.COKE.getPrice(), price);
        //25 cents paid
        vm.insertCoin(Coin.QUARTER);

        Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
        Item item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();

        //should be Coke
        assertEquals(Item.COKE, item);
        //there should not be any change
        assertTrue(change.isEmpty());
    }

    public void testBuyItemWithMorePrice(){
        long price = vm.selectItemAndGetPrice(Item.SODA);
        assertEquals(Item.SODA.getPrice(), price);

        vm.insertCoin(Coin.QUARTER);
        vm.insertCoin(Coin.QUARTER);

        Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
        Item item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();

        //should be Coke
        assertEquals(Item.SODA, item);
        //there should not be any change
        assertTrue(!change.isEmpty());
        //comparing change
        assertEquals(50 - Item.SODA.getPrice(), getTotal(change));

    }


    public void testRefund(){
        long price = vm.selectItemAndGetPrice(Item.PEPSI);
        assertEquals(Item.PEPSI.getPrice(), price);
        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.NICKEL);
        vm.insertCoin(Coin.PENNY);
        vm.insertCoin(Coin.QUARTER);

        assertEquals(41, getTotal(vm.refund()));
    }

    public void testSoldOut(){
        for (int i = 0; i < 5; i++) {
            vm.selectItemAndGetPrice(Item.COKE);
            vm.insertCoin(Coin.QUARTER);
            vm.collectItemAndChange();
        }

    }

    public void testNotSufficientChangeException(){
        for (int i = 0; i < 5; i++) {
            vm.selectItemAndGetPrice(Item.SODA);
            vm.insertCoin(Coin.QUARTER);
            vm.insertCoin(Coin.QUARTER);
            vm.collectItemAndChange();

            vm.selectItemAndGetPrice(Item.PEPSI);
            vm.insertCoin(Coin.QUARTER);
            vm.insertCoin(Coin.QUARTER);
            vm.collectItemAndChange();
        }
    }


    public void testReset(){
        VendingMachine vmachine = VendingMachineFactory.createVendingMachine();
        vmachine.reset();

        vmachine.selectItemAndGetPrice(Item.COKE);

    }

    public void testVendingMachineImpl(){
        VendingMachineImpl vm = new VendingMachineImpl();
    }

    private long getTotal(List<Coin> change){
        long total = 0;
        for(Coin c : change){
            total = total + c.getDenomination();
        }
        return total;
    }

    public static void main(String[] args) {
        setUp();
        VendingMachineTest vmObj = new VendingMachineTest();
        vmObj.testBuyItemWithExactPrice();
        vmObj.testBuyItemWithMorePrice();
        vmObj.testRefund();
        vmObj.testSoldOut();
        vmObj.testNotSufficientChangeException();
        vmObj.testReset();
        vmObj.testVendingMachineImpl();
    }
}
