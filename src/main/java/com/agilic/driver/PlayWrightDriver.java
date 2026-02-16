package com.agilic.driver;

public class PlayWrightDriver {
    public static ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal();
    public static ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal();
    public static ThreadLocal<Page> threadLocalDriver = new ThreadLocal();
    private static ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal();
    public static Scenario scenario;
    static EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

    public Page initDriver() {
        if (threadLocalDriver.get() != null) {
            return (Page)threadLocalDriver.get();
        } else {
            BrowserType browserType = null;
            boolean headless = Boolean.parseBoolean(variables.getProperty("headless", "false"));
            Playwright playwright = Playwright.create();
            threadLocalPlaywright.set(playwright);
            String browserName = variables.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);
            List<String> browserArgs = this.getBrowserArguments(browserName);
            BrowserType.LaunchOptions launchOptions = (new BrowserType.LaunchOptions()).setHeadless(headless);
            if (!browserArgs.isEmpty()) {
                launchOptions.setArgs(browserArgs);
            }

            switch (browserName) {
                case "firefox":
                    browserType = playwright.firefox();
                    threadLocalBrowser.set(browserType.launch(launchOptions));
                    break;
                case "chrome":
                    browserType = playwright.chromium();
                    launchOptions.setChannel("chrome");
                    threadLocalBrowser.set(browserType.launch(launchOptions));
                    break;
                case "edge":
                    browserType = playwright.chromium();
                    launchOptions.setChannel("msedge");
                    threadLocalBrowser.set(browserType.launch(launchOptions));
                    break;
                case "webkit":
                    browserType = playwright.webkit();
                    threadLocalBrowser.set(browserType.launch(launchOptions));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }

            if (threadLocalBrowser.get() == null) {
                throw new IllegalArgumentException("Could not launch browser for type: " + browserName);
            } else {
                BrowserContext context = this.createBrowserContext();
                this.addInitScripts(context);
                if (Boolean.parseBoolean(variables.getProperty("playwright.tracing.enabled", "false"))) {
                    context.tracing().start((new Tracing.StartOptions()).setScreenshots(Boolean.parseBoolean(variables.getProperty("playwright.tracing.screenshots", "true"))).setSnapshots(Boolean.parseBoolean(variables.getProperty("playwright.tracing.snapshots", "true"))).setSources(Boolean.parseBoolean(variables.getProperty("playwright.tracing.sources", "false"))));
                }

                Page page = context.newPage();
                threadLocalDriver.set(page);
                threadLocalContext.set(context);
                return page;
            }
        }
    }

    private List<String> getBrowserArguments(String browserName) {
        String argsProperty = variables.getProperty(browserName + ".args", "");
        if (argsProperty.isEmpty()) {
            argsProperty = variables.getProperty("browser.args", "");
        }

        if (!argsProperty.isEmpty()) {
            return (List) Arrays.stream(argsProperty.split(",")).map(String::trim).filter((arg) -> !arg.isEmpty()).collect(Collectors.toList());
        } else {
            return "chrome".equals(browserName) ? Arrays.asList("--disable-dev-shm-usage") : Arrays.asList();
        }
    }

    private BrowserContext createBrowserContext() {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        int viewportWidth = Integer.parseInt(variables.getProperty("playwright.viewport.width", "1920"));
        int viewportHeight = Integer.parseInt(variables.getProperty("playwright.viewport.height", "1080"));
        contextOptions.setViewportSize(viewportWidth, viewportHeight);
        contextOptions.setIgnoreHTTPSErrors(Boolean.parseBoolean(variables.getProperty("playwright.ignore.https.errors", "true")));
        contextOptions.setJavaScriptEnabled(Boolean.parseBoolean(variables.getProperty("playwright.javascript.enabled", "true")));
        String userAgent = variables.getProperty("playwright.user.agent");
        if (userAgent != null && !userAgent.isEmpty()) {
            contextOptions.setUserAgent(userAgent);
        }

        String locale = variables.getProperty("playwright.locale");
        if (locale != null && !locale.isEmpty()) {
            contextOptions.setLocale(locale);
        }

        String geolocation = variables.getProperty("playwright.geolocation");
        if (geolocation != null && !geolocation.isEmpty()) {
            String[] coords = geolocation.split(",");
            if (coords.length == 2) {
                double latitude = Double.parseDouble(coords[0].trim());
                double longitude = Double.parseDouble(coords[1].trim());
                contextOptions.setGeolocation(latitude, longitude);
            }
        }

        String permissions = variables.getProperty("playwright.permissions");
        if (permissions != null && !permissions.isEmpty()) {
            List<String> permissionsList = (List)Arrays.stream(permissions.split(",")).map(String::trim).collect(Collectors.toList());
            contextOptions.setPermissions(permissionsList);
        }

        String timezone = variables.getProperty("playwright.timezone");
        if (timezone != null && !timezone.isEmpty()) {
            contextOptions.setTimezoneId(timezone);
        }

        return ((Browser)threadLocalBrowser.get()).newContext(contextOptions);
    }

    private void addInitScripts(BrowserContext context) {
        for(int i = 1; i <= 10; ++i) {
            String script = variables.getProperty("playwright.init.script." + i);
            if (script == null || script.isEmpty()) {
                break;
            }

            if (script.startsWith("\"") && script.endsWith("\"")) {
                script = script.substring(1, script.length() - 1);
            }

            context.addInitScript(script);
        }

    }

    private static void cleanupCurrentThread() {
        try {
            if (threadLocalDriver.get() != null) {
                ((Page)threadLocalDriver.get()).close();
            }
        } catch (Exception var4) {
        }

        threadLocalDriver.remove();

        try {
            if (threadLocalContext.get() != null) {
                ((BrowserContext)threadLocalContext.get()).close();
            }
        } catch (Exception var3) {
        }

        threadLocalContext.remove();

        try {
            if (threadLocalBrowser.get() != null) {
                ((Browser)threadLocalBrowser.get()).close();
            }
        } catch (Exception var2) {
        }

        threadLocalBrowser.remove();

        try {
            if (threadLocalPlaywright.get() != null) {
                ((Playwright)threadLocalPlaywright.get()).close();
            }
        } catch (Exception var1) {
        }

        threadLocalPlaywright.remove();
    }

    public static synchronized Page getPage() {
        return (Page)threadLocalDriver.get();
    }

    public static synchronized BrowserContext getContext() {
        return (BrowserContext)threadLocalContext.get();
    }

    public static synchronized Browser getBrowser() {
        return (Browser)threadLocalBrowser.get();
    }

    public static void closeDriver() {
        cleanupCurrentThread();
    }
}
