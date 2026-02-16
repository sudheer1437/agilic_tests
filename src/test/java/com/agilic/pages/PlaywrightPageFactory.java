package com.agilic.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

import java.lang.reflect.Field;

public class PlaywrightPageFactory {
    public static void initElements(Page page, Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields) {
            if (field.isAnnotationPresent(PlaywrightFindBy.class)) {
                PlaywrightFindBy findBy = (PlaywrightFindBy)field.getAnnotation(PlaywrightFindBy.class);
                Locator locator = null;
                if (!findBy.css().isEmpty()) {
                    locator = page.locator(findBy.css());
                } else if (!findBy.xpath().isEmpty()) {
                    locator = page.locator(findBy.xpath());
                } else if (!findBy.text().isEmpty()) {
                    locator = page.locator("text=" + findBy.text());
                } else if (!findBy.id().isEmpty()) {
                    locator = page.locator("#" + findBy.id());
                } else if (!findBy.className().isEmpty()) {
                    locator = page.locator("." + findBy.className());
                } else if (!findBy.attribute().isEmpty()) {
                    locator = page.locator("[" + findBy.attribute() + "]");
                } else if (!findBy.combined().isEmpty()) {
                    locator = page.locator(findBy.combined());
                } else if (!findBy.role().isEmpty() && !findBy.name().isEmpty()) {
                    locator = page.getByRole(AriaRole.valueOf(findBy.role().toUpperCase(Locale.ROOT)), (new Page.GetByRoleOptions()).setName(findBy.name().isEmpty() ? null : findBy.name()));
                } else if (!findBy.altText().isEmpty()) {
                    locator = page.getByAltText(findBy.altText());
                } else if (!findBy.title().isEmpty()) {
                    locator = page.getByTitle(findBy.title());
                } else if (!findBy.testId().isEmpty()) {
                    locator = page.getByTestId(findBy.testId());
                } else if (!findBy.shadowByText().isEmpty()) {
                    if (findBy.shadowParentTag().isEmpty()) {
                        locator = page.getByText(findBy.shadowByText());
                    } else {
                        Page.LocatorOptions options = new Page.LocatorOptions();
                        options.setHasText(findBy.shadowByText());
                        locator = page.locator(findBy.shadowParentTag(), options);
                    }
                } else if (!findBy.placeholder().isEmpty()) {
                    locator = page.getByPlaceholder(findBy.placeholder());
                } else if (!findBy.byText().isEmpty()) {
                    locator = page.getByText(findBy.byText());
                } else if (!findBy.byText().isEmpty() && findBy.exactMatch()) {
                    Pattern.compile(findBy.byText(), 2);
                }

                if (locator != null) {
                    field.setAccessible(true);

                    try {
                        field.set(obj, locator);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
