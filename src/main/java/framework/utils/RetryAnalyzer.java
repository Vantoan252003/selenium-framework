package framework.utils;

import framework.config.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        int maxRetry = ConfigReader.getInstance().getRetryCount();
        if (count < maxRetry) {
            count++;
            System.out.println("[Retry] Đang chạy lại lần " + count + " cho test: " + result.getName());
            return true;
        }
        return false;
    }
}
