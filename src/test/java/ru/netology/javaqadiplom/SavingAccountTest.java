package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    //// Тест показывает на баг №1. Переменная initialBalance (начальный баланс)
    //// не учитывается в итоговом балансе при пополнении счёта
    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(5_000, account.getBalance());
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

        Assertions.assertEquals(10_000, account.getBalance());
    }

    //// Тест проходит. Сумма initialBalance + amount не должна быть больше maxBalance
    //// Возвращает сумму initialBalance
    @Test
    public void shouldNotAddMoreMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(12_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    //// Тест проходит. Сумма пополнения amount не может быть <= 0
    //// Возвращает initialBalance
    @Test
    public void shouldNotAddLessThanZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
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

    //// Тест проходит. Исключение IllegalArgumentException
    //// выкидывается при отрицательном значении rate
    @Test
    public void testWhenRateIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    41_500,
                    2_000,
                    40_000,
                    -4);
        });
    }

    //// Тест показывает на баг №9. При сумме покупки amount,
    //// превышающей значение остатка на балансе minBalance,
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

    //// Тест проходит. При сумме покупки balance - amount
    //// не ниже значения minBalance возвращает результат
    @Test
    public void testWhenAmountLessThanMinBalance() {

        SavingAccount account = new SavingAccount(
                3_500,
                500,
                5_000,
                16
        );

        account.pay(500);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    //// Тест проходит. Сумма покупки не может быть <= 0.
    //// Возвращает initialBalance
    @Test
    public void testWhenAmountLessThanZero() {

        SavingAccount account = new SavingAccount(
                3_500,
                500,
                5_000,
                16
        );

        account.pay(0);

        Assertions.assertEquals(3_500, account.getBalance());
    }

    //// Тест проходит. При значении initialBalance равном 0
    //// не производится расчёт процентов на остаток счёта.
    @Test
    public void testWhenCalcYearChangeOnInitialBalanceIsZero() {
        SavingAccount savingAccount = new SavingAccount(
                0,
                0,
                50_000,
                16
        );
        Assertions.assertEquals(0, savingAccount.yearChange());
    }

    //// Тест проходит. При значении initialBalance не <= 0, не < minBalance,
    //// производится расчёт процентов на остаток счёта.
    @Test
    public void testCalcYearChange() {
        SavingAccount savingAccount = new SavingAccount(
                15_000,
                1_000,
                50_000,
                16
        );
        Assertions.assertEquals(2_400, savingAccount.yearChange());
    }
}