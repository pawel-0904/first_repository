import java.util.ArrayList;
import java.util.EnumSet;

public class CashMachine {
    public ArrayList<BankNote> bankNoteList = new ArrayList<>();

    CashMachine() {
        //Инициализация банковских ячеек
        EnumSet.allOf(Nominal.class)
                .forEach(nominal -> bankNoteList.add(new BankNote(nominal, 0)));
    }

    //добавить деньги в банкомат
    public void addCash(Nominal nominal, int count){
        bankNoteList.forEach(el -> {
            //if (el.getNominal() == nominal) {
            if (el.nominal == nominal) {
                int prevCount = el.count;
                //int prevCount = el.getCount();
                el.setCount(prevCount + count);
            }
        });
    }

    //выдать запрашиваюмую сумму
    public boolean giveCash(int sum) {
        if (sum > giveAvailableSum()) {
            System.out.println("Запрошенная сумма превышает сумму, которую может выдать банкомат.");
            giveInformation();
            return false;
        }
        else {
            //Определим какие есть номиналы/банкноты в банкомате и сформиуем массив доступных номиналов в банкомате
            ArrayList<BankNote> availableBankNoteList = new ArrayList<>();

            for (BankNote element : bankNoteList) {
               //if (element.getCount() > 0){
                if (element.count > 0){
                   availableBankNoteList.add(new BankNote(element.nominal, element.count));
               }
            }

            int size = availableBankNoteList.size() - 1;
            //Идем по доступным банкотам от большего к меньшему и отдаем/меняем кол-во доступных/оставшихся банкнот в банкомате
            for (int i = size; i >= 0; i--) {
                //int nominalVal = availableBankNoteList.get(i).getNominalVal();
                //int nominalCount = availableBankNoteList.get(i).getCount();
                int nominalVal = availableBankNoteList.get(i).nominal.getVal();
                int nominalCount = availableBankNoteList.get(i).count;
                if (sum >= nominalVal) {
                    if (nominalCount > Math.abs(sum / nominalVal)) {
                        availableBankNoteList.get(i).setCount(nominalCount - (Math.abs(sum / nominalVal)));
                        sum = sum - (Math.abs(sum / nominalVal)) * nominalVal;
                    } else {
                        availableBankNoteList.get(i).setCount(0);
                        sum = sum - nominalCount * nominalVal;
                    }
                }
            }
            //Если сумма равно нулю -> мы смогли отдать клиенту требуюмую сумму доступными банкнотами ->
            //обновим кол-во оставшихся банкнот в банкомате
            if (sum == 0) {
               /* for (BankNote element : availableBankNoteList) {
                    for (BankNote el : bankNoteList) {
                        if (el.nominal == element.nominal){
                            el.setCount(element.count);
                        }
                    }
                }*/
                availableBankNoteList.forEach(element -> {
                    bankNoteList.forEach(el -> {
                        //if (el.getNominal() == element.getNominal()) {
                        //  el.setCount(element.getCount());
                        if (el.nominal == element.nominal) {
                            el.setCount(element.count);
                        }
                    });
                });
                return true;
            }
            else {
                System.out.println("Невозможно выдать сумму данными купюрами/банкнотами");
                return false;
            }
        }
    }

    //Вывести общее кол-во денежных средств в банкомате
    public int giveAvailableSum() {
        int sum = 0;
        for (BankNote element : bankNoteList) {
            //if (element.getCount() > 0){
            //    sum += element.getCount() * element.getNominalVal();
            if (element.count > 0){
                sum += element.count * element.nominal.getVal();
            }
        }
        return sum;
    }

    //Вывести информацию о доступных банкнотах в банкомате и их кол-ве
    public void giveInformation() {
        System.out.println("Кол-во денег в банкомате: " + giveAvailableSum() + " руб.");
        System.out.println("В банкомате доступны купюры следующих номиналов:");
        bankNoteList.forEach(el -> {
            //if (el.getCount() > 0) {
             //   System.out.println("Номинал: " + el.getNominalVal() + " руб. в кол-ве: " + el.getCount() + "шт.");
            if (el.count > 0) {
                System.out.println("Номинал: " + el.nominal.getVal() + " руб. в кол-ве: " + el.count + "шт.");
            }
        });
    }
}
