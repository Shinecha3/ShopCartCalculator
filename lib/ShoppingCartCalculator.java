import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     * @param  ArrayList<CarItem> เข้ามาเป็นรายการสินค้า
     * @return ผลลัพจากการคำนวนราคาสินค้าแบบมีส่วนลดกับไม่มีส่วนลด
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        // TODO: เขียนโค้ด Implementation ที่นี่
        double price = 0 ,total = 0 ,quantity = 0;
        if (items != null && !items.isEmpty()) {
            
            for(int i=0; i<items.size();i++){
                if (items.get(i).sku() == "BOGO") {
                    quantity = items.get(i).quantity();
                    if (quantity >1) {
                        quantity = quantity-(quantity/2);
                        price = items.get(i).price();
                        total = total+(price*quantity);
                    }else{
                        price = items.get(i).price();
                        total = total+(price*quantity);   
                    }
                } else if (items.get(i).sku() == "BULK") {
                    quantity = items.get(i).quantity();
                    if (quantity >6) {
                        price = items.get(i).price();
                        total = total+((price*quantity)-(price*quantity)/10);
                    }else{
                        price = items.get(i).price();
                        total = total+(price*quantity);   
                    }
                } else if (items.get(i).sku() == "NORMAL") {
                    quantity = items.get(i).quantity();
                    price = items.get(i).price();
                    total = total+(price*quantity);
                }
            }
            return total;
        }


        return 0.0;
    }
}