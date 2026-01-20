/*
 * Copyright 2025 - 2026 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.addons.componentfactory.demo;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import org.vaadin.addons.componentfactory.demo.helpers.SpringDemoView;
import org.vaadin.addons.componentfactory.monthpicker.MonthPicker;
import org.vaadin.addons.componentfactory.monthpicker.MonthPicker.MonthPickerI18n;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

/**
 * View for {@link MonthPicker} demo.
 *
 * @author Vaadin Ltd
 */
@Route("")
public class MonthPickerDemoView extends SpringDemoView {

    public MonthPickerDemoView() {
        doInit();
    }

    @Override
    public void initView() {
        createBasicMonthPickerDemo();
        createAutoOpenDisabledMonthPickerDemo();
        createMinMaxYearMonthPickerDemo();
        createInvalidValueMonthPickerDemo();
        createReadOnlyAndDisabledDemo();
        createInternalizedMonthPickerDemo();
        createAlternativeFormatsTwoDigitYearDemo();
        createAlternativeFormatsShortMonthNamesDemo();
        createAlternativeFormatsFullMonthNamesDemo();

        addCard("Additional code used in the demo", new Span("These methods are used in the demo."));
    }

    private void createBasicMonthPickerDemo() {
        Div message = createMessageDiv("basic-month-picker-demo-message");

        // begin-source-example
        // source-example-heading: Basic month picker
        MonthPicker monthPicker = new MonthPicker();
        monthPicker.setLabel("Month Picker");
        monthPicker.setPlaceholder("Select a month");
        monthPicker.setClearButtonVisible(true);
        monthPicker.addValueChangeListener(ev -> updateMessage(message, monthPicker));
        // end-source-example

        monthPicker.setId("basic-month-picker");

        addCard("Basic month picker", monthPicker, message);
    }

    private void createAutoOpenDisabledMonthPickerDemo() {
        Div message = createMessageDiv("auto-open-disabled-month-picker-demo-message");

        // begin-source-example
        // source-example-heading: With auto open disabled
        MonthPicker monthPicker = new MonthPicker();
        monthPicker.setLabel("Month Picker");
        monthPicker.setPlaceholder("Select a month");
        monthPicker.setAutoOpen(false);
        monthPicker.setClearButtonVisible(true);
        monthPicker.addValueChangeListener(ev -> updateMessage(message, monthPicker));
        // end-source-example

        monthPicker.setId("auto-open-disabled-month-picker");

        addCard("With auto open disabled", monthPicker, message);
    }

    private void createMinMaxYearMonthPickerDemo() {
        Div message = createMessageDiv("min-max-year-month-picker-demo-message");

        // begin-source-example
        // source-example-heading: With min and max year
        MonthPicker monthPicker = new MonthPicker();
        monthPicker.setLabel("Month Picker");
        monthPicker.setPlaceholder("Select a month");
        monthPicker.setMaxYear(2026);
        monthPicker.setMinYear(2020);
        monthPicker.setHelperText("Min year: 2020 & Max year: 2026");
        monthPicker.setClearButtonVisible(true);
        monthPicker.addValueChangeListener(ev -> updateMessage(message, monthPicker));
        // end-source-example

        monthPicker.setId("min-max-year-month-picker");

        addCard("With min and max year", monthPicker, message);
    }

    private void createInvalidValueMonthPickerDemo() {
        Div message = createMessageDiv("invalid-value-month-picker-demo-message");

        // begin-source-example
        // source-example-heading: With invalid value
        MonthPicker monthPicker = new MonthPicker();
        monthPicker.setLabel("Month Picker");
        monthPicker.setPlaceholder("Select a month");
        monthPicker.setMaxYear(2026);
        monthPicker.setMinYear(2020);
        monthPicker.setValue(YearMonth.of(2019, 6));
        monthPicker.setErrorMessage("Month is outside of year range");
        monthPicker.setHelperText(
                "Min year: 2020 & Max year: 2026. If you enter a date outside that range, an error message will be shown.");
        monthPicker.setClearButtonVisible(true);
        monthPicker.addValueChangeListener(ev -> updateMessage(message, monthPicker));
        // end-source-example

        monthPicker.setId("invalid-value-month-picker");

        addCard("With invalid value", monthPicker, message);
    }

