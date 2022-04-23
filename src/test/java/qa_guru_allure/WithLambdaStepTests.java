package qa_guru_allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class WithLambdaStepTests {

    private static final String REPOSITORY = "jenkinsci/docker";
    private static final int ISSUE_NUMBER = 1177;

    @Test
    public void testLambdaSteps() {

        step("Open home page", () -> {
            open("https://github.com");
        });
        step("Search for a repository" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys("jenkinsci/docker");
            $(".header-search-input").submit();
        });

        step("Open the repository" + REPOSITORY, () -> {
            $(By.linkText("jenkinsci/docker")).click();
        });
        step("Go to tab Issue", () -> {
            $(By.partialLinkText("Issues")).click();

        });
        step("Checking for tabs Issue" + " " + ISSUE_NUMBER, () -> {
            $(withText("#1177")).should(Condition.exist);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
