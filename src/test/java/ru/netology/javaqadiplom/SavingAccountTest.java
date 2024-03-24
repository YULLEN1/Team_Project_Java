package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    //// Тест показывает на баг №1. Переменная initialBalance (начальный баланс)
    //// не учитывается в итоговом балансе при пополнении счёта
    //// в пределах допустимого значения максимального баланса
    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }


    //// Тест показывает на баг №2. Не отображается корректно итоговый баланс
    //// в пределах допустимого максимального значения maxBalance при пополнении счёта
    @Test
    public void shouldAddEqualMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    //// Тест показывает на баг №3. Исключение IllegalArgumentException
    //// не выкидывается при отрицательном значении minBalance
    @Test
    public void testWhenMinBalanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_500,
                    -1,
                    40_000,
                    4);
        });
    }

    //// Тест показывает на баг №4. Исключение IllegalArgumentException
    //// не выкидывается при значении minBalance выше, чем значение maxBalance
    @Test
    public void testWhenMinBalanceIsMoreThanMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_500,
                    45_000,
                    40_000,
                    4);
        });
    }

    //// Тест показывает на баг №5. Исключение IllegalArgumentException
    //// не выкидывается при отрицательном значении maxBalance
    @Test
    public void testWhenMaxBalanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_500,
                    500,
                    -1,
                    4);
        });
    }

    //// Тест показывает на баг №6. Исключение IllegalArgumentException
    //// не выкидывается при отрицательном значении initialBalance
    @Test
    public void testWhenInitialBalanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -1,
                    1_000,
                    40_000,
                    4);
        });
    }

    //// Тест показывает на баг №7. Исключение IllegalArgumentException
    //// не выкидывается когда значение initialBalance меньше, чем значение minBalance
    @Test
    public void testWhenInitialBalanceLessThanMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_500,
                    2_000,
                    40_000,
                    4);
        });
    }

    //// Тест показывает на баг №8. Исключение IllegalArgumentException
    //// не выкидывается когда значение initialBalance больше, чем значение maxBalance
    @Test
    public void testWhenInitialBalanceMoreThanMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    41_500,
                    2_000,
                    40_000,
                    4);
        });
    }

    //// Тест показывает на баг №9. При сумме покупки amount,
    //// превышающей значение остатка на балансе minBalance
    //// показывает отрицательный баланс
    @Test
    public void testWhenAmountMoreThanMinBalance() {

        SavingAccount account = new SavingAccount(
                3_500,
                500,
                50_000,
                16
        );

        account.pay(3_700);

        Assertions.assertEquals(3_500, account.getBalance());
    }

    //// Тест показывает на баг №10. При сумме покупки amount,
    //// превышающей значение maxBalance, показывает отрицательный баланс
    @Test
    public void testWhenAmountMoreThanMaxBalance() {

        SavingAccount account = new SavingAccount(
                3_500,
                500,
                50_000,
                16
        );

        account.pay(53_700);

        Assertions.assertEquals(3_500, account.getBalance());
    }

    //// Тест показывает на баг №11. Переменная rate в функции yearChange
    //// не имеет ограничение на максимально допустимое значение
    //// и превышает сумму максимального баланса.
    @Test
<<<<<<< HEAD
    public void testWhenYearChangeHasNoLimit() {
=======
        public void testWhenYearChangeHasNoLimit() {
>>>>>>> 44ce823554f9132e095ea5eb4ee5a7d3a992805b
        SavingAccount account = new SavingAccount(
                3_000_000,
                500_000,
                5_000_000,
                2200
        );

        Assertions.assertEquals(66_000_000, account.yearChange());
    }

    //// Тест показывает на баг №12. При отрицательном балансе счёта
    //// и при значении initialBalance меньшем, чем значение minBalance,
    //// производится расчёт процентов на остаток счёта.
    @Test
    public void testWhenCalcYearChangeOnNegativeBalance() {
        SavingAccount savingAccount = new SavingAccount(
                -1_000,
                500,
                50_000,
                16
        );
        Assertions.assertEquals(-160, savingAccount.yearChange());
    }
}
