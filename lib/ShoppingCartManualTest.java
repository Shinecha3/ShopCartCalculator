import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: คำนวณแบบ มีส่วนลด BOGO
        ArrayList<CartItem> bogoCart = new ArrayList<>();
        bogoCart.add(new CartItem("BOGO", "Bread", 25.0, 2)); // 2ลด1 = 1*25 ==50
        bogoCart.add(new CartItem("BOGO", "Milk", 15.0, 1));      // 15
        double total4 = ShoppingCartCalculator.calculateTotalPrice(bogoCart);
        if (total4 == 40.0) {
            System.out.println("PASSED: BOGO cart total is correct (40.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 40.0 but got " + total4);
            failedCount++;
        }

        // Test 5: คำนวณแบบ มีส่วนลด BULK
        ArrayList<CartItem> bulkCart = new ArrayList<>();
        bulkCart.add(new CartItem("BULK", "Bread", 25.0, 10)); // 250ลด10% = 225
        bulkCart.add(new CartItem("BULK", "Milk", 15.0, 2));      // 30
        double total5 = ShoppingCartCalculator.calculateTotalPrice(bulkCart);
        if (total5 == 255.0) {
            System.out.println("PASSED: BULK cart total is correct (255.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart total expected 255.0 but got " + total5);
            failedCount++;
        }

            // Test 6: คำนวณแบบ มีส่วนลด BOGO & BULK
        ArrayList<CartItem> bobulkCart = new ArrayList<>();
        bobulkCart.add(new CartItem("BULK", "Bread", 25.0, 10)); // 250ลด10% = 225
        bobulkCart.add(new CartItem("BOGO", "Milk", 15.0, 10));      // 75
        double total6 = ShoppingCartCalculator.calculateTotalPrice(bobulkCart);
        if (total6 == 300.0) {
            System.out.println("PASSED: BOGO & BULK cart total is correct (300.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO & BULK cart total expected 300.0 but got " + total6);
            failedCount++;
        }

                    // Test 7: overArr ห้ามสั่งของเกิน 10000รายการ 
        ArrayList<CartItem> overArrCart = new ArrayList<>();
        for(int i=1;i<=5001;i++){ // 10002/รายการ
            overArrCart.add(new CartItem("BULK", "Bread"+i, 25.0, 10)); // 250ลด10% = 225
            overArrCart.add(new CartItem("BOGO", "Milk"+i, 15.0, 5));      // 75
        }

        double total7 = ShoppingCartCalculator.calculateTotalPrice(overArrCart);
        if (total7 == 0.0) {
            System.out.println("PASSED: overArr cart should return 0.0 (0.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: overArr cart total expected 0.0 but got " + total7);
            failedCount++;
        }

                            // Test 8: overQuantity ห้ามสั่งของเกิน 10ล้านชิ้นต่อรายการ
        ArrayList<CartItem> overQuantityCart = new ArrayList<>();
            overQuantityCart.add(new CartItem("BULK", "Bread", 25.0, 123456789)); 
            overQuantityCart.add(new CartItem("BOGO", "Milk", 15.0, 123456789));      

        double total8 = ShoppingCartCalculator.calculateTotalPrice(overQuantityCart);
        if (total8 == 0.0) {
            System.out.println("PASSED: overQuantity cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: overQuantity cart total expected 0.0 but got " + total8);
            failedCount++;
        }

                            // Test 9: minusPriceCart มีราคาติดลบ
        ArrayList<CartItem> minusPriceCart = new ArrayList<>();
            minusPriceCart.add(new CartItem("BULK", "Bread", -1.0, 8)); 
            minusPriceCart.add(new CartItem("BOGO", "Milk", -1.0, 10));      
      
        double total9 = ShoppingCartCalculator.calculateTotalPrice(minusPriceCart);
        if (total9 == 0.0) {
            System.out.println("PASSED: minusPrice cart total should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: minusPrice cart total expected 0.0 but got " + total9);
            failedCount++;
        }

                                    // Test 10: minusQuantityCart มีจำนวนติดลบ
        ArrayList<CartItem> minusQuantityCart = new ArrayList<>();
            minusQuantityCart.add(new CartItem("BULK", "Bread", 10, -1)); 
            minusQuantityCart.add(new CartItem("BOGO", "Milk", 2, -10));      
      
        double total10 = ShoppingCartCalculator.calculateTotalPrice(minusQuantityCart);
        if (total10 == 0.0) {
            System.out.println("PASSED: minusQuantity cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: minusQuantity cart total expected 0.0 but got " + total10);
            failedCount++;
        }

                                    // Test 11: zeroQuantityCart มีจำนวนเป็น 0
        ArrayList<CartItem> zeroQuantityCart = new ArrayList<>();
            zeroQuantityCart.add(new CartItem("BULK", "Bread", 10, 0)); 
            zeroQuantityCart.add(new CartItem("BOGO", "Milk", 2, 0));      
      
        double total11 = ShoppingCartCalculator.calculateTotalPrice(zeroQuantityCart);
        if (total11 == 0.0) {
            System.out.println("PASSED: zeroQuantity cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: zeroQuantity cart total expected 0.0 but got " + total11);
            failedCount++;
        }

                                            // Test 12: zeroPriceCart มีราคาเป็น 0
        ArrayList<CartItem> zeroPriceCart = new ArrayList<>();
            zeroPriceCart.add(new CartItem("BULK", "Bread", 0, 10)); 
            zeroPriceCart.add(new CartItem("BOGO", "Milk",  0, 5));      
      
        double total12 = ShoppingCartCalculator.calculateTotalPrice(zeroPriceCart);
        if (total12 == 0.0) {
            System.out.println("PASSED: zeroQuantity cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: zeroQuantity cart total expected 0.0 but got " + total12);
            failedCount++;
        }

                                                    // Test 12: overPriceCart มีราคาเกิน 100ล้าน
        ArrayList<CartItem> overPriceCart = new ArrayList<>();
            overPriceCart.add(new CartItem("BULK", "Bread", 1234567891, 10)); 
            overPriceCart.add(new CartItem("BOGO", "Milk",  1234567891, 10));      
      
        double total13 = ShoppingCartCalculator.calculateTotalPrice(overPriceCart);
        if (total13 == 0.0) {
            System.out.println("PASSED: overPrice cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: overPrice cart total expected 0.0 but got " + total13);
            failedCount++;
        }


        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}