import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * ราคารวมพื้นฐาน: คำนวณจาก price * quantity ของสินค้านั้น
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     * @param  ArrayList<CarItem> เข้ามาเป็นรายการสินค้าโดยห้าม เป็น null empty และ {จำนวนรายการเกิน 1หมื่นรายการ และรายการละไม่เกิน 10ล้านชิ้น ราคาสินค้าต่อชิ้นห้ามเกิน 100ล้าน}
     * @return ผลลัพจากการคำนวนราคาสินค้าแบบมีส่วนลดกับไม่มีส่วนลด
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        // TODO: เขียนโค้ด Implementation ที่นี่
        double price = 0 ,total = 0 ,quantity = 0;
        if (items != null && !items.isEmpty() && items.size() < 10001) {
            for(int i=0; i<items.size();i++){
                if (items.get(i).quantity() > 10000001 || items.get(i).price() > 100000001) {
                    return 0.0;                                                  
                }
                if (items.get(i).quantity() < 1 || items.get(i).price() < 1) {
                    return 0.0;                    
                }
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