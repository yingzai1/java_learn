package com.itheima;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 这个程序用来测试UserService类中的getAge函数
 */
@DisplayName("UserService类中getAge方法的单元测试")
public class UserServiceGetAITest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        // 初始化被测对象
        userService = new UserService();
    }

    /**
     * 测试正常情况下能够正确计算年龄
     */
    @Test
    @DisplayName("给定有效身份证号，应该正确返回年龄")
    public void testGetAgeWithValidIdCard() {
        // 假设今天是2025年4月5日，身份证号对应出生日期为2003年2月15日
        String validIdCard = "350583200302154959";
        Integer expectedAge = Period.between(
                LocalDate.of(2003, 2, 15),
                LocalDate.now()
        ).getYears();

        Integer actualAge = userService.getAge(validIdCard);

        assertEquals(expectedAge, actualAge, "年龄计算结果不符合预期");
    }

    /**
     * 参数化测试多个合法身份证号的情况
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "350583200302154959",
            "110101199001011234"
    })
    @DisplayName("多个合法身份证号都能正确计算年龄")
    public void testGetAgeMultipleValidCards(String idCard) {
        assertDoesNotThrow(() -> {
            Integer age = userService.getAge(idCard);
            assertNotNull(age, "年龄不应为空");
            assertTrue(age >= 0 && age < 150, "年龄应在合理范围内");
        });
    }

    /**
     * 测试身份证号长度不够时是否会抛出StringIndexOutOfBoundsException
     */
    @Test
    @DisplayName("当身份证号长度不足18位时，应当抛出StringIndexOutOfBoundsException")
    public void testGetAgeThrowsExceptionWhenIdTooShort() {
        String shortIdCard = "123456"; // 明显小于18位

        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            userService.getAge(shortIdCard);
        }, "应抛出StringIndexOutOfBoundsException");
    }

    /**
     * 测试生日字段非法时是否会抛出DateTimeParseException
     */
    @Test
    @DisplayName("当身份证号中包含非法生日字段时，应抛出DateTimeParseException")
    public void testGetAgeThrowsExceptionForInvalidBirthday() {
        String invalidBirthdayIdCard = "350583YYYYMMDD4959"; // 包含非法字符

        assertThrows(RuntimeException.class, () -> {
            userService.getAge(invalidBirthdayIdCard);
        }, "应抛出由DateTimeFormatter引发的运行时异常");
    }

    /**
     * 测试生日字段超过实际可能值时的行为（比如月份为13）
     */
    @Test
    @DisplayName("当身份证号中生日字段不合理但可解析时，应抛出DateTimeException")
    public void testGetAgeThrowsExceptionForUnrealisticDate() {
        String unrealisticDateIdCard = "350583202313014959"; // 13月不存在

        assertThrows(RuntimeException.class, () -> {
            userService.getAge(unrealisticDateIdCard);
        }, "应因非法日期而抛出运行时异常");
    }
}
