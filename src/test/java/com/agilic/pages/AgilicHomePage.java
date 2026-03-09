package com.agilic.pages;

import com.microsoft.playwright.Locator;

public class AgilicHomePage extends PlaywrightPageObject {

    public Locator settingsButton;
    public Locator sidebar;
    public Locator menuOpenButton;
    public Locator backButton;
    public Locator homeButton;
    public Locator privateWorkPackageButton;
    public Locator ownerDriverTab;
    public Locator assignedTab;
    public Locator taggedTab;
    public Locator followingTab;
    public Locator rideTab;
    public Locator primeSearchIcon;
    public Locator windowMaximizeIcon;
    public Locator askAgilicAIBetaIcon;
    public Locator suggestionIcon;
    public Locator shareIcon;

    public Locator myProfileIcon;
    public Locator userProfileMenuItem;
    public Locator recentActivityUserMenuItem;
    public Locator personalSettingsUserMenuItem;
    public Locator signOutUserMenuItem;

    public Locator pinIcon;

    public Locator myViewMenuItem;
    public Locator summarySubMenuItem;
    public Locator privateWorkPackageSubMenuItem;
    public Locator statusReportsSubMenuItem;
    public Locator profileSubMenuItem;
    public Locator personalSettingsSubMenuItem;

    public Locator workPackagesMenuItem;
    public Locator workPackagesImOnSubMenuItem;
    public Locator myWpReportsSubMenuItem;
    public Locator createWorkPackageSubMenuItem;

    public Locator kanbanBoardsMenuItem;
    public Locator myKanbanBoardsSubMenuItem;

    public Locator globalSearchMenuItem;
    public Locator searchSubMenuItem;

    public Locator orgAdminMenuItem;
    public Locator informationSubMenuItem;
    public Locator communicationSubMenuItem;
    public Locator peopleSubMenuItem;
    public Locator orgAdminWorkPackagesSubMenuItem;
    public Locator globalReportsSubMenuItem;
    public Locator globalSettingsSubMenuItem;

    public Locator aboutMenuItem;
    public Locator agilicNotificationsSubMenuItem;
    public Locator orgAdminUsersSubMenuItem;

    public Locator billingMenuItem;
    public Locator subscriptionManagementSubMenuItem;

    public AgilicHomePage() {
        super();

        this.settingsButton=page.locator("img[src='assets/demo/images/navigator/settings-icon.svg'']");
        this.sidebar= page.locator(".layout-sidebar");

        this.menuOpenButton = page.locator("span:has-text('menu_open')");
        this.backButton = page.locator("img[src='assets/demo/images/navigator/BackArrow2_Trans_light.svg']");
        this.homeButton = page.locator("img[src='assets/demo/images/navigator/home-icon.svg']");
        this.privateWorkPackageButton = page.locator("img[src='assets/demo/images/navigator/my-wp-summary-tabs.svg']");

        this.ownerDriverTab = page.getByText("Owner/Driver");
        this.assignedTab = page.getByText("Assigned");
        this.taggedTab = page.getByText("Tagged");
        this.followingTab = page.getByText("Following");
        this.rideTab = page.getByText("RIDE");

        this.primeSearchIcon = page.locator(".pi.pi-search");
        this.windowMaximizeIcon = page.locator(".pi.pi-window-maximize");
        this.askAgilicAIBetaIcon = page.locator("img[src='assets/demo/images/navigator/ai-assistant-light.png']");
        this.suggestionIcon = page.locator("img[src='assets/demo/images/navigator/feedback-icon.svg'][alt='feedback-icon']");
        this.shareIcon = page.locator(".pi.pi-share-alt");

        this.myProfileIcon = page.locator("div.p-avatar-circle");
        this.userProfileMenuItem = page.locator("a:has-text('Profile')");
        this.recentActivityUserMenuItem = page.locator("a:has-text('Recent Activity')");
        this.personalSettingsUserMenuItem = page.locator("a:has-text('RPersonal Settings')");
        this.signOutUserMenuItem = page.locator("a:has-text('Sign Out')");

        this.pinIcon = page.locator("button:has(i.pi-thumbtack)");

        this.myViewMenuItem = page.locator("a:has-text('My View')");
        this.summarySubMenuItem = page.locator("a:has-text('Summary')");
        this.privateWorkPackageSubMenuItem = page.locator("a:has-text('Private Work Package')");
        this.statusReportsSubMenuItem = page.locator("a:has-text('Status Reports')");
        this.profileSubMenuItem = page.locator("a:has-text('Profile')");
        this.personalSettingsSubMenuItem = page.locator("a:has-text('Personal Settings')");

        this.workPackagesMenuItem = page.locator("//span[normalize-space()='Work Packages']/parent::a");
        this.workPackagesImOnSubMenuItem = page.locator("a:has-text(\"Work Packages I'm On\")");
        this.myWpReportsSubMenuItem = page.locator("a:has-text('MY WP Reports')");
        this.createWorkPackageSubMenuItem = page.locator("a:has-text('Create Work Package')");

        this.kanbanBoardsMenuItem = page.locator("//span[normalize-space()='Kanban Boards']");
        this.myKanbanBoardsSubMenuItem = page.locator("a:has-text('My Kanban Boards')");

        this.globalSearchMenuItem = page.locator("a:has-text('Global Search')");
        this.searchSubMenuItem = page.locator("a[href*='global-keyword-search']");

        this.orgAdminMenuItem = page.locator("//span[normalize-space()='Org Admin']");
        this.informationSubMenuItem = page.locator("a[href*='org-information']");
        this.communicationSubMenuItem = page.locator("a[href*='organization-communication']");
        this.peopleSubMenuItem = page.locator("a[href*='people-list']");
        this.orgAdminWorkPackagesSubMenuItem = page.locator("a[href*='work-package-list']");
        this.globalReportsSubMenuItem = page.locator("a[href*='reports']");
        this.globalSettingsSubMenuItem = page.locator("a[href*='global-settings']");

        this.aboutMenuItem = page.locator("//a[.//i[contains(@class,'pi-users')]]");
        this.agilicNotificationsSubMenuItem = page.locator("//a[contains(@href,'agilic-notifications')]");
        this.orgAdminUsersSubMenuItem = page.locator("//a[contains(@href,'org-admin-users')]");//done till here

        this.billingMenuItem = page.locator("//a[.//i[contains(@class,'pi-money-bill')]]");
        this.subscriptionManagementSubMenuItem = page.locator("//a[contains(@href,'subscription-management')]");


    }


    private void verifyPageIdentity() {

        if(!sidebar.isVisible()){
            throw new IllegalStateException("Sidebar header not visible");
        }
    }
}
