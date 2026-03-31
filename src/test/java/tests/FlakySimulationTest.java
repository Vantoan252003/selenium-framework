package tests;

import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlakySimulationTest extends BaseTest {
    private static int callCount = 0;

    @Test(description = "Test mô phỏng flaky — fail 2 lần đầu, pass lần thứ 3")
    public void testFlakyScenario() {
        callCount++;
        System.out.println("[FlakyTest] Đang chạy lần thứ: " + callCount);

        // Mô phỏng: 2 lần đầu fail, lần thứ 3 mới pass
        if (callCount <= 2) {
            Assert.fail("Mô phỏng lỗi mạng tạm thời — lần " + callCount);
        }
        Assert.assertTrue(true, "Test pass ở lần thứ " + callCount);
    }
}
