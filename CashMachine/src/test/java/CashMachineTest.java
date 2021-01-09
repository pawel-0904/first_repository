import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Реализация CashMachine должна")

public class CashMachineTest {
    CashMachine cashMachine;

    @BeforeEach
    public void beforeAll() {
        cashMachine = new CashMachine();
        cashMachine.addCash( Nominal.R200, 1);
        cashMachine.addCash( Nominal.R500, 2);
        cashMachine.addCash( Nominal.R1000, 3);
    }

    @DisplayName("Не выдавать деньги, если сумма не превышает допустимую, но нет нужного номинала")
    @Test
    public void testGiveOverAvailableCash() {
        assertFalse(cashMachine.giveCash(300));
    }

    @DisplayName("Не выдавать деньги, если сумма превышает допустимую")
    @Test
    public void testGiveNotAvailableNominal() {
        assertFalse(cashMachine.giveCash( 5000));
    }

    @DisplayName("Выдавать допустимую сумму; проверить, что в баномате осталась верная сумма, после выдачи")
    @Test
    public void giveAvailableSum() {
        int sum = 500;
        int sumBeforeGive = cashMachine.giveAvailableSum();
        //System.out.println("Кол-во денег в банкомате до снятия: " + cashMachine.giveAvailableSum() + " руб.");
        assertTrue(cashMachine.giveCash( sum));
        assertEquals( sumBeforeGive - 500, cashMachine.giveAvailableSum());
        //System.out.println("Кол-во денег в банкомате после снятия: " + cashMachine.giveAvailableSum() + " руб.");
    }

    @DisplayName("Добавить сумму; проверить, что допустимая сумма к выдаче изменилась корректно")
    @Test
    public void testAdd() {
        int sumBeforeAdd = cashMachine.giveAvailableSum();
        //System.out.println("Кол-во денег в банкомате до добавления: " + cashMachine.giveAvailableSum() + " руб.");
        cashMachine.addCash( Nominal.R2000, 3);
        assertEquals( sumBeforeAdd + 6000, cashMachine.giveAvailableSum());
        //System.out.println("Кол-во денег в банкомате после добавления: " + cashMachine.giveAvailableSum() + " руб.");
    }

    @DisplayName("Вывести информацию по доступной для снятия суммы, доступные банкноты и их кол-во")
    @Test
    public void testGiveInformation() {
        cashMachine.giveInformation();
    }
}