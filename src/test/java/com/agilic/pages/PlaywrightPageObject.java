package com.agilic.pages;

import com.agilic.driver.PlayWrightDriver;
import com.microsoft.playwright.Page;

public abstract class PlaywrightPageObject {
    protected Page page;
    private final PlayWrightDriver playWrightDriver = new PlayWrightDriver();

    public PlaywrightPageObject() {
        this.page = this.playWrightDriver.initDriver();
        if (this.page != null) {
            this.page.setDefaultTimeout((double)60000.0F);
            PlaywrightPageFactory.initElements(this.page, this);
        }

    }

    public Page getPage() {
        return this.page;
    }

    public void navigate(String url) {
        this.page.navigate(url);
        this.page.waitForLoadState();
    }

    public void navigate(String url, Page.NavigateOptions options) {
        this.page.navigate(url, options);
    }

    public void addScreenshotToReport() {
        this.addScreenshotToReport("screenshot", System.getProperty("user.dir"));
    }

    public void addScreenshotToReport(String screenshotName, String saveLocation) {
        this.takeScreenshot(saveLocation + File.separator + screenshotName + ".png", false);
    }

    public void addFullScreenshotToReport() {
        this.addFullScreenshotToReport("screenshot", System.getProperty("user.dir"));
    }

    public void addFullScreenshotToReport(String screenshotName, String saveLocation) {
        this.takeScreenshot(saveLocation + File.separator + screenshotName + ".png", true);
    }

    public void addElementScreenshotToReport(Locator locator) {
        this.addElementScreenshotToReport(locator, "screenshot", System.getProperty("user.dir"));
    }

    public void addElementScreenshotToReport(Locator locator, String screenshotName, String saveLocation) {
        locator.screenshot((new Locator.ScreenshotOptions()).setPath(Paths.get(saveLocation + File.separator + screenshotName + ".png")));

        try {
            Serenity.recordReportData().withTitle("Element Screenshot").downloadable().fromFile(Path.of(saveLocation + File.separator + screenshotName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void takeScreenshot(String path, boolean fullPage) {
        this.page.screenshot((new Page.ScreenshotOptions()).setPath(Paths.get(path)).setFullPage(fullPage));

        try {
            Serenity.recordReportData().withTitle("Screenshot").downloadable().fromFile(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitForLoadState() {
        this.page.waitForLoadState();
    }

    public void waitForElementToBeVisible(Locator locator) {
        this.waitForElement(locator, WaitForSelectorState.VISIBLE);
    }

    public void waitForElementToBeHidden(Locator locator) {
        this.waitForElement(locator, WaitForSelectorState.HIDDEN);
    }

    public void waitForElementToBeAttached(Locator locator) {
        this.waitForElement(locator, WaitForSelectorState.ATTACHED);
    }

    public void waitForElementToBeDetached(Locator locator) {
        this.waitForElement(locator, WaitForSelectorState.DETACHED);
    }

    private void waitForElement(Locator locator, WaitForSelectorState state) {
        this.page.waitForSelector(this.getSelector(locator), (new Page.WaitForSelectorOptions()).setState(state));
    }

    public void waitForLoadState(LoadState state) {
        this.page.waitForLoadState(state);
    }

    public void waitForTimeout(double timeout) {
        this.page.waitForTimeout(timeout);
    }

    public void waitForURL(String url) {
        this.page.waitForURL(url);
    }

    public void waitForURL(Pattern url) {
        this.page.waitForURL(url);
    }

    public void waitForFunction(String expression) {
        this.page.waitForFunction(expression);
    }

    public void waitForFunction(String expression, Object arg) {
        this.page.waitForFunction(expression, arg);
    }

    public void waitForFunction(String expression, Object arg, Page.WaitForFunctionOptions options) {
        this.page.waitForFunction(expression, arg, options);
    }

    public void waitForSelector(String selector) {
        this.page.waitForSelector(selector);
    }

    public void waitForSelector(String selector, Page.WaitForSelectorOptions options) {
        this.page.waitForSelector(selector, options);
    }

    public String getCurrentURL() {
        return this.page.url();
    }

    public String getTitle() {
        return this.page.title();
    }

    public void click(String selector) {
        this.page.click(selector);
    }

    public void type(Locator locator, String text) {
        this.page.type(this.getSelector(locator), text);
    }

    public void fill(Locator locator, String value) {
        this.page.fill(this.getSelector(locator), value);
    }

    public void check(Locator locator) {
        this.page.check(this.getSelector(locator));
    }

    public void uncheck(Locator locator) {
        this.page.uncheck(this.getSelector(locator));
    }

    public void selectOption(Locator locator, String value) {
        this.page.selectOption(this.getSelector(locator), value);
    }

    public void hover(Locator locator) {
        this.page.hover(this.getSelector(locator));
    }

    public void setInputFiles(Locator locator, Path file) {
        this.page.setInputFiles(this.getSelector(locator), file);
    }

    public void setViewportSize(int width, int height) {
        this.page.setViewportSize(width, height);
    }

    public void goBack() {
        this.page.goBack();
    }

    public void goForward() {
        this.page.goForward();
    }

    public void reload() {
        this.page.reload();
    }

    public void close() {
        this.page.close();
    }

    public void bringToFront() {
        this.page.bringToFront();
    }

    public void setContent(String html) {
        this.page.setContent(html);
    }

    public String content() {
        return this.page.content();
    }

    public void addScriptTag(Page.AddScriptTagOptions options) {
        this.page.addScriptTag(options);
    }

    public void addStyleTag(Page.AddStyleTagOptions options) {
        this.page.addStyleTag(options);
    }

    public void emulateMedia(Page.EmulateMediaOptions options) {
        this.page.emulateMedia(options);
    }

    public void setDefaultNavigationTimeout(double timeout) {
        this.page.setDefaultNavigationTimeout(timeout);
    }

    public void setDefaultTimeout(double timeout) {
        this.page.setDefaultTimeout(timeout);
    }

    public void waitForLoadState(LoadState state, Page.WaitForLoadStateOptions options) {
        this.page.waitForLoadState(state, options);
    }

    public Path downloadFile(String downloadButtonSelector, String downloadPath) {
        return this.downloadFile((Runnable)(() -> this.page.click(downloadButtonSelector)), downloadPath);
    }

    public Path downloadFile(Locator locator, String downloadPath) {
        Objects.requireNonNull(locator);
        return this.downloadFile(locator::click, downloadPath);
    }

    private Path downloadFile(Runnable clickAction, String downloadPath) {
        Download download = this.page.waitForDownload(clickAction);
        Path path = Paths.get(downloadPath);
        download.saveAs(path);
        return path;
    }

    public void mouseMove(double x, double y) {
        this.page.mouse().move(x, y);
    }

    public void mouseDown() {
        this.page.mouse().down();
    }

    public void mouseUp() {
        this.page.mouse().up();
    }

    public void mouseClick(double x, double y) {
        this.page.mouse().click(x, y);
    }

    public void mouseDblClick(double x, double y) {
        this.page.mouse().dblclick(x, y);
    }

    public void mouseWheel(double deltaX, double deltaY) {
        this.page.mouse().wheel(deltaX, deltaY);
    }

    public String getSelector(Locator locator) {
        return locator.toString().replace("Locator@", "");
    }
}