    private void createReadOnlyAndDisabledDemo() {
        // begin-source-example
        // source-example-heading: Read-Only & Disabled
        MonthPicker readonlyMonthPicker = new MonthPicker();
        readonlyMonthPicker.setLabel("Readonly");
        readonlyMonthPicker.setValue(YearMonth.of(2019, 6));
        readonlyMonthPicker.setReadOnly(true);

        MonthPicker disabledMonthPicker = new MonthPicker();
        disabledMonthPicker.setLabel("Disabled");
        disabledMonthPicker.setValue(YearMonth.of(2019, 6));
        disabledMonthPicker.setEnabled(false);

        // end-source-example

        readonlyMonthPicker.setId("readonly-month-picker");
        disabledMonthPicker.setId("disabled-month-picker");

        addCard("Read-Only & Disabled", readonlyMonthPicker, disabledMonthPicker);
    }

    private void createInternalizedMonthPickerDemo() {
        Div message = createMessageDiv("internalized-month-picker-demo-message");

        // begin-source-example
        // source-example-heading: With Custom Months and Formats
        MonthPicker monthPicker = new MonthPicker();
        monthPicker.setLabel("Month Picker");
        monthPicker.setPlaceholder("Select a month");
        monthPicker.setClearButtonVisible(true);

        MonthPickerI18n i18n = new MonthPickerI18n();
        i18n.setFormats("MM.YYYY", "MM/YYYY", "MMYYYY", "MM-YYYY", "YYYY.MM", "MM YYYY");
        i18n.setMonthNames(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
        i18n.setMonthLabels(Arrays.asList("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep",
                "Oct", "Nov", "Dic"));

        monthPicker.seti18n(i18n);
        monthPicker.setHelperText(
                "Spanish months labels/names and formats: 'MM.YYYY', 'MM/YYYY', 'MMYYYY', 'MM-YYYY', 'YYYY.MM', 'MM YYYY'");

        monthPicker.addValueChangeListener(ev -> updateMessage(message, monthPicker));
        // end-source-example

        monthPicker.setId("internalized-month-picker");

        addCard("With Custom Months and Formats", monthPicker, message);
    }

    private void createAlternativeFormatsTwoDigitYearDemo() {
        Div message = createMessageDiv("alternative-formats-2-digit-year-demo-message");

        // begin-source-example
        // source-example-heading: Alternative Formats - 2-digit years
        MonthPicker shortYearMonthPicker = new MonthPicker();
        shortYearMonthPicker.setLabel("Short Year Month Picker");
        shortYearMonthPicker.setPlaceholder("Select a month");
        shortYearMonthPicker.setClearButtonVisible(true);

        MonthPickerI18n i18n = new MonthPickerI18n();
        i18n.setFormats("MM.YY", "MM/YY", "MMYY", "MM-YY", "YY.MM", "MM YY");

        initMonthNames(i18n);

        shortYearMonthPicker.seti18n(i18n);
        shortYearMonthPicker.setHelperText(
                "2-digit year formats: 'MM.YY', 'MM/YY', 'MMYY', 'MM-YY', 'YY.MM', 'MM YY'");

        shortYearMonthPicker.addValueChangeListener(ev -> updateMessage(message, shortYearMonthPicker));
        // end-source-example

        shortYearMonthPicker.setId("alternative-formats-2-digit-year");

        addCard("Alternative Formats - 2-digit years", shortYearMonthPicker, message);
    }

    private void createAlternativeFormatsShortMonthNamesDemo() {
        Div message = createMessageDiv("alternative-formats-short-month-names-demo-message");

        // begin-source-example
        // source-example-heading: Alternative Formats - Short month names

        // This month picker formats and parses picked values as the short month name, for instance "Jan". This format
        // can be combined with all other formats, to allow also entering months by numbers, for instance "01", that then
        // will be translated to "Jan".
        MonthPicker shortMonthNamePicker = new MonthPicker();
        shortMonthNamePicker.setLabel("Short Month Name Picker");
        shortMonthNamePicker.setPlaceholder("Select a month");
        shortMonthNamePicker.setClearButtonVisible(true);

        MonthPickerI18n shortMonthNameI18n = new MonthPickerI18n();
        shortMonthNameI18n.setFormats("MMM YYYY",
                // additional short month names
                "MMM.YYYY", "MMM/YYYY", "MMMYYYY", "MMM-YYYY", "YYYY.MMM", "MMM YYYY",
                // matching numeric formats to allow usual input
                "MM.YYYY", "MM/YYYY", "MMYYYY", "MM-YYYY", "YYYY.MM", "MM YYYY"
        );

        // This i18n setting is needed, when short month name parsing and formatting is used.
        shortMonthNameI18n.setShortMonthNames(
                Arrays.asList(Arrays.stream(Month.values())
                        .map(m -> m.getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                        .map(s -> s.substring(0, 3))
                        .toArray(String[]::new))
        );


        initMonthNames(shortMonthNameI18n);

        shortMonthNamePicker.seti18n(shortMonthNameI18n);
        shortMonthNamePicker.setHelperText(
                "Short month name formats: 'MMM YYYY', 'MMM.YYYY', 'MMM/YYYY', 'MMMYYYY','MMM.YYYY', 'MMM/YYYY', 'MMM YYYY'");

        shortMonthNamePicker.addValueChangeListener(ev -> updateMessage(message, shortMonthNamePicker));

        // end-source-example

        shortMonthNamePicker.setId("alternative-formats-short-month-names");

        addCard("Alternative Formats - Short month names", shortMonthNamePicker, message);
    }

    private void createAlternativeFormatsFullMonthNamesDemo() {
        Div message = createMessageDiv("alternative-formats-full-month-names-demo-message");

        // begin-source-example
        // source-example-heading: Alternative Formats - Month names

        // This month picker formats and parses picked values as the full month name, for instance "January". This format
        // can be combined with all other formats, to allow also entering months by numbers, for instance "01", that then
        // will be translated to "January".
        MonthPicker fullMonthNamePicker = new MonthPicker();
        fullMonthNamePicker.setLabel("Full Month Name Picker");
        fullMonthNamePicker.setPlaceholder("Select a month");
        fullMonthNamePicker.setClearButtonVisible(true);

        MonthPickerI18n fullMonthNameI18n = new MonthPickerI18n();
        fullMonthNameI18n.setFormats("MMMM YYYY",
                // additional short month names
                "MMMM.YYYY", "MMMM/YYYY", "MMMMYYYY", "MMMM-YYYY", "YYYY.MMMM", "MMMM YYYY",
                // matching numeric formats to allow usual input
                "MM.YYYY", "MM/YYYY", "MMYYYY", "MM-YYYY", "YYYY.MM", "MM YYYY"
        );
        initMonthNames(fullMonthNameI18n);

        fullMonthNamePicker.seti18n(fullMonthNameI18n);
        fullMonthNamePicker.setHelperText(
                "Full month name formats: 'MMMM YYYY', 'MMMM.YYYY', 'MMMM/YYYY', 'MMMMYYYY','MMMM.YYYY', 'MMMM/YYYY', 'MMMM YYYY'");

        fullMonthNamePicker.addValueChangeListener(ev -> updateMessage(message, fullMonthNamePicker));

        // end-source-example

        fullMonthNamePicker.setId("alternative-formats-full-month-names");

        addCard("Alternative Formats - Full month names", fullMonthNamePicker, message);
    }

    /**
     * Initializes the month names and labels for the given i18n instance with english names.
     *
     * @param i18n instance to init
     */
    private void initMonthNames(MonthPickerI18n i18n) {
        i18n.setMonthNames(
                Arrays.asList(Arrays.stream(Month.values())
                        .map(m -> m.getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                        .toArray(String[]::new))
        );
        i18n.setMonthLabels(
                Arrays.asList(Arrays.stream(Month.values())
                        .map(m -> m.getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                        .toArray(String[]::new))
        );
    }

    // begin-source-example
    // source-example-heading: Additional code used in the demo

    /**
     * Additional code used in the demo
     */
    private void updateMessage(Div message, MonthPicker monthPicker) {
        message.setText("Selected year-month: " + monthPicker.getValue());
    }

    private Div createMessageDiv(String id) {
        Div message = new Div();
        message.setId(id);
        message.getStyle().set("whiteSpace", "pre");
        return message;
    }
    // end-source-example
}
